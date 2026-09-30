package com.orangehrm.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.orangehrm.hooks.utils.ReportManager;

public class RetryAnalyzer implements IRetryAnalyzer {
	private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);

	private int retryCount = 0;

	private static final int MAX_RETRY_COUNT = 2;

	@Override
	public boolean retry(ITestResult result) {
		ReportManager.info("Retry count "+retryCount+"    "+result.getName()+" Test Name : "+result.getTestName());
		logger.info("Retry count :"+retryCount);
		if (retryCount < MAX_RETRY_COUNT) {

			retryCount++;

			return true;
		}

		return false;
	}

}