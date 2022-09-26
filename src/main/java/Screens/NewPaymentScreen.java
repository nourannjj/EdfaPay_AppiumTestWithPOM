package Screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;

public class NewPaymentScreen {
    private AndroidDriver driver;
    String check=null,comment=null;boolean expected=false;Scanner input;String[] arr;
    List<WebElement> list;

    public NewPaymentScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }

    private By elements3=By.xpath("//android.widget.TextView");
    private By Zero=By.id("sbs.softpos.edfaa:id/btn_zero");
    private By One=By.id("sbs.softpos.edfaa:id/btn_one");
    private By Two=By.id("sbs.softpos.edfaa:id/btn_two");
    private By Three=By.id("sbs.softpos.edfaa:id/btn_three");
    private By Four=By.id("sbs.softpos.edfaa:id/btn_four");
    private By Five=By.id("sbs.softpos.edfaa:id/btn_five");
    private By Six=By.id("sbs.softpos.edfaa:id/btn_six");
    private By Seven=By.id("sbs.softpos.edfaa:id/btn_seven");
    private By Eight=By.id("sbs.softpos.edfaa:id/btn_eight");
    private By Nine=By.id("sbs.softpos.edfaa:id/btn_nine");
    private By Dot=By.id("sbs.softpos.edfaa:id/btn_dot");

    private By AmountField=By.id("sbs.softpos.edfaa:id/input_amount");
    private By ClearButton=By.id("sbs.softpos.edfaa:id/btn_remove");
    private By ContinueButton=By.xpath("//*[@text='Continue']");
    private By NewTransactionBtn=By.id("sbs.softpos.edfaa:id/nav_payment");
    private By AlertText=By.id("sbs.softpos.edfaa:id/txt_body");
    private By HistoryButton=By.id("sbs.softpos.edfaa:id/nav_history");
    private By ProfileButton=By.id("sbs.softpos.edfaa:id/nav_profile");
    private By CancelAlertBtn=By.xpath("//android.widget.Button[@text='Close']");
    private By CancelLocPerScreenBtn=By.xpath("//*[@text='Close']");

    // Function1:Check that user could log in successfully by the presence of the new payments screen
    public boolean Check_Navigation_to_NewPaymentScreen()
    {
        boolean check=false;
        int attempts = 0;
        while (attempts < 2) {
        try {
            list=driver.findElements(elements3);
            for (WebElement element : list) {
                if (element.getAttribute("text").equals("New payment process")) {
                    // User logins successfully and navigated to the new payment screen
                    check = true;
                } else if (element.getAttribute("text").equals("Please, login to your account")) {
                    // User couldn't login
                    check = false;
                }
            }

            break;
        } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return check;
    }
    //Function2: Enter Amount in the Amount field
    public String EnterAmount(String Amount)
    {
        int length= Amount.length();

        for(int i=0;i<length;i++)
        {
            switch (Amount.charAt(i)) {
                case '0' : driver.findElement(Zero).click();break;
                case '1' : driver.findElement(One).click();break;
                case '2' : driver.findElement(Two).click();break;
                case '3' : driver.findElement(Three).click();break;
                case '4' : driver.findElement(Four).click();break;
                case '5' : driver.findElement(Five).click();break;
                case '6' : driver.findElement(Six).click();break;
                case '7' : driver.findElement(Seven).click();break;
                case '8' : driver.findElement(Eight).click();break;
                case '9' : driver.findElement(Nine).click();break;
                case '.' : driver.findElement(Dot).click();
            }
        }
        return Amount;

    }
    //Function3:Read the data in the Amount filed
    public String ReadDataInAmountField()
    {
        return (driver.findElement(AmountField).getText());
    }
    //Function4:Check that data in the Any Field is same as expected
    public boolean CheckSimilarity(String Actual,String Expected)
    {
        return (Actual.equals(Expected));

    }
    //Function5:Click on the clear button
    public void ClickOnClearBtn()
    {
        driver.findElement(ClearButton).click();
    }
    //Function6:Click on the Continue Button
    public ScanCardScreen ClickOnContinueBtn_Scanscreen()
    {
        driver.findElement(ContinueButton).click();
        return new ScanCardScreen(driver);
    }
    public PINEntryScreen ClickOnContinueBtn_PINscreen()
    {
        driver.findElement(ContinueButton).click();
        return new PINEntryScreen(driver);
    }
    //Function7:Check Navigation to the ScanCard Screen
    public String[] Check_Navigation_To_ScanCardScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements3);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Scan Card")) {
                // User must remain at login screen
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("New payment process")) {//ScanCardScreen
                comment =comm;
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
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function8:check whether continue button is enabled or not
    public boolean IsContinueBtnEnabled()
    {
        return (driver.findElement(ContinueButton).isEnabled());
    }
    //Function9:Click on the NewTransaction Button
    public NewPaymentScreen ClickOnNewTransactionBtn()
    {
        driver.findElement(NewTransactionBtn).click();
        return new NewPaymentScreen(driver);
    }
    //Function10:Check that GPS is off
    public boolean IsGPSOff()
    {
        boolean GPSoff = false;
        do {
            System.out.println("Did you Turn off GPS? if yes enter \"y\"");
            input = new Scanner(System.in);
            String Input = input.nextLine();
            GPSoff = Input.equals("y");
        } while (!GPSoff);

        return GPSoff;
    }
    //Function11:Check Navigation to the alert screen and not to the Scan Card Screen
    public String[] Check_Navigation_to_AlertScreen(String comm)
    {
        int attempts=0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements3);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Alert!")) {//Alert Screen
                // User must remain at login screen
                check = "true";
                expected = true;
                //Close Alert
                driver.findElement(CancelAlertBtn).click();
            } else if (element.getAttribute("text").equals("Scan Card")) {//ScanCard screen
                comment =comm;
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
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;

    }
    //Function12:Get Data written in the alert Box
    public String GetTextOfAlert()
    {
        return (driver.findElement(AlertText).getAttribute("text"));
    }
    //Function 13:Click on the Transaction History Button
    public HistoryScreen ClickOnHistoryBtn()
    {
        driver.findElement(HistoryButton).click();
        return new HistoryScreen(driver);
    }
    //Function 14: Check Navigation to the History Screen and not remaining at the new payment screen
    public String[] Check_Navigation_To_HistoryScreen(String comm)
    {
        int attempts=0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements3);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Transaction history")) { //History Screen
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("New payment process")) {
                comment =comm;
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
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function 15:Click on the profile button
    public ProfileScreen ClickOnProfileBtn()
    {
        driver.findElement(ProfileButton).click();
        return new ProfileScreen(driver);
    }
    //Function 16:Check Navigation to ProfileScreen
    public String[] Check_Navigation_To_ProfileScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements3);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Profile")) { //Profile Screen
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("New payment process")) {
                comment =comm;
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
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function 17:Check Remaining at login screen
    public String[] Check_Remaing_To_LoginScreen(String comm)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements3);
        // Check that user couldn't log in successfully by the being exist in login screen
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Please, login to your account")) {
                // User must remain at login screen
                check = "true";
                expected = true;
            } else if (element.getAttribute("text").equals("New payment process")) {
                comment =comm;
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
        String[] array =new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function18:Check appearance of GPS permission screen
    public boolean Check_appearance_of_GPS_permission_screen()
    { boolean check=false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements3);

        for (WebElement element : list)
        {
            if ((element.getAttribute("text").equals("No location access"))) {

                check = true;
            }
            else if (element.getAttribute("text").equals("New payment process")) {

                check = false;
            }
        }break;
    } catch (StaleElementReferenceException e) {
    }
    attempts++;
}
        return check;
    }
    //Function:Cancel the location Access Screen
    public void CancelLocAccessScreen()
    {
        driver.findElement(CancelLocPerScreenBtn).click();
    }

    public boolean IsNfcTurnoff()
    {
        boolean check=false;

            do {
                System.out.println("Did you turn off nfc? if yes enter \"y\"");
                input = new Scanner(System.in);
                String Input = input.nextLine();
                check = Input.equals("y");
            } while (!check);
         return check;
    }
public boolean CheckPaymentScreenIsArabic()
{
    boolean check=false;
    int attempts = 0;
    while (attempts < 2) {
        try {
            list=driver.findElements(elements3);
            for (WebElement element : list) {
                if (element.getAttribute("text").equals("عملية دفع جديدة")) {
                    check = true;
                    break;
                }
            }break;
        } catch (StaleElementReferenceException e) {
        }
        attempts++;
    }
    return check;
}
public void WaitUntilVisibilityOfPaymentScreen()
{
    WebDriverWait wait=new WebDriverWait(driver,10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(One));
}
public void ClearAmountField()
{
    driver.findElement(AmountField).clear();
}
}
