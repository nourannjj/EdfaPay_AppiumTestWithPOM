package Tests;

import Base.BaseTest;
import Screens.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginTest extends BaseTest {
    public SoftAssert softassert = new SoftAssert();
    String Comm;
    File propFile= new File("src/main/resources/configuration.properties");
    Properties props = new Properties();
    FileInputStream inputStream;
    @Test(description = "Validation on login with Empty username and password", priority = 5)
    public void TC_edfapay_005() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        // click on the login button if email or password isn't inserted
        // the login button must be disabled
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        // case1:no Email or password is inserted so login button must be disabled
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite No Email or password is inserted");

        // case2:password field is empty so login button must be disabled
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),"");
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the password field is empty");
        loginScreen.ClearEmailAndPass();

        // case3:Email field is empty
        loginScreen.fillemailAndpasword("",props.getProperty("MerchantValidPass"));
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the Email field is empty");
        loginScreen.ClearEmailAndPass();

        softassert.assertAll();
        //driver.quit();
    }

    @Test(description = "Validation on entering an invalid email (doesn't contain @ or .com)", priority = 6)
    public void TC_edfapay_006() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();
        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        // enter an invalid email format "doesn't contain @"
        loginScreen.fillemailAndpasword(props.getProperty("MerchantInvalidEmail_no@"),props.getProperty("MerchantValidPass"));
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the Email format is invalid");
        loginScreen.ClearEmailAndPass();

        // enter an invalid email format "doesn't contain .com"
        loginScreen.fillemailAndpasword(props.getProperty("MerchantInvalidEmail_no@com"),props.getProperty("MerchantValidPass"));
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the Email format is invalid");
        loginScreen.ClearEmailAndPass();

        softassert.assertAll();
       // driver.quit();

    }
    @Test(description = "Validation on entering an invalid password", groups = "1", priority = 7)
    public void TC_edfapay_007() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        ///Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        // enter an invalid password of less than 8 digits
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantInvalidPass"));
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the password is less than 8 digits");
        loginScreen.ClearEmailAndPass();

        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "validation on login using a merchant account with the correct email and correct password", priority = 8, groups = "try")
    public void TC_edfapay_008() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();
        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        // Check that Login button is Enabled
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),true,"Error Behavior : Login Button disabled despite entering the right merchant email and password format");
        OTPScreen otpscreen=loginScreen.click_on_loginbtn_for_Merchant();
        // Check that OTP screen appears
        softassert.assertEquals(otpscreen.is_OTPScreen_dispalyed(),true,"Error Behavior : OTP field isn't displayed");
       //if navigated successfully to otp screen
        if(otpscreen.is_OTPScreen_dispalyed()) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();


    }
    @Test(description = "validation on login using a merchant account with the correct email and incorrect password.", priority = 9)
    public void TC_edfapay_009() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }

        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantWrongPass"));//wrong password
        OTPScreen otpscreen=loginScreen.click_on_loginbtn_for_Merchant();

        Comm="Error Behavior : OTP screen appears despite wrong merchant password";
        arr=loginScreen.check_Remaining_At_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        //if navigated  to otp screen by wrong
        String[] arr1=otpscreen.check_Navigation_To_OTPScreen("nothing");
        if(arr1[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();


    }
    @Test(description = "validation on login using a merchant account with an invalid email and valid password.", priority = 10)
    public void TC_edfapay_010() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        loginScreen.fillemailAndpasword(props.getProperty("MerchantWrongEmail"),props.getProperty("MerchantValidPass")); //wrong Email
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        Comm="Error Behavior : OTP screen appears despite wrong merchant Email";
        arr=loginScreen.check_Remaining_At_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        String[] arr1=otpScreen.check_Navigation_To_OTPScreen("nothing");
        if(arr1[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();
        //driver.quit();


    }
    @Test(description = "Validate merchant login with the wrong password more than 3 times.", priority = 11)
    public void TC_edfapay_011() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        SoftAssert softassert = new SoftAssert();
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //entering password wrong 4 times
        for(int i=0;i<4;i++)
        {
            loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantWrongPass"));
            OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        }
        // Enter the right password after 3 times entering the wrong password
        // Merchant mustn't be allowed to navigate to the OTP screen
        loginScreen.ClearEmailAndPass();
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        Comm="Error Behavior : OTP screen appears despite Merchant is supposed to be Blocked";
        arr=loginScreen.check_Remaining_At_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        String[] arr1=otpScreen.check_Navigation_To_OTPScreen("nothing");
        if(arr1[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();

    }
    @Test(description = "validate that Merchant Received OTP via SMS", priority = 13)
    public void TC_edfapay_013() throws IOException, InterruptedException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        SoftAssert softassert = new SoftAssert();
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        // Check that merchant receives OTP by inserting "Y"
        System.out.println("from Test case "+otpScreen.DidMerchantReceiveOTP());
        softassert.assertEquals(otpScreen.DidMerchantReceiveOTP(),true,"Merchant doesn't receive the OTP");
        String[] arr1=otpScreen.check_Navigation_To_OTPScreen("nothing");
        if(arr1[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();
    }
    @Test(description = "Validation on entering the OTP sent on the merchant number.", priority = 14)
    public void TC_edfapay_014() throws IOException, InterruptedException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        // Enter OTP
        message="Enter OTP";
        otpScreen.EnterOTP(message); //Display message asking to Enter OTP
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();

        // Check that merchant receives OTP and inserts it by verifying the presence of the outlet screen
        Comm="outlet screen doesn't appear despite Entering right OTP";
        arr=gpsscreen.check_Navigation_To_OutletScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        //if navigated successfully to outlet screen or remaing at otp screen back and reopen app
        String[] arr1=otpScreen.check_Remaining_At_OTPScreen("nothing");
        if(arr[0].equals("true")||arr1[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();

    }
    @Test(description = "Validation on entering OTP except for the last digit", priority = 15)
    public void TC_edfapay_015() throws IOException, InterruptedException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

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
        message="Enter OTP except last digit";
        otpScreen.EnterOTP(message);
        // Validate that Confirm button is disabled until entering all The OTP digits
        softassert.assertEquals(otpScreen.IsConfirmButtonEnabledOrNot(),false,"Confirm button is enabled despite not entering the whole OTP bits");
        //if remaining at otp screen
        arr=otpScreen.check_Remaining_At_OTPScreen("nothing");
        if(arr[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();


    }
    @Test(description = "Validation on resend OTP", priority = 16)
    public void TC_edfapay_016() throws IOException, InterruptedException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

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

        // After navigating to the OTP screen, Wait 1:30 min without entering otp until the resend button appears, then check that resend button is displayed
        softassert.assertEquals(otpScreen.IsResendButtonDiaplyedOrNot(driver),true,"OTP Resend button isn't displayed after 1:30 min after sending the otp");
        //if resend button displayed click on it and continue
        if(otpScreen.IsResendButtonDiaplyedOrNot(driver))
        {
            // Click on the resend button
            otpScreen.ClickOnTheResendOTPButton(driver);
            // Enter the new OTP and click on the confirm button
            message="Enter the new OTP";
            otpScreen.EnterOTP(message);
            OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
            // check that Allow GPS pop up appears
            Comm="Despite entering the right resend OTP can't navigate successfully to the next screen";
            arr=gpsscreen.check_Navigation_To_OutletScreen(Comm);
            softassert.assertEquals(arr[0],"true",arr[1]);
            //if navigated successfully to outlet screen or raming at otp screen
            String[] arr1=otpScreen.check_Remaining_At_OTPScreen("nothing");
            if(arr[0].equals("true")||arr1[1].equals("true")) {
                //Back
                driver.navigate().back();
                driver.launchApp();//launch app from background
            }

        }

        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "validation on entering a pre-sent OTP", priority = 17)
    public void TC_edfapay_017() throws IOException, InterruptedException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

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
        // Wait 1:30 min without entering otp until the resend button appears, if resend button dispalyed click on it
        if(otpScreen.IsResendButtonDiaplyedOrNot(driver))
        {
            otpScreen.ClickOnTheResendOTPButton(driver);
        }
        // Enter a pre-sent otp
        message="Enter a pre-sent OTP";
        otpScreen.EnterOTP(message);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
        // Validate that merchant Remaning at the otp screen cause of entering a pre-sent otp
        Comm="Merchant could login with a pre-sent OTP";
        arr= otpScreen.check_Remaining_At_OTPScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        //if remaining at OTP screen or navigated to outlet screen
        String[] arr1=outletScreen.check_remaining_To_OutletScreen("nothing");
        if(arr[0].equals("true")||arr1[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "Validation of the existence of all and only the created outlets to the login merchant in the outlets list ", priority = 19)
    public void TC_edfapay_019() throws IOException, InterruptedException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

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

        // Check the validation of only the created outlet in the outlet list
        softassert.assertEquals(outletScreen.ChecktheoutletsintheList(),true,"a non created outlet is exist in the outlet list");
        //if remaining at outlet screen
        String[] arr1=outletScreen.check_remaining_To_OutletScreen("nothing");
        if(arr1[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "Validate that the Terminal is a assigned to the desired merchant and for the desired outlet", priority = 20)
    public void TC_edfapay_020() throws IOException, InterruptedException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

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
        // Check that terminal is assigned to desired merchant and desired outlet (Need to be automative)
        softassert.assertEquals(outletScreen.IsTerminalAssignedTotTheRightOutlet(),true,"Error:The terminal is n't assigned to the right merchant or the right outlet");

        //if remaining at outlet screen
        String[] arr1=outletScreen.check_remaining_To_OutletScreen("nothing");
        if(arr[0].equals("true")||arr1[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "Validation on logging in with a user account linked to the merchant used in terminal registration and linked to the outlet chosen in terminal registration (valid username and valid password)", priority = 21)
    public void TC_edfapay_021() throws IOException, InterruptedException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        GPSScreen gpsscreen=new GPSScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        // Check that user could log in successfully by the presence of the new payments screen
        softassert.assertEquals(newPaymentScreen.Check_Navigation_to_NewPaymentScreen(),true,"User couldn't login despite inserting right credentials");
        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on turning off GPS.", priority = 22,enabled = false)
    public void TC_edfapay_022() throws IOException {
        GPSScreen gpsscreen=new GPSScreen(driver);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);

        SoftAssert softassert = new SoftAssert();

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
        //Enter OTP
        message="Enter OTP";
        otpScreen.EnterOTP(message);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
        //Check the navigation to GPS screen
        Comm="Error : Despite gps is off , no message appears asking for turning it on";
        arr=gpsscreen.check_Navigation_To_AllowGPSScreen(Comm);
        //if remaining at OTP screen or navigated to outlet screen
        String[] arr1=otpScreen.check_Navigation_To_OTPScreen("nothing");
        String[] arr2=outletScreen.check_remaining_To_OutletScreen("nothing");
        if(arr1[0].equals("true")||arr2[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertEquals(arr[0],"true",arr[1]);
        System.out.println("Turn on GPS");
        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "Validate that user or supervisor logins with the wrong password more than 3 times.", priority = 23)
    public void TC_edfapay_023() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
        }
        else
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();
        }
        // login with supervisor credentials // login with wrong password 4 times
        for (int i=0;i<4;i++)
        {
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"), props.getProperty("Sup1WrongPass"));
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }
        // Enter the right password after 4 times entering the wrong password
        // supervisor mustn't be allowed to navigate to the OTP screen
        loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"));
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        // Check that user couldn't log in successfully by the being exist in login screen
        Comm="Error behaviour:Supervisor could login despite entering password wrong more than 3 times";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "validation of using forget password option for the merchant account", priority = 25)
    public void TC_edfapay_025() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        // click on the forget password button
        ForgetPasswordScreen forgetPasswordScreen=loginScreen.ClickOnForgetPasswordButton();
        // Enter merchant Email and click on the send button
        forgetPasswordScreen.EnterEmail(props.getProperty("MerchantValidEmail"));
        forgetPasswordScreen.ClickOnSendButton();
        driver.navigate().back();
        //Validation on receiving the reset mail
        softassert.assertEquals(forgetPasswordScreen.DidYouReceiveResetEmail(),true,"Merchant didn't receive the resend password mail");

        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "Validation of resetting password for a suspended account", priority = 33,enabled = false)
    public void TC_edfapay_033() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        // Check that user is suspended
        loginScreen.CheckThatUserIsSuspended();
        // click on the forget password button
        ForgetPasswordScreen forgetPasswordScreen=loginScreen.ClickOnForgetPasswordButton();
        //Enter User Email and click on the send button
        forgetPasswordScreen.EnterEmail(props.getProperty("User1ValidEmail"));
        forgetPasswordScreen.ClickOnSendButton();
        driver.navigate().back();
        // check whether the suspended user receive the resend mail or not
        softassert.assertEquals(forgetPasswordScreen.DidYouReceiveResetEmail(),false,"user received the reset mail despite being suspended");

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging with a merchant account if this merchant is suspendeMerchantValidEmaild on web portals", priority = 34)
    public void TC_edfapay_034() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        SoftAssert softassert = new SoftAssert();

        // Check that Merchant is suspended
        loginScreen.CheckThatMerhantIsSuspended();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        // login with Merchant credentials
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        //Validate Remaining at login screen and doesn't navigate to the OTP screen
        Comm="Error behaviour:Merchant could login despite being suspended";
        arr=loginScreen.check_Remaining_At_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        //if navigated to OTP screen
        String[] arr1=otpScreen.check_Navigation_To_OTPScreen("nothing");
        if(arr1[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging with user account if its merchant is suspended on web portals", priority = 35)
    public void TC_edfapay_035() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

         //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }

        // Check that Merchant is suspended
        loginScreen.CheckThatMerhantIsSuspended();

        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        //Validation on remaining at loginScreen And doesn't navigate to the New Payment Screen
        Comm="Error behaviour:User could login although his merchant is suspended";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);

        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "validation on logging with merchant account if it is activated on web portals after being suspended", priority = 36,enabled = false)
    public void TC_edfapay_036() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        // Check that Merchant is Activated
        loginScreen.CheckThatMerchnatIsActivated();
        // login with Merchant credentials
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        //Validation on navigating to the OTP screen
        Comm="Error behaviour:Merchant couldn't login despite activating it";
        arr=otpScreen.check_Navigation_To_OTPScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);

        //if navigated to OTP screen
        if(arr[0].equals("true")) {
            //Back
            driver.navigate().back();
            driver.launchApp();//launch app from background
        }
        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging with a user account if its merchant is activated on web portals after being suspended but user remains suspended as it is", priority = 37,enabled = false)
    public void TC_edfapay_037() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();
        // Check that Merchant is Activated
        loginScreen.CheckThatMerchnatIsActivated();
        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }

        // login with user credentials
        loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        //Check Remaining at the login Screen
        Comm="Error behaviour:User could login despite being suspended";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging with a user account if its merchant is activated on web portals after being suspended and the user is activated too.", priority = 38,enabled = false)
    public void TC_edfapay_038() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration terminalRegistration =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        //Check that User is Activated
        loginScreen.CheckThatUserIsActivated();
        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }
        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        //Validation on Login and to navigate to the new Payment screen
        newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        softassert.assertEquals(newPaymentScreen.Check_Navigation_to_NewPaymentScreen(),true,"Error behaviour:User couldn't login despite Activating it");

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging with a user account that is created under a different outlet than this terminal is assigned to ", priority = 39)
    public void TC_edfapay_039() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }
        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User2ValidEmail"),props.getProperty("User2ValidPass")))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            // login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User2ValidEmail"),props.getProperty("User2ValidPass"));//Add User Not Working At Maadi
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        //Check Remaining at login Screen
        Comm="Error behaviour:User could login despite being working under anther outlet than terminal is already assigned to";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "Validation on login with user that working for another merchant than the terminal is already assigned to ", priority = 40)
    public void TC_edfapay_040() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }
        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            // login with user credentials not working for merchant m8
            loginScreen.fillemailAndpasword(props.getProperty("UserWorkingForAnotherMerchantEmail"),props.getProperty("UserWorkingForAnotherMerchantPass")); //Add User not working at m8
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        //Validation on remaining at the login screen
        Comm="Error behaviour:User could login despite being working under another outlet than terminal is already assigned to";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging on the same outlet's terminals with a user account after editing the outlet in which that user is working from web portals.", priority = 41)
    public void TC_edfapay_041() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        //Wait Until User outlet is changed
        loginScreen.CheckThatUserOutletIsChanged();

        ///Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            // login with user credentials working for merchant m8
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        //Validation on remaining at the login screen
        Comm="Error behaviour:User could login despite Changing the to another outlet than terminal is already assigned to";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging with the new user email after editing it.", priority = 42)
    public void TC_edfapay_042() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        //Wait Until User email is changed
        loginScreen.CheckThatUserEmailIsChanged();

        ///Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1EditedEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            // login with user credentials working for merchant m8
            loginScreen.fillemailAndpasword(props.getProperty("User1EditedEmail"),props.getProperty("User1ValidPass")); //Add User not working at m8
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        //Validation on Navigation to New Payment screen
        Comm="Error couldn't login with the updated email";
        boolean check=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        softassert.assertEquals(check,true,Comm);

        softassert.assertAll();

    }
    @Test(description = "validation on logging with the previously used user email after editing it.", priority = 43)
    public void TC_edfapay_043() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        //Wait Until User email is changed
        loginScreen.CheckThatUserEmailIsChanged();

        ///Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            // login with user credentials working for merchant m8
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        //Validation on remaining at the login screen
        Comm="Error behaviour:User could login with the old email despite editing it";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);

        softassert.assertAll();
    }
    @Test(description = "Validation of assigning the Terminal for only one merchant ", priority = 44)
    public void TC_edfapay_044() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        ///Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Terminal Registration for merchant 8
         loginScreen=termReg.TerminalReg();
        //login with merchant 6 credentials
        loginScreen.fillemailAndpasword(props.getProperty("AnotherMerchantEmail"),props.getProperty("AnotherMerchantPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        //Check either navigating to alert screen or remaining at login screen
        arr=loginScreen.check_Navigation_to_LoginAlertScreen("nothing");
        String[] arr1=loginScreen.check_Remaining_At_LoginScreen("nothing");
        String[] arr2=otpScreen.check_Navigation_To_OTPScreen("nothing");
        boolean TotalCheck=(arr[0]=="true"||arr1[0]=="true")&&(arr2[0]=="false");
        Comm="Error : More than one merchant can login on the same terminal";

        softassert.assertEquals(TotalCheck,true,Comm);
        softassert.assertAll();

    }
    @Test(description = "Validation of assigning the Terminal for only one outlet", priority = 45)
    public void TC_edfapay_045() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        ///Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //Terminal Registration for outlet maddi
        loginScreen=termReg.TerminalReg();
        //login with merchant 8 credentials
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        message="Enter OTP";
        otpScreen.EnterOTP(message);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
        //Choose defult outlet
        outletScreen.ChoosedefultOutlet();
        //Check either navigating to alert screen or remaining at outlet screen
        arr=outletScreen.check_remaining_To_OutletScreen("nothing");
        String[] arr1=outletScreen.check_Navigation_to_AlertScreen("nothing");
        boolean check=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        boolean TotalCheck=(arr[0]=="true"||arr1[0]=="true")&&(!check);
        Comm="Error :Terminal can be assigned to more than one outlet";

        softassert.assertEquals(TotalCheck,true,Comm);
        //if test case filed assign terminal back to maadi
        if(!TotalCheck)
        {
            loginScreen.BackToLoginScreen(driver);
            loginScreen=termReg.TerminalReg();
        }
        softassert.assertAll();

    }
    @Test(description = "validate that the user or supervisor account login failed without first logging in with the merchant account and assigning the terminal to a specific outlet", priority = 49)
    public void TC_edfapay_049() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        ///Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }
        //un complete terminal registration process
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        driver.navigate().back();
        driver.launchApp();
        //login with user account
        loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();

        //Check either navigating to alert screen or remaining at login screen and not navigating to new payment screen
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        String[] arr1=loginScreen.check_Navigation_to_AlertScreen("nothing");
        boolean check=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        boolean TotalCheck=(arr[0]=="true"||arr1[0]=="true")&&(!check);
        Comm="Error :User can login without completing terminal registration process";

        softassert.assertEquals(TotalCheck,true,Comm);
        softassert.assertAll();

    }
}
