package Pages.DcsHome;

public class DcsDashBoard extends DcsDashBoardObject {



    public void clickDashBoard () throws InterruptedException {
        dashBoardLocator.click();
        Thread.sleep(2000);
    }

    public  void checkDashBoardtitle (){

        dashBoardTitleLocator.getAttribute ("DEPARTURE CONTROL SYSTEM");
        //System.out.println(dashBoardTitleLocator.getAttribute ("title"));

    }

    public void searchFlight (String flightDesignator){
        flightSearchLocator.clear();
        flightSearchLocator.sendKeys(flightDesignator);
    }

    public void loadCheckInFlight (){

        checkInFlight.click();
    }
}
