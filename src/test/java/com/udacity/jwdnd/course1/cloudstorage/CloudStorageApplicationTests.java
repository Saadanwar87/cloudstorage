package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	HomePage homePage;
	LoginPage loginPage;
	SignupPage signupPage;
	ResultPage resultPage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		signupPage = new SignupPage(driver);
		resultPage = new ResultPage(driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void signupAndLogin() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		loginPage.signupLink();
		Thread.sleep(2000);
		signupPage.inputFirstName("me");
		signupPage.inputLastName("me");
		signupPage.inputUsername("me");
		signupPage.inputPassword("me");
		signupPage.signupButton();
		Thread.sleep(1000);
		signupPage.loginPage();
		Thread.sleep(1000);
		loginPage.inputUsername("me");
		loginPage.inputPassword("me");
		loginPage.loginButton();
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	public void logout() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		loginPage.inputUsername("me");
		loginPage.inputPassword("me");
		loginPage.loginButton();
		Thread.sleep(1000);
		homePage.logoutHome();
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void creatingNote() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		loginPage.inputUsername("me");
		loginPage.inputPassword("me");
		loginPage.loginButton();
		Thread.sleep(1000);
		homePage.clickNoteTab();
		Thread.sleep(1000);
		homePage.clickAddNewNoteButton();
		Thread.sleep(1000);
		homePage.enterNoteTitle("SampleTitle");
		homePage.enterNoteDescription("This is a sample description.");
		homePage.saveNoteChanges();
		Thread.sleep(1000);
		resultPage.homeLink();
		Thread.sleep(1000);
		homePage.clickNoteTab();
		Thread.sleep(1000);
		Assertions.assertEquals("SampleTitle", homePage.displayNoteTitle());
		Assertions.assertEquals("This is a sample description.", homePage.displayNoteDescription());
	}

	@Test
	public void editNote() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		loginPage.inputUsername("me");
		loginPage.inputPassword("me");
		loginPage.loginButton();
		Thread.sleep(1000);
		homePage.clickNoteTab();
		Thread.sleep(1000);
		homePage.clickEditNoteButton();
		Thread.sleep(1000);
		homePage.enterNoteTitle("editedSampleTitle");
		homePage.enterNoteDescription("This is edited sample description");
		homePage.saveNoteChanges();
		Thread.sleep(1000);
		resultPage.homeLink();
		Thread.sleep(1000);
		homePage.clickNoteTab();
		Thread.sleep(1000);
		Assertions.assertEquals("editedSampleTitle", homePage.displayNoteTitle());
		Assertions.assertEquals("This is edited sample description", homePage.displayNoteDescription());
	}

	@Test
	public void deleteNote() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		loginPage.inputUsername("me");
		loginPage.inputPassword("me");
		loginPage.loginButton();
		Thread.sleep(1000);
		homePage.clickNoteTab();
		Thread.sleep(1000);
		homePage.clickDeleteNoteButton();
		Thread.sleep(1000);
		resultPage.homeLink();
		Thread.sleep(1000);
		homePage.clickNoteTab();
		Thread.sleep(1000);
		Assertions.assertThrows(NoSuchElementException.class, ()-> {
				homePage.displayNoteTitle();
    });
		Assertions.assertThrows(NoSuchElementException.class, ()->{
			homePage.displayNoteDescription();
		});
	}

	@Test
	public void creatingCredential() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		loginPage.inputUsername("me");
		loginPage.inputPassword("me");
		loginPage.loginButton();
		Thread.sleep(1000);
		homePage.clickCredentialTab();
		Thread.sleep(1000);
		homePage.clickAddNewCredentialButton();
		Thread.sleep(1000);
		homePage.enterUrl("testUrl");
		homePage.enterUsername("testUsername");
		homePage.enterPassword("testPassword");
		homePage.saveCredentialChanges();
		Thread.sleep(1000);
		resultPage.homeLink();
		Thread.sleep(1000);
		homePage.clickCredentialTab();
		Thread.sleep(1000);
		Assertions.assertEquals("testUrl", homePage.displayCredentialUrl());
		Assertions.assertEquals("testUsername", homePage.displayCredentialUsername());
		Assertions.assertEquals("testPassword", homePage.displayCredentialPassword());
	}

	@Test
	public void editCredential() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		loginPage.inputUsername("me");
		loginPage.inputPassword("me");
		loginPage.loginButton();
		Thread.sleep(1000);
		homePage.clickCredentialTab();
		Thread.sleep(1000);
		homePage.clickEditCredentialButton();
		Thread.sleep(1000);
		homePage.enterUrl("editedUrl");
		homePage.enterUsername("editedUsername");
		homePage.enterPassword("editedPassword");
		homePage.saveCredentialChanges();
		Thread.sleep(1000);
		resultPage.homeLink();
		Thread.sleep(1000);
		homePage.clickCredentialTab();
		Thread.sleep(1000);
		Assertions.assertEquals("editedUrl", homePage.displayCredentialUrl());
		Assertions.assertEquals("editedUsername", homePage.displayCredentialUsername());
		Assertions.assertEquals("editedPassword", homePage.displayCredentialPassword());
	}

	@Test
	public void deleteCredential() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		loginPage.inputUsername("me");
		loginPage.inputPassword("me");
		loginPage.loginButton();
		Thread.sleep(1000);
		homePage.clickCredentialTab();
		Thread.sleep(1000);
		homePage.clickDeleteCredentialButton();
		Thread.sleep(1000);
		resultPage.homeLink();
		Thread.sleep(1000);
		homePage.clickCredentialTab();
		Thread.sleep(1000);
		Assertions.assertThrows(NoSuchElementException.class, ()->{
			homePage.displayCredentialUrl();
		});

		Assertions.assertThrows(NoSuchElementException.class, ()->{
			homePage.displayCredentialUsername();
		});

		Assertions.assertThrows(NoSuchElementException.class, ()->{
			homePage.displayCredentialPassword();
		});
	}

}
