package com.lambdatest.util;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lambdatest.baselayer.BrowserDriver;

public class TestUtil {

	static WebDriver driver;
	
	public static void hoverMenuItem(WebElement webElement) {
		driver = BrowserDriver.getWebDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).build().perform();
	}
	
	
	static String baseDirectoryProject = System.getProperty("user.dir");
	static String filePath = baseDirectoryProject + "\\src\\test\\resources\\TestDataRegistrationPage.xlsx";

	public static Object[][] readTestData(String sheetName) {
		
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			Workbook workbook =  WorkbookFactory.create(fileInputStream);
			Sheet sheet =  workbook.getSheet(sheetName);
			
			int columnSize = sheet.getRow(0).getLastCellNum();
			int rowSize = sheet.getLastRowNum();
			
			
			Object[][] data = new Object[rowSize][columnSize];
			
			for(int i=1; i<= rowSize;i++) {
				for(int j=0;j<columnSize;j++) {
					data[i-1][j] = sheet.getRow(i).getCell(j).toString();
				}
			}
			
			return data;
			
		}catch(Exception e) {
			
		}
		
		return null;
		
	}
	
	public static <T> T waitExplicit(WebDriver webDriver, int timout, ExpectedCondition<T> condition) {
		WebDriverWait driverWait = new WebDriverWait(webDriver,Duration.ofSeconds(timout));
		return driverWait.until(condition);
	
	}
	
	
	
	public  static WebElement waitForFluent(WebDriver webDriver, int maxWaitTime, int frequncyTime, final By locator) {
		
		FluentWait<WebDriver> fluentWait = new FluentWait<>(webDriver)
				.withTimeout(Duration.ofSeconds(maxWaitTime))// maximum time to wait
				.pollingEvery(Duration.ofSeconds(frequncyTime))// frequence of check 
				.ignoring(NoSuchElementException.class);
				
			 return fluentWait.until(new Function<WebDriver, WebElement>() {

				 public WebElement apply(WebDriver webDriver) {
					 return webDriver.findElement(locator);
					}
			 });
	}
	
}
