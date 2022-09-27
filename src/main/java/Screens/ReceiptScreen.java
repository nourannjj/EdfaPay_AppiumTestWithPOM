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

public class ReceiptScreen {
    AndroidDriver driver;
    String check=null,comment=null;boolean expected=false;
    Scanner input;String[] arr;
    List<WebElement> list;
    public ReceiptScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By CancelButton=By.id("sbs.softpos.edfaa:id/btn_refund");
    private By RefundButton=By.xpath("//*[@text='Refund']");
    private By SendReceiptBtn=By.xpath("//*[@text='Send receipt']");
    private By CancelScreenBtn=By.id("sbs.softpos.edfaa:id/btn_action");
    private By elements=By.xpath("//android.widget.TextView");

    private By PaymentField=By.id("sbs.softpos.edfaa:id/txt_amount");
    private By CardNumber=By.id("sbs.softpos.edfaa:id/txt_cardNumber");
    private By TransDateAndTime=By.id("sbs.softpos.edfaa:id/txt_paymentDateTime");
    private By Location=By.id("sbs.softpos.edfaa:id/txt_location");
    private By TransNum=By.id("sbs.softpos.edfaa:id/txt_transactionNumber");
    private By RRN=By.id("sbs.softpos.edfaa:id/txt_rrnNumber");

    //Function:click on the Cancel Button
    public ReceiptScreen ClickONCancelBtn()
    {
        driver.findElement(CancelButton).click();
        return new ReceiptScreen(driver);
    }

    //Function:Check the presence of refund button after 60 sec
    public boolean CheckPresenceOfRefundBtn(AppiumDriver driver)
    {
        //Explicit wait until the refund button is visible
        WebDriverWait wait= new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(RefundButton));
        //Check the visibility and enable of Refund button
        return ((driver.findElement(RefundButton).isEnabled())&&(driver.findElement(RefundButton).isDisplayed()));

    }
    //Function:click on the refund button
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
    public NewPaymentScreen CancelReceiptScreen()
    {
        int attempts = 0;
        while(attempts < 2) {
            try {
                driver.findElement(CancelScreenBtn).click();

                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }

        return new NewPaymentScreen(driver);
    }
    public SendReceiptScreen ClickOnSendReceiptButton()
    {
        driver.findElement(SendReceiptBtn).click();
        return new SendReceiptScreen(driver);
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
        } break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }

    //Check transaction Amount
    public boolean CheckTransactionAmount(String Amount)
    {
        boolean Check=driver.findElement(PaymentField).getAttribute("text").equals(Amount);
        return Check;
    }
    //Check Card number
    public boolean CheckCardNumber(String CardNum)
    {
        boolean Check=driver.findElement(CardNumber).getAttribute("text").equals(CardNum);
        return Check;
    }
    //Check location
    public boolean CheckLocation(String location)
    {
        boolean Check=driver.findElement(Location).getAttribute("text").equals(location);
        return Check;
    }
    public String GetTransactionNumber()
    {
        String Total[]=driver.findElement(TransNum).getText().split(":");
        String TXNumber=Total[1];
        return TXNumber;

    }

}
