package com.ticketing_system.Util;

import java.util.Random;

public class VerifyCodeUtil {

    private static final String VERIFY_CODES = "0123456789";



    public static String generateVerifyCode(int verifySize, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }
}
