package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(tagName = "button")
    private WebElement signupButton;

    @FindBy(id = "loginLink")
    private WebElement loginPage;

    @FindBy(tagName = "span")
    private WebElement signupMessage;

    public SignupPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void inputFirstName(String name){
        inputFirstName.sendKeys(name);
    }

    public void inputLastName(String name){
        inputLastName.sendKeys(name);
    }

    public void inputUsername(String username){
        inputUsername.sendKeys(username);
    }

    public void inputPassword(String password){
        inputPassword.sendKeys(password);
    }

    public void signupButton(){
        signupButton.click();
    }

    public void loginPage(){
        loginPage.click();
    }

    public String signupMessage(){
        return signupMessage.getText();
    }


}
