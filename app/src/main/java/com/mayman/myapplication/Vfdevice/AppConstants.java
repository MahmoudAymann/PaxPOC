package com.mayman.myapplication.Vfdevice;

public class AppConstants {
    public static String TAG="SEETAG";

    public static final class EMV
    {
        public static final String CARD_TYPE="cardType";
        public static final String AUTH_AMOUNT="authAmount";
        public static final String IS_SUPPORT_Q="isSupportQ";
        public static final String IS_SUPPORT_SM="isSupportSM";
        public static final String IS_FORCE_ONLINE="isQPBOCForceOnline";
    }

    public static final class CTLS
    {
        public static final String TRANSACTION_PROCESS_CODE="transProcessCode";
    }

    public static final class MERCHANT
    {
        public static final String MERCHANT_NAME="merchantName";
        public static final String MERCHANT_NAME_VALUE="X990 EMV Demo";
        public static final String MERCHANT_ID="merchantId";
        public static final String MERCHANT_ID_VALUE="ABCDE0123456789";
    }

    public static final class TERMINAL
    {
        public static final String TERMINAL_ID="terminalId";
        public static final String TERMINAL_ID_VALUE="01020304";
    }

    public static final class KEYS
    {
        public static final String mainKey_MasterKey = "F336AC78CDF6E6DA9FB67125F82E6E44";
        public static final int mainKeyId = 97;
        public static final int workKeyId = 1;
        //0: MASTER KEY
        public static final int MASTERKEY = 0;

        //1:MAC key
        public static final int MACKEY = 1;

        // 2:PIN key
        public static final int PINKEY = 2;

        //3:TDK key
        public static final int TDKEY = 3;

        //4:(SM4)MASTER KEY
        public static final int SM_MASTERKEY = 4;

        //5:(SM4)MAC key
        public static final int SM_MACKEY = 5;

        //6:(SM4)PIN key
        public static final int SM_PINKEY = 6;

        //7:(SM4)TDK key
        public static final int SM_TDKEY = 7;

        //8:(SM4)MASTER KEY
        public static final int AES_MASTERKEY = 8;

        //9:(SM4)MAC key
        public static final int AES_MACKEY = 9;

        //10:(SM4)PIN key
        public static final int AES_PINKEY = 10;

        //11:(SM4)TDK key
        public static final int AES_TDKEY = 11;

        //12:DUKPT key
        public static final int DUKPTKEY = 12;
    }

    public static final class TransactionsTypes
    {
        public static final int SALE =0;
        public static final int VOID =1;
        public static final int REFUND =2;
        public static final int SETTELMENT =3;
        public static final int REVERSAL =4;
        public static final int WORKINGKEY =5;
    }
}
