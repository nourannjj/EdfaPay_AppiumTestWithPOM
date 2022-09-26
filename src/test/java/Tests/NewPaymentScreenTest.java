package Tests;

import Base.BaseTest;
import Screens.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Scanner;

public class NewPaymentScreenTest extends BaseTest {
    String Comm;
    @Test(description = "Validate that the Merchant shouldn't be allowed to do the payment process", priority = 1,enabled = false)
    public void TC_edfapay_060() throws IOException
    {
        GPSScreen gpsscreen=new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //if GPS screen appear allow GPS
        arr=gpsscreen.check_Navigation_To_AllowGPSScreen("nothing");
        if(arr[0].equals("true"))
        {
            gpsscreen.AllowGPS();
        }
        // Choose the desired outlet
        newPaymentScreen=outletScreen.ChooseOutlet();
        //Enter Any amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        //Check navigation to Alert Screen not scan card screen
        Comm="Error: Merchant can perform payment process ";
        arr=newPaymentScreen.Check_Navigation_to_AlertScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);

        //"Validation of the error message appears when the merchant tries to perform any operation "
        if(arr[0].equals("true"))
        {
            String ActualText=newPaymentScreen.GetTextOfAlert();
            String ExpectedText="You are not  authorized to perform this action, only permitted users can perform this transaction";
            //Check similarity between the actual text appears in the Alert box and the expected one
            Comm="The Alert message Appears when merchant tries to perform a transaction isn't as expected";
            softAssert.assertEquals(newPaymentScreen.CheckSimilarity(ActualText,ExpectedText),true,Comm);

        }

