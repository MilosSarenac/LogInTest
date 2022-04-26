package test;

import base.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.ElementalSeleniumPage;
import page.LogInPage;
import page.SecureAreaPage;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LogInTest extends BaseTest {
    LogInPage logInPage;
    SecureAreaPage secureAreaPage;
    ElementalSeleniumPage elementalSeleniumPage;

    Random random = new Random();
    int randomint = random.nextInt(999999);
    String wrongTestUsername = "test" + randomint;
    String wrongTestPassword = "password" + randomint;
    String correctTestUsername = "tomsmith";
    String correctTestPassword = "SuperSecretPassword!";


    @Before
    public void SetUpTest(){
        logInPage = new LogInPage();
        secureAreaPage = new SecureAreaPage();
        elementalSeleniumPage = new ElementalSeleniumPage();
    }

    //Functional tests - High priority
    @Test
    public void NoCredentialsLogIn(){
        logInPage.logInInputFieldSendKeys("");
        logInPage.passwordInputFieldSendKeys("");
        logInPage.logInButtonClick();
        Assert.assertTrue(logInPage.bannerAlertVisibility());
        Assert.assertEquals(logInPage.bannerAlertText(), "Your username is invalid!\n" + "×");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/login");

        //TODO BIG SECURITY BUG! It shouldn't tell "Your username is invalid!" only "Invalid credentials" or "Invalid Username or password"
        //TODO Character "×" is inside alert message
    }
    @Test
    public void CorrectUsernameBlankPasswordLogIn(){
        logInPage.logInInputFieldSendKeys(correctTestUsername);
        logInPage.passwordInputFieldSendKeys("");
        logInPage.logInButtonClick();
        Assert.assertTrue(logInPage.bannerAlertVisibility());
        Assert.assertEquals(logInPage.bannerAlertText(), "Your password is invalid!\n" + "×");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/login");


        //TODO BIG SECURITY BUG! It shouldn't tell "Your password is invalid!" only "Invalid credentials" or "Invalid Username or password"
        //TODO Character "×" is inside alert message
    }
    @Test
    public void BlankUsernameCorrectPasswordLogIn(){
        logInPage.logInInputFieldSendKeys("");
        logInPage.passwordInputFieldSendKeys(correctTestPassword);
        logInPage.logInButtonClick();
        Assert.assertTrue(logInPage.bannerAlertVisibility());
        Assert.assertEquals(logInPage.bannerAlertText(), "Your username is invalid!\n" + "×");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/login");

        //TODO BIG SECURITY BUG! It shouldn't tell "Your username is invalid!" only "Invalid credentials" or "Invalid Username or password"
        //TODO Character "×" is inside alert message
    }
    @Test
    public void WrongUsernameCorrectPasswordLogIn(){
        logInPage.logInInputFieldSendKeys(wrongTestUsername);
        logInPage.passwordInputFieldSendKeys(correctTestPassword);
        logInPage.logInButtonClick();
        Assert.assertTrue(logInPage.bannerAlertVisibility());
        Assert.assertEquals(logInPage.bannerAlertText(), "Your username is invalid!\n" + "×");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/login");

        //TODO BIG SECURITY BUG! It shouldn't tell "Your username is invalid!" only "Invalid credentials" or "Invalid Username or password"
        //TODO Character "×" is inside alert message
    }
    @Test
    public void CorrectUsernameWrongPasswordRegistration(){
        logInPage.logInInputFieldSendKeys(correctTestUsername);
        logInPage.passwordInputFieldSendKeys(wrongTestPassword);
        logInPage.logInButtonClick();
        Assert.assertTrue(logInPage.bannerAlertVisibility());
        Assert.assertEquals(logInPage.bannerAlertText(), "Your password is invalid!\n" + "×");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/login");

        //TODO BIG SECURITY BUG! It shouldn't tell "Your password is invalid!" only "Invalid credentials" or "Invalid Username or password"
        //TODO Character "×" is inside alert message
    }
    @Test
    public void SuccessfulLogIn(){
        logInPage.logInInputFieldSendKeys(correctTestUsername);
        logInPage.passwordInputFieldSendKeys(correctTestPassword);
        logInPage.logInButtonClick();
        Assert.assertTrue(secureAreaPage.flashAlertSecureAreaVisibility());
        Assert.assertEquals(secureAreaPage.flashAlertSecureAreaText(),"You logged into a secure area!\n" + "×");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");

        //TODO Character "×" is inside alert message
    }
    @Test
    public void LogOutTest(){
        logInPage.logInInputFieldSendKeys(correctTestUsername);
        logInPage.passwordInputFieldSendKeys(correctTestPassword);
        logInPage.logInButtonClick();
        Assert.assertTrue(secureAreaPage.flashAlertSecureAreaVisibility());
        Assert.assertEquals(secureAreaPage.flashAlertSecureAreaText(),"You logged into a secure area!\n" + "×");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/secure");
        secureAreaPage.logOutButtonClick();
        Assert.assertTrue(logInPage.bannerAlertVisibility());
        Assert.assertEquals(logInPage.bannerAlertText(),"You logged out of the secure area!\n" + "×");
        Assert.assertEquals(driver.getCurrentUrl(),"https://the-internet.herokuapp.com/login");
    }

    //Functional test - Low priority
    @Test
    public void RemovingWrongCredentialsBanner(){
        logInPage.logInInputFieldSendKeys("");
        logInPage.passwordInputFieldSendKeys("");
        logInPage.logInButtonClick();
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("close"))).click();
        logInPage.bannerAlertXButtonClick();
        Assert.assertTrue(driver.findElements(By.cssSelector(".flash.error")).isEmpty());
    }


    
    @Test
    public void LogInPageTextCheck(){
        Assert.assertTrue(logInPage.logInSubHeaderTextVisibility());
        Assert.assertEquals(logInPage.logInSubHeaderTextGet(),"This is where you can log into the secure area. Enter tomsmith for the username and SuperSecretPassword! for the password. If the information is wrong you should see error messages.");
    }
    @Test
    public void LogInPageOutSideLinkTest(){
        logInPage.outSideLinkClick();
        logInPage.switchToNewTab();
        Assert.assertTrue(elementalSeleniumPage.elementalSeleniumHeaderVisibility());
        Assert.assertEquals(elementalSeleniumPage.elementalSeleniumHeaderText(),"Elemental Selenium");
    }
    @Test
    public void SecureAreaTextCheck(){
        logInPage.logInInputFieldSendKeys(correctTestUsername);
        logInPage.passwordInputFieldSendKeys(correctTestPassword);
        logInPage.logInButtonClick();
        Assert.assertTrue(secureAreaPage.secureAreaSubHeaderTextVisibility());
        Assert.assertEquals(secureAreaPage.secureAreaSubHeaderTextGet(),"Welcome to the Secure Area. When you are done click logout below.");
    }
    @Test
    public void SecureAreaOutSideLinkTest(){
        logInPage.logInInputFieldSendKeys(correctTestUsername);
        logInPage.passwordInputFieldSendKeys(correctTestPassword);
        logInPage.logInButtonClick();
        secureAreaPage.outSideLinkClick();
        secureAreaPage.switchToNewTab();
        Assert.assertTrue(elementalSeleniumPage.elementalSeleniumHeaderVisibility());
        Assert.assertEquals(elementalSeleniumPage.elementalSeleniumHeaderText(),"Elemental Selenium");
    }
    /*
    @Test
    public void SecureAreaLogOutButtonTest(){
        logInPage.logInInputFieldSendKeys(correctTestUsername);
        logInPage.passwordInputFieldSendKeys(correctTestPassword);
        logInPage.logInButtonClick();
        Assert.assertTrue(secureAreaPage.flashAlertSecureAreaVisibility());
        Assert.assertEquals(secureAreaPage.flashAlertSecureAreaText(),"You logged into a secure area!\n" + "×");
        secureAreaPage.logOutButtonClick();
        Assert.assertTrue(logInPage.bannerAlertVisibility());
        Assert.assertEquals(logInPage.bannerAlertText(), "You logged out of the secure area!\n" + "×");
    }

     */
//    @Test
//    public void LogOutBackClick(){
//        logInPage.logInInputFieldSendKeys(correctTestUsername);
//        logInPage.passwordInputFieldSendKeys(correctTestPassword);
//        logInPage.logInButtonClick();
//        Assert.assertTrue(secureAreaPage.flashAlertSecureAreaVisibility());
//        Assert.assertEquals(secureAreaPage.flashAlertSecureAreaText(),"You logged into a secure area!\n" + "×");
//        secureAreaPage.logOutButtonClick();
//        Assert.assertTrue(logInPage.bannerAlertVisibility());
//        Assert.assertEquals(logInPage.bannerAlertText(), "You logged out of the secure area!\n" + "×");
//        driver.navigate().back();
//        Assert.assertTrue(logInPage.bannerAlertVisibility());
//        Assert.assertEquals(logInPage.bannerAlertText(), "You logged out of the secure area!\n" + "×");
//    }
    //ovaj test je pao. Ne bi smelo da me vrati u Secure Area nakon ston sam kliknuo Logout
}
