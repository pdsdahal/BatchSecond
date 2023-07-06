package com.lambdatest.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lambdatest.baselayer.BrowserDriver;
import com.lambdatest.pages.HeaderPage;

public class HeaderPageTest {
	
	private HeaderPage headerPage;
	
	
	@BeforeMethod
	public void setUp() {
		BrowserDriver.start();
		headerPage = new HeaderPage();
	}
	
	
	@Test(priority = 1)
	public void VerifyAllTheHeaderWebElementArePresent() {
		Assert.assertEquals(headerPage.homeElmentPresentCheck(), true, "Home Menu should present in the browser.");
		Assert.assertEquals(headerPage.SpecialHotPresentCheck(), true, "Special Hot Menu should present in the browser.");
		Assert.assertEquals(headerPage.blogPresentCheck(), true, "Blog menu should present in the browser.");
	
	}
	
	
	@Test(priority = 2)
	public void verifyAllTheContentOfHeadersMenus() {
		
		Assert.assertEquals(headerPage.getTextHomeMenu(),"Home", "Text value for the Home menu is not as expected.");
		Assert.assertEquals(headerPage.getTextSpecial(),"Special\nHot", "Text value for the Special menu is not as expected.");
		Assert.assertEquals(headerPage.getTextAccountMenu(),"My account", "Text value for the My account is not as expected.");
	}
	
	
	@Test(priority = 3)
	public void validateMouseHoverOverMyAccountMenu() {
		
		Assert.assertEquals(headerPage.myAccountMenuPresentCheck(),true, "My Account menu should preset.");
		headerPage.hoverOverMyAccountMenu();
		Assert.assertEquals(headerPage.loginMenuPresentCheck(),true, "Login menu should preset.");
		Assert.assertEquals(headerPage.registerMenuPresentCheck(),true, "Register menu should preset.");
	}
	
	@Test(priority = 4)
	public void verifyRegisterMenuFunctionality() {
		headerPage.hoverOverMyAccountMenu();
		headerPage.clickRegisterMenu();
		Assert.assertEquals(headerPage.getTitleOfRegisterPage(), "Register Account", "Register Title should matched");
		
	}
	
	
	
	
	@AfterMethod
	public void tearDown() {
		BrowserDriver.close();
	}
	
	
	
	

}
