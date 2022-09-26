package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Scanner;

public class GPSScreen {
    private AndroidDriver driver;
    String check=null,comment=null;boolean expected=false;
    Scanner input;
    List<WebElement> list;
    public GPSScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By PreciseLocation=By.xpath("//*[@text='Precise']");
    private By allow_GPS_button=By.xpath("//*[@text='Only this time']");
    private By elements1=By.xpath("//android.widget.TextView");

    //Function6:Allow GPS
    public void AllowGPS()
    {
        driver.findElement(PreciseLocation).click();
        driver.findElement(allow_GPS_button).click();
    }
    public String[] check_Navigation_To_AllowGPSScreen(String Comment)
    {
        list=driver.findElements(elements1);
        // Get elements displayed in the screen
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Allow EdfaPay to access this device’s location?")) {//GPS screen
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Enter your OTP code")) {//OTP screen
                comment = Comment;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";
            }
        }
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function7:Check the ability to navigate to the outlet screen
    public String[] check_Navigation_To_OutletScreen(String Comment)
    {
        list=driver.findElements(elements1);
        // Get elements displayed in the screen
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Please select one outlet from the list below to complete the terminal process.")) {//outlet screen
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Allow EdfaPay to access this device’s location?")) {//GPS screen
                comment = Comment;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";
            }
        }
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;

    }

}
