package com.mayman.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.mayman.myapplication.databinding.ActivityMainBinding
import com.mayman.myapplication.vfprinter.PosServiceVf
import com.mayman.myapplication.vfprinter.PrinterFonts
import com.mayman.myapplication.vfprinter.PrinterManagervf
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException
import org.json.JSONObject
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    val format: Bundle = Bundle()
    private var printermanager: PrinterManagervf? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setupPaymentReceiver(intent)
        binding.fab.setOnClickListener { view ->
            payment(14.9)
//            print()
        }
    }


    fun callApi(baseUrl:String, token:String, transactionRequest: TransactionRequest){
        callPostTransactions(baseUrl, token,
            TransactionRequest(
                orderId = "honestatis",
                status = 8436,
                authorizationCode = "corrumpit",
                amount = 2.3,
                appFlag = 1146,
                bankResponse = "dicat",
                senderRequestNumber = "falli",
                cardNumber = maskCardNumber("123444"),
                terminalId = "ponderum"
            )
        ).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
                println("API call failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.let { responseBody ->
                        val responseString = responseBody.string()
                        try {
                            // Parse the response JSON
                            val apiResponse = parseTicketsFromJson(JSONObject(responseString))
                            // Access response data
                            println(apiResponse)
                        } catch (e: Exception) {
                            println("Failed to parse response: ${e.message}")
                        }
                    }
                } else {
                    println("API call failed with code: ${response.code}, $response")
                }
            }
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setupPaymentReceiver(intent)
    }

    private fun setupPaymentReceiver(intent: Intent?) {
        if (intent != null && intent.extras?.isEmpty == false) {
            if (intent.hasExtra("key")) {
                val message = intent.getStringExtra("key")
                Log.d("ReceivedData", "Message: $message")
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                this.intent = null
            } else {
                print("key is null")
            }
        } else {
            Toast.makeText(this, "null intent", Toast.LENGTH_LONG).show()
        }
    }

    private fun formatDoubleToPOSString(amount: Double?): String? {
        return try {
            // Handle null or zero values
            if (amount == null || amount == 0.0) {
                println("Error: Value is null or zero")
                return null
            }

            // Separate the integer and decimal parts
            val integerPart = amount.toInt()
            val decimalPart = ((amount - integerPart) * 100).toInt()

            // Ensure proper formatting: 12 digits with 2 decimal places at the end
            return String.format(
                Locale.ENGLISH,
                "%010d%02d", integerPart, decimalPart
            )
        } catch (e: Exception) {
            println("Error: $e")
            null
        }
    }

    private fun payment(amount: Double?) {
        val posAmount = formatDoubleToPOSString(amount)
        if (posAmount == null) {
            //show error
            println("Error: posAmount is null")
            return
        } else {
            val intent = Intent()
            intent.setClassName(
                "com.seegypt.efinance",
                "com.seegypt.efinance.android.presentation.view.activity.AppLauncherActivity"
            )

            intent.putExtra("amount", posAmount)
            startActivity(intent)
        }
    }



    private fun print() {
        format.putString("fontStyle", PrinterFonts.path + PrinterFonts.FONT_AGENCYB)
        //create your constructor
        printermanager = PrinterManagervf(this, format)
        try {
            PosServiceVf.getPrintManager().checkPrinterStatus()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        printermanager?.printLogo()
        printermanager?.startPrint()
//        printermanager.PrintENR_Reciept(0);
//        printermanager.printQRCode();
//        printermanager.Startprint();

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}