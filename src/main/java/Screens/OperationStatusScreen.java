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

public class OperationStatusScreen {
    AndroidDriver driver;
    String check=null,comment=null;boolean expected=false;
    Scanner input;String[] arr;
    List<WebElement> list;
    public OperationStatusScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By elements=By.xpath("//android.widget.TextView");
    private By ShowReceiptButton =By.xpath("//*[@text='Show receipt']");
    private By CancelScreenBtn =By.id("sbs.softpos.edfaa:id/btn_action");
    private By OperationStatuestitle=By.xpath("//*[@text='Operation status']");


    //Function1:Click on the Receipt button
    public ReceiptScreen ClickONShowReceiptBtn()
    {
        driver.findElement(ShowReceiptButton).click();
        return new ReceiptScreen(driver);
    }
    //Function2:click on the Cancel Button
    public NewPaymentScreen cancelScreen()
    {
        driver.findElement(CancelScreenBtn).click();
        return new NewPaymentScreen(driver);

    }
    public void WaitUntilVisibilityOfOperationStatusScr()
    {
        WebDriverWait wait=new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(OperationStatuestitle));
    }
    //Function7:Check navigation to the receipt screen
    public String[] Check_Navigation_To_ReceiptScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Receipt")) { // receipt screen
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Payment approved")) {// Transaction approved screen
                comment =comm;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";

            }
        }  break;
    } catch (
    StaleElementReferenceException e) {
    }
    attempts++;
}
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function7:Check navigation to the receipt screen
    public String[] Check_Navigation_To_OperationStautsScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Payment approved")) { // operation status screen
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("Transaction history")) {// Transaction history screen
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
}
