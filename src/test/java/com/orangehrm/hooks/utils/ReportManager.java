package com.orangehrm.hooks.utils;

public class ReportManager {

    public static void info(String message) {
    	if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().info(message);
        } else {
            System.out.println("ExtentTest is null: " + message);
        }
    }

    public static void pass(String message) {
    	if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().pass(message);
        } else {
            System.out.println("ExtentTest is null: " + message);
        }
    }

    public static void fail(String message) {
    	if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().fail(message);
        } else {
            System.out.println("ExtentTest is null: " + message);
        }
    }
}
