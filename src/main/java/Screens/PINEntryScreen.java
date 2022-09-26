package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Scanner;

public class PINEntryScreen {
    private AndroidDriver driver;
    String check=null,comment=null;boolean expected=false;
    Scanner input;String[] arr;
    List<WebElement> list;
    public PINEntryScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }

    private By PINfield=By.id("sbs.softpos.edfaa:id/input_code");
    private By ContBTN=By.xpath("//*[@text='Continue']");
    private By ResetOTPbtn=By.xpath("//*[@text='Reset']");
    private By Zero=By.xpath("//*[@text='0']");
    private By One=By.xpath("//*[@text='1']");
    private By Two=By.xpath("//*[@text='2']");
    private By Three=By.xpath("//*[@text='3']");
    private By Four=By.xpath("//*[@text='4']");
    private By Five=By.xpath("//*[@text='5']");
    private By Six=By.xpath("//*[@text='6']");
    private By Seven=By.xpath("//*[@text='7']");
    private By Eight=By.xpath("//*[@text='8']");
    private By Nine=By.xpath("//*[@text='9']");

    private By elements=By.xpath("//android.widget.TextView");
    private By Backbtn=By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

    //Function1: Enter PIN code
    public void EnterPINCode(String PIN)
    {
        driver.findElement(PINfield).sendKeys(PIN);
    }
    //Function2:Click on continue button to enter PIN code
    public OperationStatusScreen ClickOnContBtn_LowerThan200()
    {

        driver.findElement(ContBTN).click();
        return new OperationStatusScreen(driver);
    }

    public ScanCardScreen ClickOnContBtn_greaterThan200()
    {

        driver.findElement(ContBTN).click();
        return new ScanCardScreen(driver);
    }

    //Function:Read the data in the PIN field
    public String ReadDataInPINFiled()
    {
        return (driver.findElement(PINfield).getAttribute("text"));
    }
    //Function5:Check similarity between the Actual and expected data
    public boolean CheckSimilarity(String Actual,String Expected)
    {
        return (Actual.equals(Expected));

    }
    //Function:Click on the reset OTP button
    public void ClickOnResetOTPbtn()
    {

        driver.findElement(ResetOTPbtn).click();
    }
    //Function:Check remaining at PIN scree
    public boolean Check_RemainingAtPINScreen()
    {
        list=driver.findElements(elements);
        boolean check=false;
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Card PIN")) {
                check = true;
            } else if (element.getAttribute("text").equals("New payment process")) {
                check = false;
            }
        }
        return check;
    }
    //Function:close the PIN entry screen and navigate to New Payment screen
    public NewPaymentScreen NavigateBackToNewPaymentScreen()
    {
        driver.findElement(Backbtn).click();
        return new NewPaymentScreen(driver);
    }
    public void ClickOnresetButton()
    {
        driver.findElement(ResetOTPbtn).click();
    }
    public String[] check_Navigating_at_PINEntryScreen(String Comment)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
                // Get elements displayed in the screen
                for (WebElement element : list) {
                    if (element.getAttribute("text").equals("Card PIN")) {
                        check = "true";
                        expected = true;
                    } else if (element.getAttribute("text").equals("Payment approved")) {
                        comment = Comment;
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
        System.out.println(check);
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
}
