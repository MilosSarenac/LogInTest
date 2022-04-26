package page;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class SecureAreaPage extends BaseTest {
    public SecureAreaPage(){
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "flash")
    WebElement flashAlertSecureArea;
    @FindBy(css = ".button")
    WebElement logOutButton;
    @FindBy(className = "subheader")
    WebElement secureAreaSubHeaderText;
    @FindBy(xpath = "//*[contains(text(),'Elemental Selenium')]")
    WebElement outSideLink;

    public boolean flashAlertSecureAreaVisibility(){
        return wdWait.until(ExpectedConditions.visibilityOf(flashAlertSecureArea)).isDisplayed();
    }
    public String flashAlertSecureAreaText(){
        wdWait.until(ExpectedConditions.visibilityOf(flashAlertSecureArea)).isDisplayed();
        return flashAlertSecureArea.getText();
    }
    public void logOutButtonClick(){
        wdWait.until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
    }
    public void outSideLinkClick(){
        wdWait.until(ExpectedConditions.elementToBeClickable(outSideLink)).click();
    }
    public boolean secureAreaSubHeaderTextVisibility(){
        return wdWait.until(ExpectedConditions.visibilityOf(secureAreaSubHeaderText)).isDisplayed();
    }
    public String secureAreaSubHeaderTextGet(){
        wdWait.until(ExpectedConditions.visibilityOf(secureAreaSubHeaderText)).isDisplayed();
        return secureAreaSubHeaderText.getText();
    }
    public void switchToNewTab(){
        wdWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> numbersOfTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(numbersOfTabs.get(1));
    }

}
