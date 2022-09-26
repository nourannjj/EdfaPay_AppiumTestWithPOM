package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.util.Scanner;

public class TerminalRegistration {
    private AndroidDriver driver;
    public TerminalRegistration(AndroidDriver driver)
    {
        this.driver=driver;
    }
    public LoginScreen TerminalReg()
    {
        LoginScreen loginScreen=new LoginScreen(driver);
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        //EnterOTP
        Scanner input = new Scanner(System.in);
        System.out.println("Enter OTP");
        String otp = input.nextLine();
        otpScreen.EnterOTP(otp);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
        /*
        // Allow GPS
        PreciseLocation.click();
        allow_GPS_button.click();

         */
        // Choose the desired outlet
        NewPaymentScreen newPaymentScreen=outletScreen.ChooseOutlet();
        // logout from the merchant account
        ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
        loginScreen=profileScreen.ClickOnlogoutBtn();
        System.out.println("I am logout out");
        return loginScreen;


    }

}
