package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id ="inputUsername")
    private WebElement inputUsername;

    @FindBy(id ="inputPassword")
    private WebElement inputPassword;

    @FindBy(tagName = "button")
    private WebElement loginButton;

    @FindBy(linkText = "Click here to sign up")
    private WebElement signupLink;

    @FindBy(className = "alert alert-danger")
    private WebElement loginErrorMessage;

    @FindBy(className = "alert alert-dark")
    private WebElement logoutMessage;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void inputUsername(String username){
        inputUsername.sendKeys(username);
    }

    public void inputPassword(String password){
        inputPassword.sendKeys(password);
    }

    public void loginButton(){
        loginButton.click();
    }

    public void signupLink(){
        signupLink.click();
    }

    public String loginErrorMessage(){
        return loginErrorMessage.getText();
    }

    public String logoutMessage(){
        return logoutMessage.getText();
    }
}
