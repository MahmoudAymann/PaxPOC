package com.mayman.myapplication.vfprinter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.RemoteException;

import com.mayman.myapplication.R;
import com.mayman.myapplication.Vfdevice.DeviceHelper;
import com.vfi.smartpos.deviceservice.aidl.PrinterListener;
import com.vfi.smartpos.deviceservice.constdefine.ConstIPrinter;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class PrinterManagervf  {
    static Context context;
    Map<String, String> QRticketIdMap;
    Bundle format;
    private static volatile PrinterManagervf sPrinter;
    public static DeviceHelper deviceHelper = DeviceHelper.getInstance();
    public PrinterManagervf()
    {
        if(sPrinter !=null)
        {
            throw new RuntimeException("Use getInstance() method ");
        }
    }

    public PrinterManagervf(Context context, Bundle format) {
        this.context = context;
        this.format = format;
    }

    public static Context getContext() {
        return  context;
    }
    public static PrinterManagervf getInstance()
    {

        if(sPrinter ==null)
        {
            sPrinter = new PrinterManagervf();
        }
        return sPrinter;
    }
    public void initFonts(AssetManager assets)
    {
        PrinterFonts.initialize(assets);
    }
    public void doPrintString(String str) throws RemoteException {
        Bundle format = new Bundle();
        format.putString("fontStyle", PrinterFonts.path + PrinterFonts.FONT_AGENCYB );
        format.putInt("fontSize",3);

        deviceHelper.getPrinter().addTextInLine(format, str,"","",0);
        deviceHelper.getPrinter().feedLine(5);
        deviceHelper.getPrinter().startPrint(new PrintListener());
    }
    public void doPrintSuccess(String cardHolderName) throws RemoteException {
        Bundle format = new Bundle();
        format.putString("fontStyle", PrinterFonts.path + PrinterFonts.FONT_AGENCYB );
        format.putInt("fontSize",3);



        deviceHelper.getPrinter().addTextInLine(format, "","Systems Engineering of Egypt","",0);

        deviceHelper.getPrinter().feedLine(3);
        deviceHelper.getPrinter().addTextInLine(format,"","عملية مقبولة","",0);
        deviceHelper.getPrinter().addTextInLine(format,"",cardHolderName,"",0);
        deviceHelper.getPrinter().feedLine(3);



        deviceHelper.getPrinter().feedLine(5);
        deviceHelper.getPrinter().startPrint(new PrintListener());
    }
    public void printLogo() {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }
            format.putInt(ConstIPrinter.addQrCode.format.KEY_Offset_int, 100);
            format.putInt("width", 384);
            format.putInt(ConstIPrinter.addBarCode.format.KEY_Height_int, 134);
            deviceHelper.getPrinter().addText(format,"Hello World");
            deviceHelper.getPrinter().addText(format,"Hello World");
            deviceHelper.getPrinter().addText(format,"Hello World");
            deviceHelper.getPrinter().addText(format,"Hello World");
            PosServiceVf.getPrintManager().addImage(getPrintImage(0,100,192,
                    R.drawable.ic_launcher_background));
///*            format.putInt(ConstIPrinter.addQrCode.format.KEY_Offset_int, 0);
//            format.putInt("width", 384);
//            format.putInt(ConstIPrinter.addBarCode.format.KEY_Height_int, 134);
//            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bareed);
//            deviceHelper.getPrinter().addBmpImage(format,bitmap);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void PrintHeader() {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }
            format.putInt("fontSize",2);
            format.putInt("align", 1);
            format.putInt("font",1);

//            String date = SystemDateTime.getDateString().replace('-','\\');
//            String Time = SystemDateTime.getHHmmss();
/*            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                Time = Utils.fomratEngToArabicNumbers(Time);
            }*/
//            String time = String.format("%s:%s:%s", Time.substring(0,2),Time.substring(2,4),Time.substring(4));
//            deviceHelper.getPrinter().addText(format, GeneralSettings.getAddress());
//            deviceHelper.getPrinter().addText(format, GeneralSettings.getAddress2());
            //deviceHelper.getPrinter().addText(format,GeneralSettings.getAddress3());
            format.putInt("align", 0);
//            deviceHelper.getPrinter().addText(format,"Time:"+date+" Time:" +time);
//            deviceHelper.getPrinter().addText(format,"TID    :" +posTransaction.getTID());
//            deviceHelper.getPrinter().addText(format,"MID    :" +posTransaction.getMID());
//            deviceHelper.getPrinter().addText(format,"Batch  :" +GeneralSettings.getBatch());
//            deviceHelper.getPrinter().addText(format,"STAN   :" +posTransaction.getSTAN());
//            if (posTransaction.getsAuthID() != null)
//                deviceHelper.getPrinter().addText(format,"AUTH NO:" +posTransaction.getsAuthID());
//            if (posTransaction.getsRRN() != null)
//                deviceHelper.getPrinter().addText(format,"RRN    :" +posTransaction.getsRRN());
            format.putInt("align", 1);
            deviceHelper.getPrinter().addText(format,"---------------------------");
            format.putInt("align", 0);

        } catch (RemoteException e) {
            e.printStackTrace();

        }
    }
