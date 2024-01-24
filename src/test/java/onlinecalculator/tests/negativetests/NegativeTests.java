package onlinecalculator.tests.negativetests;

import onlinecalculator.base.BaseSuiteSetup;
import onlinecalculator.pages.CalcPage;
import onlinecalculator.testdata.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.eblocks.onlinecalculator.drivers.DriverManager.getDriver;

public class NegativeTests extends BaseSuiteSetup {
    protected CalcPage calcPage;

    @BeforeMethod
    public void setupTest() {
        calcPage = new CalcPage(getDriver());

        getDriver().get("https://www.online-calculator.com/html5/online-calculator/index.php?v=10");
    }

    @Test(priority = 1,dataProvider = "getDivideByZeroTestCases", dataProviderClass = DataProviders.class)
    public void testDivideByZeroError(double value1, double value2) {

        calcPage.enterNumber(value1);
        calcPage.enterOperator("/");
        calcPage.enterNumber(value2);
        calcPage.enterOperator("=");

        String formattedActualResult = calcPage.getResultNonFormatted();

        Assert.assertEquals(formattedActualResult, "Error");
    }

    @Test(priority = 2,dataProvider = "getSquareRootErrorTestCases", dataProviderClass = DataProviders.class)
    public void testSquareRootOfANegativeNumber(double value1) {
        // Using square root symbol as char
        char squareRootSymbol = '√';

        calcPage.enterNumber(value1);
        calcPage.enterOperator("=");
        calcPage.enterCharOperator(squareRootSymbol);

        String formattedActualResult = calcPage.getResultNonFormatted();

        Assert.assertEquals(formattedActualResult, "Error");
    }
}
