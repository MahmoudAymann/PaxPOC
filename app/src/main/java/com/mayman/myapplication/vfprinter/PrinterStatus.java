package com.mayman.myapplication.vfprinter;

import androidx.annotation.NonNull;

import com.mayman.myapplication.R;

//import com.vfc.android.domain.R;
//import io.reactivex.annotations.NonNull;

public enum PrinterStatus {
    ERROR_NONE(0, getString(R.string.ERROR_NONE)),
    ERROR_PAPERENDED(240, getString(R.string.ERROR_PAPERENDED)),
    ERROR_HARDERR(242, getString(R.string.ERROR_HARDERR)),
    ERROR_OVERHEAT(243, getString(R.string.ERROR_OVERHEAT)),
    ERROR_BUFOVERFLOW(245, getString(R.string.ERROR_BUFOVERFLOW)),
    ERROR_LOWVOL(225, getString(R.string.ERROR_LOWVOL)),
    ERROR_PAPERENDING(244, getString(R.string.ERROR_PAPERENDING)),
    ERROR_MOTORERR(251, getString(R.string.ERROR_MOTORERR)),
    ERROR_PENOFOUND(252, getString(R.string.ERROR_PENOFOUND)),
    ERROR_PAPERJAM(238, getString(R.string.ERROR_PAPERJAM)),
    ERROR_NOBM(246, getString(R.string.ERROR_NOBM)),
    ERROR_BUSY(247, getString(R.string.ERROR_BUSY)),
    ERROR_BMBLACK(248, getString(R.string.ERROR_BMBLACK)),
    ERROR_WORKON(230, getString(R.string.ERROR_WORKON)),
    ERROR_LIFTHEAD(224, getString(R.string.ERROR_LIFTHEAD)),
    ERROR_CUTPOSITIONERR(226, getString(R.string.ERROR_CUTPOSITIONERR)),
    ERROR_LOWTEMP(227, getString(R.string.ERROR_LOWTEMP));
    
    private int id;
    private String msg;

    private PrinterStatus(int id2, String msg2) {
        this.id = id2;
        this.msg = msg2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg2) {
        this.msg = msg2;
    }

    public static PrinterStatus findPrinterStatusById(@NonNull int id2) {
        PrinterStatus[] values = values();
        for (PrinterStatus status : values) {
            if (status.getId() == id2) {
                return status;
            }
        }
        return ERROR_NONE;
    }

    public static String getString(int id2) {
        return PrinterManagervf.getContext().getString(id2);
    }
}
