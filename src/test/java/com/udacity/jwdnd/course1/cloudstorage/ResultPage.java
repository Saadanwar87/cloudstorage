package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(tagName = "a")
    private WebElement homeLink;

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void homeLink(){
        homeLink.click();
    }
}
