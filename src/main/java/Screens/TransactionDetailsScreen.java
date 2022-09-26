package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;

public class TransactionDetailsScreen {
    AndroidDriver driver;
    String check=null,comment=null;boolean expected=false;
    Scanner input;String[] arr;
    List<WebElement> list;

    public TransactionDetailsScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By CancelButton=By.id("sbs.softpos.edfaa:id/btn_refund");
    private By RefundButton=By.xpath("//*[@text='Refund']");
    private By SendReceiptBtn=By.xpath("//*[@text='Send receipt']");
    private By CancelScreenBtn=By.xpath("//android.widget.ImageButton");
    private By elements=By.xpath("//android.widget.TextView");
    private By PaymentField=By.id("sbs.softpos.edfaa:id/txt_amount");
    private By CardNumber=By.id("sbs.softpos.edfaa:id/txt_cardNumber");
    private By TransDateAndTime=By.id("sbs.softpos.edfaa:id/txt_paymentDateTime");
    private By Location=By.id("sbs.softpos.edfaa:id/txt_location");
    private By TransNum=By.id("sbs.softpos.edfaa:id/txt_transactionNumber");
    private By RRN=By.id("sbs.softpos.edfaa:id/txt_rrnNumber");
    private By OperationType=By.id("sbs.softpos.edfaa:id/txt_operationType");
    private By OperationStatus=By.id("sbs.softpos.edfaa:id/txt_operationStatus");
    private By Scheme=By.id("sbs.softpos.edfaa:id/txt_schema");
    private By CloseButton=By.xpath("//*[@text='Close']");


    //Function2:click on the Cancel Button
    public TransactionDetailsScreen ClickONCancelBtn()
    {
        driver.findElement(CancelButton).click();
        return new TransactionDetailsScreen(driver);
    }

    //Function3:Check the presence of refund button after 60 sec
    public boolean CheckPresenceOfRefundBtn(AppiumDriver driver)
    {
        //Explicit wait until the refund button is visible
        WebDriverWait wait= new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(RefundButton));
        //Check the visibility and enable of Refund button
        return ((driver.findElement(RefundButton).isEnabled())&&(driver.findElement(RefundButton).isDisplayed()));

    }
    //Function4:click on the refund button
    public SupervisorListScreen ClickOnRefundBtn_fromUser()
    {
        driver.findElement(RefundButton).click();
        return new SupervisorListScreen(driver);
    }
    public ScanCardScreen ClickOnRefundBtn_fromSupervisor()
    {
        driver.findElement(RefundButton).click();
        return new ScanCardScreen(driver);
    }
    public HistoryScreen CancelScreen()
    {
        driver.findElement(CancelScreenBtn).click();
        return new HistoryScreen(driver);
    }
    public String[] Check_Navigation_To_supervisorListScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Supervisor Name")) { //supervisor list Screen
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Receipt")) {//show receipt screen
                comment =comm;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";

            }
        }   break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    public String[] Check_Remainig_At_Transaction_DetailsScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Transaction Details")) {
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Operation status")) {
                comment =comm;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";

            }
        }  break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function3:Get the transactionType
    public  String GetOperationType()
    {
        return (driver.findElement(OperationType).getAttribute("text"));
    }

    //Function"Get Transaction amount
    public  String GetTransactionAmount()
    {
        return (driver.findElement(PaymentField).getAttribute("text"));
    }
    //Function"Get Card number
    public  String GetCardNumber()
    {
        return (driver.findElement(CardNumber).getAttribute("text"));
    }
    //Function"Get Schema type
    public  String GetScheme()
    {
        return (driver.findElement(Scheme).getAttribute("text"));
    }
    //Function"Get Transaction number
    public  String GetTransactionNumber()
    {
        return (driver.findElement(TransNum).getAttribute("text"));
    }
    //Function"Get RRN
    public  String GetRRN()
    {
        return (driver.findElement(RRN).getAttribute("text"));
    }//Function"Get OperationStatus
    public  String GetOperationStatus()
    {
        return (driver.findElement(OperationStatus).getAttribute("text"));
    }

    //Navigation to Alert merchant screen
    public String[] Check_Navigation_To_AlertScreen(String comm)
    {
        String AlertTxt="Your are not authorized to perform this action, only the permitted users can perform this transaction.";
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals(AlertTxt)) {
                check = "true";
                expected = true;
                //Close Alert
                driver.findElement(CloseButton).click();
                break;
            } else if (element.getAttribute("text").equals("Scan Card")) {
                comment =comm;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";

            }
        } break;
    } catch (StaleElementReferenceException e) {
    }
    attempts++;
}
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    public SendReceiptScreen ClickOnSendReciptButton()
    {
        driver.findElement(SendReceiptBtn).click();
        return new SendReceiptScreen(driver);
    }

}
