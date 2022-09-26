package Tests;

import Base.BaseTest;
import Screens.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class LoginTest extends BaseTest {
    public SoftAssert softassert = new SoftAssert();
    String Comm;
    @Test(description = "Validation on login with Empty username and password", priority = 5)
    public void TC_edfapay_005() throws IOException {
        // click on the login button if email or password isn't inserted
        // the login button must be disabled
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        // case1:no Email or password is inserted so login button must be disabled
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite No Email or password is inserted");

        // case2:password field is empty so login button must be disabled
        loginScreen.fillemailAndpasword(" m6test@sbs.com","");
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the password field is empty");
        loginScreen.ClearEmailAndPass();

        // case3:Email field is empty
        loginScreen.fillemailAndpasword("","12345678");
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the Email field is empty");
        loginScreen.ClearEmailAndPass();

        softassert.assertAll();
        //driver.quit();
    }

    @Test(description = "Validation on entering an invalid email (doesn't contain @ or .com)", priority = 6)
    public void TC_edfapay_006() throws IOException {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        // enter an invalid email format "doesn't contain @"
        loginScreen.fillemailAndpasword("m6testsbs.com","569661398278");
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the Email format is invalid");
        loginScreen.ClearEmailAndPass();

        // enter an invalid email format "doesn't contain .com"
        loginScreen.fillemailAndpasword("m6test@sbs","569661398278");
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the Email format is invalid");
        loginScreen.ClearEmailAndPass();

        softassert.assertAll();
       // driver.quit();

    }
    @Test(description = "Validation on entering an invalid password", groups = "1", priority = 7)
    public void TC_edfapay_007() throws IOException {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        // enter an invalid password of less than 8 digits
        loginScreen.fillemailAndpasword("m6test@sbs.com","5696");
        softassert.assertEquals(loginScreen.Is_logintn_enabled(),false,"Error Behavior : Login Button is enabled despite the password is less than 8 digits");
        loginScreen.ClearEmailAndPass();

        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "validation on login using a merchant account with the correct email and correct password", priority = 8, groups = "try")
    public void TC_edfapay_008() throws IOException {

        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
        //driver.quit();

    }
    @Test(description = "validation on login using a merchant account with the correct email and incorrect password.", priority = 9)
    public void TC_edfapay_009() throws IOException {

        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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

        loginScreen.fillemailAndpasword("m8@sbs.com","536860574633");//wrong password
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
        //driver.quit();

    }
    @Test(description = "validation on login using a merchant account with an invalid email and valid password.", priority = 10)
    public void TC_edfapay_010() throws IOException {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }
        loginScreen.fillemailAndpasword("m8_error@sbs.com","536860574604"); //wrong Email
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
        SoftAssert softassert = new SoftAssert();
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }
        //entering password wrong 4 times
        for(int i=0;i<4;i++)
        {
            loginScreen.fillemailAndpasword("m8@sbs.com","11111111");
            OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        }
        // Enter the right password after 3 times entering the wrong password
        // Merchant mustn't be allowed to navigate to the OTP screen
        loginScreen.ClearEmailAndPass();
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
       // driver.quit();

    }
    @Test(description = "validate that Merchant Received OTP via SMS", priority = 13)
    public void TC_edfapay_013() throws IOException, InterruptedException {
        SoftAssert softassert = new SoftAssert();
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
        //driver.quit();

    }
    @Test(description = "Validation on entering the OTP sent on the merchant number.", priority = 14)
    public void TC_edfapay_014() throws IOException, InterruptedException {
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
        //driver.quit();
    }
    @Test(description = "Validation on entering OTP except for the last digit", priority = 15)
    public void TC_edfapay_015() throws IOException, InterruptedException {

        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();

        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }

        // Enter Email and Password
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
        //driver.quit();

    }
    @Test(description = "Validation on resend OTP", priority = 16)
    public void TC_edfapay_016() throws IOException, InterruptedException {
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        // Enter Email and Password
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softassert = new SoftAssert();
        boolean falg=false;
        //Check remaining at login screen
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }
        // Enter Email and Password
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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

        // Enter Email and Password
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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

        // Enter Email and Password
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
        GPSScreen gpsscreen=new GPSScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        if(!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com"))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com","12345678");
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
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }
        // Enter Email and Password
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "Validate that user or supervisor logins with the wrong password more than 3 times.", priority = 23)
    public void TC_edfapay_023() throws IOException {
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        falg=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        System.out.println("flag="+falg);
        if(!(arr[0].equals("true"))&&falg)//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();

        }
        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered("m8sup2@sbs.com"))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
        }
        else
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen=profileScreen.ClickOnlogoutBtn();
        }
        // login with supervisor credentials // login with wrong password 4 times
        for (int i=0;i<4;i++)
        {
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "11111111");
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }
        // Enter the right password after 4 times entering the wrong password
        // supervisor mustn't be allowed to navigate to the OTP screen
        loginScreen.fillemailAndpasword("m8u1@sbs.com","12345678");
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        // Check that user couldn't log in successfully by the being exist in login screen
        Comm="Error behaviour:User could login despite entering password wrong more than 3 times";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "validation of using forget password option for the merchant account", priority = 25)
    public void TC_edfapay_025() throws IOException {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        // click on the forget password button
        ForgetPasswordScreen forgetPasswordScreen=loginScreen.ClickOnForgetPasswordButton();
        // Enter merchnat Email and click on the send button
        forgetPasswordScreen.EnterEmail("m8@sbs.com");
        forgetPasswordScreen.ClickOnSendButton();
        driver.navigate().back();
        //Validation on receiving the reset mail
        softassert.assertEquals(forgetPasswordScreen.DidYouReceiveResetEmail(),true,"Merchant didn't receive the resend password mail");

        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "Validation of resetting password for a suspended account", priority = 33,enabled = false)
    public void TC_edfapay_033() throws IOException {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        // Check that user is suspended
        loginScreen.CheckThatUserIsSuspended();
        // click on the forget password button
        ForgetPasswordScreen forgetPasswordScreen=loginScreen.ClickOnForgetPasswordButton();
        //Enter User Email and click on the send button
        forgetPasswordScreen.EnterEmail("m8u1@sbs.com");
        forgetPasswordScreen.ClickOnSendButton();
        driver.navigate().back();
        // check whether the suspended user receive the resend mail or not
        softassert.assertEquals(forgetPasswordScreen.DidYouReceiveResetEmail(),false,"user received the reset mail despite being suspended");

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging with a merchant account if this merchant is suspended on web portals", priority = 34,enabled = false)
    public void TC_edfapay_034() throws IOException {
        SoftAssert softassert = new SoftAssert();

        // Check that Merchant is suspended
        loginScreen.CheckThatMerhantIsSuspended();
        // login with Merchant credentials
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
    @Test(description = "validation on logging with user account if its merchant is suspended on web portals", priority = 35,enabled = false)
    public void TC_edfapay_035() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com"))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com","12345678");
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }
        // Check that Merchant is suspended
        loginScreen.CheckThatMerhantIsSuspended();
        // insert user credentials
        loginScreen.fillemailAndpasword("m8u1@sbs.com","12345678");
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        //Validation on remaining at loginScreen And doesn't navigate to the New Paymnet Screen
        Comm="Error behaviour:User could login although his merchant is suspended";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);

        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "validation on logging with merchant account if it is activated on web portals after being suspended", priority = 36,enabled = false)
    public void TC_edfapay_036() throws IOException {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
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
        // Check that Merchant is Activated
        loginScreen.CheckThatMerchnatIsActivated();
        // login with Merchant credentials
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
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
        TerminalRegistration termReg =new TerminalRegistration(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softassert = new SoftAssert();
        // Check that Merchant is Activated
        loginScreen.CheckThatMerchnatIsActivated();
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
        if(!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com"))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com","12345678");
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }
        // login with user credentials
        loginScreen.fillemailAndpasword("m8u1@sbs.com","12345678");
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        //Check Remaining at the login Screen
        Comm="Error behaviour:User could login despite being suspended";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging with a user account if its merchant is activated on web portals after being suspended but user remains suspended as it is", priority = 38,enabled = false)
    public void TC_edfapay_038() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com"))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com","12345678");
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }
        // login with user credentials
        loginScreen.fillemailAndpasword("m8u1@sbs.com","12345678");
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        //Validation on Login and to navigate to the new Payment screen
        newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        softassert.assertEquals(newPaymentScreen.Check_Navigation_to_NewPaymentScreen(),true,"Error behaviour:User couldn't login despite Activating it");

        softassert.assertAll();
        //driver.quit();
    }
    @Test(description = "validation on logging with a user account that is created under a different outlet than this terminal is assigned to ", priority = 39)
    public void TC_edfapay_039() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered("m8u2@sbs.com"))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            // login with user credentials
            loginScreen.fillemailAndpasword("m8u2@sbs.com","12345678");//Add User Not Working At Maadi
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        //Check Remaining at login Screen
        Comm="Error behaviour:User could login despite being working under aother outlet than termianl is alrwady assigned to";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);
        softassert.assertAll();
        //driver.quit();

    }
    @Test(description = "Validation on login with user that working for another merchant than the terminal is already assigned to ", priority = 40)
    public void TC_edfapay_040() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered("m6u1@sbs.com"))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            // login with user credentials not working for merchant m8
            loginScreen.fillemailAndpasword("m6u1@sbs.com","12345678"); //Add User not working at m8
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }

        //Validation on remaining at the login screen
        Comm="Error behaviour:User could login despite being working under another outlet than terminal is already assigned to";
        arr=newPaymentScreen.Check_Remaing_To_LoginScreen(Comm);
        softassert.assertEquals(arr[0],"true",arr[1]);

        softassert.assertAll();
        //driver.quit();
    }
}