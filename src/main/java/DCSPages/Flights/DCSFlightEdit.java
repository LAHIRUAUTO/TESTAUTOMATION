package DCSPages.Flights;

public class DCSFlightEdit extends DCS_Objects_Flight {

    public void editFlight (){
        waitElementClickable(editFlightButtonLocator);
        elementByXpath(editFlightButtonLocator).click();
    }

    public void selectFlightDepStatus (String depStatus){
        waitElementVisible(selectDepartureStatusLocator);
        elementByXpath(selectDepartureStatusLocator).click();
        selectelementByText(elementByXpath(selectDepartureStatusLocator), depStatus);
    }

    public void clicksaveButton() {
        waitElementClickable(saveButtonLocator);
        elementByXpath(saveButtonLocator).click();
    }

    public void confirmationMessage (){
        waitElementVisible(confirmationMessageLocator);
        System.out.println("Message output: " + elementByXpath(confirmationMessageLocator).getText());
    }

    public void confirmChange (){
        elementByXpath(yesProceedButtonLocator).click();
    }

}
