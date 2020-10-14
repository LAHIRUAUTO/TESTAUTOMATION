package Pages.Flights;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Flights extends FlightsObject{




    public void gotoFlightManagerLink (){

        mouseHover(elementByXpath(flightManagerLocator));


    }

    public void clickFlight (){

        elementByXpath(flightsLocator).click();
    }
    
    public void clickWrapper (){
        elementByXpath(wrapper).click();
    }
    
    public void clickAddFlight (){
        elementByXpath(addFlightLocator).click();
    }
    
    public void selectTimeMode (){
        selectelementByText(elementByXpath(timeModeLocator) , "UTC");
    }

    public void selectOperationType (){
        selectelementByText(elementByXpath(operationTypeLocator), "Charter");
    }

    public void selecrAircraftModel (){
        selectelementByText(elementByXpath(aircraftModelLocator), "300B4-605R-J18Y243");
    }

    public void enterFlightNumber (){
        elementByXpath(flightNumberLocator).clear();
        elementByXpath(flightNumberLocator).sendKeys("3002");
    }

    public void selectSeatMap (){
        selectelementByText(elementByXpath(seatMapLocator), "SEAT300B4605RJ18Y243");
    }

    public void selectOperatedBy (){
        selectelementByText(elementByXpath(operateByLocator), "IR");
    }

    public void selectFlightType (){
        selectelementByText(elementByXpath(flightTypeLocator), "Domestic");
    }

    public void selectAircradtTail (){
        selectelementByText(elementByXpath(aircraftTailLocator), "EP-IBB");
    }

    public void openCallender (){
        elementByXpath(callenderLocator).click();
    }
    
    public void selectDepartureDate (){

        WebElement dateWidget = elementByXpath(dateWidgetLocator);
        List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
        for (WebElement cell: columns)

        {
            if (cell.getText().equals("25"))
            {
                cell.click();
                break;
            }
        }
    }

    public void selectSsrTemplate (){
        selectelementByText(elementByXpath(ssrTemplateLocator), "SSR300B4605RJ18Y243");
    }

    public void selectDepartureAirport (){
        selectelementByText(elementByXpath(departureAirportLocator), "IKA");
    }

    public void selectArrivalAirport (){
        selectelementByText(elementByXpath(arrivalAirportLocator), "IST");
    }

    public void enterDepartureTimeHH (){
        elementByXpath(departureTimeHHLocator).clear();
        elementByXpath(departureTimeHHLocator).sendKeys("06");
    }

    public void enterArrivalTimeHH (){
        elementByXpath(arrivalTimeHHLocator).clear();
        elementByXpath(arrivalTimeHHLocator).sendKeys("08");
    }

    public void enterDepartureTimeMM (){
        elementByXpath(departurTimeMMLocator).clear();
        elementByXpath(departurTimeMMLocator).sendKeys("00");
    }

    public void enterArrivalTimeMM (){
        elementByXpath(arrivalTimeMMLocator).clear();
        elementByXpath(arrivalTimeMMLocator).sendKeys("30");
    }

    public void enterDepartureOffSet (){
        elementByXpath(departureOffSetLocator).clear();
        elementByXpath(departureOffSetLocator).sendKeys("0");
    }

    public void enterArrivalOffSet (){
        elementByXpath(arrivalOffSetLocator).clear();
        elementByXpath(arrivalOffSetLocator).sendKeys("0");
    }

    public void clicksaveButton (){
        elementByXpath(saveButtonLocator).click();
        elementByXpath(saveButtonLocator).click();
    }


}
