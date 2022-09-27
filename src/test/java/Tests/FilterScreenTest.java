package Tests;

import Base.BaseTest;
import Screens.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FilterScreenTest extends BaseTest {
    String Comm;
    @Test(description = "Validation on filtering by only transaction type", priority =1)
    public void TC_edfapay_164() throws IOException {

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
        //open history screen then filter screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        FilterScreen filterScreen=historyScreen.ClickONFilterButton();
        //Make type=Payment and click on apply filter
        filterScreen.chooseTypeOfTransaction("Payment");
        historyScreen=filterScreen.ClickOnApplyFilter();
        //Check that only payment transactions appear
        boolean check;
        check=historyScreen.CheckThePresenceOfOnlyFilterdByTransactionTypeTransactions("Payment");
        Comm="Error:Refund and cancel operation appears despite filer transaction type=Payment";
        softAssert.assertEquals(check,true,Comm);

        //Click on filter screen and choose type=refund
       filterScreen=historyScreen.ClickONFilterButton();
       filterScreen.chooseTypeOfTransaction("Refund");
       historyScreen=filterScreen.ClickOnApplyFilter();
       //Check that only Refund transactions appear
        check= historyScreen.CheckThePresenceOfOnlyFilterdByTransactionTypeTransactions("Refund");
        Comm="Error:Payment and cancel operation appears despite filer transaction type=Refund";
        softAssert.assertEquals(check,true,Comm);

        //Click on filter screen and choose type=Cancel
        filterScreen=historyScreen.ClickONFilterButton();
        filterScreen.chooseTypeOfTransaction("Cancel");
        historyScreen=filterScreen.ClickOnApplyFilter();
        //Check that only Cancel transactions appear
        check=historyScreen.CheckThePresenceOfOnlyFilterdByTransactionTypeTransactions("Cancel");
        Comm="Error:Refund and Payment operation appears despite filer transaction type=Cancel";
        softAssert.assertEquals(check,true,Comm);

        softAssert.assertAll();

    }
    @Test(description = "Validation on filtering by only date of operation", priority =2)
    public void TC_edfapay_165() throws IOException {

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
        //open history screen then filter screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        FilterScreen filterScreen=historyScreen.ClickONFilterButton();
        //Filter by date 25/9/2022
        filterScreen.ChooseDate(2022,9,25);
        //Click on Apply
        historyScreen=filterScreen.ClickOnApplyFilter();
        boolean check=historyScreen.CheckThePresenceOfOnlyFilterdByDate(2022,9,25);

        Comm="Filtration by date option isn't work asExpected";
        softAssert.assertEquals(check,true,Comm);
        softAssert.assertAll();

    }
    @Test(description = "Validation on filtering by only amount range", priority =3)
    public void TC_edfapay_166() throws IOException {

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
        //Enter Amount>1000
        newPaymentScreen.EnterAmount("2000");
        pinEntryScreen=newPaymentScreen.ClickOnContinueBtn_PINscreen();
        pinEntryScreen.EnterPINCode("0116");
        ScanCardScreen scanCardScreen=pinEntryScreen.ClickOnContBtn_greaterThan200();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Close Operation status screen
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //Enter Amount<100
        newPaymentScreen.ClearAmountField();
        newPaymentScreen.EnterAmount("50");
        scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Close Operation status screen
        operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //Enter 1000>Amount>100 to make sure transaction history isn't empty
        newPaymentScreen.ClearAmountField();
        newPaymentScreen.EnterAmount("150");
        scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Close Operation status screen
        operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //open history screen then filter screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();

        FilterScreen filterScreen=historyScreen.ClickONFilterButton();
        //Filter by amount range from 100  to 1000
        filterScreen.ChooseStartOfRange("100");
        filterScreen.ChooseEndOfRange("1000");
        //Click on Apply
        historyScreen=filterScreen.ClickOnApplyFilter();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            check1=historyScreen.CheckThePresenceOfOnlyFilterdByTransactionAmountRange(100,1000);

        }
        boolean TotalCheck=(!check)&&check1;
        if (check) Comm="Error:No transaction to show despite the transaction history isn't empty";
        else if(check1==false)Comm="Filtration by Transaction Amount option isn't work asExpected";

        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();

    }
    @Test(description = "Validation on filtering by only the ID(transaction number) ", priority =4)
    public void TC_edfapay_168() throws IOException {

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

        //Enter Amount
        newPaymentScreen.ClearAmountField();
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Open receipt screen to get transaction number
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
        String TransacNumber=receiptScreen.GetTransactionNumber();
        //Close Receipt screen
        newPaymentScreen=receiptScreen.CancelReceiptScreen();
        //open history screen then filter screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        FilterScreen filterScreen=historyScreen.ClickONFilterButton();
        //Filter by transaction number
        filterScreen.EnterTransactionNumber(TransacNumber);
        //Click on Apply
        historyScreen=filterScreen.ClickOnApplyFilter();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            check1=historyScreen.CheckThePresenceOfOnlyFilterdByTransactionNumber(TransacNumber);

        }
        boolean TotalCheck=(!check)&&check1;
        if (check) Comm="Error:No transaction to show despite the transaction history isn't empty";
        else if(check1==false)Comm="Filtration by Transaction Number option isn't work asExpected";

        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();

    }
    @Test(description = "Validation on filtering with the amount range from '0' SAR to '0' SAR", priority =5)
    public void TC_edfapay_169() throws IOException {

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

        //Enter Amount
        newPaymentScreen.ClearAmountField();
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Cancel Operation status screen
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //open history screen then filter screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        FilterScreen filterScreen=historyScreen.ClickONFilterButton();
        //Filter by amount range from 0 to 0
        filterScreen.ChooseStartOfRange("0");
        filterScreen.ChooseEndOfRange("0");
        //Click on Apply
        historyScreen=filterScreen.ClickOnApplyFilter();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen is empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        if(!check)
        {
            Comm="User isn't navigated to Alert screen warning him that end and start of range are equal to zero";
            //Check navigation to Alert Screen
            arr= filterScreen.Check_Navigation_to_AlertScreen(Comm);
            softAssert.assertEquals(arr[0],"true",arr[1]);
        }

        softAssert.assertAll();

    }
    @Test(description = "Validation on filtering with the amount range from 'x' SAR to 'x' SAR", priority =6)
    public void TC_edfapay_170() throws IOException {

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

        //Enter Amount
        newPaymentScreen.ClearAmountField();
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Cancel Operation status screen
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //open history screen then filter screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        FilterScreen filterScreen=historyScreen.ClickONFilterButton();
        //Filter by amount range from 150 to 150
        filterScreen.ChooseStartOfRange("150");
        filterScreen.ChooseEndOfRange("150");
        //Click on Apply
        historyScreen=filterScreen.ClickOnApplyFilter();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen is empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1=true;
        if(!check)
        {
           check1= historyScreen.CheckThePresenceOfOnlyFilterdByTransactionAmountRange(150,150);
        }
        boolean TotalCheck=(!check)&&check1;
        if (check) Comm="Error:No transaction to show despite the transaction history isn't empty";
        else if(check1==false)Comm="Error: the transaction that was expected to appear only of 150 SAR";

        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();

    }
    @Test(description = "Validation on filtering if the start of the amount range > end of the amount range ", priority =7)
    public void TC_edfapay_171() throws IOException {

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

        //Enter Amount
        newPaymentScreen.ClearAmountField();
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Cancel Operation status screen
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        newPaymentScreen=operationStatusScreen.cancelScreen();
        //open history screen then filter screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        FilterScreen filterScreen=historyScreen.ClickONFilterButton();
        //Filter by amount range with start range > End
        filterScreen.ChooseStartOfRange("150");
        filterScreen.ChooseEndOfRange("100");
        //Click on Apply
        historyScreen=filterScreen.ClickOnApplyFilter();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen is empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1=true;
        if(!check)
        {
            Comm="Error : User isn't navigated to alert screen";
            arr=filterScreen.Check_Navigation_to_AlertScreen(Comm);
            softAssert.assertEquals(arr[0],"true",arr[1]);
        }

        else if (check)
        {
            Comm="Error:No transaction to show despite the transaction history isn't empty";
            softAssert.assertEquals(check,false,Comm);
        }
        softAssert.assertAll();

    }
    @Test(description = "Validation on filtering with a part of ID number ", priority =8)
    public void TC_edfapay_172() throws IOException {

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

        //Enter Amount
        newPaymentScreen.ClearAmountField();
        newPaymentScreen.EnterAmount("150");
        ScanCardScreen scanCardScreen=newPaymentScreen.ClickOnContinueBtn_Scanscreen();
        scanCardScreen.WaitUntilInvisibilityOfScanscreen();
        //Open receipt screen to get transaction number
        OperationStatusScreen operationStatusScreen=new OperationStatusScreen(driver);
        receiptScreen=operationStatusScreen.ClickONShowReceiptBtn();
        String TransacNumber=receiptScreen.GetTransactionNumber();
        //Close Receipt screen
        newPaymentScreen=receiptScreen.CancelReceiptScreen();
        //open history screen then filter screen
        HistoryScreen historyScreen=newPaymentScreen.ClickOnHistoryBtn();
        FilterScreen filterScreen=historyScreen.ClickONFilterButton();
        //Filter by Part of transaction number
        String Sub_TransactionNumber=TransacNumber.substring(0,7);
        filterScreen.EnterTransactionNumber(Sub_TransactionNumber);

        //Click on Apply
        historyScreen=filterScreen.ClickOnApplyFilter();
        historyScreen.WaitUntilvisibilityOfHistoryScreen();

        //Check that history screen isn't empty
        boolean check=historyScreen.IsHistoryScreenEmpty();
        boolean check1 = false;
        //History isn't Empty
        if(!check)
        {
            check1=historyScreen.CheckThePresenceOfOnlyFilterdByPartOfTransactionNumber(Sub_TransactionNumber);

        }
        boolean TotalCheck=(!check)&&check1;
        if (check) Comm="Error:No transaction to show despite the transaction history isn't empty";
        else if(check1==false)Comm="Filtration by Part of Transaction Number option isn't work asExpected not only the transactions that start with that pert of transaction number appear";

        softAssert.assertEquals(TotalCheck,true,Comm);
        softAssert.assertAll();


    }
}
