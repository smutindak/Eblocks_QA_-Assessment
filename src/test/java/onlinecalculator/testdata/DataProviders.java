package onlinecalculator.testdata;

import onlinecalculator.utils.DataReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider
    public static Object[][] getAdditionTestCases() throws IOException {
        DataReader reader = new DataReader("src/test/resources/testData.xls", "Addition");
        return reader.readExcel();
    }

    @DataProvider
    public static Object[][] getSubtractionTestCases() throws IOException {
        DataReader reader = new DataReader("src/test/resources/testData.xls", "Subtraction");
        return reader.readExcel();
    }
    @DataProvider
    public static Object[][] getMultiplicationTestCases() throws IOException {
        DataReader reader = new DataReader("src/test/resources/testData.xls", "Multiplication");
        return reader.readExcel();
    }
    @DataProvider
    public static Object[][] getDivisionTestCases() throws IOException {
        DataReader reader = new DataReader("src/test/resources/testData.xls", "Division");
        return reader.readExcel();
    }
    @DataProvider
    public static Object[][] getDivideByZeroTestCases() throws IOException {
        DataReader reader = new DataReader("src/test/resources/testData.xls", "DivideByZero");
        return reader.readExcel();
    }
    @DataProvider
    public static Object[][] getSquareRootErrorTestCases() throws IOException {
        DataReader reader = new DataReader("src/test/resources/testData.xls", "SquareNegativeNumber");
        return reader.readExcel();
    }
    @DataProvider
    public static Object[][] getSquareRootTestCases() throws IOException {
        DataReader reader = new DataReader("src/test/resources/testData.xls", "SquareRoot");
        return reader.readExcel();
    }
}
