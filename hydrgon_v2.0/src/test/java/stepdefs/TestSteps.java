package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class TestSteps {

    @Given("^today is Sunday$")
    public void today_is_Sunday(){

        System.out.println("111");


    }

    @When("^I ask whether it's Friday yet$")
    public void i_ask_whether_it_s_Friday_yet(){
        System.out.println("222");

    }

    @Then("^I should be told")
    public void i_should_be_told(){
        System.out.println("333");

    }



}
