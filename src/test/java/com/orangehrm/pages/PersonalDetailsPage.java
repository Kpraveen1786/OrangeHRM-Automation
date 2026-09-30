package com.orangehrm.pages;

import org.openqa.selenium.By;

public class PersonalDetailsPage {

    //================ Profile ==================

    public By profileImage = By.cssSelector("img.employee-image");

    public By employeeFullName =
            By.xpath("(//input[@class='oxd-input oxd-input--active'])[1]");

    public By middleName =
            By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");

    public By lastName =
            By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");

    public By employeeId =
            By.xpath("(//label[text()='Employee Id']/../following-sibling::div/input)");

    public By otherId =
            By.xpath("//label[text()='Other Id']/../following-sibling::div/input");

    public By driversLicense =
            By.xpath("//label[contains(text(),'Driver')]/../following-sibling::div/input");

    public By licenseExpiry =
            By.xpath("//label[contains(text(),'License Expiry')]/../following-sibling::div//input");

    //================ Personal Details ==================

    public By nationalityDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");

    public By maritalStatusDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-text')])[2]");

    public By dob =
            By.xpath("//label[text()='Date of Birth']/../following-sibling::div//input");

    public By genderMale =
            By.xpath("//label[text()='Male']/preceding-sibling::input");

    public By genderFemale =
            By.xpath("//label[text()='Female']/preceding-sibling::input");

    //================ Save Buttons ==================

    public By firstSaveButton =
            By.xpath("(//button[@type='submit'])[1]");

    public By secondSaveButton =
            By.xpath("(//button[@type='submit'])[2]");

    //================ Attachments ==================

    public By addAttachment =
            By.xpath("//button[normalize-space()='Add']");

    public By browseFile =
            By.xpath("//input[@type='file']");

    public By comment =
            By.xpath("//textarea");

    public By uploadButton =
            By.xpath("//button[@type='submit']");

    //================ Toast ==================

    public By successMessage =
            By.xpath("//div[contains(@class,'oxd-toast-content')]");
}