package Pages.Flights;

public class FlightEdit extends FlightsObject {

    public void searByFlightNumber (String flightDesignator){
        waitElementVisible(searchFlightNumberLocator);
        elementByXpath(searchFlightNumberLocator).clear();
        elementByXpath(searchFlightNumberLocator).sendKeys(flightDesignator);
    }

    public void searchResult (){
        waitElementClickable(searchButtonLocator);
        elementByXpath(searchButtonLocator).click();
    }
}
