package com.mayman.myapplication.Vfdevice;

import android.os.RemoteException;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class Led {

    DeviceHelper deviceHelper = DeviceHelper.getInstance();

    public static final int BLUE_LIGHT = 1;
    public static final int YELLOW_LIGHT = 2;
    public static final int GREEN_LIGHT = 3;
    public static final int RED_LIGHT = 4;
    public static boolean ledBlueStatus = false;
    public static boolean ledRedStatus = false;
    public static boolean ledBYellowStatus = false;
    public static boolean ledGreenStatus = false;

    public void turnOffRfCardLedAll()
    {
        turnOffRfCardLedAll(true, false);
    }


    public void turnOffRfCardLedAll(boolean force, boolean isQPBOCTransaction) {
        if (!force && isQPBOCTransaction) {
            return;
        }

        if (deviceHelper.getLed() == null) {
            return;
        }

        turnOffBlue();
        turnOffRed();
        turnOffYellow();
        turnOffGreen();
    }

    /**
     * 非接交易失败
     *
     * @param isQPBOCTransaction
     */
    public  void turnOnRFCardLedForFail(boolean isQPBOCTransaction) {
        if (!isQPBOCTransaction) {
            return;
        }

        if (isQPBOCTransaction) {
            //Beeper.tribleBeep();
        }

        turnOffBlue();
        turnOffYellow();
        turnOffGreen();
        turnOnRed();
    }

    /**
     * 非接交易失败
     *
     * @param isQPBOCTransaction
     */
    public  void turnOnRFCardLedForFail(final boolean isQPBOCTransaction, int delay) {
        if (!isQPBOCTransaction) {
            return;
        }

        turnOffBlue();
        turnOffYellow();
        turnOffGreen();
        turnOnRed();

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                turnOffRfCardLedAll(true, isQPBOCTransaction);
            }
        }, delay);

    }

    /**
     * 非接交易,脱机批准
     *
     * @param isQPBOCTransaction
     */
    public  void turnOnRFCardLedForSuccess(final boolean isQPBOCTransaction) {
        if (!isQPBOCTransaction) {
            return;
        }
        turnOnBlue();
        turnOnYellow();
        turnOnGreen();
        turnOffRed();

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                turnOffRfCardLedAll(true, isQPBOCTransaction);
            }
        }, 800);

    }

    public void turnOnRFCardLedForProcess(boolean isQPBOCTransaction) {
        if (!isQPBOCTransaction) {
            return;
        }


        turnOnBlue();
        turnOnYellow();
        turnOffGreen();
        turnOffRed();
    }

    public void turnOnBlue() {
        try {
            deviceHelper.getLed().turnOn(BLUE_LIGHT);
            ledBlueStatus = true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void turnOffBlue() {
        if (!ledBlueStatus) {
            return;
        }
        try {
            deviceHelper.getLed().turnOff(BLUE_LIGHT);
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.i(AppConstants.TAG, "DeviceHelper.getLed().turnOff RemoteException=" + e.getMessage());
        }
    }

    public  void turnOnRed() {
        try {
            deviceHelper.getLed().turnOn(RED_LIGHT);
            ledRedStatus = true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void turnOffRed() {
        if (!ledRedStatus) {
            return;
        }
        try {
            deviceHelper.getLed().turnOff(RED_LIGHT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public  void turnOnYellow() {
        try {
            deviceHelper.getLed().turnOn(YELLOW_LIGHT);
            ledBYellowStatus = true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void turnOffYellow() {
        if (!ledBYellowStatus) {
            return;
        }
        try {
            deviceHelper.getLed().turnOff(YELLOW_LIGHT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void turnOnGreen() {
        try {
            deviceHelper.getLed().turnOn(GREEN_LIGHT);
            ledGreenStatus = true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void turnOffGreen() {
        if (!ledGreenStatus) {
            return;
        }
        try {
            deviceHelper.getLed().turnOff(GREEN_LIGHT);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
