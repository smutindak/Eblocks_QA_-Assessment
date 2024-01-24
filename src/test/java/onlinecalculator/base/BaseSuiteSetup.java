package onlinecalculator.base;

import com.eblocks.onlinecalculator.enums.Browsers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static com.eblocks.onlinecalculator.drivers.DriverManager.*;

public class BaseSuiteSetup {

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setupTest(final String browser) {
        createDriver(Browsers.valueOf(browser.toUpperCase()));
        getDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        quitDriver();
    }
}
