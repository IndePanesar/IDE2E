/**
 * Author : Inderpal Panesar
 * Date   : 08/06/2017
 * Description :
 *  Will get a single instance of the driver
 */

package com.panesaris.bdd;

import org.openqa.selenium.WebDriver;

// Set up the driver instance
public final class DriverInstance
{
    private static WebDriver _driver;
    public static WebDriver Driver() throws Throwable
    {
        if (null == _driver)
        {
            try
            {
                _driver = WebDriverThread.GetWebDriver();
            }
            catch (Exception exp)
            {
                System.out.println("Failed to create instance of driver.");
                throw exp;
            }
        }
        return _driver;
    }

    // Quit and close the local driver instance
    public static void QuitWebDriver()
    {
        if (null == _driver) return;
        WebDriverThread.QuitDriver();
        _driver.quit();
        _driver = null;
    }
}
