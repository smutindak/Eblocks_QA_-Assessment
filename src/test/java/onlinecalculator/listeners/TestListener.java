package onlinecalculator.listeners;


import com.eblocks.onlinecalculator.drivers.Config;
import onlinecalculator.utils.LogHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static com.eblocks.onlinecalculator.drivers.DriverManager.getDriver;

public class TestListener implements ITestListener {

    /**
     * This class listens to tests when they start, fail and succeed and the logic beneath is executed
     */
    @Override
    public void onTestStart(ITestResult result) {
        LogHelper.logInfo("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogHelper.logInfo("Test passed: " + result.getName());

        // Take a Screenshot on success
        takeScreenShot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogHelper.logError("Test failed: " + result.getName(), result.getThrowable());

        // Take a Screenshot on failure
        takeScreenShot(result);
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        LogHelper.logInfo("Test skipped: " + result.getName());
    }

    public void takeScreenShot(ITestResult result) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Get the test class name
        String testClassName = result.getTestClass().getName();

        // Get the package name from the test class name
        String packageName = testClassName.substring(0, testClassName.lastIndexOf(".")) + "_" + "ScreenShots";

        // Get the test method name
        String testMethodName = result.getMethod().getMethodName();

        // Construct the destination folder based on the package name
        String destFolder = Config.current_work_directory + File.separator + "screenshots"
                + File.separator + packageName.replace(".", File.separator);

        // Create the destination folder if it doesn't exist
        new File(destFolder).mkdirs();

        // Take a screenshot
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

        // Define the destination file path with the test name
        String destFilePath = destFolder + File.separator + testMethodName + "_screenshot.png";

        // Copy the screenshot to the destination path
        try {
            FileUtils.copyFile(src, new File(destFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
