package com.nationalguard.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.nationalguard.base.BaseTest;
import com.nationalguard.utilities.DataUtil;

public class GetStartedFormTest extends BaseTest {
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void GetStartedForm(Hashtable<String, String> data) throws IOException {
		
		ExtentTest test = BaseTest.getLog();
		
		if (!DataUtil.isRunnable("loginValid") || data.get("Runmode").equals("N")) {
			
			
				test.log(Status.SKIP, "Test skipped since rumode is N");
				throw new SkipException("Test skipped since rumode is N");

			}	
		
		test.log(Status.INFO, "Opening a browser "+data.get("Browser"));
		// opening chrome browser
		openBrowser(data.get("Browser"));
		// navigate to given url
		test.log(Status.INFO, "Navigate to "+prop.getProperty("appurl"));
		navigate("appurl");
		// click on the get startd button
		test.log(Status.INFO, "Click on get started  "+prop.getProperty("getstartedbutton_name"));
		click("getstartedbutton_name");
		// type data to the fields from excel database
		test.log(Status.INFO, "Typing data on fields ");
		type("firstname_name", data.get("FirstName"));
		type("lastname_name", data.get("LastName"));
		type("email_name", data.get("Email"));
		type("phone_name", data.get("PhoneNumber"));
		type("zip_name", data.get("Zipcode"));
		// click
		click("submit_name");
		// data input and click
		type("birthdate_name", data.get("Birthdate"));
		click("submit_name");
		// phone
		type("phone_name", data.get("PhoneNumber"));
		click("submit_name");
		// gender selection
		click("gender_xpath");
		click("submit_name");

		// Military Experience
		click("militaryexperience_xpath");
		click("submit_name");
		// diploma
		click("diploma_xpath");
		click("submit_name");
		// college
		click("college_xpath");
		click("submit_name");
		// college degree
		click("collegedegree_xpath");
		click("submit_name");
		// select height
		select("heightfeet_xpath", "5");
		select("heightinches_xpath", "10");
		click("submit_name");
		// weight
		click("weight_xpath");
		click("submit_name");
		// motivation
		click("motivation_xpath");
		click("submit_name");
		// final submit
		test.log(Status.INFO, "Form submitted after review." );
		click("submit_name");
		// check recruiter is present and print it with zipcode
		String recruiterName = verifyTextPresent("recruitername_xpath");
		System.out.println("The Recruiter appointed on Zipcode "+data.get("Zipcode")+" is " +recruiterName);

}
}
