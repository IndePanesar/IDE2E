/**
 * Author : Inderpal Panesar
 * Date   : 08/06/2017
 * Description :
 */

package com.panesaris.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/resources/Search.feature",  plugin = {"pretty", "html:target/cucumber"})
public class TestRunner {

}
