package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Scanner;

public class OutletScreen {
    private AndroidDriver driver;
    List<WebElement> list;Scanner input;
    String check=null,comment=null;boolean expected=false;String[] arr;


    public OutletScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By outletList=By.xpath("//*[@text='Default Outlet branch']");
    private By desiredOutlet=By.xpath("//*[@text='Maadi']");
    private By Register_TerminalButton=By.xpath("//*[@text='Register Terminal']");
    private By elements5=By.xpath("//android.widget.TextView");

    // Function2:Check the validation of only the created outlet in the outlet list
    public boolean ChecktheoutletsintheList(){
        driver.findElement(outletList).click(); // open the list
        String outlet1 = "Default Outlet branch", outlet2 = "Maadi";
       list=driver.findElements(elements5);
        boolean existed_outlet = false;
        for (WebElement element : list) {
            existed_outlet = ((element.getAttribute("text")).equals(outlet1))
                    || ((element.getAttribute("text")).equals(outlet2));

        }
        return existed_outlet;
    }
    //Choose the Desired outlet
    public NewPaymentScreen ChooseOutlet()
    {
        driver.findElement(outletList).click(); // open the list
        driver.findElement(desiredOutlet).click();//Maadi is the desired outlet
        driver.findElement(Register_TerminalButton).click();//Click on registerButton
        return new NewPaymentScreen((AndroidDriver) driver);
    }

    // Check that terminal is assigned to desired merchant and desired outlet (Need to be automotive)
    public boolean IsTerminalAssignedTotTheRightOutlet()
    {
        boolean check = false;
        System.out.println("If terminal is assigned to desired merchant and desired outlet enter 'y' else enter 'no'");
        input = new Scanner(System.in);
        String validation = input.next();
        check = validation.equals("y");
        return check;
    }
    //Function7:Check the ability to navigate to the outlet screen
    public String[] check_remaining_To_OutletScreen(String Comment)
    {
        // Get elements displayed in the screen
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements5);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Please select one outlet from the list below to complete the terminal process.")) {//outlet screen
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
        break;
    } catch (
    StaleElementReferenceException e) {
    }
    attempts++;
}
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;

    }


}
