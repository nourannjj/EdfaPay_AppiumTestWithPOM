package Screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Scanner;

public class SendReceiptScreen {
    AndroidDriver driver;
    String check=null,comment=null;boolean expected=false;
    Scanner input;String[] arr;
    List<WebElement> list;
    public SendReceiptScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By elements3=By.xpath("//android.widget.TextView");
    private By EmailFiled=By.id("sbs.softpos.edfaa:id/input_email");
    private By SendButton=By.id("sbs.softpos.edfaa:id/btn_send");
    private By ResendButton;
    private By ExitButton=By.id("sbs.softpos.edfaa:id/btn_action");


    //Check navigation to send receipt screen
    public String[] Check_Navigation_To_SendingReceiptScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements3);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Send receipt")) {
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Receipt")) {
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
    //Function:insert Email
    public void InsertEmail(String Email)
    {
        driver.findElement(EmailFiled).sendKeys(Email);
    }
    //Function:Click on SendButton
    public void ClickOnSendButton()
    {
        driver.findElement(SendButton).click();
    }
    //Function:Cancel SendReceipt Screen
    public ReceiptScreen CancelScreen()
    {
        driver.findElement(ExitButton).click();
        return new ReceiptScreen(driver);
    }
    //Function: Check that client receives the receipt mail
    public boolean CheckClientReceiveReceiptMail()
    {
        boolean ReceiveMail = false;

            System.out.println("Did client receive Receipt mail? if yes enter \"y\"");
            input = new Scanner(System.in);
            String Input = input.nextLine();
            ReceiveMail = Input.equals("y");

        return ReceiveMail;
    }
    //Click on the resend button
    public void ClickONResendButton()
    {
        driver.findElement(ResendButton).click();
    }
    //Check remaining at send receipt screen
    public String[] check_remaining_To_SendReceiptScreen(String Comment)
    {
        // Get elements displayed in the screen
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements3);
                for (WebElement element : list) {
                    System.out.println(element.getText());
                    if (element.getAttribute("text").equals("Send receipt")) {//
                        check = "true";
                        break;
                    } else  {
                        comment = Comment;
                        check = "false";

                    }
                }
                break;
            } catch (
                    StaleElementReferenceException e) {
            }
            attempts++;
        }
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;

    }
    public boolean SendButtonEnabled()
    {
        return (driver.findElement(SendButton).isEnabled());

    }
}
