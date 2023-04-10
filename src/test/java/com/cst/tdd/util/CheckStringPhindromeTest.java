package com.cst.tdd.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class CheckStringPalindromeTest {
    private static CheckStringPalindrome checkStringPalindrome = null;

    @BeforeAll
    public static void init() {
        checkStringPalindrome = new CheckStringPalindrome();
    }
    @AfterAll
    public static void destroy() {
        checkStringPalindrome = null;
    }

    @ParameterizedTest
    @ValueSource(strings = {"kayak", "radar", "madam", "deed"})
    public void isPalindromeTest(String str) {
        boolean actual = checkStringPalindrome.isPalindrome(str);
        Assertions.assertTrue(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void checkEvenOrOddTest(String input, String expected) {
        System.out.println(input);
        String actual = checkStringPalindrome.checkEvenOrOdd(input);
        Assertions.assertEquals(expected, actual);
    }
}