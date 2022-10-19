package Tests;

import Base.BaseTest;
import Screens.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProfileScreenTest extends BaseTest {
    String Comm;
    File propFile= new File("src/main/resources/configuration.properties");
    Properties props = new Properties();
    FileInputStream inputStream;

    @Test(description = "Validation on the ability of change the preferred language", priority = 1)
    public void TC_edfapay_155() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        ReceiptScreen receiptScreen = new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"), props.getProperty("User1ValidPass"));
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Navigate to profile screen and Check username filed
        ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
        //Change language to Arabic
        newPaymentScreen=profileScreen.ChangeLanguageToArabic();
        newPaymentScreen.WaitUntilVisibilityOfPaymentScreen();
        boolean check=newPaymentScreen.CheckPaymentScreenIsArabic();
        Comm="Changing language option isn't work";
        softAssert.assertEquals(check,true,Comm);
        //Change language back to English
        profileScreen=newPaymentScreen.ClickOnProfileBtn();
        profileScreen.ChangeLanguageToEnglish();
        softAssert.assertAll();
    }
    @Test(description = "Validation on loging out from the application", priority = 2)
    public void TC_edfapay_156() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        ReceiptScreen receiptScreen = new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"))) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"), props.getProperty("User1ValidPass"));
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Navigate to profile screen and logout
        ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
        loginScreen=profileScreen.ClickOnlogoutBtn();
        //Check navigation to login screen
        Comm="Error:clicking on the logout button doesn't navigate user to login screen";
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        softAssert.assertEquals(arr[0],"true",Comm);
        softAssert.assertAll();
    }

    @Test(description = "Validation of the name of the Supervisor", priority = 3)
    public void TC_edfapay_158() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        ReceiptScreen receiptScreen = new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"))) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"), props.getProperty("Sup1ValidPass"));
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Navigate to profile screen and Check username filed
        ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
        boolean check=profileScreen.CheckUserNameField(props.getProperty("Sup1Name"));
        //Check UserName is right
        Comm="Error:Supervisor name is wrong";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation of the name of the merchant", priority = 4)
    public void TC_edfapay_159() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        ReceiptScreen receiptScreen = new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        // Enter Email and Password
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        // Enter OTP
        message="Enter OTP";
        otpScreen.EnterOTP(message);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
        // Choose the desired outlet
        newPaymentScreen=outletScreen.ChooseOutlet();
        //Navigate to profile screen and Check username filed
        ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
        boolean check=profileScreen.CheckUserNameField(props.getProperty("MerchantName"));
        //Check UserName is right
        Comm="Error:Merchant name is wrong";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation of the user outlet", priority = 5)
    public void TC_edfapay_160() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        ReceiptScreen receiptScreen = new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"), props.getProperty("User1ValidPass"));
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Navigate to profile screen and Check username filed
        ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
        boolean check=profileScreen.CheckOutletNameField(props.getProperty("Outlet2"));
        Comm="User outlet is wrong";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation of the supervisor outlet", priority = 6)
    public void TC_edfapay_161() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        ReceiptScreen receiptScreen = new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"))) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"), props.getProperty("Sup1ValidPass"));
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Navigate to profile screen and Check username filed
        ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
        boolean check=profileScreen.CheckOutletNameField(props.getProperty("Outlet2"));
        Comm="Supervisor outlet is wrong";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation of the Merchant outlet", priority = 7)
    public void TC_edfapay_162() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        ReceiptScreen receiptScreen = new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        // Enter Email and Password
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        // Enter OTP
        message="Enter OTP";
        otpScreen.EnterOTP(message);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
        // Choose the desired outlet
        newPaymentScreen=outletScreen.ChooseOutlet();
        //Navigate to profile screen and Check username filed
        ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
        boolean check=profileScreen.CheckOutletNameField(props.getProperty("Outlet2"));
        Comm="Merchant outlet is wrong";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();
    }
}
