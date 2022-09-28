package com.kawahedukasi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {
    public static boolean isStandardNameInput(String input){
        Pattern pattern = Pattern.compile("^[A-Za-z.'` ]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean isNumericInput(String input){
        Pattern pattern = Pattern.compile("\\d+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean isStandardAlphabetInput(String input){
        Pattern pattern = Pattern.compile("^$|^[A-Za-z ]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean isStandardPhoneNumber(String input){
        Pattern pattern = Pattern.compile("^(\\+62|62|0)8[1-9][0-9]{6,9}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean isStandardEmail(String input){
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean isStandardBoolean(String input){
        Pattern pattern = Pattern.compile("^(true|false)$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean isGenderCodeInput(String input){
        Pattern pattern = Pattern.compile("^[FMfm]$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
