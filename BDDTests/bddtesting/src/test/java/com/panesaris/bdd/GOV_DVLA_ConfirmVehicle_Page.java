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

import java.util.HashMap;
import java.util.List;

public class GOV_DVLA_ConfirmVehicle_Page
{
    private WebDriver _driver;

    public GOV_DVLA_ConfirmVehicle_Page(WebDriver p_Driver)
    {
        _driver = p_Driver;
        PageFactory.initElements(p_Driver, this);
    }

    // Page elements
    @FindBy(css = "#pr3 h1.heading-large")
    private WebElement pageHeader;

    @FindBy(css = "#pr3 li.list-summary-item")
    private List<WebElement> detailsSummaryList;

    @FindBy(css = "input#Correct_True")
    private WebElement rbYes;

    @FindBy(css = "input#Correct_False")
    private WebElement rbNo;

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

    // Get Hashmap for vehicle summary
    public HashMap<String, String> GetVehiclSummaryList()
    {
       HashMap<String, String> details = new HashMap<String, String>();
       for(WebElement liItem : detailsSummaryList)
       {
           String[] text = liItem.getText().split("\\s+");
           details.put(text[0], text[1]);
       }
       return details;
    }

    // Click the Yes radio button
    public void ConfirmVehicleDetails(boolean p_YesNo)
    {
        WebElement element = p_YesNo ? rbYes : rbNo;
        element.click();
    }

    // Click Continue
    public GOV_DVLA_ViewVehicle_Page ClickContinue()
    {
        btnContinue.click();
        return new GOV_DVLA_ViewVehicle_Page(_driver);
    }
}
