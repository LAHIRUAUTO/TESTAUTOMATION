package Pages.Flights;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FlightSearch extends Objects_Flight {

    public void searByFlightNumber (String flightDesignator){
        waitElementVisible(searchFlightNumberLocator);
        elementByXpath(searchFlightNumberLocator).clear();
        elementByXpath(searchFlightNumberLocator).sendKeys(flightDesignator);
    }

    public void openCallenderDepFrom() {
        elementByXpath(departureDateFromLocator).click();
    }

    public void selectDepartureDateFrom(String departureDate) {

        WebElement dateWidget = elementByXpath(dateWidgetLocator);
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
        for (WebElement cell : columns) {
            if (cell.getText().equals(departureDate)) {
                cell.click();
                break;
            }
        }
    }

    public void openCallenderDepTo() {
        elementByXpath(departureDateFromLocator).click();
    }

    public void selectDepartureDateTo(String departureDate) {

        WebElement dateWidget = elementByXpath(dateWidgetLocator);
        List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
        for (WebElement cell : columns) {
            if (cell.getText().equals(departureDate)) {
                cell.click();
                break;
            }
        }
    }



    public void searchResult (){
        waitElementClickable(searchButtonLocator);
        elementByXpath(searchButtonLocator).click();
    }
}
