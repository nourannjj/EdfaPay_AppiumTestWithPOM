package Tests;

import Base.BaseTest;
import Screens.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PINEntryScreenTest extends BaseTest {
    String Comm;
    File propFile= new File("src/main/resources/configuration.properties");
    Properties props = new Properties();
    FileInputStream inputStream;

    @Test(description = "Validation of navigating to the transaction approved screen after entering the correct pin.", priority = 1)
    public void TC_edfapay_076() throws IOException {
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
        //Enter Amount and navigate to the PIN screen
        newPaymentScreen.EnterAmount("500");
        pinEntryScreen = newPaymentScreen.ClickOnContinueBtn_PINscreen();
        pinEntryScreen.EnterPINCode("0116");
        ScanCardScreen scanCardScreen=pinEntryScreen.ClickOnContBtn_greaterThan200();//navigate to scan card screen
        //wait until invisibility of scan screen
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to transaction approved screen
        Comm="Error: User isn't navigate to transaction approved screen";
        arr=scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0],"true",arr[1]);
        softAssert.assertAll();
    }
    @Test(description = "Validate that the PIN code isn't displayed and presented in *******", priority = 2)
    public void TC_edfapay_077() throws IOException {
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
        //Enter Amount and navigate to the PIN screen
        newPaymentScreen.EnterAmount("500");
        pinEntryScreen = newPaymentScreen.ClickOnContinueBtn_PINscreen();
        pinEntryScreen.EnterPINCode("0116");
        //Read data in PIN field
        String PIN=pinEntryScreen.ReadDataInPINFiled();
        Comm="PIN isn't represented in the **** format";
        softAssert.assertEquals(PIN,"****",Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation of declining the transaction if PIN code is invalid transaction ", priority = 3)
    public void TC_edfapay_080() throws IOException {
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
        //Enter Amount and navigate to the PIN screen
        newPaymentScreen.EnterAmount("500");
        pinEntryScreen = newPaymentScreen.ClickOnContinueBtn_PINscreen();
        pinEntryScreen.EnterPINCode("0000");//Wrong PIN
        ScanCardScreen scanCardScreen=pinEntryScreen.ClickOnContBtn_greaterThan200();//navigate to scan card screen
        //wait until invisibility of scan screen
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        Comm="User isn't navigated to the transaction declined screen despite entering wrong PIN";
        arr=scanCardScreen.Check_Navigation_To_TransactionDeclinedScreen(Comm);
        softAssert.assertEquals(arr[0],"true",Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation on the Reset button", priority = 4)
    public void TC_edfapay_082() throws IOException {
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
        //Enter Amount and navigate to the PIN screen
        newPaymentScreen.EnterAmount("500");
        pinEntryScreen = newPaymentScreen.ClickOnContinueBtn_PINscreen();
        pinEntryScreen.EnterPINCode("0116");
        //Click on reset button
        pinEntryScreen.ClickOnResetOTPbtn();
        //Read the data in the PIN filed
        Comm="PIN field isn't empty despite clicking on the Reset button";
        String PIN=pinEntryScreen.ReadDataInPINFiled();
        softAssert.assertEquals(PIN,"****",Comm);
        softAssert.assertAll();
    }
    @Test(description = "Validation of the presence of the PIN ENTRY screen in the refund process done by a supervisor account", priority = 5)
    public void TC_edfapay_084() throws IOException {
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass")))
        {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"), props.getProperty("Sup1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("500");
        pinEntryScreen=newPaymentScreen.ClickOnContinueBtn_PINscreen();
        pinEntryScreen.EnterPINCode("0116");
        ScanCardScreen scanCardScreen=pinEntryScreen.ClickOnContBtn_greaterThan200();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Click on the show receipt button
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
        //Click on the refund button
        scanCardScreen=receiptScreen.ClickOnRefundBtn_fromSupervisor();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to PIN entry screen after scanning card
        Comm="Error : Supervisor isn't navugated to PIN Entry Screen to complete the refund process";
        arr=pinEntryScreen.check_Navigating_at_PINEntryScreen(Comm);
        softAssert.assertEquals(arr[0],"true",Comm);
        softAssert.assertAll();
    }

}
