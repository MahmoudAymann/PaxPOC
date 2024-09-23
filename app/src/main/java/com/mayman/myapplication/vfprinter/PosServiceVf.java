package com.mayman.myapplication.vfprinter;

public class PosServiceVf {
    private static final String TAG = "PosService";
    public static PrinterManagervf getPrintManager() {
        return PrinterManagervf.getInstance();
    }

}
