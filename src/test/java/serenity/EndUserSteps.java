package serenity;

import CommonActions.CommonActions;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class EndUserSteps extends ScenarioSteps {
    CommonActions commonActions;

    @Step
    public void createBookingUsingRequestAPI(List<String> list) throws IOException, ParseException {
        commonActions.createBookingUsingRequestAPI(list);
    }

    @Step
    public void acceptBookingAsClearedTeacherUsingRequestAPI(List<String> list) throws IOException, ParseException {
        commonActions.acceptBookingAsClearedTeacherUsingRequestAPI(list);
    }
}