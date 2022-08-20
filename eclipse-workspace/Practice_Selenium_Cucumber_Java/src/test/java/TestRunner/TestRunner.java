package TestRunner;

import org.junit.runner.RunWith;   
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;  

@RunWith(Cucumber.class) 
@CucumberOptions(features="src/test/resources/Features",
glue={"StepDefinitions"}, monochrome = true,
//plugin = { "pretty", "json:target/reports/cucumber.json"},
//plugin = { "pretty",  "junit:target/reports/cucumber.xml"},
plugin = { "pretty", "html:target/reports"})

public class TestRunner {
}
