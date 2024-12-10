package com.mayman.myapplication

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.mayman.myapplication.databinding.ActivityMainBinding
import com.mayman.myapplication.vfprinter.PosServiceVf
import com.mayman.myapplication.vfprinter.PrinterFonts
import com.mayman.myapplication.vfprinter.PrinterManagervf


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    val format: Bundle = Bundle()
    private var printermanager: PrinterManagervf? = null

    private val paymentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val paymentSuccess = result.data?.getBooleanExtra("paymentSuccess", false) ?: false
            if (paymentSuccess) {
                // Payment was successful
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
            } else {
                // Payment failed
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            payment()
//            print()
        }


    }

    private fun payment() {

        val intent = Intent("com.example.PAYMENT_PROCESS")
        intent.putExtra("amount", "0011") // Pass the amount
        paymentLauncher.launch(intent)
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