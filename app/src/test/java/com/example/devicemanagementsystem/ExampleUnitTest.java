package com.example.devicemanagementsystem;

import com.example.devicemanagementsystem.Utilities.DateUtils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void toISO8601String_Test() {
        DateUtils dateUtils = new DateUtils();

        String result = dateUtils.toISO8601String(new Date());
        assertEquals("2019", result);
    }
}