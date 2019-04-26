package com.oli;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Author: Oliver
 */
@Slf4j
public class TestUtil {
    public static final String INVALID_LONG_ID = "2147483647";
    public static final String UUID_INVALID = "69ac56ad-7e41-42ee-b1d3-57d6d5814cb1";
    public static final String INVALID_SPACE = " ";
    public static final String NOT_FOUND_REASON = " NOT FOUND with Id:";
    public static final String INVALID_ARGUMENT = "Invalid Argument:";
    public static final String NULL_ARGUMENT = "java.lang.NullPointerException: The validated object is null";
    public static final String ILLEGAL_ARGUMENT_BLANK = "ava.lang.IllegalArgumentException: The validated character sequence is blank";
    public static final String ILLEGAL_ARGUMENT = "java.lang.IllegalArgumentException: ";

    public static final String TEST_STRING_1 = "TEST_EMAIL_DISPOSABLE";
    public static final String TEST_STRING_2 = "TEST_PASS_DISPOSABLE";
    public static final String TEST_STRING_3 = "TEST_SUBJECT_DISPOSABLE";
    public static final String TEST_STRING_4 = "TEST_FIRST_NAME_DISPOSABLE";
    public static final String TEST_STRING_5 = "TEST_FIRST_NAME_DISPOSABLE2";
    public static final String TEST_STRING_6 = "TEST_FIRST_NAME_DISPOSABLE3";
    public static final String TEST_STRING_7 = "TEST_FIRST_NAME_DISPOSABLE4";
    public static final List<String> STRING_LIST_TEST2 = Arrays.asList("str1", "str2");

    public static final String USER = "user";

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
}

