package com.mayman.myapplication.Vfdevice;

/**
 *
 * Created by laikey for get service device instance
 */

import android.os.RemoteException;

import com.vfi.smartpos.deviceservice.aidl.IBeeper;
import com.vfi.smartpos.deviceservice.aidl.IDeviceInfo;
import com.vfi.smartpos.deviceservice.aidl.IDeviceService;
import com.vfi.smartpos.deviceservice.aidl.IEMV;
import com.vfi.smartpos.deviceservice.aidl.IInsertCardReader;
import com.vfi.smartpos.deviceservice.aidl.ILed;
import com.vfi.smartpos.deviceservice.aidl.IMagCardReader;
import com.vfi.smartpos.deviceservice.aidl.IPBOC;
import com.vfi.smartpos.deviceservice.aidl.IPinpad;
import com.vfi.smartpos.deviceservice.aidl.IPrinter;
import com.vfi.smartpos.deviceservice.aidl.IRFCardReader;
import com.vfi.smartpos.deviceservice.aidl.IScanner;
import com.vfi.smartpos.deviceservice.aidl.ISerialPort;

public class DeviceHelper {
    public IDeviceService deviceService;
    private static IPinpad pinpad;
    private static IPBOC pboc;
    private static IBeeper beeper;
    private static ILed led;
    private static IPrinter printer;
    private static IDeviceInfo deviceInfo;
    private static ISerialPort serialPort;
    private static IScanner scanner;
    private static IMagCardReader magCardReader;
    private static IInsertCardReader insertCardReader;
    private static IRFCardReader rfCardReader;
    private static IEMV iemv;
    private static volatile DeviceHelper sDeviceHelper;
    private static int lastCameraId = 1;

    private DeviceHelper()
    {
        if(sDeviceHelper !=null)
        {
            throw new RuntimeException("Use getInstance() method ");
        }
    }

    public static DeviceHelper getInstance()
    {
        if(sDeviceHelper ==null)
        {
            synchronized (DeviceHelper.class)
            {
                if(sDeviceHelper ==null)
                    sDeviceHelper = new DeviceHelper();
            }
        }
        return sDeviceHelper;
    }

    public IDeviceService getDeviceService() {
        return deviceService;
    }

    public IPinpad getPinpad()
    {
        if (pinpad == null)
        {
            try
            {
                pinpad = getDeviceService().getPinpad(AppConstants.KEYS.workKeyId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return pinpad;
        }
        else
        {
            return pinpad;
        }
    }

    public IPBOC getPBOC()
    {
        if (pboc == null)
        {
            try
            {
                pboc = getDeviceService().getPBOC();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return pboc;
        }
        else
        {
            return pboc;
        }
    }

    public IPrinter getPrinter()
    {
        if (printer == null)
        {
            try
            {
                printer = getDeviceService().getPrinter();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return printer;
        }
        else
        {
            return printer;
        }
    }

    public IDeviceInfo getDeviceInfo()
    {
        if (deviceInfo == null)
        {
            try
            {
                deviceInfo = getDeviceService().getDeviceInfo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return deviceInfo;
        }
        else
        {
            return deviceInfo;
        }
    }

    public IEMV getIemv()
    {
        if (iemv == null)
        {
            try
            {
                iemv = getDeviceService().getEMV();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return iemv;
        }
        else
        {
            return iemv;
        }
    }
    public IRFCardReader getRFCardReader()
    {
        if (rfCardReader == null)
        {
            try
            {
                rfCardReader = getDeviceService().getRFCardReader();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return rfCardReader;
        }
        else
        {
            return rfCardReader;
        }
    }

    public IInsertCardReader getInsertCardReader()
    {
        if (insertCardReader == null)
        {
            try
            {
                insertCardReader = getDeviceService().getInsertCardReader(0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return insertCardReader;
        }
        else
        {
            return insertCardReader;
        }
    }
    public IMagCardReader getMagCardReader()
    {
        if (magCardReader == null)
        {
            try
            {
                magCardReader = getDeviceService().getMagCardReader();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return magCardReader;
        }
        else
        {
            return magCardReader;
        }
    }
    public IScanner getScanner(int cameraId)
    {
        if (scanner == null)
        {
            try
            {
                lastCameraId = cameraId;
                scanner = getDeviceService().getScanner(cameraId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return scanner;
        }
        else
        {
            return scanner;
        }
    }

    public ILed getLed() {
        if (led == null)
        {
            try
            {
                led = getDeviceService().getLed();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return led;
        }
        else
        {
            return led;
        }
    }
    public ISerialPort getSerialPort() {
        if (serialPort == null)
        {
            try
            {
                serialPort = getDeviceService().getSerialPort("usb-rs232");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return serialPort;
        }
        else
        {
         return serialPort;
        }
    }
    public IBeeper getBeeper() {
        if (beeper == null)
        {
            try
            {
                beeper = getDeviceService().getBeeper();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return beeper;
        }
        else
        {
            return beeper;
        }
    }
    public void reset() {
        deviceService = null;
        pinpad = null;
        pboc = null;
        printer = null;
        deviceInfo = null;
        serialPort = null;
        scanner = null;
        magCardReader = null;
        insertCardReader = null;
        rfCardReader = null;
        beeper = null;
        led = null;
        iemv=null;
    }

    public void initServices()
    {

        printer =getPrinter();
        deviceInfo = getDeviceInfo();
        beeper = getBeeper();
        led = getLed();
    }
}
