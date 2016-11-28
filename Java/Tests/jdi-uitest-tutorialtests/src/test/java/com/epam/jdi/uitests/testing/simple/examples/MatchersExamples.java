package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.web.matcher.testng.Assert;
import com.epam.web.matcher.testng.Check;
import com.epam.web.matcher.testng.ScreenAssert;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.web.matcher.base.DoScreen.SCREEN_ON_FAIL;
import static java.util.Arrays.asList;


public class MatchersExamples {

    @Test
    public void commonAsserts() {
        Assert.contains("Test Text", "Text");
        Assert.matches("1352-423-85746", "\\d{4}-\\d{3}-\\d{5}");
        Assert.isTrue(1 == 1);
        Assert.isFalse("test".contains("a"));
        Assert.ignoreCase().areEquals("Test String", "test STRING");
    }
    @Test
    public void differentAsserts() {
        new Check("This important thing").contains("Test Text", "Text");
        ScreenAssert.matches("1352-423-85746", "\\d{4}-\\d{3}-\\d{5}");
        new Check().doScreenshot(SCREEN_ON_FAIL).isTrue(1 == 1);
    }

    @Test
    public void listAsserts() {
        String[] searchResults =
                new String[] { "IPhone 4", "IPhone 5S", "IPhone 6" };
        Assert.each(searchResults).contains("IPhone");
        Assert.each(searchResults).matches("IPhone \\d.*");
        Assert.arrayEquals(searchResults,
                new String[] { "IPhone 4", "IPhone 5S", "IPhone 6" });
        Assert.listEquals(asList(searchResults),
                asList("IPhone 4", "IPhone 5S", "IPhone 6"));
        Assert.each(searchResults).areDifferent();

        List<String> sameList = asList("test", "test", "test");
        Assert.each(sameList).areSame();

        List<Integer> sortedListAsc = asList(1, 2, 3);
        Assert.isSortedByAsc(sortedListAsc);

        int[] sortedArrayDesc = new int[] {8, 7, 6};
        Assert.isSortedByDesc(sortedArrayDesc);
    }

    private int i = 0;
    private String[] searchResults =
            new String[] { "IPhone 4", "IPhone 5S", "IPhone 6" };
    private String getNext() {
        if (i == 3) i = 0;
        return searchResults[i++];
    }

    @Test
    public void waitAsserts() {
        Assert.areEquals(() -> getNext(), "IPhone 6");
        Assert.contains(() -> getNext(), "IPhone 5");
        Assert.waitTimeout(5).matches(() -> getNext(), ".*S");
    }

    private void throwException() {
        throw new RuntimeException("Test Exception");
    }
    private void assertException() { throw Assert.exception("Assert Exception"); }
    private void failException() { Assert.fail("Fail Exception"); }

    @Test
    public void exceptionAsserts() {
        Assert.throwException(this::assertException, "Assert Exception");
        Assert.throwException(this::throwException, RuntimeException.class, "Test Exception");
        Assert.throwException(this::failException, "Fail Exception");
        Assert.hasNoExceptions(this::getNext);
    }

}
