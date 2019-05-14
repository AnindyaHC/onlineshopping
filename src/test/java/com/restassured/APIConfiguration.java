package com.restassured;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class APIConfiguration {
	
	protected ExtentReports extent;
	public static RequestSpecification request;
	protected static Response response;

	@BeforeSuite(alwaysRun=true)
	public void configure() {
		
		RestAssured.baseURI="http://localhost/";
		RestAssured.port=8080;
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		extent = new ExtentReports (System.getProperty("user.dir") +"\\test-output\\" + new Date() + "\\TestAPI" + fileName + ".html", true);
		  //extent = new ExtentReports (System.getProperty("user.dir") +"\\test-output\\HSBCLedgerMigration\\TestAPI.html", true);
		}
	
	public static RequestSpecification getReqSpecification() {
		return RestAssured.given().contentType(ContentType.JSON);		
	}

}
