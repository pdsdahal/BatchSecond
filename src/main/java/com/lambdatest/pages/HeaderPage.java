package com.lambdatest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.lambdatest.baselayer.BrowserDriver;
import com.lambdatest.util.TestUtil;

public class HeaderPage {

	
	private WebDriver webDriver;
	
	
	//by defining in one single line as a single parameter
	@FindBy(linkText = "Home")
	private WebElement webElmHomeMenu;
	
	//by defining in one single line as a multiple parameters
	@FindBy(how=How.PARTIAL_LINK_TEXT, using="Special")
	private WebElement webElmSpecialHotMenu;
	
	@FindBy(linkText = "Blog")
	private WebElement webElmBlogMenu;
	
	@FindBy(how=How.LINK_TEXT, using = "My account")
	private WebElement webElmMyAccountMenu;
	
	
	@FindBy(xpath = "(//input[@name='search'])[1]")
	private WebElement webElmSearchProduct;
	
	@FindBy(xpath = "(//button[@data-toggle='dropdown'])[1]")
	private WebElement webElmAllCategories;
	
	@FindBy(linkText = "Login")
	private WebElement webElmLogin;

	@FindBy(linkText = "Register")
	private WebElement webElmRegister;
	
	
	
	public HeaderPage() {
		webDriver = BrowserDriver.getWebDriver();
		PageFactory.initElements(webDriver, this);
	}
	
	public boolean homeElmentPresentCheck() {
		return webElmHomeMenu.isDisplayed();
	}
	
	public boolean SpecialHotPresentCheck() {
		return webElmSpecialHotMenu.isDisplayed();
	}
	
	
	public boolean blogPresentCheck() {
		return webElmBlogMenu.isDisplayed();
	}
	
	
	public boolean myAccountMenuPresentCheck() {
		return webElmMyAccountMenu.isDisplayed();
	}
	
	
	public String getTextHomeMenu() {
		return webElmHomeMenu.getText();
	}
	
	public String getTextSpecial() {
		return	webElmSpecialHotMenu.getText();
	}
	
	public String getTextAccountMenu() {
		return webElmMyAccountMenu.getText();
	}
	
	public void hoverOverMyAccountMenu() {
		TestUtil.hoverMenuItem(webElmMyAccountMenu);
	}
	
	
	public boolean loginMenuPresentCheck(){
		return webElmLogin.isDisplayed();
	}
	
	public boolean registerMenuPresentCheck(){
		return webElmRegister.isDisplayed();
	}
	
	public RegistrationPage clickRegisterMenu() {
		 webElmRegister.click();
		 return new RegistrationPage();
	}
	
	public String getTitleOfRegisterPage() {
		return webDriver.getTitle();
	}
	
	
}
