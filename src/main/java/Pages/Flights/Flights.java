package Pages.Flights;


public class Flights extends FlightsObject{




    public void gotoFlightManagerLink (){

        mouseHover(elementByXpath(flightManagerLocator));


    }

    public void clickFlight (){

        elementByXpath(flightsLocator).click();
    }


}
