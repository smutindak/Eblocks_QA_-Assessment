package onlinecalculator.tests.positivetests;

import onlinecalculator.base.BaseSuiteSetup;
import onlinecalculator.pages.CalcPage;
import onlinecalculator.testdata.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.eblocks.onlinecalculator.drivers.DriverManager.getDriver;

public class PositiveTests extends BaseSuiteSetup {

    protected CalcPage calcPage;

    @BeforeMethod
    public void setupTest() {
        calcPage = new CalcPage(getDriver());

        getDriver().get("https://www.online-calculator.com/html5/online-calculator/index.php?v=10");
    }

    @Test(priority = 1, dataProvider = "getAdditionTestCases", dataProviderClass = DataProviders.class)
    public void testAdditionOfTwoValues(double value1, double value2, double expectedResult) {

        calcPage.enterNumber(value1);
        calcPage.enterOperator("+");
        calcPage.enterNumber(value2);
        calcPage.enterOperator("=");

        String formattedExpectedResult = CalcPage.formatToTwoDecimalPlaces(expectedResult);
        String formattedActualResult = calcPage.getResult();

        Assert.assertEquals(formattedActualResult, formattedExpectedResult);
    }

    @Test(priority = 2, dataProvider = "getSubtractionTestCases", dataProviderClass = DataProviders.class)
    public void testSubtractionOfTwoValues(double value1, double value2, double expectedResult) {

        calcPage.enterNumber(value1);
        calcPage.enterOperator("-");
        calcPage.enterNumber(value2);
        calcPage.enterOperator("=");

        String formattedExpectedResult = CalcPage.formatToTwoDecimalPlaces(expectedResult);
        String formattedActualResult = calcPage.getResult();

        Assert.assertEquals(formattedActualResult, formattedExpectedResult);
    }

    @Test(priority = 3, dataProvider = "getMultiplicationTestCases", dataProviderClass = DataProviders.class)
    public void testMultiplicationOfTwoValues(double value1, double value2, double expectedResult) {

        calcPage.enterNumber(value1);
        calcPage.enterOperator("*");
        calcPage.enterNumber(value2);
        calcPage.enterOperator("=");

        String formattedExpectedResult = CalcPage.formatToTwoDecimalPlaces(expectedResult);
        String formattedActualResult = calcPage.getResult();

        Assert.assertEquals(formattedActualResult, formattedExpectedResult);
    }

    @Test(priority = 4, dataProvider = "getDivisionTestCases", dataProviderClass = DataProviders.class)
    public void testDivisionOfTwoValues(double value1, double value2, double expectedResult) {

        calcPage.enterNumber(value1);
        calcPage.enterOperator("/");
        calcPage.enterNumber(value2);
        calcPage.enterOperator("=");

        String formattedExpectedResult = CalcPage.formatToTwoDecimalPlaces(expectedResult);
        String formattedActualResult = calcPage.getResult();

        Assert.assertEquals(formattedActualResult, formattedExpectedResult);
    }

    @Test(priority = 5, dataProvider = "getSquareRootTestCases", dataProviderClass = DataProviders.class)
    public void testSquareRootOfAValue(double value1, double expectedResult) {
        // Using square root symbol as char
        char squareRootSymbol = '√';

        calcPage.enterNumber(value1);
        calcPage.enterCharOperator(squareRootSymbol);

        String formattedExpectedResult = CalcPage.formatToTwoDecimalPlaces(expectedResult);
        String formattedActualResult = calcPage.getResult();

        Assert.assertEquals(formattedActualResult, formattedExpectedResult);
    }

}
