package com.mayman.myapplication

import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject

fun callPostTransactions(baseUrl: String, token: String, body: TransactionRequest): Call {
    val apiUrl = "$baseUrl/reservations/postTransaction"
    // Create request
    val requestBody = body.toJson().toString()
        .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Logs request/response body
    }

// Add the interceptor to the OkHttp client
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val request = Request.Builder()
        .url(apiUrl)
        .post(requestBody)
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept-Language", "ar")
        .addHeader("Accept", "application/json")
        .addHeader("lang", "ar")
        .addHeader("x-auth-token", token)
        .build()

    // Execute request
    return client.newCall(request)
}


fun parseTicketsFromJson(jsonObject: JSONObject): ApiResponse? {
    try {
        // Extract fields from the JSON object
        val success = jsonObject.getBoolean("success")
        val code = jsonObject.getInt("code")
        val result = jsonObject.getString("result")

        // Convert the ticketList JSONArray to a list of Ticket objects
        val ticketList = mutableListOf<Ticket>()
        val jsonArray = jsonObject.getJSONArray("ticketList")

        for (i in 0 until jsonArray.length()) {
            val ticketJson = jsonArray.getJSONObject(i)
            val ticket = Ticket(
                barcode = ticketJson.getString("Barcode"),
                productName = ticketJson.getString("ProductName"),
                unitAmount = ticketJson.getDouble("UnitAmount"),
                visitDateTime = ticketJson.getString("VisitDateTime"),
                reservationDate = ticketJson.getString("ReservationDate"),
                eventName = ticketJson.getString("EventName")
            )
            ticketList.add(ticket)
        }

        // Return the ApiResponse object
        return ApiResponse(
            success = success,
            code = code,
            result = result,
            ticketList = ticketList
        )
    } catch (e: Exception) {
        println(e)
        return null
    }
}

fun maskCardNumber(creditCardNumber: String): String {
    // Extract the first 6 digits
    val firstSix = creditCardNumber.substring(0, 6)
    // Extract the last 4 digits
    val lastFour = creditCardNumber.substring(creditCardNumber.length - 4)
    // Calculate the number of asterisks needed
    val asteriskCount = creditCardNumber.length - 10 // total length - first 6 - last 4
    // Generate asterisk string
    val asterisks = "*".repeat(asteriskCount)
    // Combine the first 6, asterisks, and last 4
    return "$firstSix$asterisks$lastFour"
}

data class TransactionRequest(
    val orderId: String,
    val status: Int,
    val authorizationCode: String,
    val amount: Double,
    val appFlag: Int,
    val bankResponse: String,
    val senderRequestNumber: String,
    val cardNumber: String,
    val terminalId: String
)

fun TransactionRequest.toJson(): JSONObject {
    return JSONObject().apply {
        put("order_id", orderId)
        put("status", status)
        put("authorizationCode", authorizationCode)
        put("amount", amount)
        put("app_flag", appFlag)
        put("bank_response", bankResponse)
        put("senderRequestNumber", senderRequestNumber)
        put("cardNumber", cardNumber)
        put("terminal_id", terminalId)
    }
}

data class ApiResponse(
    val success: Boolean,
    val code: Int,
    val result: String,
    val ticketList: List<Ticket>
)

data class Ticket(
    val barcode: String,
    val productName: String,
    val unitAmount: Double,
    val visitDateTime: String,
    val reservationDate: String,
    val eventName: String
)