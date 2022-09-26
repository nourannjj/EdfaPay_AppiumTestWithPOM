package Screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DateOfOperationScreen {
    private AndroidDriver driver;
    List<WebElement> list;
    public  DateOfOperationScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By YearTitle=By.id("sbs.softpos.edfaa:id/tvYearTitle");
    private By DayOfMonth=By.id("sbs.softpos.edfaa:id/dayOfMonthText");
    private By NavigateRight=By.id("sbs.softpos.edfaa:id/imgVNavRight");
    private By NavigateLeft=By.id("sbs.softpos.edfaa:id/imgVNavLeft");
    private By ApplyButton=By.id("sbs.softpos.edfaa:id/btn_apply");


    public int[] GetDataInYearFiled()
    {
        //Get Data
       String YearAndMonth=driver.findElement(YearTitle).getText();
       //Split Year and month
       String[] YearAndMonth_Splitted=YearAndMonth.split(" ");
       //Convert from String to integer
        //Convert month to int
        int Month = 0;
        String month=YearAndMonth_Splitted[0];
        switch (month) {
            case "January" -> Month = 1;
            case "February" -> Month = 2;
            case "March" -> Month = 3;
            case "April" -> Month = 4;
            case "May" -> Month = 5;
            case "June" -> Month = 6;
            case "July" -> Month = 7;
            case "August" -> Month = 8;
            case "September" -> Month = 9;
            case "October" -> Month = 10;
            case "November" -> Month = 11;
            case "December" -> Month = 12;
        }
        //Convert Year to int
        int year=Integer.valueOf(YearAndMonth_Splitted[1]);
        int[] YearAndMonth_int={Month,year};
        return YearAndMonth_int;
    }

    public void ChooseTheDesiredMonth(int ReadMonth,int DesiredMonth)
    {
        int Month;
       if(DesiredMonth>ReadMonth)
       {
         //Navigate right until find the desired month
           do {
               driver.findElement(NavigateRight).click();
               //Read Month
               int[] YearAndMonth=GetDataInYearFiled();
               Month=YearAndMonth[0];
           }while(Month!=DesiredMonth);

       }
       else if (DesiredMonth<ReadMonth)
       {
           //Navigate left until find the desired month
           do {
               driver.findElement(NavigateLeft).click();
               //Read Month
               int[] YearAndMonth=GetDataInYearFiled();
               Month=YearAndMonth[0];
           }while(Month!=DesiredMonth);
       }
    }
    public void ChooseTheDesiredYear(int ReadYear,int DesiredYear)
    {
        int year;
        if(DesiredYear>ReadYear)
        {
            //Navigate right until find the desired year
            do {
                driver.findElement(NavigateRight).click();
                //Read Month
                int[] YearAndMonth=GetDataInYearFiled();
                year=YearAndMonth[1];
            }while(year!=DesiredYear);

        }
        else if (DesiredYear<ReadYear)
        {
            //Navigate left until find the desired month
            do {
                driver.findElement(NavigateLeft).click();
                //Read Month
                int[] YearAndMonth=GetDataInYearFiled();
                year=YearAndMonth[1];
            }while(year!=DesiredYear);
        }
    }
    public void ChooseDay(int Day)
    {
        int attempts=0;
        list=driver.findElements(DayOfMonth);
        while(attempts < 2) {
            try {
        switch (Day) {
            case 1 ->  list.get(0).click();
            case 2 ->  list.get(1).click();
            case 3 ->  list.get(2).click();
            case 4 ->  list.get(3).click();
            case 5 ->  list.get(4).click();
            case 6 ->  list.get(5).click();
            case 7 ->  list.get(6).click();
            case 8 ->  list.get(7).click();
            case 9 ->  list.get(8).click();
            case 10 -> list.get(9).click();
            case 11 -> list.get(10).click();
            case 12 -> list.get(11).click();
            case 13 -> list.get(12).click();
            case 14 -> list.get(13).click();
            case 15 -> list.get(14).click();
            case 16 -> list.get(15).click();
            case 17 -> list.get(16).click();
            case 18 -> list.get(17).click();
            case 19 -> list.get(18).click();
            case 20 -> list.get(19).click();
            case 21 -> list.get(20).click();
            case 22 -> list.get(21).click();
            case 23->  list.get(22).click();
            case 24 -> list.get(23).click();
            case 25 -> list.get(24).click();
            case 26 -> list.get(25).click();
            case 27 -> list.get(26).click();
            case 28 -> list.get(27).click();
            case 29 -> list.get(28).click();
            case 30 -> list.get(29).click();
            case 31 -> list.get(30).click();
        }
        break;}catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
    }
    public  FilterScreen ClickOnApply ()
    {
        driver.findElement(ApplyButton).click();
        return new FilterScreen(driver);
    }
    public FilterScreen Choose_Year_Month_Day(int DesiredYear,int DesiredMonth,int DesiredDay)
    {
        //Get the data in the year filed
         int[] YearAndMonth=GetDataInYearFiled();
         int ReadMonth=YearAndMonth[0];
         int ReadYear=YearAndMonth[1];
        //Compare the desired Date with the read one
        if(ReadYear!=DesiredYear)
        {
            //Choose the desired year
            ChooseTheDesiredYear(ReadYear,DesiredYear);
        }
        //Check month
        if(ReadMonth!=DesiredMonth)
        {
            //Choose the desired Month
            ChooseTheDesiredMonth(ReadMonth,DesiredMonth);
        }
        //Chose Day
        ChooseDay(DesiredDay);
        //Click on Apply
        FilterScreen filterScreen=ClickOnApply();
        return filterScreen;
    }
}
