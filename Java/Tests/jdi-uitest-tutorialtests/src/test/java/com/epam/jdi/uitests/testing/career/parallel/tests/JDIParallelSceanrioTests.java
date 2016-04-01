package com.epam.jdi.uitests.testing.career.parallel.tests;

import com.epam.jdi.uitests.testing.career.common.tests.TestsBaseParallel;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.testing.career.parallel.tests.TestScenarios.sendCVTest;

public class JDIParallelSceanrioTests extends TestsBaseParallel {

    @Test
    public void simpleParallelTest() {
        sendCVTest("Test 1");
    }
    @Test
    public void simpleParallelTest2() {
        sendCVTest("Test 2");
    }
    @Test
    public void simpleParallelTest3() {
        sendCVTest("Test 3");
    }
    @Test
    public void simpleParallelTest4() {
        sendCVTest("Test 4");
    }
    @Test
    public void simpleParallelTest5() {
        sendCVTest("Test 5");
    }
    @Test
    public void simpleParallelTest6() {
        sendCVTest("Test 6");
    }
    @Test
    public void simpleParallelTest7() {
        sendCVTest("Test 7");
    }
    @Test
    public void simpleParallelTest8() {
        sendCVTest("Test 8");
    }
}