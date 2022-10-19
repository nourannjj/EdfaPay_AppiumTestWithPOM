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

public class ReceiptScreenTest extends BaseTest {
    String Comm;
    File propFile= new File("src/main/resources/configuration.properties");
    Properties props = new Properties();
    FileInputStream inputStream;


    @Test(description = "validation of showing the receipt if the transaction is approved", priority = 85)
    public void TC_edfapay_085() throws IOException {
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
        }

        softAssert.assertAll();
    }
    @Test(description = "Validation of the cancel button before the 60 seconds the transaction is approved is ended", priority = 86)
    public void TC_edfapay_086() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);
        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"), props.getProperty("User1ValidPass"));
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);

            //Click on the cancel button
            receiptScreen.ClickONCancelBtn();
            //navigate to newPayment screen
            newPaymentScreen=receiptScreen.CancelReceiptScreen();
            //Check statues is changed to Canceled
            HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
            String RealStatus="Cancel";
            String ActualStatus=historyScreen.GetStatues();
            Comm="statues isn't the canceled";
            softAssert.assertEquals(ActualStatus,RealStatus,Comm);
        }
        softAssert.assertAll();
    }
    @Test(description = "validation on the cancel button after 60 seconds of the transaction initiation", priority =87)
    public void TC_edfapay_087() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"), props.getProperty("User1ValidPass"));
            newPaymentScreen=loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            //Wait 60 sec and check the presence of Refund button
            Comm="Refund button isn't working ";
            softAssert.assertEquals(receiptScreen.CheckPresenceOfRefundBtn(driver),true,Comm);
        }
        softAssert.assertAll();
    }
    @Test(description = "validation on refunding the process after 60 seconds the transaction is approved", priority = 88)
    public void TC_edfapay_088() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"), props.getProperty("User1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            //Wait 60 sec and check the presence of Refund button
            Comm="Refund button isn't working ";
            boolean check=receiptScreen.CheckPresenceOfRefundBtn(driver);
            softAssert.assertEquals(check,true,Comm);
            //click on the refund button and check navigation to supervisor list screen
            if(check)
            {
                receiptScreen.ClickOnRefundBtn_fromUser();
                ///Check navigation to supervisor list screen
                Comm="Error:User isn't navigated to the Supervisor list screen";
                arr=receiptScreen.Check_Navigation_To_supervisorListScreen(Comm);
                softAssert.assertEquals(arr[0], "true", arr[1]);
            }

        }
        softAssert.assertAll();

    }
    @Test(description = "validate that supervisor permission is required to complete the refund process", priority = 89)
    public void TC_edfapay_089() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"), props.getProperty("User1ValidPass"));

            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            //Wait 60 sec and check the presence of Refund button
            Comm="Refund button isn't working ";
            boolean check=receiptScreen.CheckPresenceOfRefundBtn(driver);
            softAssert.assertEquals(check,true,Comm);
            //click on the refund button and check navigation to supervisor list screen
            if(check)
            {
                receiptScreen.ClickOnRefundBtn_fromUser();
                ///Check navigation to supervisor list screen
                Comm="Error:Supervisor permission isn't required despite performing refund from user account";
                arr=receiptScreen.Check_Navigation_To_supervisorListScreen(Comm);
                softAssert.assertEquals(arr[0], "true", arr[1]);

            }

        }
        softAssert.assertAll();

    }
    @Test(description = "Validation on requiring not only supervisor's name to continue the refund but also its password", priority = 90)
    public void TC_edfapay_090() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"), props.getProperty("User1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            //Wait 60 sec and check the presence of Refund button
            Comm="Refund button isn't working ";
            boolean check=receiptScreen.CheckPresenceOfRefundBtn(driver);
            softAssert.assertEquals(check,true,Comm);
            //click on the refund button and check navigation to supervisor list screen
            if(check)
            {
                SupervisorListScreen supervisorListScreen=receiptScreen.ClickOnRefundBtn_fromUser();
                ///Check navigation to supervisor list screen
                Comm="Error:Supervisor permission isn't required despite performing refund from user account";
                arr=receiptScreen.Check_Navigation_To_supervisorListScreen(Comm);
                softAssert.assertEquals(arr[0], "true", arr[1]);
                //if navigated to supervisor list screen
                if(arr[0].equals("true"))
                {
                    //Choose any supervisor and click on cont button
                    SupervisorPasswordScreen supervisorPasswordScreen=supervisorListScreen.ChooseSup();
                    //Check navigated to supervisor pass screen
                    Comm="Error:Supervisor password isn't required";
                   arr= supervisorPasswordScreen.Check_Navigation_To_SupervisorPassScreen(Comm);
                   softAssert.assertEquals(arr[0],"true",arr[1]);
                   if (arr[0].equals("true")) driver.navigate().back();
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "validation of containing the supervisor list of all and only supervisors for the outlet terminal is registered to ", priority =98)
    public void TC_edfapay_098() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("User1ValidEmail"),props.getProperty("User1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("User1ValidEmail"), props.getProperty("User1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            //Wait 60 sec and check the presence of Refund button
            Comm="Refund button isn't working ";
            boolean check=receiptScreen.CheckPresenceOfRefundBtn(driver);
            softAssert.assertEquals(check,true,Comm);
            //click on the refund button and check navigation to supervisor list screen
            if(check)
            {
                SupervisorListScreen supervisorListScreen=receiptScreen.ClickOnRefundBtn_fromUser();
                supervisorListScreen.WaitUntilVisibilityOfSupList();
                ///Check navigation to supervisor list screen
                Comm="Error:Supervisor permission isn't required despite performing refund from user account";
                arr=receiptScreen.Check_Navigation_To_supervisorListScreen(Comm);
                softAssert.assertEquals(arr[0], "true", arr[1]);
                //if navigated to supervisor list screen
                if(arr[0].equals("true"))
                {
                    check=supervisorListScreen.ChecktheSupervisorList();
                    //Check navigated to supervisor pass screen
                    Comm="Error:Supervisor list contains supervisors that aren't working for that outlet";
                    softAssert.assertEquals(check,true,Comm);
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation on supervisor can perform refund directly without any permissions", priority =102)
    public void TC_edfapay_102() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"), props.getProperty("Sup1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            //Wait 60 sec and check the presence of Refund button
            Comm="Refund button isn't working ";
            boolean check=receiptScreen.CheckPresenceOfRefundBtn(driver);
            softAssert.assertEquals(check,true,Comm);
            //click on the refund button and check navigation to supervisor list screen
            if(check)
            {
                scanCardScreen=receiptScreen.ClickOnRefundBtn_fromSupervisor();
                ///Check navigation to Scan card screen directly
                Comm="Error:user isn't navigated directly to the scan card screen";
                arr=scanCardScreen.check_remaining_at_ScanCardScreen(Comm);
                softAssert.assertEquals(arr[0], "true", arr[1]);
            }

        }
        softAssert.assertAll();

    }
    @Test(description = "Validation of the ability of the supervisor to perform the refund process after changing his role on web portals from supervisor to a user without a supervisor's permission ", priority = 103)
    public void TC_edfapay_103() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
        ReceiptScreen receiptScreen=new ReceiptScreen(driver);
        SoftAssert softAssert = new SoftAssert();

        //Check that supervisor role is changed to user
        receiptScreen.CheckSupervisorRoleIsChangeToUser();
        //Check remaining at login screen
        arr=loginScreen.check_Remaining_At_LoginScreen("nothing");
        if(!(arr[0].equals("true")))//if Not at login screen and at new payment screen then should be navigated to login screen
        {
            //Back to login screen
            loginScreen.BackToLoginScreen(driver);
        }
        //Check whether terminal is registered or not
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"), props.getProperty("Sup1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            //Wait 60 sec and check the presence of Refund button
            Comm="Refund button isn't working ";
            boolean check=receiptScreen.CheckPresenceOfRefundBtn(driver);
            softAssert.assertEquals(check,true,Comm);
            //click on the refund button and check navigation to supervisor list screen
            if(check)
            {
                SupervisorListScreen supervisorListScreen=receiptScreen.ClickOnRefundBtn_fromUser();
                //check navigation to supervisor list screen not scan screen
                arr=receiptScreen.Check_Navigation_To_supervisorListScreen("nothing");
                String[] arr1=scanCardScreen.check_remaining_at_ScanCardScreen("nothing");
                check=(arr[0].equals("true"))&&(arr1[0].equals("false"));
                Comm="Error:Despite changing Supervisor role to user it still do refund operation without permission";
                softAssert.assertEquals(check,true,Comm);
            }

        }
        softAssert.assertAll();

    }
    @Test(description = "Validation on refunding the same transaction for more than one time ", priority = 104)
    public void TC_edfapay_104() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"), props.getProperty("Sup1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            //Wait 60 sec and check the presence of Refund button
            Comm="Refund button isn't working ";
            boolean check=receiptScreen.CheckPresenceOfRefundBtn(driver);
            softAssert.assertEquals(check,true,Comm);
            //click on the refund button and check navigation to supervisor list screen
            if(check)
            {
                scanCardScreen=receiptScreen.ClickOnRefundBtn_fromSupervisor();
                //Wait untill the invisibility of scan screen and enter pin code
                scanCardScreen.WaitUntilInvisibilityOfScanscreen();
                pinEntryScreen.EnterPINCode("0116");
                scanCardScreen=pinEntryScreen.ClickOnContBtn_greaterThan200();
                //Navigate back to history screen
                newPaymentScreen=operationStatusScreen.cancelScreen();
                HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
                //Click on the first payment process and try to refund it
                TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();
                historyScreen.WaitUntilInvisibilityOfHistoryScreen();
                scanCardScreen=transactionDetailsScreen.ClickOnRefundBtn_fromSupervisor();
                pinEntryScreen.EnterPINCode("0116");
                scanCardScreen=pinEntryScreen.ClickOnContBtn_greaterThan200();
                scanCardScreen.WaitUntilInvisibilityOfScanscreen();
                //Check that the transaction filed by checking not navigating to Payment approved screen
                Comm="User is navigated to the transaction approved screen despite refunding an already refunded process";
                arr=operationStatusScreen.Check_Navigation_To_OperationStautsScreen("nothing");
                softAssert.assertEquals(arr[0],"false",Comm);
            }

        }
        softAssert.assertAll();

    }
    @Test(description = "Validation on refunding a canceled transaction ", priority = 105)
    public void TC_edfapay_105() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen = new OperationStatusScreen(driver);
            receiptScreen = operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            if (arr[0].equals("true"))
            {
                //Cancel Transaction
                receiptScreen=receiptScreen.ClickONCancelBtn();
                newPaymentScreen= receiptScreen.CancelReceiptScreen();//Navigate back to new payment screen
                //open history screen and choose the payment process ant try to refund it
                HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
                TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();//Open the last payment
                historyScreen.WaitUntilInvisibilityOfHistoryScreen();
                scanCardScreen=transactionDetailsScreen.ClickOnRefundBtn_fromSupervisor();//Refund it
                pinEntryScreen.EnterPINCode("0116"); //Enter PIN code
                scanCardScreen=pinEntryScreen.ClickOnContBtn_greaterThan200();
                scanCardScreen.WaitUntilInvisibilityOfScanscreen();
                Comm="Error:User is navigated to the transaction approved screen despite refunding a process that is already canceled";
                arr=operationStatusScreen.Check_Navigation_To_OperationStautsScreen("nothing");
                softAssert.assertEquals(arr[0],"false",Comm);
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation of canceling an already canceled operation", priority = 106)
    public void TC_edfapay_106() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen = new OperationStatusScreen(driver);
            receiptScreen = operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            if (arr[0].equals("true"))
            {
                //Cancel Transaction
                receiptScreen=receiptScreen.ClickONCancelBtn();
                newPaymentScreen= receiptScreen.CancelReceiptScreen();//Navigate back to new payment screen
                //open history screen and choose the payment process ant try to refund it
                HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
                TransactionDetailsScreen transactionDetailsScreen=historyScreen.OpenFirstPaymentProcess();//Open the last payment
                transactionDetailsScreen=transactionDetailsScreen.ClickONCancelBtn();//Cancel transaction
                Comm="Error:User is navigated to the transaction approved screen despite cancelling a process that is already canceled";
                arr=transactionDetailsScreen.Check_Remainig_At_Transaction_DetailsScreen("nothing");
                softAssert.assertEquals(arr[0],"true",Comm);
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "validation on refunding a transaction with another card than the previously used in the purchase", priority = 107)
    public void TC_edfapay_107() throws IOException {
        inputStream = new FileInputStream(propFile);
        props.load(inputStream);

        NewPaymentScreen newPaymentScreen = new NewPaymentScreen(driver);
        PINEntryScreen pinEntryScreen = new PINEntryScreen(driver);
        TerminalRegistration terminalRegistration = new TerminalRegistration(driver);
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
        if (!loginScreen.checkThatTerminalRegistered(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"))) {
            //Terminal Registration
            terminalRegistration.TerminalReg();
            //login with user credentials
            loginScreen.fillemailAndpasword(props.getProperty("Sup1ValidEmail"),props.getProperty("Sup1ValidPass"));
            loginScreen.click_on_loginbtn_for_User();
        }
        //Enter Amount
        newPaymentScreen.EnterAmount("100");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Check navigation to the transaction approved screen
        Comm = "Error : User isn't navigated to the transaction approved screen";
        arr = scanCardScreen.check_Navigating_at_transactionApprovedScreen(Comm);
        softAssert.assertEquals(arr[0], "true", arr[1]);
        //if navigated successfully click on the show receipt button
        if (arr[0].equals("true")) {
            //Click on the show receipt button
            OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
            receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
            //Check navigation to the Receipt screen
            Comm = "Error: User isn't navigated to the Receipt screen";
            arr = operationStatusScreen.Check_Navigation_To_ReceiptScreen(Comm);
            softAssert.assertEquals(arr[0], "true", arr[1]);
            //Wait 60 sec and check the presence of Refund button
            Comm="Refund button isn't working ";
            boolean check=receiptScreen.CheckPresenceOfRefundBtn(driver);
            softAssert.assertEquals(check,true,Comm);
            //click on the refund button and check navigation to supervisor list screen
            if(check)
            {
                scanCardScreen=receiptScreen.ClickOnRefundBtn_fromSupervisor();
                System.out.println("Scan with another card");
                pinEntryScreen.EnterPINCode("0116");
                scanCardScreen=pinEntryScreen.ClickOnContBtn_greaterThan200();
                scanCardScreen.WaitUntilInvisibilityOfScanscreen();
                //Check not navigating to transaction approved screen
                Comm="Error: refunding with another card done successfully";
                arr=operationStatusScreen.Check_Navigation_To_OperationStautsScreen(Comm);
                softAssert.assertEquals(arr[0],"false",Comm);
            }

        }
        softAssert.assertAll();

    }
                ////////////////Receipt Screen TestCases ////////////////////////////////

    @Test(description = "Validation on Payment field on Receipt Page  ", priority = 110)
    public void TC_edfapay_110() throws IOException {
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
            //if navigated successfully to show receipt screen.Check the payment filed
            if (arr[0].equals("true"))
            {
                Comm = "Payment filed contain wrong value";
                softAssert.assertEquals(receiptScreen.CheckTransactionAmount("100"), true, Comm);
            }
        }

        softAssert.assertAll();
    }
    @Test(description = "validation on the cardholder number field in the receipt screen", priority = 111)
    public void TC_edfapay_111() throws IOException {
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
                Comm = "Card number field contains wrong value";
                System.out.println("Enter the last four digits in card");
                input = new Scanner(System.in);
                String text = input.nextLine();
                text = "************" + text;
                softAssert.assertEquals(receiptScreen.CheckCardNumber(text), true, Comm);
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "validation on the date, and  the time of the transaction in the receipt screen", priority = 112)
    public void TC_edfapay_112() throws IOException {
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
            //if navigated successfully to show receipt screen
            if (arr[0].equals("true"))
            {
                //The Date from receipt
                int[]Date=receiptScreen.GetTransactionDate();
                int ReceiptDay=Date[0];
                int ReceiptMonth=Date[1];
                int ReceiptYear=Date[2];
                //The Real data
                System.out.println("Enter the Day of transaction");
                input=new Scanner(System.in);
                int ActualDay=input.nextInt();
                System.out.println("Enter the Month of transaction");
                input=new Scanner(System.in);
                int ActualMonth=input.nextInt();
                System.out.println("Enter the Year of transaction");
                input=new Scanner(System.in);
                int ActualYear=input.nextInt();

                boolean check=((ReceiptYear==ActualYear)&&(ReceiptMonth==ActualMonth)&&(ReceiptDay==ActualDay));
                Comm="The Date in the receipt is wrong";
                softAssert.assertEquals(check, true, Comm);
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "validation on the location of the transaction in the receipt screen", priority = 113)
    public void TC_edfapay_113() throws IOException {
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
                Comm = "Location filed is empty";
                boolean check=!(receiptScreen.GetLocation().equals(""));//Check that location filed isn't empty
                softAssert.assertEquals(check, true, Comm);
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation on RRN  field in Receipt Page ", priority = 114)
    public void TC_edfapay_114() throws IOException {
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
                Comm = "RRN filed is empty";
                boolean check=!(receiptScreen.GetRRN().equals(""));//Check that RRN filed isn't empty
                softAssert.assertEquals(check, true, Comm);
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation on Transaction No field on Receipt Page  ", priority = 115)
    public void TC_edfapay_115() throws IOException {
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
                Comm = "Transaction number filed is empty";
                boolean check=!(receiptScreen.GetTransactionNumber().equals(""));//Check that Transaction number field filed isn't empty
                softAssert.assertEquals(check, true, Comm);
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation of the cancel button", priority = 116)
    public void TC_edfapay_116() throws IOException {
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
                //Click on the cancel button
                receiptScreen.ClickONCancelBtn();
                //navigate to newPayment screen
                newPaymentScreen=receiptScreen.CancelReceiptScreen();
                //Check statues is changed to Canceled
                HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
                String RealStatus="Cancel";
                String ActualStatus=historyScreen.GetStatues();
                Comm="statues isn't the canceled";
                softAssert.assertEquals(ActualStatus,RealStatus,Comm);
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation of the refund button", priority = 117)
    public void TC_edfapay_117() throws IOException {
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
                //Wait 60 sec and check the presence of Refund button
                Comm="Refund button isn't working ";
                boolean check=receiptScreen.CheckPresenceOfRefundBtn(driver);
                softAssert.assertEquals(check,true,Comm);
                //click on the refund button and check navigation to supervisor list screen
                if(check)
                {
                    scanCardScreen=receiptScreen.ClickOnRefundBtn_fromSupervisor();
                    ///Check navigation to Scan card screen directly
                    Comm="Error:user isn't navigated directly to the scan card screen";
                    arr=scanCardScreen.check_remaining_at_ScanCardScreen(Comm);
                    softAssert.assertEquals(arr[0], "true", arr[1]);
                }
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation of the Sending Receipt Button   ", priority = 118)
    public void TC_edfapay_118() throws IOException {
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
            }
        }
        softAssert.assertAll();
    }
    @Test(description = "Validation of the exit icon", priority = 119)
    public void TC_edfapay_119() throws IOException {
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
                //Cancel receipt screen
                newPaymentScreen=receiptScreen.CancelReceiptScreen();
                //check navigation to payment screen
                Comm="Error: canceling the receipt button doesn't navigate user to payment screen";
                boolean check=newPaymentScreen.Check_Navigation_to_NewPaymentScreen();
                softAssert.assertEquals(check,true,Comm);
            }
        }
        softAssert.assertAll();
    }
}
