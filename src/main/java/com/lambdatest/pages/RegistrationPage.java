package com.lambdatest.pages;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.lambdatest.baselayer.BrowserDriver;
import com.lambdatest.util.TestUtil;

public class RegistrationPage {

	WebDriver webDriver;
	
	@FindBy(tagName = "h1")
	private WebElement webElmH1Reg;
	
	@FindBy(css = "div#content>p")
	private WebElement webElmPReg;
	
	
	@FindBy(css = "fieldset#account>legend")
	private WebElement webElmfieldSet;
	
	
	@FindBy(css = "label[for='input-firstname']")
	private WebElement webElmLblFirstName;
	
	
	@FindBy(id = "input-firstname")
	private WebElement webElmTxtFirstName;
	
	
	@FindBy(id="input-lastname")
	private WebElement webElmLastName;
	
	@FindBy(id = "input-email")
	private WebElement webElmEmail;
	
	
	
	@FindBy(css = "input[value='Continue']")
	private WebElement webElmBtnContinue;
	
	@FindBy(xpath = "//div[contains(@class,'alert alert-danger')]")
	private WebElement webElmWarning;
	
	@FindBy(css = "label[for='input-agree']")
	private WebElement webElmCheckBoxPrivacyPolicy;
	
	
	public RegistrationPage() {
		webDriver = BrowserDriver.getWebDriver();
		PageFactory.initElements(webDriver, this);
	}
	
	public String getTitleOfRegisterPage() {
		return webDriver.getTitle();
	}
	
	public boolean checkH1Present() {
		//explicit wait
		return TestUtil.waitExplicit(webDriver,  2, ExpectedConditions.visibilityOf(webElmH1Reg)).isDisplayed();
	}
	
	public boolean checkWebElmPRegPresent() {
		
	 	return TestUtil.waitForFluent(webDriver,30,3,By.cssSelector("div#content>p")).isDisplayed();
	}
	
	public boolean checkWebElmfieldSetPresent() {
		return webElmfieldSet.isDisplayed();
	}
	
	public String getTextWebElmH1Reg() {
		return webElmH1Reg.getText();
	}
	
	public String getTexWebElmLblFirstName() {
		return webElmLblFirstName.getText();
	}
	
	public void clickContinueBtn() {
		webElmBtnContinue.click();
	}
	
	public boolean checkWebElmWarningPresent() {
		try {
			webElmWarning.isDisplayed();
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	
	}
	
	public String getWebElmWarningMessage() {
		return webElmWarning.getText();
	}
	
	public void clickWebElmCheckBoxPrivacyPolicy() {
		webElmCheckBoxPrivacyPolicy.click();
	}
	
	public void enterFirstName(String firstName) {
		webElmTxtFirstName.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		webElmLastName.sendKeys(lastName);
	}
	
	public void enterEmail(String emil) {
		webElmEmail.sendKeys(emil);
	}
	
	
	
}
