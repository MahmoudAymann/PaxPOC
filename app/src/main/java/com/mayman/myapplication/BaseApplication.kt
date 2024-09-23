package com.mayman.myapplication

import android.app.Application
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.mayman.myapplication.Vfdevice.DeviceHelper
import com.vfi.smartpos.deviceservice.aidl.IDeviceService

class BaseApplication : Application() {

    val deviceHelper: DeviceHelper = DeviceHelper.getInstance()

    override fun onCreate() {
        super.onCreate()
        if(initApp())
            Log.e("main","success")
        else
            Log.e("main","false")
    }

    fun initApp(): Boolean {
        Log.e("main","start")
        val intent = Intent()
        intent.setAction("com.vfi.smartpos.device_service")
        intent.setPackage("com.vfi.smartpos.deviceservice")
        val isSuccess: Boolean = bindService(intent, conn, Service.BIND_AUTO_CREATE)
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
        if (isSuccess) {
            Toast.makeText(applicationContext, "Device Ready", Toast.LENGTH_SHORT).show()
            return true
        } else
            Toast.makeText(applicationContext, "Device Not Ready", Toast.LENGTH_SHORT).show()
        return false
    } //Binding Services

    val conn: ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            if (deviceHelper.deviceService == null) {
                deviceHelper.deviceService = IDeviceService.Stub.asInterface(service)
                deviceHelper.initServices()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            deviceHelper.deviceService = null
        }
    }

}