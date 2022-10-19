package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class TerminalRegistration {
    private AndroidDriver driver;
    File propFile= new File("src/main/resources/configuration.properties");
    Properties props = new Properties();
    FileInputStream inputStream;
    public TerminalRegistration(AndroidDriver driver)
    {
        this.driver=driver;
    }
    public LoginScreen TerminalReg() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        LoginScreen loginScreen=new LoginScreen(driver);
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        //EnterOTP
        Scanner input = new Scanner(System.in);
        System.out.println("Enter OTP");
        String otp = input.nextLine();
        otpScreen.EnterOTP(otp);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();

        NewPaymentScreen newPaymentScreen=outletScreen.ChooseOutlet();
        // logout from the merchant account
        ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
        loginScreen=profileScreen.ClickOnlogoutBtn();
        System.out.println("I am logout out");
        return loginScreen;


    }

}
