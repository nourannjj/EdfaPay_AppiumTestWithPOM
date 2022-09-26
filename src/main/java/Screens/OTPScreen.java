package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;

public class OTPScreen {
    String check=null,comment=null;boolean expected=false;Scanner input;
    List<WebElement> list;
    private AndroidDriver driver;
    public OTPScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }

    private By otp_Field=By.id("sbs.softpos.edfaa:id/input_code");
    private By ConfirmButton=By.id("sbs.softpos.edfaa:id/btn_continue");
    private By ResendButton=By.xpath("//*[@text='Resend']");
    private By elements4 =By.xpath("//android.widget.TextView");

    //Function1:Check that OTP screen is displayed or not
    public boolean is_OTPScreen_dispalyed(){
        return (driver.findElement(otp_Field).isDisplayed());
    }
    //Function2:Check the ability to navigate to the otp screen
    public String[] check_Navigation_To_OTPScreen(String Comment)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements4);
        // Get elements displayed in the screen
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Enter your OTP code")) {//OTP
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Please, login to your account")) {
                comment = Comment;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";
            }
        }  break;
    } catch (
    StaleElementReferenceException e) {
    }
    attempts++;
}
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function3:Check that Merchant receives the OTP on his mobile or not
    public boolean DidMerchantReceiveOTP ()
    {
        System.out.println("If you receive OTP enter \"true\" unless enter \"no\"");
        input = new Scanner(System.in);
        boolean received_OTP = input.nextBoolean();
        System.out.println(received_OTP);
        return received_OTP;
    }
    //Function4:Enter OTP
    public void EnterOTP(String msg)
    {
        input = new Scanner(System.in);
        System.out.println(msg);
        String otp = input.next();
        driver.findElement(otp_Field).sendKeys(otp);

    }
    //Function5: Click on the confirmation button after entering OTP
    public OutletScreen ClickOnConfirmButton()
    {
        driver.findElement(ConfirmButton).click();
        return new OutletScreen(driver);
    }


    //Function8:Validate that confirm button is enabled or not
    public boolean IsConfirmButtonEnabledOrNot()
    {
        return (driver.findElement(ConfirmButton).isEnabled());
    }
    //Function9:Check that resend Button is displayed after 2min
    public boolean IsResendButtonDiaplyedOrNot(AppiumDriver driver)
    {
        // Wait 1:30 min without entering otp until the resend button appears
        System.out.println(driver);
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ResendButton));
        return (driver.findElement(ResendButton).isDisplayed());
    }
    //Function10:Click on the ResendOTPButton
    public void ClickOnTheResendOTPButton(AppiumDriver driver)
    {
        driver.findElement(ResendButton).click();
    }
    //Function11:Validation on navigating to the Allow GPS screen

    //Function12:Check remaining at the OTP Screen
    public String[] check_Remaining_At_OTPScreen(String Comment)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements4);
        // Get elements displayed in the screen
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Enter your OTP code")) {//OTPScreen
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
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }

}
