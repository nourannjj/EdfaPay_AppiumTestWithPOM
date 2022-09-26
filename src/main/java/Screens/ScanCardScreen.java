package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;

public class ScanCardScreen {
    String check=null,comment=null;boolean expected=false;
    Scanner input;String[] arr;
    List<WebElement> list;
    private AndroidDriver driver;
    public ScanCardScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By BackToLoginScreenBtn=By.xpath("//android.widget.ImageButton");
    private By elements=By.xpath("//android.widget.TextView");
    private By ScandCardtitle=By.xpath("//*[@text='PLACE YOUR CARD']");
    private By SeesionExpiredscr=By.xpath("//*[@text='Session expired']");

    public NewPaymentScreen ClickOnBackbutton()
    {
       driver.findElement(BackToLoginScreenBtn) .click();
       return new NewPaymentScreen(driver);
    }
    public String[] check_remaining_at_ScanCardScreen(String Comment)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
        // Get elements displayed in the screen
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Scan Card")) {
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("New payment process")) {
                comment = Comment;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";
            }
        }break;
    } catch (
    StaleElementReferenceException e) {
    }
    attempts++;
}
        System.out.println(check);
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    public String[] check_Navigating_at_transactionApprovedScreen(String Comment)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
        // Get elements displayed in the screen
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Payment approved")) {
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Card PIN")) {
                comment = Comment;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";
            }
        }break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        System.out.println(check);
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }

    public String[] check_Navigating_at_SessionExpiredScreen(String Comment)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
        // Get elements displayed in the screen
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Session expired")) {
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Scan Card")) {
                comment = Comment;
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
        System.out.println(check);
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    public void WaitUntilInvisibilityOfScanscreen()
    {
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ScandCardtitle));
    }
    //Function3: Check navigation to the transaction approved screen
    public String[] Check_Navigation_To_TransactionApprovedScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
                for (WebElement element : list) {
                    if (element.getAttribute("text").equals("Payment approved")) { //Transaction approved screen
                        check = "true";
                        expected = true;
                    } else if (element.getAttribute("text").equals("Card PIN")) {//PIN code screen
                        comment =comm;
                        check = "false";
                        expected = true;

                    }
                    if (!expected) {
                        comment = "Unexpected output";
                        check = "false";

                    }
                }     break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function6:Check the presence of the transaction declined screen
    public String[] Check_Navigation_To_TransactionDeclinedScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
                for (WebElement element : list) {
                    if (element.getAttribute("text").equals("")) { //Transaction declined screen
                        check = "true";
                        expected = true;
                    } else if (element.getAttribute("text").equals("Payment approved")) {//transaction approved screen
                        comment =comm;
                        check = "false";
                        expected = true;

                    }
                    if (!expected) {
                        comment = "Unexpected output";
                        check = "false";

                    }
                }
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }

}
