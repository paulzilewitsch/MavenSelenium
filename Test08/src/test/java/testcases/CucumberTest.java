package testcases;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/features/"},
				glue={"cucumberSteps"},
				format={"json:target/cucumber.json","html:target/site/cucumber-pretty"})
public class CucumberTest 
{
	
}
