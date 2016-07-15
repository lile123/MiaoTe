package com.qianfeng.laosiji.miaote;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testTime(){
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        String str = format.format(new Date(1468675800000L));
        System.out.print(str);
    }
}