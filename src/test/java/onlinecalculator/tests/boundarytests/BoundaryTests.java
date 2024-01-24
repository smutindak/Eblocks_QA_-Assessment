package onlinecalculator.tests.boundarytests;

import onlinecalculator.base.BaseSuiteSetup;
import onlinecalculator.pages.CalcPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.eblocks.onlinecalculator.drivers.DriverManager.getDriver;

public class BoundaryTests extends BaseSuiteSetup {
    protected CalcPage calcPage;

    @BeforeMethod
    public void setupTest() {
        calcPage = new CalcPage(getDriver());

        getDriver().get("https://www.online-calculator.com/html5/online-calculator/index.php?v=10");
    }

    @Test(priority = 1)
    public void testDivisionByOneReturnsTheOriginalValue() {

        calcPage.enterNumber(9);
        calcPage.enterOperator("/");
        calcPage.enterNumber(1);
        calcPage.enterOperator("=");

        String formattedActualResult = calcPage.getResultNonFormatted();

        Assert.assertEquals(formattedActualResult, "9");
    }
    @Test(priority = 2)
    public void testNumberWithLargeDecimalPrecisionReturnsAccurateResultWithNoRounding() {

        calcPage.enterNumber(546.469874);
        calcPage.enterOperator("+");
        calcPage.enterNumber(123.464461);
        calcPage.enterOperator("=");

        String formattedActualResult = calcPage.getResultNonFormatted();

        Assert.assertEquals(formattedActualResult, "669.934335");
    }
    @Test(priority = 3)
    public void testConvertingANumberToPercentageIsAccurate() {

        calcPage.enterNumber(50);
        calcPage.enterOperator("%");

        String formattedActualResult = calcPage.getResultNonFormatted();

        Assert.assertEquals(formattedActualResult, "0.5");
    }
    @Test(priority = 4)
    public void testMultiplyTwoOneDigitValuesAndAssertASingleDigitIsReturned() {

        calcPage.enterNumber(2);
        calcPage.enterOperator("*");
        calcPage.enterNumber(2);
        calcPage.enterOperator("=");

        String formattedActualResult = calcPage.getResultNonFormatted();

        Assert.assertEquals(formattedActualResult, "4");
    }
    @Test(priority = 5)
    public void testClearTheDisplayAndPerformAnOperation() {

        calcPage.enterNumber(2);
        calcPage.enterOperator("*");
        calcPage.enterNumber(2);
        calcPage.enterOperator("=");
        calcPage.enterOperator("c");
        calcPage.enterNumber(3);
        calcPage.enterOperator("*");
        calcPage.enterNumber(2);
        calcPage.enterOperator("=");


        String formattedActualResult = calcPage.getResultNonFormatted();

        Assert.assertEquals(formattedActualResult, "6");
    }
}
