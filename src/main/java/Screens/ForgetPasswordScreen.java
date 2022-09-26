package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.Scanner;

public class ForgetPasswordScreen {
    private AndroidDriver driver;
    public ForgetPasswordScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By Email_field= By.xpath("//*[@text='Email']") ;
    private By SendButton=By.xpath("//*[@text='Send']");

    //Function : Enter Email of merchant ar user
    public void EnterEmail(String email)
    {
        driver.findElement(Email_field).sendKeys(email);
    }
    //Function:Click on the SendButton
    public void ClickOnSendButton()
    {
        driver.findElement(SendButton).click();
    }
    //Function:Validation on receiving the reset Password Email
    public boolean DidYouReceiveResetEmail()
    {
        System.out.println("Did you receive the resend Email? if yes enter 'y'");
        // create a new scanner
        // with the specified String Object
        Scanner scanner = new Scanner(System.in);
        String input= scanner.nextLine();
        boolean check = input.equals("y");
        return check;
    }

}
