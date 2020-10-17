package Pages.Flights;

public class FlightEdit extends Objects_Flight {

    public void editFlight (){
        waitElementClickable(editFlightButtonLocator);
        elementByXpath(editFlightButtonLocator).click();
        System.out.println("Flight edit initiated");
    }

    public void selectFlightDepStatus (String depStatus){
        waitElementVisible(selectDepartureStatus);
        elementByXpath(selectDepartureStatus).click();
        selectelementByText(elementByXpath(selectDepartureStatus), depStatus);
    }

    public void clicksaveButton() {
        waitElementClickable(saveButtonLocator);
        elementByXpath(saveButtonLocator).click();
    }

}
