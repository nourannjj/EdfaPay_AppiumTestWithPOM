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
    //Get location
    public String GetLocation()
    {
        String location=driver.findElement(Location).getAttribute("text");
        return location;
    }
    //Get RRN
    public String GetRRN()
    {
        String rrn=driver.findElement(RRN).getAttribute("text");
        return rrn;
    }
    public String GetTransactionNumber()
    {
        String Total[]=driver.findElement(TransNum).getText().split(":");
        String TXNumber=Total[1];
        return TXNumber;

    }
    public String[] Check_Navigation_To_ReceiptScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
                for (WebElement element : list) {
                    if (element.getAttribute("text").equals("Receipt")) {
                        check = "true";
                        expected = true;
                    } else if (element.getAttribute("text").equals("Send receipt")) {
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
    //Function: Check that Supervisor role is changed to user
    public void CheckSupervisorRoleIsChangeToUser()
    {
        boolean mSuspend = false;
        do {
            System.out.println("Did you Change Supervisor2 role to user ? if yes enter \"y\"");
            input = new Scanner(System.in);
            String Input = input.nextLine();
            mSuspend = Input.equals("y");
        } while (!mSuspend);
    }
    //Get Day , Month and Year
    public int[] GetTransactionDate()
    {
       String[] WithDayText= driver.findElement(TransDateAndTime).getText().split(",");
       String[] WithoutDayText=WithDayText[1].split(" ");
       int Day=Integer.valueOf(WithoutDayText[1]);
       String month=WithoutDayText[2];
       int Month=0;
        switch (month) {
            case "January" -> Month = 1;
            case "February" -> Month = 2;
            case "March" -> Month = 3;
            case "April" -> Month = 4;
            case "May" -> Month = 5;
            case "June" -> Month = 6;
            case "July" -> Month = 7;
            case "August" -> Month = 8;
            case "September" -> Month = 9;
            case "October" -> Month = 10;
            case "November" -> Month = 11;
            case "December" -> Month = 12;
        }
       int Year =Integer.valueOf(WithoutDayText[3].substring(0,4));

       int[] TransactionDate={Day,Month,Year};
       return TransactionDate;
    }
}
