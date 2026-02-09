package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//to run Cucumber, must upon testNG or Junit runner
//provide attributes: path for feature file, glue code file, present in readable format(monochrome), generate report with plugin html
//run test with specific tag, add attribute: tags="@Regresion"
@CucumberOptions(features="src\\test\\java\\cucumber", glue="framedesign\\stepdefinitions", tags= "@Regression",
monochrome=true,plugin= {"html:target/cocumber.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests{//extends class AbstractTestNGCucumberTests;
	

}
