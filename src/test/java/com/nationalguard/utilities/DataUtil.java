package com.nationalguard.utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.nationalguard.base.BaseTest;


public class DataUtil extends BaseTest {

	@DataProvider(name = "data")
	public static Object[][] getData(Method m) {
		
		String sheet = prop.getProperty("testDataSheetName");

		int rows = excel.getRowCount(sheet);

		System.out.println("Total rows are : " + rows);

		String testName = m.getName();
		System.out.println("Test name is : " + testName);

		// Find the test case start row

		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(sheet, 0, testCaseRowNum);

			if (testCaseName.equalsIgnoreCase(testName))
				break;

		}

		System.out.println("Test case starts from row num: " + testCaseRowNum);

		// Checking total rows in test case

		int dataStartRowNum = testCaseRowNum + 2;

		int testRows = 0;
		while (!excel.getCellData(sheet, 0, dataStartRowNum + testRows).equals("")) {

			testRows++;
		}

		System.out.println("Total rows of data are : " + testRows);

		// Checking total cols in test case

		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;

		while (!excel.getCellData(sheet, testCols, colStartColNum).equals("")) {

			testCols++;

		}

		System.out.println("Total cols are : " + testCols);

		// Printing data

		Object[][] data = new Object[testRows][1];

		int i = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

			Hashtable<String, String> table = new Hashtable<String, String>();

			for (int cNum = 0; cNum < testCols; cNum++) {

				// System.out.println(excel.getCellData(config.getProperty("testDataSheetName"),
				// cNum, rNum));
				String testData = excel.getCellData(sheet, cNum, rNum);
				String colName = excel.getCellData(sheet, cNum, colStartColNum);

				table.put(colName, testData);

			}

			data[i][0] = table;
			i++;

		}

		return data;
	}
	
	public static boolean isRunnable(String testName){
		String sheet="Testcases";
		int rows = excel.getRowCount(sheet);		
		for(int r=2;r<=rows;r++){
			String tName=excel.getCellData(sheet, "TCID", r);
			//String testName = m.getName();
			if(tName.equals(testName)){
				String runmode=excel.getCellData(sheet, "Runmode", r);
				if(runmode.equals("Y"))
					return true;
				else
					return false;

			}
		}
		return false;
	}


}
