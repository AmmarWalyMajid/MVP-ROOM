package com.mp2.mvproom.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Util {

    public static boolean isValidName(String name) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        return patron.matcher(name).matches();
    }

    public static boolean isValidPhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
