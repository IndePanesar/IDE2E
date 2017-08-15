/**
 * Author : Inderpal Panesar
 * Date   : 08/06/2017
 * Description :
 * Step definitions for the BDD features.
 */
package com.panesaris.bdd;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URL;

public class BDDStepDefinitions
{
    private static WebDriver _driver;
    private GOV_DVLA_GetInformation_Page giPage;
    private GOV_DVLA_VehicleQuery_Page vqPage;
    private GOV_DVLA_ConfirmVehicle_Page cvPage;
    private GOV_DVLA_ViewVehicle_Page vvPage;

    // Hooks
    // Get an instance of the webdriver for the test
    @BeforeSuite
    public static void InstantiateDriverObject() throws Throwable
    {
        try
        {
            _driver = new WebDriverThread().GetWebDriver();
        }
        catch(Exception excp)
        {
            System.out.printf("Exception occured when Initiating driver\nError\n%s", excp.toString());
            throw excp;
        }
    }

    // Create the page objects
    @Before
    public void BeforeScenario() throws Throwable
    {
        if (_driver == null)
            _driver = new WebDriverThread().GetWebDriver();
        giPage = new GOV_DVLA_GetInformation_Page(_driver);
        vqPage = new GOV_DVLA_VehicleQuery_Page(_driver);
        cvPage = new GOV_DVLA_ConfirmVehicle_Page(_driver);
        vvPage = new GOV_DVLA_ViewVehicle_Page(_driver);
    }

    // Delete the cookie information associated with this scenario
    @After
    public static void AfterScenario() throws Throwable
    {
        try
        {
            // Delete all cookie information
            _driver.manage().deleteAllCookies();
        }
        catch(Exception excp)
        {
            System.out.println(String.format("Exception thrown when clearing cookies %s", excp));
            throw excp;
        }
    }

    // Quit all the drivers from the thread pool
    @AfterSuite
    public static void CloseAllDrivers()
    {
        DriverInstance.QuitWebDriver();
        _driver = null;
    }

    // Step definitions
    @Given("^I am on the gov dvla page$")
    public void GivenIAmOnTheGovDvlaPage() throws Throwable
    {
         _driver.navigate().to(new URL("https://www.gov.uk/get-vehicle-information-from-dvla"));
    }

    @Then("^I can see the heading '(.*)' on '(.*)' page$")
    public void ThenICanSeeTheHeadingOnPage(String p_Header, String p_Page) throws Throwable
    {
        if (p_Page.toLowerCase().contains("get information"))
            Assert.assertEquals(giPage.GetPageHeader(), p_Header);
        else if (p_Page.toLowerCase().contains("vehicle enquiry"))
            Assert.assertEquals(vqPage.GetPageHeader(), p_Header);
        else if(p_Page.toLowerCase().contains("confirm vehicle"))
            Assert.assertEquals(cvPage.GetPageHeader(), p_Header);
        else if(p_Page.toLowerCase().contains("verify vehicle"))
            Assert.assertEquals(vvPage.GetPageHeader(), p_Header);
        else
            Assert.fail(String.format("Page not handled with details - page (%s) header(%s)", p_Page, p_Header));
    }

    @When("^I click on the button labeled '(.*)' on '(.*)' page$")
    public void ThenIClickOnTheButtonOnPage(String p_Page, String p_Button) throws Throwable
    {
        if (p_Page.toLowerCase().contains("get information") && p_Button.toLowerCase().contains("start now"))
            giPage.ClickStartNow();
        else if (p_Page.toLowerCase().contains("vehicle enquiry") && p_Button.toLowerCase().contains("continue"))
            vqPage.ClickContinue();
        else if (p_Page.toLowerCase().contains("confirm vehicle") && p_Button.toLowerCase().contains("continue"))
            cvPage.ClickContinue();
        else
            Assert.fail(String.format("Page not handled with details - page (%s) button (%s)", p_Page, p_Button));

    }

    @Then("^I land on the '(.*)' page with title like '(.*)'$")
    public void ThenILandOnPageWithTitleLike(String p_Page, String p_Title) throws Throwable
    {
        if (p_Page.toLowerCase().contains("vehicle enquiry"))
            Assert.assertTrue(_driver.getTitle().toLowerCase().contains(p_Title));
        else if (p_Page.toLowerCase().contains("confirm vehicle"))
            Assert.assertTrue(_driver.getTitle().toLowerCase().contains(p_Title));
        else if (p_Page.toLowerCase().contains("verify vehicle"))
            Assert.assertTrue(_driver.getTitle().toLowerCase().contains(p_Title));
        else
            Assert.fail(String.format("Page not handled with details - page (%s) title (%s)", p_Page, p_Title));
    }

    @When("^I enter a valid vehicle registration '(.*)' on '(.*)' page$")
    public void WhenIEnterAValidVehicleRegistrationPage(String p_VRN, String p_Page )
    {
        if (p_Page.toLowerCase().contains("vehicle enquiry"))
            vqPage.EnterVehicleRegistration(p_VRN);
    }

    @When("^I click radio button '(.*)' on the confirm vehicle page$")
    public void WhenIClickRadioButtonOnTheConfirmVehiclePage(String p_YesNo) throws Throwable
    {
        cvPage.ConfirmVehicleDetails(p_YesNo.toLowerCase().contains("yes"));
    }

    @And("^I verify the vehicle has Tax and MOT on the view vehicle page$")
    public void ThenIVerifyTheVehicleHasValidTaxAndMOT()
    {
        String[] mottax = vvPage.GetVehicleTaxMot().split(",");
        Assert.assertTrue(mottax.length==2);
    }

    @And("^I verify '(.*)' of the vehicle on the '(.*)' page$")
    public void AndVerifyDetailsOfTheVehicleOnThePage(String p_Details, String p_Page) throws Throwable
    {
        if (p_Page.toLowerCase().contains("view vehicle"))
            VerifyVehicleSummary(p_Details);
        else if (p_Page.toLowerCase().contains("view vehicle"))
            VerifyVehicleSummary(p_Details);
    }

    private void VerifyVehicleSummary(String p_Details)
    {

    }


}
