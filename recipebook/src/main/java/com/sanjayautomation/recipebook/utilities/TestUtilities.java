package com.sanjayautomation.recipebook.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sanjayautomation.recipebook.baseclass.BaseSetup;

public class TestUtilities extends BaseSetup {
	
	public static String EXCEL_SHEET_PATH = "/Users/sanjaygurung/git/Selenium-Test-Automation---Recipe-Book/POMFramework/src/main/testdata/TestData_New_Recipes.xlsx";
	public static Workbook book;
	
	
	// Function to take screenshot. This function should be called in the event listener method. Eg: It is being called in TestNGListerner's onTestFailure().
	public static void takeScreenshot(String testName) throws IOException {
		TakesScreenshot srcShot = ((TakesScreenshot) driver);  //converting webdriver object to TakesScreenshot
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		String dateTime = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
		FileUtils.copyFile(srcFile, new File(currentDir + "/screenshots/" + testName + dateTime + ".png"));
	}
	
	
	// Function to read data from Excel file
	public static Object[][] getTestDataFromExcel(String sheetName) throws Exception {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(EXCEL_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("Test excel sheet file not found" + e.getStackTrace());
		}
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		int totalNumberofRows = sheet.getLastRowNum();
		int totalNumberofColumn = sheet.getRow(0).getLastCellNum();
		
		String [][] arrayExcelData = new String [totalNumberofRows][totalNumberofColumn];
		
		for(int i = 0; i<totalNumberofRows; i++) {
			for(int j=0; j<totalNumberofColumn; j++) {
				XSSFRow row = sheet.getRow(i+1);
				arrayExcelData[i][j] = row.getCell(j).toString();
			}
		}
		wb.close();
		return arrayExcelData;
	}
	
	
	// Explicit wait for element to be visible
	public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	
	
	
}
