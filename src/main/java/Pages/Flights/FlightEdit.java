package Pages.Flights;

public class FlightEdit extends Objects_Flight {

    public void editFlight (){
        waitElementClickable(editFlightButtonLocator);
        elementByXpath(editFlightButtonLocator).click();
    }

}
