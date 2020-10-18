package DCSPages.Flights;

public class DCSFlightView extends DCS_Objects_Flight {



    public void viewFlight() {
        waitElementVisible(viewFlightLocator);
        doubleClick(elementByXpath(viewFlightLocator));
    }
}
