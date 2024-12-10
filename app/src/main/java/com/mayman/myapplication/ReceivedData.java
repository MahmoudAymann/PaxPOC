package com.mayman.myapplication;

public class ReceivedData {
    static ReceivedData data;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    private String Response;

    public static ReceivedData getinstance(){
        if (data == null)
        {
            data = new ReceivedData();
        }
        return data;
    }
}
