package Tests;

import Base.BaseTest;
import Screens.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

public class ScanCardScreenTest extends BaseTest {
    String Comm;
    @Test(description = "Validation on the back button should be disabled during scanning the card", priority = 1)
    public void TC_edfapay_064() throws IOException
    {
        String [] arr1=new String[2];
        GPSScreen gpsscreen=new GPSScreen(driver);
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
        newPaymentScreen.EnterAmount("100");
        //Check that clicking on the continue button navigate user to the scan card screen
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        // as you have been during scanning process click on the back button and check that you are not navigated to scan card screen or new payment screen
        driver.navigate().back();
        Comm="Error: Back button isn't disabled during the scanning card process";
        arr=scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0],"true",Comm);
        if(arr[0].equals("true")) driver.navigate().back();//Back to new paymnet screen
        softAssert.assertAll();
    }

    @Test(description = "Validation on NFC if it is turned off during the scanning process ", priority = 2)
    public void TC_edfapay_065() throws IOException {
        String[] arr1 = new String[2];
        GPSScreen gpsscreen = new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        ScanCardScreen scanCardScreen=new ScanCardScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //if at the scan card screen back to new payment screen
        arr=scanCardScreen.check_remaining_at_ScanCardScreen("nothing");
        if(arr[0].equals("true")) driver.navigate().back();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen = newPaymentScreen.ClickOnProfileBtn();
            loginScreen = profileScreen.ClickOnlogoutBtn();

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }

        boolean check=newPaymentScreen.IsNfcTurnoff();
        //Enter the Desired Transaction Amount
        newPaymentScreen.EnterAmount("100");
        scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        //Check remaining at new payment screen
        Comm="Error:Despite NFC is turned off user is navigated successfully to scan card screen";
        arr= scanCardScreen.check_remaining_at_ScanCardScreen(Comm);
        softAssert.assertEquals(arr[0],"false",Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation of navigating the user directly to the Receipt screen without entering the PIN if transaction amount <199.  ", priority = 3)
    public void TC_edfapay_071() throws IOException {
        String[] arr1 = new String[2];
        GPSScreen gpsscreen = new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen = newPaymentScreen.ClickOnProfileBtn();
            loginScreen = profileScreen.ClickOnlogoutBtn();

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter the Desired Transaction Amount<199
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to transaction approved screen directly
        Comm="Error : user isn't navigated directly to payment approved screen but navigated to pin entry screen";
        arr=scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        if(arr[0].equals("true")) driver.navigate().back();
        softAssert.assertAll();
    }
    @Test(description = "Validation of navigating the user to the PIN entry screen first  if transaction amount >199 ", priority = 4)
    public void TC_edfapay_072() throws IOException {
        String[] arr1 = new String[2];
        GPSScreen gpsscreen = new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen = newPaymentScreen.ClickOnProfileBtn();
            loginScreen = profileScreen.ClickOnlogoutBtn();

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter the Desired Transaction Amount>199
        newPaymentScreen.EnterAmount("500");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        Comm="Error: User isn't navigated to Pin entry screen despite amount>199 ";
        PINEntryScreen pinEntryScreen=new PINEntryScreen(driver);
        arr=pinEntryScreen.check_Navigating_at_PINEntryScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        if(arr[0].equals("true"))driver.navigate().back();
        softAssert.assertAll();
    }
    @Test(description = "Validation on  Timeout in Scan Card Page ", priority = 5,enabled = false)
    public void TC_edfapay_073() throws IOException, InterruptedException {
        String[] arr1 = new String[2];
        GPSScreen gpsscreen = new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen = newPaymentScreen.ClickOnProfileBtn();
            loginScreen = profileScreen.ClickOnlogoutBtn();

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }


        //Enter the Desired Transaction Amount>199
        newPaymentScreen.EnterAmount("500");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        driver.lockDevice();
        //Add edit
        //Check navigation to the session expired message
        Comm="error: the session expired message does not appear";
        arr=scanCardScreen.check_Navigating_at_SessionExpiredScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        softAssert.assertAll();

    }
    @Test(description = "Validation on  Timeout in Scan Card Page ", priority = 6)
    public void TC_edfapay_075() throws IOException, InterruptedException {
        String[] arr1 = new String[2];
        GPSScreen gpsscreen = new GPSScreen(driver);
        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr = loginScreen.check_Remaining_At_LoginScreen("nothing");
        if (!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            ProfileScreen profileScreen = newPaymentScreen.ClickOnProfileBtn();
            loginScreen = profileScreen.ClickOnlogoutBtn();

        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered("m8sup2@sbs.com")) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8sup2@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter the Desired Transaction Amount<199
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        //Wait until visibility of operation statues screen
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        operationStatusScreen.WaitUntilVisibilityOfOperationStatusScr();
        //navigate to the receipt screen
        ReceiptScreen receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
        if(receiptScreen.CheckPresenceOfRefundBtn(driver))
        {scanCardScreen= receiptScreen.ClickOnRefundBtn_fromSupervisor();}
        //Check navigation to scan card screen
        Comm="Error:supervisor isn't navigated to scan card screen after clcik on the refund button";
        arr= scanCardScreen.check_remaining_at_ScanCardScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        softAssert.assertAll();
    }
}
