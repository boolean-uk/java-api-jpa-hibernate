package com.booleanuk.api.validation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ValidationChecker {
    public static boolean checkIfNullExists(Object target) {
        return Arrays.stream(target.getClass()
                        .getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .map(f -> getFieldValue(f, target))
                .filter(x->x==null)
                .collect(Collectors.toList()).size()>1;
//                .anyMatch(x-> Objects.isNull(x))
//                ;
    }

    private static Object getFieldValue(Field field, Object target) {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isEmail(String emailAddress) {
        return Pattern.compile("^(.+)@(\\S+)$")
                .matcher(emailAddress)
                .matches();
    }
}
