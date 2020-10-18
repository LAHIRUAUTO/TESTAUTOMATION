package DCSPages.Flights;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DCSFlightCreate extends DCS_Objects_Flight {


    public void gotoFlightManagerLink() {

        mouseHover(elementByXpath(flightManagerLocator));
    }



    public void clickFlight() {

        elementByXpath(flightsLocator).click();
    }

    public void clickWrapper() {
        elementByXpath(wrapper).click();
    }

    public void clickAddFlight() {
        elementByXpath(addFlightLocator).click();
    }

    public void selectTimeMode(String timeMode) {
        waitElementClickable(timeModeLocator);
        selectelementByText(elementByXpath(timeModeLocator), timeMode);
    }

    public void selectOperationType(String operationType) {
        selectelementByText(elementByXpath(operationTypeLocator), operationType);
    }

    public void selecrAircraftModel(String aircraftModel) {
        selectelementByText(elementByXpath(aircraftModelLocator), aircraftModel);
    }

    public void enterFlightNumber(String flightDesignator) {
        elementByXpath(flightNumberLocator).clear();
        elementByXpath(flightNumberLocator).sendKeys(flightDesignator);
    }

    public void selectSeatMap(String seatMap) {
        selectelementByText(elementByXpath(seatMapLocator), seatMap);
    }

    public void selectOperatedBy(String operatedBy) {
        selectelementByText(elementByXpath(operateByLocator), operatedBy);
    }

    public void selectFlightType(String flightType) {
        selectelementByText(elementByXpath(flightTypeLocator), flightType);
    }

    public void selectAircradtTail(String aircraftTail) {
        selectelementByText(elementByXpath(aircraftTailLocator), aircraftTail);
    }

    public void openCallender() {
        elementByXpath(callenderLocator).click();
    }

    public void selectDepartureDate(String departureDate) {

        WebElement dateWidget = elementByXpath(dateWidgetLocator);
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
        for (WebElement cell : columns) {
            if (cell.getText().equals(departureDate)) {
                cell.click();
                break;
            }
        }
    }

    public void selectSsrTemplate(String ssrTemplate) {
        selectelementByText(elementByXpath(ssrTemplateLocator), ssrTemplate);
    }

    public void selectDepartureAirport(String departureAirport) {
        selectelementByText(elementByXpath(departureAirportLocator), departureAirport);
    }

    public void selectArrivalAirport(String arrivalAirport) {
        selectelementByText(elementByXpath(arrivalAirportLocator), arrivalAirport);
    }

    public void enterDepartureTimeHH(String departureTimeHH) {
        elementByXpath(departureTimeHHLocator).clear();
        elementByXpath(departureTimeHHLocator).sendKeys(departureTimeHH);
    }

    public void enterArrivalTimeHH(String arrivvalTimeHH) {
        elementByXpath(arrivalTimeHHLocator).clear();
        elementByXpath(arrivalTimeHHLocator).sendKeys(arrivvalTimeHH);
    }

    public void enterDepartureTimeMM(String departureTimeMM) {
        elementByXpath(departurTimeMMLocator).clear();
        elementByXpath(departurTimeMMLocator).sendKeys(departureTimeMM);
    }

    public void enterArrivalTimeMM(String arrivalTimeMM) {
        elementByXpath(arrivalTimeMMLocator).clear();
        elementByXpath(arrivalTimeMMLocator).sendKeys(arrivalTimeMM);
    }

    public void enterDepartureOffSet(String departureOffset) {
        elementByXpath(departureOffSetLocator).clear();
        elementByXpath(departureOffSetLocator).sendKeys(departureOffset);
    }

    public void enterArrivalOffSet(String arrivalOffset) {
        elementByXpath(arrivalOffSetLocator).clear();
        elementByXpath(arrivalOffSetLocator).sendKeys(arrivalOffset);
    }


    public void selectDepartureTerminal(String departureTerminal) {
        selectelementByText(elementByXpath(departureTerminalLocator), departureTerminal);
    }

    public void selectArrivalTerminal(String arrivalTerminal) {
        selectelementByText(elementByXpath(arrivalTerminalLocator), arrivalTerminal);
    }

    public void clicksaveButton() {
        waitElementClickable(saveButtonLocator);
        elementByXpath(saveButtonLocator).click();
    }

    public void validateMessage (){
        waitElementVisible(messageOutputLocator);
        System.out.println("Message output: " + elementByXpath(messageOutputLocator).getText());
    }

}
