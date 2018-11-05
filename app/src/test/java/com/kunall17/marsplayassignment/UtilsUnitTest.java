package com.kunall17.marsplayassignment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsUnitTest {
    @Test
    public void getFileExtension() throws Exception {
        assertEquals(".jpg", Utils.getFileExtension("new.jpg"));
    }

    @Test
    public void getFileNoExtension() throws Exception {
        assertEquals("", Utils.getFileExtension("new"));

    }


}