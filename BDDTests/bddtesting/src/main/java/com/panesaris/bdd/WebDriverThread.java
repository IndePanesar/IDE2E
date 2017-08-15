/**
 * Author : Inderpal Panesar
 * Date   : 08/06/2017
 * Description :
 *  Set up the required drivers
 */

package com.panesaris.bdd;

import com.sun.javafx.runtime.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebDriverThread
{
    private static WebDriver _driver;

    // Set the default driver to be FIREFOX
    private static String _defaultdriver = DriverTypesEnum.CHROME.name().toUpperCase();

    // Set the local driver instance and return it
    public static WebDriver GetWebDriver() throws Exception
    {
        if (_driver != null) return _driver;

        try
        {
            String type = GetRequiredDriverType();
            if (type.equals("FIREFOX"))
                    _driver = new FirefoxDriver(DesiredCapabilities.firefox());

            else if (type.equals("CHROME"))
            {
                DesiredCapabilities dc = DesiredCapabilities.chrome();

                // Disable the Chrome default browser check
                dc.setCapability("chrome.switches", "--no-default-browser-check");

                // Save passwords pop-up
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("profile.password_manager_enabled", "false");
                dc.setCapability("chrome.prefs", map);

                // System.setProperty("webdriver.chrome.driver","C:/Java/Drivers/ChromeDriver.exe");
                _driver = new ChromeDriver(dc);
            }
            else if (type.equals("IE"))
            {
                DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
                dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
                dc.setCapability("requireWindowFocus", true);
                _driver = new InternetExplorerDriver(dc);
            }
            else
            {
                _driver = new FirefoxDriver(DesiredCapabilities.firefox());
            }
        }

        catch (Exception exception)
        {
            System.out.println("Driver type requested was not handled");
            // default to Firefox

            DesiredCapabilities dc = DesiredCapabilities.chrome();

            // Disable the Chrome default browser check
            dc.setCapability("chrome.switches", "--no-default-browser-check");

            // Save passwords pop-up
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("profile.password_manager_enabled", "false");
            dc.setCapability("chrome.prefs", map);

            // System.setProperty("webdriver.chrome.driver","C:/Java/Drivers/ChromeDriver.exe");
            _driver = new ChromeDriver(dc);            _driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            _driver.manage().window().maximize();
        }
        return _driver;
    }

    // Function to get the driver type to use set in properties in POM
    private static String GetRequiredDriverType() throws Exception
    {
        try
        {
            return SystemProperties.getProperty("browser").toUpperCase();
        }
        catch (Exception exception)
        {
            String b = System.getProperty("browser");
System.out.printf(b);
            System.out.printf("Exception thrown \n Attempting to use default driver FIREFOX");
            return _defaultdriver;
        }
    }

    public static void QuitDriver()

    {
        if (_driver != null)
            _driver = null;
    }
}
