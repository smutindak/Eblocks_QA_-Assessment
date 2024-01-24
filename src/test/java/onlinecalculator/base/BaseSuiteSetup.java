package onlinecalculator.base;

import com.eblocks.onlinecalculator.drivers.Config;
import com.eblocks.onlinecalculator.enums.Browsers;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

import static com.eblocks.onlinecalculator.drivers.DriverManager.*;

public class BaseSuiteSetup {

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setupTest(@Optional("firefox") final String browser) {
        createDriver(Browsers.valueOf(browser.toUpperCase()));
        getDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        quitDriver();
    }
}
