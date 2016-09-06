package com.epam.jdi.uitests.testing.career.common.tests.simple.examples;

import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static java.util.Arrays.*;


public class MatchersExamples {

    @Test
    public void commonAssertsExample() {
        Assert.contains("Test Text", "Text");
        Assert.matches("1352-423-85746", "\\d{4}-\\d{3}-\\d{5}");
        Assert.isTrue(1 == 1);
        Assert.isFalse("test".contains("a"));
    }

    @Test
    public void listAssertsExample() {
        String[] searchResults =
                new String[] { "Iphone 4", "Iphone 5S", "Iphone 6" };
        Assert.assertEach(searchResults).contains("Iphone");
        Assert.assertEach(searchResults).matches("Iphone \\d.*");
        Assert.arrayEquals(searchResults,
                new String[] { "Iphone 4", "Iphone 5S", "Iphone 6" });
        Assert.listEquals(asList(searchResults),
                asList("Iphone 4", "Iphone 5S", "Iphone 6"));
        Assert.assertEach(searchResults).areDifferent();

        List<String> sameList = asList("test", "test", "test");
        Assert.assertEach(sameList).areSame();

        List<Integer> sortedListAsc = asList(1, 2, 3);
        Assert.isSortedByAsc(sortedListAsc);

        int[] sortedArrayDesc = new int[] {1, 2, 3};
        Assert.isSortedByDesc(sortedArrayDesc);
    }

    private int i = 0;
    private String[] searchResults =
            new String[] { "Iphone 4", "Iphone 5S", "Iphone 6" };
    private String getNext() {
        if (i == 3) i = 0;
        return searchResults[i++];
    }

    @Test
    public void waitAssertsExample() {
        Assert.areEquals(() -> getNext(), "Iphone 6");
        Assert.contains(() -> getNext(), "Iphone 5");
        Assert.matches(() -> getNext(), ".*S");
    }

    private void throwException() {
        throw new RuntimeException("Test Exception");
    }
    @Test
    public void exceptionAssertsExample() {
        Assert.throwException(this::throwException, "Test Exception");
        Assert.throwException(this::throwException, RuntimeException.class, "Test Exception");
        Assert.hasNoExceptions(this::getNext);
    }

}
