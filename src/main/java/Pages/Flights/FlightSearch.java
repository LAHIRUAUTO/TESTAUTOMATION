package Pages.Flights;

public class FlightSearch extends Objects_Flight {

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
