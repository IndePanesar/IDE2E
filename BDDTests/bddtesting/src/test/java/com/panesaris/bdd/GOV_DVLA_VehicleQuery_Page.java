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

public class GOV_DVLA_VehicleQuery_Page
{
    private WebDriver _driver;

    public GOV_DVLA_VehicleQuery_Page(WebDriver p_Driver)
    {
        _driver = p_Driver;
        PageFactory.initElements(p_Driver, this);
    }

    // Page elements
    @FindBy(css = "form[action^=\"/Confirm\"] h1.heading-large")
    private WebElement pageHeader;

    @FindBy(css = "input#Vrm")
    private WebElement inputRegistration;

    @FindBy(css = "button[name=\"Continue\"]")
    private WebElement btnContinue;

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

    // Enter vehicle registration
    public void EnterVehicleRegistration(String p_VRN)
    {
        inputRegistration.clear();
        inputRegistration.sendKeys(p_VRN);
    }

    // Click Continue
    public GOV_DVLA_ConfirmVehicle_Page ClickContinue()
    {
        btnContinue.click();
        return new GOV_DVLA_ConfirmVehicle_Page(_driver);
    }
}
