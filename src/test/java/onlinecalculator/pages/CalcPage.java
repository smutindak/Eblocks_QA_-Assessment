package onlinecalculator.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Locale;

import static com.eblocks.onlinecalculator.drivers.DriverManager.getDriver;

public class CalcPage {

    private final JavascriptExecutor js;

    public CalcPage(WebDriver driver) {
        this.js = (JavascriptExecutor) getDriver();
        PageFactory.initElements(getDriver(), this);
    }

    public void switchToCalculatorFrame() {
        getDriver().switchTo().frame("fullframe");
    }

    public void enterNumber(double number) {
        executeKeyPressEvent(String.valueOf(number));
    }

    public void enterOperator(String operator) {
        executeKeyPressEvent(operator);
    }
    public void enterCharOperator(char operator) {
        executeKeyPressEvent(String.valueOf(operator));
    }

    public String getResult() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String result = (String) js.executeScript("return exportRoot.showscreen_txt.text;");

        // Remove spaces from the result
        result = result.replaceAll("\\s+", "");

        // Convert the result to a double and format it with two decimal places
        try {
            double doubleResult = Double.parseDouble(result);
            result = String.format(Locale.US, "%.2f", doubleResult);
        } catch (NumberFormatException e) {
            // Handle the case where the result is not a valid number
            e.printStackTrace(); // or log the exception
        }

        return result;
    }
    public String getResultNonFormatted() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return exportRoot.showscreen_txt.text;");
    }

    public static String formatToTwoDecimalPlaces(double value) {
        return String.format(Locale.US, "%.2f", value);
    }

    private void executeKeyPressEvent(String value) {
        for (char digit : value.toCharArray()) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript(
                    String.format("document.getElementById('canvas').dispatchEvent(new KeyboardEvent('keypress',{which:%s,keyCode:%s,bubbles:true}))",
                            (int) digit, (int) digit));
        }
    }
}