//    public void PrintBillPayment_Reciept() {
//        try {
//            if (deviceHelper.getPrinter() == null) {
//                return;
//            }
//            format.putInt("font",1);
//            format.putInt("align", 2);
//
//            String ResponseMes = new String(android.util.Base64.decode(posTransaction.getDE63(),
//                    android.util.Base64.DEFAULT), StandardCharsets.UTF_8);
//            char Seperatoer = StringUtils.getSeperatoer(ResponseMes);
//            int index = 0;
//            while (ResponseMes != null && index != -1){
//                index = ResponseMes.indexOf(Seperatoer);
//                if (index != -1){
//                    deviceHelper.getPrinter().addText(format,ResponseMes.substring(0,index));
//                    ResponseMes = ResponseMes.substring(index+1);
//                }else {
//                    deviceHelper.getPrinter().addText(format,ResponseMes);
//                    break;
//                }
//            }
//
///*            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
//                deviceHelper.getPrinter().addText(format,"المبلغ   :" + Utils.fomratEngToArabicNumbers(StringUtils.FormatAmount(dataForPrinting.getAmount()+""))+ " EGP");
//                deviceHelper.getPrinter().addText(format,"الرسوم  :" + Utils.fomratEngToArabicNumbers(StringUtils.FormatAmount(dataForPrinting.getFees()+""))+" EGP");
//                deviceHelper.getPrinter().addText(format,"الإجمالى :" + Utils.fomratEngToArabicNumbers(StringUtils.FormatAmount(posTransaction.getAmount()))+ " EGP");
//            }else*/ {
//                deviceHelper.getPrinter().addText(format,"المبلغ   :" + StringUtils.FormatAmount(dataForPrinting.getAmount()+"")+ " EGP");
//                deviceHelper.getPrinter().addText(format,"الرسوم  :" + StringUtils.FormatAmount(dataForPrinting.getFees()+"")+" EGP");
//                deviceHelper.getPrinter().addText(format,"الإجمالى :" + StringUtils.FormatAmount(posTransaction.getAmount())+ " EGP");
//            }
//
//            format.putInt("align", 1);
//            format.putInt("font",2);
//            deviceHelper.getPrinter().addText(format,"APPROVED");
//            deviceHelper.getPrinter().addText(format,"عملية مقبولة");
//            format.putInt("align", 1);
//            deviceHelper.getPrinter().addText(format,"---------------------------");
//            format.putInt("align", 0);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            //TODO return status and dont print recipt and to mack revirsal
//        }
//
//    }


    public void printQRCode() {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }
            format.putInt(ConstIPrinter.addQrCode.format.KEY_Offset_int, 10);
            format.putInt(ConstIPrinter.addQrCode.format.KEY_Height_String, 330);
           // String QRmessage = tripData.ticketDataList.get(tripData.TicketCont).QR;



