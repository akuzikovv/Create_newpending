package steps;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.json.simple.parser.ParseException;
import serenity.EndUserSteps;

import java.io.IOException;
import java.util.List;

public class DefinitionSteps {

    @Steps
    EndUserSteps steps;

    @When("^Create Booking using request API$")
    public void createBookingUsingRequestAPI(List<String>list) throws IOException, ParseException {
        steps.createBookingUsingRequestAPI(list);
    }

    @When("^Accept booking as cleared teacher using request API$")
    public void acceptBookingAsClearedTeacherUsingRequestAPI(List<String>list) throws IOException, ParseException {
        steps.acceptBookingAsClearedTeacherUsingRequestAPI(list);
    }


}

