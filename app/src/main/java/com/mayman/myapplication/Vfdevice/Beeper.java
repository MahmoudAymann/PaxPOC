package com.mayman.myapplication.Vfdevice;

import android.os.RemoteException;

public class Beeper {

    DeviceHelper deviceHelper = DeviceHelper.getInstance();
    public void beep() {
        try {
            deviceHelper.getBeeper().startBeep(200);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doubleBeep() {
        try {
            deviceHelper.getBeeper().startBeep(200);
            Thread.sleep(200);

            deviceHelper.getBeeper().startBeep(200);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tribleBeep() {

        try {
            deviceHelper.getBeeper().startBeep(200);
            Thread.sleep(200);

            deviceHelper.getBeeper().startBeep(200);
            Thread.sleep(200);

            deviceHelper.getBeeper().startBeep(200);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