//            deviceHelper.getPrinter().addQrCode(format, QRticketIdMap.get(tripData.ticketDataList.get(tripData.TicketCont).TicktId));
            deviceHelper.getPrinter().addText(format,"---------------------------");
//            if (tripData.TicketCont != tripData.TicketNo-1){
//                deviceHelper.getPrinter().addText(format,"---------------------------");
//                deviceHelper.getPrinter().addText(format,"---------------------------");
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printfooterLogo() {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }
            format.putInt("align", 1);
            format.putInt("bold", 1);
            format.putInt("font",2);
            deviceHelper.getPrinter().addText(format,"powered By:");

/*            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.efinancelogo);
            deviceHelper.getPrinter().addBmpImage(format,bitmap);*/


            PosServiceVf.getPrintManager().addImage(getPrintImage(0,75,192, R.mipmap.ic_launcher));
            //deviceHelper.getPrinter().addText(format,"--Merchant Copy--");
            deviceHelper.getPrinter().feedLine(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printcredential() {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }
            format.putInt("font",1);
            format.putInt("align", 2);
            deviceHelper.getPrinter().addText(format,"المسافر الرئيسي هو صاحب الرقم القومي المسجل في الايصال.");
            deviceHelper.getPrinter().addText(format,"في حالة الرغبة في الغاء التذكرة برجاء التوجه الى محطة القطار");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void startPrint() {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }
            deviceHelper.getPrinter().startPrint(new PrinterManagervf.PrintListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   /* public void test(Context context1) throws Exception {
        this.context = context1;
        Bundle format = new Bundle();
        DeviceHelper deviceHelper = DeviceHelper.getInstance();

        format.putString("fontStyle", PrinterFonts.path + PrinterFonts.FONT_AGENCYB );
        format.putInt("font",1);
        format.putInt("align", 1);
//        PosServiceVf.getPrintManager().addImage(getPrintImage(0,75,192,R.drawable.efinancelogo));
//        PosServiceVf.getPrintManager().addImage(getPrintImage(0,100,192,R.drawable.bareed2));
*//*        format.putInt(ConstIPrinter.addQrCode.format.KEY_Offset_int, 10);
        format.putInt(ConstIPrinter.addQrCode.format.KEY_Height_String, 330);
        deviceHelper.getPrinter().addText(format,"ملية مقبولة");

        String QRmessage = "793820682373824476;ENR_1;ENR_101;qr;231521030;MCECDkS+P3+A/jJH+dKD1V0HAg8Alhyx+NVTu39+6f3zwMY=";
        deviceHelper.getPrinter().addQrCode(format,QRmessage);*//*
*//*        format.putInt(ConstIPrinter.addQrCode.format.KEY_Height_String, 500);
        deviceHelper.getPrinter().addQrCode(format,QRmessage);*//*
        format.putInt("font",2);
        deviceHelper.getPrinter().addText(format,"ملية مقبولة");

        deviceHelper.getPrinter().addText(format,"---------------------------");
        format.putInt("font",3);
        deviceHelper.getPrinter().addText(format,"ملية مقبولة");
        format.putInt("font",4);
        deviceHelper.getPrinter().addText(format,"ملية مقبولة");
        format.putInt("font",5);
        deviceHelper.getPrinter().addText(format,"ملية مقبولة");
        format.putInt("font",6);
        deviceHelper.getPrinter().addText(format,"ملية مقبولة");

        deviceHelper.getPrinter().addText(format,"---------------------------");
        deviceHelper.getPrinter().feedLine(5);
        deviceHelper.getPrinter().startPrint(new PrintListener());

        int indexOfSep;
        int ticketNo = 2;
        String newTickitId ="";
        String tickitId = "797813772239503332;2;35-797813772239503330;2;36";
        for (int i = 0; i < ticketNo; i++) {
            Trips_Activity.TicketData ticketData = new Trips_Activity.TicketData();
            indexOfSep = tickitId.indexOf(';');
            newTickitId = newTickitId+tickitId.substring(0,indexOfSep);
            tickitId = tickitId.substring(indexOfSep+1);

            indexOfSep = tickitId.indexOf(';');
            ticketData.CarNo = tickitId.substring(0,indexOfSep);
            tickitId = tickitId.substring(indexOfSep+1);

            indexOfSep = tickitId.indexOf('-');
            if (indexOfSep == -1){
                ticketData.SeatNo = tickitId;
            }else {
                ticketData.SeatNo = tickitId.substring(0,indexOfSep);
                tickitId = tickitId.substring(indexOfSep);
            }
            tripData.ticketDataList.add(i,ticketData);
        }
        tickitId = "793820682373824476;ENR_1;ENR_101;qr;231521030;MCECDkS+P3+A/jJH+dKD1V0HAg8Alhyx+NVTu39+6f3zwMY=-793820682373824478;ENR_1;ENR_101;qr;231521030;MCECDgV3KqfzUeOhg52QDP97Ag8Am+PIUmF3sg9VgUlzHFM=";
        for (int i = 0; i < ticketNo; i++) {
            Trips_Activity.TicketData ticketData = new Trips_Activity.TicketData();
            indexOfSep = tickitId.indexOf('-');
            if (indexOfSep == -1){
                ticketData = tripData.ticketDataList.get(i);
                ticketData.QR = tickitId;
            }else {
                ticketData = tripData.ticketDataList.get(i);
                ticketData.QR = tickitId.substring(0,indexOfSep);
                tickitId = tickitId.substring(indexOfSep+1);
            }
            tripData.ticketDataList.set(i,ticketData);
        }
    }*/

    public void checkPrinterStatus() throws Exception {
        try {
            int retState = deviceHelper.getPrinter().getStatus();
            if (retState != PrinterStatus.ERROR_NONE.getId()) {
                throw new Exception("check here checkPrinterStatus()");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public void addImage(VfPrinterParamIn paramIn) {
        try {
            Bundle bundle = new Bundle();
            bundle.putInt(ConstIPrinter.addQrCode.format.KEY_Offset_int, paramIn.getOffset());
            bundle.putInt("width", paramIn.getWidth());
            bundle.putInt(ConstIPrinter.addBarCode.format.KEY_Height_int, paramIn.getHeight());
            deviceHelper.getPrinter().addImage(bundle, paramIn.getImageData());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /*public void PrintDetails() {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }

            String date = String.format("%s/%s",posTransaction.getDateTime().substring(0,2),posTransaction.getDateTime().substring(2,4));
            String time = String.format("%s:%s:%s",posTransaction.getDateTimeSec().substring(0,2),posTransaction.getDateTimeSec().substring(2,4),posTransaction.getDateTimeSec().substring(4));
            deviceHelper.getPrinter().addText(format,"Date  :" +date+ "   Time:"+time);
            deviceHelper.getPrinter().addText(format,"STAN  :" +posTransaction.getSTAN()+ "   RRN:"+posTransaction.getsRRN());
            deviceHelper.getPrinter().addText(format,"Amount:"+ Utils.fomratEngToArabicNumbers( StringUtils.FormatAmount(posTransaction.getAmount()))+ "EGP"+" AUTH NO:"+posTransaction.getsAuthID());
            //deviceHelper.getPrinter().addText(format,"Amount:"+ Settlement.arabicToDecimal( StringUtils.FormatAmount(posTransaction.getAmount()))+ "EGP"+" AUTH NO:"+posTransaction.getsAuthID());
            format.putInt("align", 1);
            deviceHelper.getPrinter().addText(format,"---------------------------");
            format.putInt("align", 0);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }*/
    /*public void footer(int Count,int Total) {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }
            deviceHelper.getPrinter().addText(format,"Count:"+Count+" Total:"+StringUtils.FormatAmount(String.valueOf(Total)));
            format.putInt("align", 1);
            deviceHelper.getPrinter().addText(format,"Settlement Report");
            deviceHelper.getPrinter().addText(format,"GB "+posTransaction.getBatch()+" Accepted " );

            deviceHelper.getPrinter().addText(format,"---------------------------");
            format.putInt("align", 0);

            deviceHelper.getPrinter().addText(format,"powered By:");
            PosServiceVf.getPrintManager().addImage(getPrintImage(0,75,192,R.drawable.efinancelogo));

            deviceHelper.getPrinter().feedLine(5);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }*/
    /*public void ReportsFooter(int Count,int Total,boolean ReportType) {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }
            deviceHelper.getPrinter().addText(format,"Count:"+Count+" Total:"+Utils.fomratEngToArabicNumbers( StringUtils.FormatAmount(String.valueOf(Total))));
            format.putInt("align", 1);
            if (ReportType == true) {
                deviceHelper.getPrinter().addText(format,"Detailed Report");
            }else {
                deviceHelper.getPrinter().addText(format,"Total Report");
            }
            deviceHelper.getPrinter().addText(format,"---------------------------");
            format.putInt("align", 0);

            deviceHelper.getPrinter().addText(format,"powered By:");
            PosServiceVf.getPrintManager().addImage(getPrintImage(0,75,192,R.drawable.efinancelogo));

            deviceHelper.getPrinter().feedLine(5);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void PrintENR_Reciept(int isAllAmount) {
        try {
            if (deviceHelper.getPrinter() == null) {
                return;
            }

            String hHmmss = Utils.GetCurrentDate();

            format.putInt("align", 0);
            format.putInt("font",1);
            format.putBoolean("bold",false);
            if (isAllAmount != 2){
                deviceHelper.getPrinter().addText(format,"Time   :" +Utils.fomratEngToArabicNumbers(hHmmss));
                deviceHelper.getPrinter().addText(format,"TID    :" +posTransaction.getTID());
                deviceHelper.getPrinter().addText(format,"MID    :" +posTransaction.getMID());
                deviceHelper.getPrinter().addText(format,"Batch  :" +GeneralSettings.getBatch());
                deviceHelper.getPrinter().addText(format,"STAN   :" +posTransaction.getSTAN());
                deviceHelper.getPrinter().addText(format,"AUTH NO:" +posTransaction.getsAuthID());
                deviceHelper.getPrinter().addText(format,"RRN    :" +posTransaction.getsRRN());
                format.putInt("align", 1);
                deviceHelper.getPrinter().addText(format,"---------------------------");
                format.putInt("align", 0);
            }
            if (isAllAmount != 2){

                String ResponseMes = hexToStr(posTransaction.getDE60());
                deviceHelper.getPrinter().addText(format,"National NO: "+ResponseMes.substring(0,14));
                deviceHelper.getPrinter().addText(format,"Epay Ref No: "+ResponseMes.substring(15));
                format.putInt("font",1);
                format.putInt("align", 2);

                deviceHelper.getPrinter().addText(format,"المبلغ   :" + Utils.fomratEngToArabicNumbers(StringUtils.FormatAmount(String.valueOf(tripData.totalamount)))+ " EGP");
                deviceHelper.getPrinter().addText(format,"الرسوم  :" + Utils.fomratEngToArabicNumbers(StringUtils.FormatAmount(String.valueOf(tripData.totalfees)))+" EGP");
                deviceHelper.getPrinter().addText(format,"الإجمالى :" + Utils.fomratEngToArabicNumbers(StringUtils.FormatAmount(posTransaction.getAmount()))+ " EGP");
                format.putInt("align", 1);
                format.putInt("font",2);
                deviceHelper.getPrinter().addText(format,"APPROVED");
                deviceHelper.getPrinter().addText(format,"عملية مقبولة");
                format.putInt("align", 1);
                deviceHelper.getPrinter().addText(format,"---------------------------");
                format.putInt("align", 0);
            }

            if (isAllAmount != 1){
                deviceHelper.getPrinter().addText(format,tripData.StationsData.StartStationEN+" -> "+tripData.StationsData.EndStationEN);
                deviceHelper.getPrinter().addText(format,"Train   :"+trip.train.name);
                deviceHelper.getPrinter().addText(format,"TicketId:"+tripData.ticketDataList.get(tripData.TicketCont).TicktId);
                deviceHelper.getPrinter().addText(format,"Car No  :"+tripData.ticketDataList.get(tripData.TicketCont).CarNo);
                deviceHelper.getPrinter().addText(format,"Seat No :"+tripData.ticketDataList.get(tripData.TicketCont).SeatNo);
                deviceHelper.getPrinter().addText(format,"Class   :"+tripData.coachClass.coachClassEn);
                deviceHelper.getPrinter().addText(format,"type    :"+trip.train.trainTypeLocalizationMap.en);
                deviceHelper.getPrinter().addText(format,"start   :"+trip.startTime);
                deviceHelper.getPrinter().addText(format,"finish  :"+trip.finishTime);
                format.putInt("align", 1);
                deviceHelper.getPrinter().addText(format,"---------------------------");
                format.putInt("align", 0);
            }





            if (isAllAmount != 1) {
                format.putInt("font",1);
                format.putInt("align", 2);
                deviceHelper.getPrinter().addText(format,tripData.StationsData.StartStationAR+" -> "+tripData.StationsData.EndStationAR);
                deviceHelper.getPrinter().addText(format,"القطار  :" +trip.train.name);
                deviceHelper.getPrinter().addText(format,"تذكرة رقم:"+tripData.ticketDataList.get(tripData.TicketCont).TicktId);
                deviceHelper.getPrinter().addText(format,"العربة  :" +tripData.ticketDataList.get(tripData.TicketCont).CarNo);
                deviceHelper.getPrinter().addText(format,"المقعد  :" +tripData.ticketDataList.get(tripData.TicketCont).SeatNo);
                deviceHelper.getPrinter().addText(format,"الفئة   :" +tripData.coachClass.coachClassAr);
                deviceHelper.getPrinter().addText(format,"النوع   :" +trip.train.trainTypeLocalizationMap.ar);
                //deviceHelper.getPrinter().addTextInLine(format,"","النوع   :" +trip.train.trainTypeLocalizationMap.ar, ""    , 0);
                deviceHelper.getPrinter().addText(format,"تاريخ القيام :" +trip.startTime);
                deviceHelper.getPrinter().addText(format,"تاريخ الوصول :" +trip.finishTime);
                format.putInt("align", 1);
                deviceHelper.getPrinter().addText(format,"---------------------------");
                format.putInt("align", 0);
            }

*/
    /*            if (isAllAmount != 2){
                deviceHelper.getPrinter().addText(format,"الرسوم  :" + StringUtils.FormatAmount(String.valueOf(tripData.totalfees))+" EGP");
                deviceHelper.getPrinter().addText(format,"السعر   :" + StringUtils.FormatAmount(String.valueOf(tripData.totalamount))+ " EGP");
                deviceHelper.getPrinter().addText(format,"الإجمالى :" + StringUtils.FormatAmount(posTransaction.getAmount())+ " EGP");
            }*//*

        } catch (Exception e) {
            e.printStackTrace();
            //TODO return status and dont print recipt and to mack revirsal

        }

    }*/


    public static VfPrinterParamIn getPrintImage(int offset, int height, int width, int reId){
        VfPrinterParamIn printerParamIn = new VfPrinterParamIn();
        printerParamIn.setType(3);
        printerParamIn.setOffset(offset);
        printerParamIn.setHeight(height);
        printerParamIn.setWidth(width);
        printerParamIn.setGray(1);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), reId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        printerParamIn.setImageData(stream.toByteArray());

        return printerParamIn;
    }

    static class PrintListener extends PrinterListener.Stub {
        @Override
        public void onFinish() throws RemoteException {


        }

        @Override
        public void onError(int error) throws RemoteException {
/*            BillInquiry billInquiry = new BillInquiry();
            billInquiry.dfff();*/
        }
    }
}
