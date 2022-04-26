package page;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ElementalSeleniumPage extends BaseTest {
    public ElementalSeleniumPage(){
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//h1")
    WebElement elementalSeleniumHeader;
    public String elementalSeleniumHeaderText(){
        wdWait.until(ExpectedConditions.visibilityOf(elementalSeleniumHeader)).isDisplayed();
        return elementalSeleniumHeader.getText();
    }
    public boolean elementalSeleniumHeaderVisibility(){
        return wdWait.until(ExpectedConditions.visibilityOf(elementalSeleniumHeader)).isDisplayed();
    }
}
