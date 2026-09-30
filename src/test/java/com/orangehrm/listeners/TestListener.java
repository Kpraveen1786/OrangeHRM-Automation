package com.orangehrm.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class TestListener implements ITestListener, IAnnotationTransformer {

	private static final Logger logger = LogManager.getLogger(TestListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		
		logger.info("Test Started : " + result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		logger.info("Test Passed : " + result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.error("Test Failed : " + result.getName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn("Test Skipped : " + result.getName());

	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		annotation.setRetryAnalyzer(com.orangehrm.listeners.RetryAnalyzer.class);
	}

}