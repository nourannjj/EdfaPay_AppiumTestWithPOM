package Tests;

import Base.BaseTest;
import Screens.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class TransactionDetailsScreenTest extends BaseTest {
    String Comm=null;
    File propFile= new File("src/main/resources/configuration.properties");
    Properties props = new Properties();
    FileInputStream inputStream;


    @Test(description = "Validate that the merchant can't refund any transaction ", priority = 1,enabled = false)
    public void TC_edfapay_141() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();

        //Back to login screen
        loginScreen.BackToLoginScreen(driver);

        // Enter Email and Password of Merchant
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        // Enter OTP
        message="Enter OTP";
        otpScreen.EnterOTP(message);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
        // Choose the desired outlet
        newPaymentScreen=outletScreen.ChooseOutlet();

        //Navigate to History screen and click on the last payment
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();
        TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();

        //Check the presence of refund button and try to refund the transaction
        boolean check=transactionDetailsScreen.CheckPresenceOfRefundBtn(driver);
        if(check)
        {
            transactionDetailsScreen.ClickOnRefundBtn_fromSupervisor();
            //Check Navigation to Alert screen
            Comm="Merchant isn't navigated to Alert screen despite his try to refund a transaction";
            arr=transactionDetailsScreen.Check_Navigation_To_AlertScreen(Comm);
            softAssert.assertEquals(arr[0],"true",arr[1]);
        }
        softAssert.assertAll();

    }
    @Test(description = "Validate that the merchant can't cancel any transaction ", priority = 2,enabled = false)
    public void TC_edfapay_142() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        //Check navigation to operation status screen
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        arr= operationStatusScreen.Check_Navigation_To_OperationStautsScreen("nothing");
        if (arr[0].equals("true"))
        {
         //Back to login screen
        loginScreen.BackToLoginScreen(driver);
        }

        // Enter Email and Password of Merchant
        loginScreen.fillemailAndpasword(props.getProperty("MerchantValidEmail"),props.getProperty("MerchantValidPass"));
        OTPScreen otpScreen=loginScreen.click_on_loginbtn_for_Merchant();
        // Enter OTP
        message="Enter OTP";
        otpScreen.EnterOTP(message);
        OutletScreen outletScreen=otpScreen.ClickOnConfirmButton();
        // Choose the desired outlet
        newPaymentScreen=outletScreen.ChooseOutlet();

        //Navigate to History screen and click on the last payment
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();
        TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();

        //Try to cancel the last transaction
        transactionDetailsScreen.ClickONCancelBtn();
        //Check Navigation to Alert screen
        Comm="Merchant isn't navigated to Alert screen despite his try to Cancel a transaction";
        arr=transactionDetailsScreen.Check_Navigation_To_AlertScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);

        softAssert.assertAll();

    }
    @Test(description = "Validation on Payment field on Receipt Page  ", priority =3)
    public void TC_edfapay_143() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Check the value in the Payment field
            check1=transactionDetailsScreen.GetTransactionAmount().equals("100.00");

        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(!check)&&(check1);
        if (check) Comm="Transaction history is empty";
        else if (!check1) Comm="Payment field contains the wrong value";
        softAssert.assertEquals(TotalCheck,true,Comm);

        softAssert.assertAll();

    }
    @Test(description = "validation on the cardholder number field in the receipt screen", priority =4)
    public void TC_edfapay_144() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Check the value in the Card number field
            System.out.println("Enter the last four digits of card number");
            input=new Scanner(System.in);
            String Cardnum=input.nextLine();
            check1=transactionDetailsScreen.GetCardNumber().equals("************"+Cardnum);

        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(!check)&&(check1);
        if (check) Comm="Transaction history is empty";
        else if (!check1) Comm="Card number filed contains the wrong value";
        softAssert.assertEquals(TotalCheck,true,Comm);

        softAssert.assertAll();

    }
    @Test(description = "Validation on the RRN field in Receipt Page", priority =5)
    public void TC_edfapay_147() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Check the value in the RRN field
            check1=!(transactionDetailsScreen.GetRRN().equals("RRN: 000000000000")); //Check that RRN isn't zero

        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(!check)&&(check1);
        if (check) Comm="Transaction history is empty";
        else if (!check1) Comm="RRN filed contains No value";
        softAssert.assertEquals(TotalCheck,true,Comm);

        softAssert.assertAll();

    }
    @Test(description = "Validation on Transaction No field on Receipt Page  ", priority =6)
    public void TC_edfapay_148() throws IOException {

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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Check the value in the TransactionNumber field
            check1=!(transactionDetailsScreen.GetTransactionNumber().equals("Transaction no: ")); //Check that Transaction no. isn't empty

        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(!check)&&(check1);
        if (check) Comm="Transaction history is empty";
        else if (!check1) Comm="Transaction number filed contains No value";
        softAssert.assertEquals(TotalCheck,true,Comm);

        softAssert.assertAll();

    }
    @Test(description = "Validation on Operation type field in Receipt Page  ", priority =7)
    public void TC_edfapay_149() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Check the value in the Operation Type field
            check1=transactionDetailsScreen.GetOperationType().equals("Operation type: PAYMENT");

        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(!check)&&(check1);
        if (check) Comm="Transaction history is empty";
        else if (!check1) Comm="Operation Type filed contains wrong value";
        softAssert.assertEquals(TotalCheck,true,Comm);

        softAssert.assertAll();
    }
    @Test(description = "Validation on operation status field in Receipt Page  ", priority =8)
    public void TC_edfapay_150() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Check the value in the Operation Status field
            check1=transactionDetailsScreen.GetOperationStatus().equals("Operation status: APPROVED");

        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(!check)&&(check1);
        if (check) Comm="Transaction history is empty";
        else if (!check1) Comm="Operation Status filed contains wrong value";
        softAssert.assertEquals(TotalCheck,true,Comm);

        softAssert.assertAll();

    }
    @Test(description = "Validation on Scheme  field in Receipt Page ", priority =9)
    public void TC_edfapay_151() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Check the value in the Scheme field
            check1=transactionDetailsScreen.GetScheme().equals(props.getProperty("Scheme"));

        }
        //Behavior is right if history isn't empty and the last transaction appears at the top of history
        boolean TotalCheck=(!check)&&(check1);
        if (check) Comm="Transaction history is empty";
        else if (!check1) Comm="Scheme filed contains wrong value";
        softAssert.assertEquals(TotalCheck,true,Comm);

        softAssert.assertAll();

    }
    @Test(description = "Validation of the Sending Receipt Button   ", priority =10)
    public void TC_edfapay_152() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Click on the Send Receipt button and check navigation to sending receipt screen
            SendReceiptScreen sendReceiptScreen=transactionDetailsScreen.ClickOnSendReciptButton();
            arr= sendReceiptScreen.Check_Navigation_To_SendingReceiptScreen("nothing");


        }
        Comm="Sending receipt button at the transaction details screen doesn't navigate user to the send receipt screen";
        softAssert.assertEquals(arr[0],"true",Comm);

        softAssert.assertAll();

    }
    @Test(description = "Validation on the back button", priority =11)
    public void TC_edfapay_153() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Click on the Back button and check navigation to History screen
            historyScreen=transactionDetailsScreen.CancelScreen();
            check1=historyScreen.Check_Navigation_to_HistoryScreen();
        }
        Comm="Back button of the transaction details screen doesn't navigate user to transaction history screen";
        softAssert.assertEquals(check1,true,Comm);

        softAssert.assertAll();

    }
    @Test(description = "Validation on the refund button", priority =12)
    public void TC_edfapay_154() throws IOException {
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
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }

        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen = newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();

        //Navigate to History screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            //Open tha last transaction
            TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
            //Wait until visibility of refund button and click on it
            if(transactionDetailsScreen.CheckPresenceOfRefundBtn(driver))
            {
                scanCardScreen=transactionDetailsScreen.ClickOnRefundBtn_fromSupervisor();
                //Check navigation to scan screen
                arr=scanCardScreen.check_remaining_at_ScanCardScreen("nothing");
            }
        }
        Comm="Error:Supervisor isn't navigated to the Scan screen after clicking on the refund button from Transaction Details screen";
        softAssert.assertEquals(arr[0],"true",Comm);

        softAssert.assertAll();

    }
}
