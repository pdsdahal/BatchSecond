package com.lambdatest.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lambdatest.baselayer.BrowserDriver;
import com.lambdatest.pages.HeaderPage;
import com.lambdatest.pages.RegistrationPage;
import com.lambdatest.util.TestUtil;

public class RegistrationPageTest {


	private HeaderPage headerPage;
	
	RegistrationPage registrationPage;
	
	@BeforeMethod
	public void setUp() {
		BrowserDriver.start();
		headerPage = new HeaderPage();
		headerPage.hoverOverMyAccountMenu();
		registrationPage = headerPage.clickRegisterMenu();
	}
	
	
	@Test(priority = 1)
	public void verifyTitleOfRegistationPage() {
		Assert.assertEquals(registrationPage.getTitleOfRegisterPage(),"Register Account", "Register Title is not expected.");
	}
	
	
	@Test(priority = 2)
	public void verifyAllTheWebElementsAreDisplay() {
		Assert.assertEquals(registrationPage.checkH1Present(), true, "H1 tag of Registration should displayed.");
		Assert.assertEquals(registrationPage.checkWebElmPRegPresent(), true, "Paragaraph tag of Registration page should displayed.");
		Assert.assertEquals(registrationPage.checkWebElmfieldSetPresent(), true, "FieldSet of Registration page should displayed.");
	}
	
	
	@Test(priority = 3)
	public void verifyAllTheContentOfRegister() {
		Assert.assertEquals(registrationPage.getTextWebElmH1Reg(), "Register Account", "Content of H1 tag is not as expected.");
		Assert.assertEquals(registrationPage.getTexWebElmLblFirstName(), "First Name", "Content of FirstName label is not as expected.");
	}
	
	
	@Test(priority = 4)
	public void verifyRegisterWithoutEnteringAnyValues() {
		registrationPage.clickContinueBtn();
		Assert.assertEquals(registrationPage.checkWebElmWarningPresent(), true, "Warning message should present");
		Assert.assertEquals(registrationPage.getWebElmWarningMessage(), "Warning: You must agree to the Privacy Policy!", "Warning message is not as expected");
	}
	
	@Test(priority = 5)
	public void verifyPrivacyPolicyWithAgreeWithoutEnteringAnyValues() {
		
		registrationPage.clickWebElmCheckBoxPrivacyPolicy();
		registrationPage.clickContinueBtn();
		Assert.assertEquals(registrationPage.checkWebElmWarningPresent(), false, "Warning message should not present");
	}
	
	
	@DataProvider
	public Object[][] getValidTestDataForRegistration(){
		Object[][] testdata  = TestUtil.readTestData("vaidtestdata");
		return testdata;
	}
	
	
	@Test(priority = 6, dataProvider = "getValidTestDataForRegistration")
	public void verifyRegistweWithEnteringValidUserInformation(String firstname, String lastname, String email, String telephone, String password, String conpassword, String subscribe) {
		registrationPage.enterFirstName(firstname);
		registrationPage.enterLastName(lastname);
		registrationPage.enterEmail(email);
	}
	
	
	@AfterMethod
	public void tearDown() {
		BrowserDriver.close();
	}
}
