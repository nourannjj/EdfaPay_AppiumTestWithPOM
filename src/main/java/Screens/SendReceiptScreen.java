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
}
