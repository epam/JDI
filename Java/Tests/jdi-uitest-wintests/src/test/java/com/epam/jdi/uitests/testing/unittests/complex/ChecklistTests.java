package com.epam.jdi.uitests.testing.unittests.complex;

import com.epam.jdi.uitests.core.interfaces.complex.ICheckList;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Nature;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static com.epam.web.matcher.testng.Assert.assertContains;
import static java.util.Arrays.asList;


public class ChecklistTests extends InitTests {
    private static final List<String> natureOptions = asList("Water", "Earth", "Wind", "Fire");
    private static final String allValues = "Water, Earth, Wind, Fire";
    private Supplier<ICheckList<Nature>> checkListSupplier =
            () -> mainWindow.mainTabPane.metalsAndColorsTab.nestedMetalsAndColorsView.natureCheckList;

    @BeforeMethod
    public void before(Method method) throws IOException {
        isInState(METALS_AND_COLORS_PAGE);
    }

    @Test
    public void selectStringTest() {
        checkListSupplier.get().select("Fire");
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
    }

    @Test
    public void selectIndexTest() {
        checkListSupplier.get().select(4);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
    }

    @Test
    public void selectEnumTest() {
        checkListSupplier.get().select(Nature.FIRE);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
    }

    @Test
    public void select2StringTest() {
        checkListSupplier.get().select("Water", "Fire");
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
        Assert.assertContains(mainWindow.logTextBox.getLines()[1], "Water: condition changed to true");
    }

    @Test
    public void select2IndexTest() {
        checkListSupplier.get().select(1, 4);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
        Assert.assertContains(mainWindow.logTextBox.getLines()[1], "Water: condition changed to true");
    }

    @Test
    public void select2EnumTest() {
        checkListSupplier.get().select(Nature.WATER, Nature.FIRE);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
        Assert.assertContains(mainWindow.logTextBox.getLines()[1], "Water: condition changed to true");
    }

    @Test
    public void checkStringTest() {
        checkListSupplier.get().check("Fire");
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
    }

    @Test
    public void checkIndexTest() {
        checkListSupplier.get().check(4);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
    }

    @Test
    public void checkEnumTest() {
        checkListSupplier.get().check(Nature.FIRE);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
    }

    @Test
    public void check2StringTest() {
        checkListSupplier.get().check("Water", "Fire");
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
        Assert.assertContains(mainWindow.logTextBox.getLines()[1], "Water: condition changed to true");
    }

    @Test
    public void check2IndexTest() {
        checkListSupplier.get().check(1, 4);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
        Assert.assertContains(mainWindow.logTextBox.getLines()[1], "Water: condition changed to true");
    }

    @Test
    public void check2EnumTest() {
        checkListSupplier.get().check(Nature.WATER, Nature.FIRE);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
        Assert.assertContains(mainWindow.logTextBox.getLines()[1], "Water: condition changed to true");
    }

    @Test
    public void selectAllTest() {
        checkListSupplier.get().selectAll();
        String[] log = mainWindow.logTextBox.getLines();
        assertContains(log[3], "Water: condition changed to true");
        assertContains(log[2], "Earth: condition changed to true");
        assertContains(log[1], "Wind: condition changed to true");
        assertContains(log[0], "Fire: condition changed to true");
    }

    @Test
    public void checkAllTest() {
        checkListSupplier.get().checkAll();
        String[] log = mainWindow.logTextBox.getLines();
        assertContains(log[3], "Water: condition changed to true");
        assertContains(log[2], "Earth: condition changed to true");
        assertContains(log[1], "Wind: condition changed to true");
        assertContains(log[0], "Fire: condition changed to true");
    }

    @Test
    public void clearAllTest() {
        checkListSupplier.get().checkAll();
        checkListSupplier.get().clear();
        String[] log = mainWindow.logTextBox.getLines();
        assertContains(log[3], "Water: condition changed to false");
        assertContains(log[2], "Earth: condition changed to false");
        assertContains(log[1], "Wind: condition changed to false");
        assertContains(log[0], "Fire: condition changed to false");
    }

    @Test
    public void uncheckAllTest() {
        checkListSupplier.get().checkAll();
        checkListSupplier.get().uncheckAll();
        String[] log = mainWindow.logTextBox.getLines();
        assertContains(log[3], "Water: condition changed to false");
        assertContains(log[2], "Earth: condition changed to false");
        assertContains(log[1], "Wind: condition changed to false");
        assertContains(log[0], "Fire: condition changed to false");
    }

    @Test
    public void getOptionsTest() {
        Assert.listEquals(checkListSupplier.get().getOptions(), natureOptions);
    }

    @Test
    public void getNamesTest() {
        Assert.listEquals(checkListSupplier.get().getNames(), natureOptions);
    }

    @Test
    public void getValuesTest() {
        Assert.listEquals(checkListSupplier.get().getValues(), natureOptions);
    }

    @Test
    public void getOptionsAsTextTest() {
        Assert.areEquals(checkListSupplier.get().getOptionsAsText(), allValues);
    }

    @Test
    public void setValueTest() {
        checkListSupplier.get().setValue("Fire");
        assertContains(mainWindow.logTextBox.getLines()[0], "Fire: condition changed to true");
    }

    @Test
    public void getNameTest() {
        areEquals(checkListSupplier.get().getName(), "Nature Check List");
    }

    @Test
    public void areSelectedTest() {
        checkListSupplier.get().select("Water", "Wind");
        Assert.listEquals(checkListSupplier.get().areSelected(), asList("Water", "Wind"));
    }

    @Test
    public void areDeselectedTest() {
        checkListSupplier.get().select("Water", "Wind");
        Assert.listEquals(checkListSupplier.get().areDeselected(), asList("Earth", "Fire"));
    }

    @Test
    public void getValueTest() {
        checkListSupplier.get().select("Water", "Wind");
        Assert.areEquals(checkListSupplier.get().getValue(), "Water, Wind");
    }
}
