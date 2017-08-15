/**
 * Author : Inderpal Panesar
 * Date   : 08/06/2017
 * Description :
 *  Page object definition for the gov dvla vehicle information page.
 */
package com.panesaris.bdd;

import com.google.common.base.Joiner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class GOV_DVLA_ViewVehicle_Page
{
    private WebDriver _driver;

    public GOV_DVLA_ViewVehicle_Page(WebDriver p_Driver)
    {
        _driver = p_Driver;
        PageFactory.initElements(p_Driver, this);
    }

    // Page elements
    @FindBy(css = "h1.heading-large .reg-mark")
    private WebElement pageHeader;

    @FindBy(css = "div.isValidMot")
    private List<WebElement> validTaxMot;

    @FindBy(css = "#pr3 li.list-summary-item")
    private List<WebElement> detailsSummaryList;

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

    // Returns the vehicle tax and mot as a comma string
    public String GetVehicleTaxMot()
    {
        ArrayList<String> taxmot = new ArrayList<String>();
        for(WebElement we : validTaxMot)
        {
            taxmot.add(we.getText());
        }

        return Joiner.on(',').join(taxmot);
    }

    // Return the vehicle details as a comma seperated lstring
    public String GetVehiclSummaryList()
    {
        ArrayList<String> details = new ArrayList<String>();
       for(WebElement we : detailsSummaryList)
       {
            details.add(we.getText());
       }
       return Joiner.on(',').join(details);
    }
}
