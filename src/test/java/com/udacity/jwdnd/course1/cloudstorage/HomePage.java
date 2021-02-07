package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "newNote")
    private WebElement addNewNote;

    @FindBy(name = "noteTitle")
    private WebElement noteTitle;

    @FindBy(name = "noteDescription")
    private WebElement noteDescription;

    @FindBy(id = "noteSaveChanges")
    private WebElement noteSaveChanges;

    @FindBy(id = "noteClose")
    private WebElement noteClose;

    @FindBy(id = "displayNoteTitle")
    private WebElement displayNoteTitle;

    @FindBy(id = "displayNoteDescription")
    private WebElement displayNoteDescription;

    @FindBy(id = "editNote")
    private WebElement editNoteButton;

    @FindBy(id = "deleteNote")
    private WebElement deleteNoteButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "newCredential")
    private WebElement addNewCredential;

    @FindBy(id = "credential-url")
    private WebElement url;

    @FindBy(id = "credential-username")
    private WebElement username;

    @FindBy(id = "credential-password")
    private WebElement password;

    @FindBy(id = "credentialSaveChanges")
    private WebElement credentialSaveChanges;

    @FindBy(id = "urlDisplay")
    private WebElement urlDisplay;

    @FindBy(id = "usernameDisplay")
    private WebElement usernameDisplay;

    @FindBy(id = "passwordDisplay")
    private WebElement passwordDisplay;

    @FindBy(id = "editCredential")
    private WebElement editCredential;

    @FindBy(id = "deleteCredential")
    private WebElement deleteCredential;

    @FindBy(id = "Logout")
    private WebElement logout;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void clickNoteTab(){
        noteTab.click();
    }

    public void clickAddNewNoteButton(){
        addNewNote.click();
    }

    public void enterNoteTitle(String title){
        noteTitle.clear();
        noteTitle.sendKeys(title);
    }

    public void enterNoteDescription(String description){
        noteDescription.clear();
        noteDescription.sendKeys(description);
    }

    public void saveNoteChanges(){
        noteSaveChanges.click();
    }

    public String displayNoteTitle(){
            return displayNoteTitle.getText();
    }

    public String displayNoteDescription(){
            return displayNoteDescription.getText();
    }

    public void clickEditNoteButton(){
        editNoteButton.click();
    }

    public void clickDeleteNoteButton(){
        deleteNoteButton.click();
    }

    public void clickCredentialTab(){
        credentialTab.click();
    }

    public void clickAddNewCredentialButton(){
        addNewCredential.click();
    }

    public void enterUrl(String url){
        this.url.clear();
        this.url.sendKeys(url);
    }

    public void enterUsername(String username){
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void enterPassword(String password){
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void saveCredentialChanges(){
        credentialSaveChanges.click();
    }

    public String displayCredentialUrl(){
        return urlDisplay.getText();
    }

    public String displayCredentialUsername(){
        return usernameDisplay.getText();
    }

    public String displayCredentialPassword(){
       return passwordDisplay.getText();
    }

    public void clickEditCredentialButton(){
        editCredential.click();
    }

    public void clickDeleteCredentialButton(){
        deleteCredential.click();
    }

    public void logoutHome(){
        logout.click();
    }
}
