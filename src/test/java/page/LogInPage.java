package page;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LogInPage extends BaseTest {
    public LogInPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "username")
    WebElement logInInputField;
    @FindBy(id = "password")
    WebElement passwordInputField;
    @FindBy(css = ".fa")
    WebElement logInButton;
    @FindBy(xpath = "//*[contains(text(),'Elemental Selenium')]")
    WebElement outSideLink;
    @FindBy(id = "flash")
    WebElement bannerAlert;
    @FindAll({
            @FindBy(id = "flash")
    })public List<WebElement> bannerList;
    @FindBy(className = "close")
    WebElement banneralertXButton;
    @FindBy(className = "subheader")
    WebElement logInSubheaderText;

    public void logInInputFieldSendKeys(String logIn){
        wdWait.until(ExpectedConditions.visibilityOf(logInInputField)).clear();
        logInInputField.sendKeys(logIn);
    }
    public void passwordInputFieldSendKeys(String password){
        wdWait.until(ExpectedConditions.visibilityOf(passwordInputField)).clear();
        passwordInputField.sendKeys(password);
    }
    public void logInButtonClick(){
        wdWait.until(ExpectedConditions.elementToBeClickable(logInButton)).click();
    }
    public boolean bannerAlertVisibility(){
     return    wdWait.until(ExpectedConditions.visibilityOf(bannerAlert)).isDisplayed();
    }
    public String bannerAlertText(){
        wdWait.until(ExpectedConditions.visibilityOf(bannerAlert)).isDisplayed();
        return bannerAlert.getText();
    }
    public int bannerListSize(){
        return bannerList.size();
    }
    public void bannerAlertXButtonClick(){
        wdWait.until(ExpectedConditions.elementToBeClickable(banneralertXButton)).click();
    }
    public boolean logInSubHeaderTextVisibility(){
       return wdWait.until(ExpectedConditions.visibilityOf(logInSubheaderText)).isDisplayed();
    }
    public String logInSubHeaderTextGet(){
        wdWait.until(ExpectedConditions.visibilityOf(logInSubheaderText)).isDisplayed();
        return logInSubheaderText.getText();
    }
    public void outSideLinkClick(){
        wdWait.until(ExpectedConditions.elementToBeClickable(outSideLink)).click();
    }
    public void switchToNewTab(){
        wdWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> numbersOfTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(numbersOfTabs.get(1));
    }

}
