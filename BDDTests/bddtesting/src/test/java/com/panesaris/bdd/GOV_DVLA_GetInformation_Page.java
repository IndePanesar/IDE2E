/**
 * Author : Inderpal Panesar
 * Date   : 08/06/2017
 * Description :
 *  Page object definition for the gov dvla vehicle information page.
 */
package com.panesaris.bdd;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GOV_DVLA_GetInformation_Page
{
    private WebDriver _driver;

    public GOV_DVLA_GetInformation_Page(WebDriver p_Driver)
    {
        _driver = p_Driver;
        PageFactory.initElements(p_Driver, this);
    }

    // Page elements
    @FindBy(css = "#content .page-header h1")
    private WebElement pageHeader;

    @FindBy(css = "#get-started .button")
    private WebElement btnStartNow;

    // Page methods
    // Returns the page title
    public String GetPageTitle()
    {
        return _driver.getTitle();
    }

    // Returns the text of pageHeader element
    public String GetPageHeader()
    {
        return pageHeader.getText();
    }

    // Click StartNow
    public GOV_DVLA_VehicleQuery_Page ClickStartNow()
    {
        btnStartNow.click();
        return new GOV_DVLA_VehicleQuery_Page(_driver);
    }
}
