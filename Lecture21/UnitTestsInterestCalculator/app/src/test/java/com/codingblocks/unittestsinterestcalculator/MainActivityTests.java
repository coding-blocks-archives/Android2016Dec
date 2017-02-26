package com.codingblocks.unittestsinterestcalculator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by championswimmer on 26/02/17.
 */

public class MainActivityTests {

    @Test
    public void calcAmount_isCorrect () throws Exception {
        assertEquals(110, MainActivity.calcAmount(100, 10, 1), 0.001);

    }
}
