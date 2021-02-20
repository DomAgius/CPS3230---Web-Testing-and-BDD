package edu.cps3230.webtesting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
)
// Class used to run all of the acceptance tests
public class TestRunner
{

}