        softAssert.assertAll();
       // driver.closeApp();
    }

    @Test(description = "Validation on entering Amount Page ", priority = 2)
    public void TC_edfapay_053() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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
        //Check Navigation to the NewPayment Screen and not remaining at log on screen
        Comm="Error:User isn't navigated to New Payment Screen despite Entering the right credentials";
        softAssert.assertEquals(newPaymentScreen.Check_Navigation_to_NewPaymentScreen(),true,Comm);
        softAssert.assertAll();
        //driver.closeApp();
    }

    @Test(description = "Validation on Enter Amount (click on numbers)", priority = 3)
    public void TC_edfapay_054() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //Enter the Desired Transaction Amount
        System.out.println("Enter your Transaction amount");
        input=new Scanner(System.in);
        String Input=input.nextLine();
        String RealAmount =newPaymentScreen.EnterAmount(Input);
        //Check that number button are working by checking that amount has been entered in the amount field
        String ActualAmount=newPaymentScreen.ReadDataInAmountField();
        //Check that amount exists in the amount field is the same as inserted
        Comm="The exist Amount in the amount filed isn't the same as the inserted one ";
        softAssert.assertEquals(newPaymentScreen.CheckSimilarity(ActualAmount,RealAmount),true,Comm);

        softAssert.assertAll();
        //driver.closeApp();
    }
    @Test(description = "Validation on the limit of Amount ", priority = 4)
    public void TC_edfapay_055() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //Enter the maximum limit of amount
        String MaxAmount="99999.99";
        newPaymentScreen.EnterAmount(MaxAmount);
        //Read the Actual data in the amount field box
        String ActualAmount= newPaymentScreen.ReadDataInAmountField();
        //Check that the last digit isn't inserted by checking that the Actual and the Real aren't the same
        Comm="The maximum limit of the transaction amount isn't \"99999.99\" but \"99999.999\" ";
        softAssert.assertEquals(newPaymentScreen.CheckSimilarity(ActualAmount,MaxAmount),true,Comm);

        softAssert.assertAll();
        //driver.closeApp();

    }
    @Test(description = "Validation on (click on Clear Button ) ", priority = 5)
    public void TC_edfapay_056() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //Enter Amount say 100
        newPaymentScreen.EnterAmount("100");
        //Clear the amount filed
        newPaymentScreen.ClickOnClearBtn();
        //Read the Actual data in the amount field box
        String ActualAmount= newPaymentScreen.ReadDataInAmountField();
        //Check that Amount is 10 after click on the clear button
        String RealAmount="10";
        Comm="Error:Clear button isn't working as expected";
        softAssert.assertEquals(newPaymentScreen.CheckSimilarity(ActualAmount,RealAmount),true,Comm);

        softAssert.assertAll();
        //driver.closeApp();

    }
    @Test(description = "Validation on click on Continue Button with Enter Amount", priority = 6)
    public void TC_edfapay_057() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //Enter the Desired Transaction Amount
        System.out.println("Enter your Transaction amount<200");
        input=new Scanner(System.in);
        String Input=input.nextLine();
        newPaymentScreen.EnterAmount(Input);
        //Check that clicking on the continue button navigate user to the scan card screen
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        Comm="Error:User isn't navigated to the scan card screen Despite entering an amount";
        arr=newPaymentScreen.Check_Navigation_To_ScanCardScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        //Back from scan card screen new payment screen
        driver.navigate().back();

        softAssert.assertAll();
        //driver.closeApp();

    }
    @Test(description = "Validation of the continue button after clicking on the new transaction button ", priority = 7)
    public void TC_edfapay_057b() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //Enter any amount say 100
        newPaymentScreen.EnterAmount("100");
        //Check that continue button is enabled
        boolean Enabled=newPaymentScreen.IsContinueBtnEnabled();
        if(Enabled) System.out.println("Continue Button is enabled in first time using app");
        //Click on the new transaction button
        newPaymentScreen.ClickOnNewTransactionBtn();
        //Enter any amount say 100
        newPaymentScreen.EnterAmount("100");
        //Check that continue button is enabled
        if(Enabled)
            Comm="Continue button is disabled despite being enabled in first time login and see the New Payment screen ";
        else
            Comm="Continue button is disabled despite amount not equal zero";

        softAssert.assertEquals(newPaymentScreen.IsContinueBtnEnabled(),true,Comm);

        softAssert.assertAll();
        //driver.closeApp();
    }
    @Test(description = "Validation on click on Continue  Button with Enter Amount =zero", priority = 8)
    public void TC_edfapay_058() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen=newPaymentScreen.ClickOnProfileBtn();
            loginScreen= profileScreen.ClickOnlogoutBtn();

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

        //Check that continue button isn't Enabled because amount=zero
        newPaymentScreen.EnterAmount("0");
        Comm="Continue Button is enabled despite Amount=Zero";
        softAssert.assertEquals(newPaymentScreen.IsContinueBtnEnabled(),false,Comm);

        softAssert.assertAll();
        //driver.closeApp();
    }
    @Test(description = "Validate the \"continue\" button  if GPS is turned off", priority = 9)
    public void TC_edfapay_059() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //Check that GPS is off
        if(newPaymentScreen.IsGPSOff())
        {
            String Comm1;
            //Check appearance of GPS permission screen
            if(newPaymentScreen.Check_appearance_of_GPS_permission_screen())
            {
                Comm1="The GPS permission screen " ;
                //Close the GPS permission screen
                newPaymentScreen.CancelLocAccessScreen();
            }

            else
                Comm1="App doesn't ask user to turn on GPS";

            //Enter Amount
            newPaymentScreen.EnterAmount("100");
            //Check that Continue button is disabled cause of turning of GPS
            Comm="Error: "+Comm1+"and"+" Continue button is enabled despite GPS is off";
            softAssert.assertEquals(newPaymentScreen.IsContinueBtnEnabled(),false,Comm);
        }

        softAssert.assertAll();
        //driver.closeApp();
    }
    /*
        @Test(description = "Validation of the error message appears when the merchant tries to perform any operation ", priority = 62)
        public void TC_edfapay_061() throws IOException
        {
            Loginscreen logscreen=new Loginscreen(driver);
            OTPscreen otpscreen=new OTPscreen(driver);
            GPSscreen gpsscreen=new GPSscreen(driver);
            OutletScreen outletScreen=new OutletScreen(driver);
            NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
            SoftAssert softAssert=new SoftAssert();

            // Enter Email and Password
            logscreen.fillemailAndpasword("m8@sbs.com","536860574604");
            logscreen.click_on_loginbtn();
            // Enter OTP
            message="Enter OTP";
            otpscreen.EnterOTP(message);
            otpscreen.ClickOnConfirmButton();
            // Allow GPS
            gpsscreen.AllowGPS();
            // Choose the desired outlet
            outletScreen.ChooseOutlet();
            //Enter Any amount
            newPaymentScreen.EnterAmount("100");
            newPaymentScreen.ClickOnContinueBtn();
            //first check navigation to alert screen if yes check the text in the alert box
            Comm="Error";
            arr=newPaymentScreen.Check_Navigation_to_AlertScreen(Comm);
            if(arr[0].equals("true"))
            {
                String ActualText=newPaymentScreen.GetTextOfAlert();
                String ExpectedText="You are not  authorized to perform this action, only permitted users can perform this transaction";
                //Check similarity between the actual text appears in the Alert box and the expected one
                Comm="The Alert message Appears when merchant tries to perform a transaction isn't as expected";
                softAssert.assertEquals(newPaymentScreen.CheckSimilarity(ActualText,ExpectedText),true,Comm);

            }
            softAssert.assertAll();
            driver.quit();

        }

     */
    @Test(description = "validation on the ability to navigate to the transaction history screen from the transaction amount screen", priority = 10)
    public void TC_edfapay_062() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //Click on the transaction history icon
        newPaymentScreen.ClickOnHistoryBtn();
        //Check Navigation to the transaction history screen
        Comm="Error:User isn't navigated to the Transaction history screen";
        arr=newPaymentScreen.Check_Navigation_To_HistoryScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);

        softAssert.assertAll();
        //driver.closeApp();

    }
    @Test(description = "validation on the ability to navigate to the profile screen from the transaction amount screen", priority = 11)
    public void TC_edfapay_063() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //Click on the profile screen button
        newPaymentScreen.ClickOnProfileBtn();
        //Check navigation to the profile screen and doesn't remain at the new payment screen
        Comm="Error:User isn't navigated to the Profile screen";
        arr=newPaymentScreen.Check_Navigation_To_ProfileScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);

        softAssert.assertAll();
        //driver.closeApp();
    }
    @Test(description = "validation on entering a new transaction amount", priority = 12)
    public void TC_edfapay_064() throws IOException
    {
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        SoftAssert softAssert=new SoftAssert();

        //Check remaining at login screen
        boolean falg=false;
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
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

        //Enter any amount
        newPaymentScreen.EnterAmount("100");
        //Click on the new transaction button
        newPaymentScreen.ClickOnNewTransactionBtn();
        //Get the value in the amount field and check that it's empty
        String Expected="00.00" ;//Amount=0
        String Actual= newPaymentScreen.ReadDataInAmountField();
        Comm="Error:Amount field isn't empty despite entering on the new transaction button";
        softAssert.assertEquals(newPaymentScreen.CheckSimilarity(Actual,Expected),true,Comm);

        softAssert.assertAll();
        //driver.closeApp();
    }
}
