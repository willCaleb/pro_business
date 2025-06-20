package com.will.caleb.business.exception;

import java.text.MessageFormat;

public class MessageConverter {

    public static String convert(String message, Object... args) {
        return MessageFormat.format(message, args);
    }

    public static String convert(EnumCustomException customException, Object... args){
        return MessageFormat.format(customException.getMessage(), args);
    }

}
