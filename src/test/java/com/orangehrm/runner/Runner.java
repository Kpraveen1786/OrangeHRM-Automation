package com.orangehrm.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = "src/test/resources/features",

		glue = { "com.orangehrm.stepdefinitions", "com.orangehrm.hooks" },

		plugin = { "pretty",

				"html:target/cucumber-report.html",

				"json:target/cucumber.json",

				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },

		monochrome = true,

		publish = false)

public class Runner extends AbstractTestNGCucumberTests {

}