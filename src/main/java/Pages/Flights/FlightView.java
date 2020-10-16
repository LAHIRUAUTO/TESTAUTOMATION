package Pages.Flights;

public class FlightView extends Objects_Flight {



    public void viewFlight() {
        waitElementVisible(viewFlightLocator);
        doubleClick(elementByXpath(viewFlightLocator));
    }
}
