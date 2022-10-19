package Tests;

import Base.BaseTest;
import Screens.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SendReceiptMailTest extends BaseTest {
    String Comm;
    File propFile= new File("src/main/resources/configuration.properties");
    Properties props = new Properties();
    FileInputStream inputStream;

    @Test(description = "Validation of client receiving the receipt mail", priority = 120)
    public void TC_edfapay_120() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen=new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        ReceiptScreen receiptScreen=new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }

        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm="Error : User isn't navigated to the transaction approved screen";
        arr=scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        //if navigated successfully click on the show receipt button
        if(arr[0].equals("true"))
        {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm="Error: User isn't navigated to the Receipt screen";
            arr=operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0],"true",arr[1]);
            //if navigated successfully to show receipt screen.Check the Card number filed
            if (arr[0].equals("true"))
            {
                //Click on the send receipt button
                SendReceiptScreen sendReceiptScreen= receiptScreen.ClickOnSendReceiptButton();
                Comm="Error:User isn't navigated to the sending receipt screen";
                arr=sendReceiptScreen.Check_Navigation_To_SendingReceiptScreen(Comm);
                softAssert.assertEquals(arr[0],"true",arr[1]);

                //If navigated successfully to send receipt screen  Enter client email and click on send button
                if(arr[0].equals("true"))
                {
                    sendReceiptScreen.InsertEmail(props.getProperty("CardHolderEmail"));
                    sendReceiptScreen.ClickOnSendButton();
                    //Check that client receives reset mail
                    boolean check = sendReceiptScreen.CheckClientReceiveReceiptMail();
                    Comm = "Error:Client doesn't receive the receipt mail";
                    softAssert.assertEquals(check, true, Comm);
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation of the ability to resend the receipt mail again if the cardholder didn't receive it", priority = 121)
    public void TC_edfapay_121() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen=new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        ReceiptScreen receiptScreen=new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }

        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm="Error : User isn't navigated to the transaction approved screen";
        arr=scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        //if navigated successfully click on the show receipt button
        if(arr[0].equals("true"))
        {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm="Error: User isn't navigated to the Receipt screen";
            arr=operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0],"true",arr[1]);
            //if navigated successfully to show receipt screen.Check the Card number filed
            if (arr[0].equals("true"))
            {
                //Click on the send receipt button
                SendReceiptScreen sendReceiptScreen= receiptScreen.ClickOnSendReceiptButton();
                Comm="Error:User isn't navigated to the sending receipt screen";
                arr=sendReceiptScreen.Check_Navigation_To_SendingReceiptScreen(Comm);
                softAssert.assertEquals(arr[0],"true",arr[1]);

                //If navigated successfully to send receipt screen  Enter client email and click on send button
                if(arr[0].equals("true"))
                {
                    sendReceiptScreen.InsertEmail(props.getProperty("CardHolderEmail"));
                    sendReceiptScreen.ClickOnSendButton();
                    //Click on Resend button
                    sendReceiptScreen.ClickONResendButton();
                    //Check that client receives reset mail
                    boolean check = sendReceiptScreen.CheckClientReceiveReceiptMail();
                    Comm = "Error:Client doesn't receive the receipt mail despite clicking on the resend button";
                    softAssert.assertEquals(check, true, Comm);
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation on Sending Receipt  with invalid Email ", priority = 122)
    public void TC_edfapay_122() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen=new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        ReceiptScreen receiptScreen=new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }

        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm="Error : User isn't navigated to the transaction approved screen";
        arr=scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        //if navigated successfully click on the show receipt button
        if(arr[0].equals("true"))
        {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm="Error: User isn't navigated to the Receipt screen";
            arr=operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0],"true",arr[1]);
            //if navigated successfully to show receipt screen.Check the Card number filed
            if (arr[0].equals("true"))
            {
                //Click on the send receipt button
                SendReceiptScreen sendReceiptScreen= receiptScreen.ClickOnSendReceiptButton();
                Comm="Error:User isn't navigated to the sending receipt screen";
                arr=sendReceiptScreen.Check_Navigation_To_SendingReceiptScreen(Comm);
                softAssert.assertEquals(arr[0],"true",arr[1]);

                //If navigated successfully to send receipt screen  Enter client email and click on send button
                if(arr[0].equals("true"))
                {
                    sendReceiptScreen.InsertEmail(props.getProperty("InvalidCardHolderEmail"));//invalid Email
                    sendReceiptScreen.ClickOnSendButton();
                    //Check Remaining at send receipt screen
                    arr = sendReceiptScreen.check_remaining_To_SendReceiptScreen(Comm);
                    //Check that send button is disabled
                    boolean check = sendReceiptScreen.SendButtonEnabled();
                    boolean TotalCheck = (!check) && (arr[0].equals("true"));

                    if (check) Comm = "Send button is enabled despite email format is invalid";
                    else Comm = "Error:User isn't remaining at send receipt screen despite entering invalid email";
                    softAssert.assertEquals(TotalCheck, false, Comm);
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation on exit icon on Sending Receipt Page ", priority = 124)
    public void TC_edfapay_124() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen=new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration=new TerminalRegistration(driver);
        ReceiptScreen receiptScreen=new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check remaining at login screen
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);

        }

        //Check whether terminal is registered or not
        if(!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass")))
        {
            //Terminal Registration
            loginScreen=terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"));
            newPaymentScreen= loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm="Error : User isn't navigated to the transaction approved screen";
        arr=scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        //if navigated successfully click on the show receipt button
        if(arr[0].equals("true"))
        {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm="Error: User isn't navigated to the Receipt screen";
            arr=operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0],"true",arr[1]);
            //if navigated successfully to show receipt screen.Check the Card number filed
            if (arr[0].equals("true")) {
                //Click on the send receipt button
                SendReceiptScreen sendReceiptScreen = receiptScreen.ClickOnSendReceiptButton();
                Comm = "Error:User isn't navigated to the sending receipt screen";
                arr = sendReceiptScreen.Check_Navigation_To_SendingReceiptScreen(Comm);
                softAssert.assertEquals(arr[0], "true", arr[1]);

                //If navigated successfully to send receipt screen  Enter client email and click on send button
                if (arr[0].equals("true"))
                {
                    //Click on exit button
                    receiptScreen=sendReceiptScreen.CancelScreen();
                    //Check navigation to receipt screen
                    Comm="Error:User isn't navigated to receipt screen after clicking on the exit button at SendReceipt Screen";
                    arr=receiptScreen.Check_Navigation_To_ReceiptScreen(Comm);
                    softAssert.assertEquals(arr[0], "true", arr[1]);
                }
            }
        }
        softAssert.assertAll();
    }
}
