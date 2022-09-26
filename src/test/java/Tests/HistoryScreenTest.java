package Tests;

import Base.BaseTest;
import Screens.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Scanner;

public class HistoryScreenTest extends BaseTest {
    String Comm;
    @Test(description = "Validation that each app user should only see his transactions", priority = 1)
    public void TC_edfapay_126() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount and scan card
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Back to login screen
        loginScreen=loginScreen.BackToLoginScreen(driver);
        //Login with another user credentials
        loginScreen.fillemailAndpasword("m8u2@sbs.com","12345678");
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        //Navigate to history screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Check 1 must be false so that is means that the transaction performed from another account isn't presented
             check1=(historyScreen.GetStatues().equals("Payment"))&&(historyScreen.GetTransactionAmount().equals("150.00"));
             Comm="The transaction performed by user1 appears in the history of user 2";
        }
        //Behavior is right if history is empty or the last transaction isn't appear at the non-performing account
        boolean TotalCheck=check||(check1==false);
        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation that each app Supervisor should only see his transactions", priority = 2)
    public void TC_edfapay_127() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount and scan card
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Back to login screen
        loginScreen=loginScreen.BackToLoginScreen(driver);
        //Login with another user credentials
        loginScreen.fillemailAndpasword("m8sup2@sbs.com","12345678");
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        //Navigate to history screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Check 1 must be false so that is means that the transaction performed from another account isn't presented
            check1=(historyScreen.GetStatues().equals("Payment"))&&(historyScreen.GetTransactionAmount().equals("150.00"));
            Comm="The transaction performed by user1 appears in the history of Supervisor2";
        }
        //Behavior is right if history is empty or the last transaction isn't appear at the non-performing account
        boolean TotalCheck=check||(check1==false);
        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validate the merchant can view the transaction history from his account", priority = 3)
    public void TC_edfapay_128() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount and scan card
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Back to login screen
        loginScreen=loginScreen.BackToLoginScreen(driver);
        //Login with another user credentials
        // Enter Email and Password
        loginScreen.fillemailAndpasword("m8@sbs.com","536860574604");
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        // Enter OTP
        message="Enter OTP";
        otpScreen.EnterOTP(message);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
        // Choose the desired outlet
        newPaymentScreen=outletScreen.ChooseOutlet();
        //Navigate to history screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Check 1 must be false so that is means that the transaction performed from another account isn't presented
            check1=(historyScreen.GetStatues().equals("Payment"))&&(historyScreen.GetTransactionAmount().equals("150.00"));
            Comm="The transaction performed by user1 doesn't appear in merchant history";
        }
        //Behavior is right if history isn't empty and the last transaction  appears at Merchant account
        boolean TotalCheck=(check!=true)&&(check1==true);
        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation of the order of transactions in The transactions list ", priority = 4)
    public void TC_edfapay_130() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount and scan card
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //Navigate to history screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Check 1 must be false so that is means that the transaction performed from another account isn't presented
            check1=(historyScreen.GetStatues().equals("Payment"))&&(historyScreen.GetTransactionAmount().equals("150.00"));
            Comm="The transaction performed by user1 doesn't appear in merchant history";
        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(check!=true)&&(check1==true);
        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation on viewing the transaction type of each transaction", priority = 5)
    public void TC_edfapay_132() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount and scan card
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //Navigate to history screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Check 1 must be false so that is means that the transaction performed from another account isn't presented
            check1=(historyScreen.GetStatues().equals("Payment"));
            Comm="The transaction type isn't \"Payment\"";
        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(check!=true)&&(check1==true);
        softAssert.assertEquals(TotalCheck,true,Comm);

        //Check the cancel type appears
        //Back to log in screen
        loginScreen.BackToLoginScreen(driver);
        loginScreen.fillemailAndpasword("m8u1@sbs.com","12345678");
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        //Perform a payment transaction and cancel it
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();

        //Click on the show receipt button
        operationStatusScreen=new OperationStatusScreen(driver);
        receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();

        //Click on the cancel button
        receiptScreen.ClickONCancelBtn();
        //navigate to newPayment screen
        newPaymentScreen=receiptScreen.CancelReceiptScreen();
        //Check statues is changed to Canceled
        historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Check that history screen isn't empty
        check=historyScreen.IsHistoryScreenEmpty();
        check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Check 1 must be false so that is means that the transaction performed from another account isn't presented
            check1=(historyScreen.GetStatues().equals("Cancel"));
            Comm="The transaction type isn't \"Cancel\"";
        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        TotalCheck=(check!=true)&&(check1==true);
        softAssert.assertEquals(TotalCheck,true,Comm);

        //Check the Refund type appears
        //Back to log in screen
        loginScreen.BackToLoginScreen(driver);
        loginScreen.fillemailAndpasword("m8sup2@sbs.com","12345678");
        newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        //Perform a payment transaction and cancel it
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();

        //Click on the show receipt button
        operationStatusScreen=new OperationStatusScreen(driver);
        receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
        //Check the presence of refund button
        check=receiptScreen.CheckPresenceOfRefundBtn(driver);
        if(check)
        {
            //Click on refund button
            scanCardScreen=receiptScreen.ClickOnRefundBtn_fromSupervisor();
            pinEntryScreen.EnterPINCode("0116");
            operationStatusScreen=pinEntryScreen.ClickOnContBtn_LowerThan200();
            operationStatusScreen.cancelScreen();

            //Open History screen
            historyScreen=newPaymentScreen.ClickOnHistoryBtn();
            //Check that history screen isn't empty
            check=historyScreen.IsHistoryScreenEmpty();
            check1 = false;
            //History isn't Empty
            if(!check)
            {
                //Check 1 must be false so that is means that the transaction performed from another account isn't presented
                check1=(historyScreen.GetStatues().equals("Refund"));
                Comm="The transaction type isn't \"Refund\"";
            }
            //Behavior is right if history isn't empty and the last transaction appears at the top of history
            TotalCheck=(check!=true)&&(check1==true);
            softAssert.assertEquals(TotalCheck,true,Comm);

        }
        softAssert.assertAll();
    }
    @Test(description = "Validation on viewing the last four digits of the card number for each transaction.", priority = 6)
    public void TC_edfapay_133() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount and scan card
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //Navigate to history screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Enter your card number
            System.out.println("Enter the last four digits of the used card number ");
            input=new Scanner(System.in);
            String num=input.nextLine();
            //Check 1 must be false so that is means that the transaction performed from another account isn't presented
            check1=historyScreen.GetCardNumber().equals("************"+num);
            Comm="The card number of the card used in transaction is recorded wrong in history";
        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(check!=true)&&(check1==true);
        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation on viewing the amount of transaction for each transaction.", priority = 7)
    public void TC_edfapay_135() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount and scan card
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //Navigate to history screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            check1=historyScreen.GetTransactionAmount().equals("150.00");
            Comm="The Transaction amount represented in history screen is wrong";
        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(check!=true)&&(check1==true);
        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation on the transaction's history list if there are no transactions to show.", priority = 8)
    public void TC_edfapay_136() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u2@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u2@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Navigate to history screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Check that history screen is empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        Comm="Transaction history isn't Empty despite no transaction is performed through that account";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation of the new transaction amount button", priority = 9)
    public void TC_edfapay_137() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Navigate to history screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        newPaymentScreen=historyScreen.ClickOnNewTransactionBtn();
        //Check navigation to New Payment screen
        boolean check=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
        Comm="User isn't navigated to the New Payment screen from the History Screen ";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation on clicking on the filter button", priority = 10)
    public void TC_edfapay_138() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Navigate to filter screen
        historyScreen.WaitUntilvisibilityOfHistoryScreen();
        FilterScreen filterScreen=historyScreen.ClickONFilterButton();
        //Check navigation to New Payment screen
        boolean check=historyScreen.Check_Navigation_to_FilterScreen();
        Comm="User isn't navigated to the Filter screen from the History Screen ";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation on clicking on the profile icon button", priority = 11)
    public void TC_edfapay_139() throws IOException {

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
        if (!loginScreen.checkThatTerminalRegistered("m8u1@sbs.com")) {
            //Terminal Registration
            loginScreen = terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword("m8u1@sbs.com", "12345678");
            newPaymentScreen = loginScreen.click_on_loginbtn_for_User();
        }
        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        //Navigate to Profile screen
        historyScreen.WaitUntilvisibilityOfHistoryScreen();
        ProfileScreen profileScreen=historyScreen.ClickOnProfileButton();
        //Check navigation to Profile screen
        boolean check=historyScreen.Check_Navigation_to_ProfileScreen();
        Comm="User isn't navigated to the Profile screen from the History Screen ";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();
    }
}
