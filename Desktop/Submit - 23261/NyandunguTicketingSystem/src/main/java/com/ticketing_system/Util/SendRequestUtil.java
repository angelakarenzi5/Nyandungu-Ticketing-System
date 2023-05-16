package com.ticketing_system.Util;




import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SendRequestUtil {

    private static final String pindoToken = "eyJhbGciOiJub25lIn0.eyJpZCI6OTE1LCJyZXZva2VkX3Rva2VuX2NvdW50IjowfQ.";

    public static boolean sendPhoneNumberVerificationCode(String phoneNumber, String verificationCode) throws IOException, JSONException {
        String message = "Your Nyandungu ticket ID code is " + verificationCode + ". Please show this to our agents and complete the payment to activate your ticket. ";
        String messageSender = "SawaMob";

        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to","+25"+phoneNumber);
        jsonObject.put("text",message);
        jsonObject.put("sender", messageSender);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.valueOf(jsonObject));
        Request request = new Request.Builder()
                .url("https://api.pindo.io/v1/sms/")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + pindoToken)
                .build();
        Response response = client.newCall(request).execute();
        return true;

    }
}
