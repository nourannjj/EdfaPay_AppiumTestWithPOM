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

public class HistoryScreen {
    private AndroidDriver driver;
    String check=null,comment=null;boolean expected=false;
    Scanner input;String[] arr;
    List<WebElement> list;
    public HistoryScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }

    private By StatusField=By.id("sbs.softpos.edfaa:id/txt_transactionType");
    private By TransactionAmount=By.id("sbs.softpos.edfaa:id/txt_amount");
    private By CardNumber=By.id("sbs.softpos.edfaa:id/txt_cardNumber");
    private By Date=By.id("sbs.softpos.edfaa:id/txt_date");
    private By HistoryTile=By.xpath("//*[@text='Transaction history']");
    private By elements=By.xpath("//android.widget.TextView");
    private By EmptyText=By.id("sbs.softpos.edfaa:id/txt_empty");
    private By NewTransactionBtn=By.id("sbs.softpos.edfaa:id/nav_payment");
    private By FilterButton=By.id("sbs.softpos.edfaa:id/txt_filter");
    private By ProfileButton=By.id("sbs.softpos.edfaa:id/nav_profile");


    //Function3:Get the transactionType
    public  String GetStatues()
    {
        return (driver.findElement(StatusField).getAttribute("text"));
    }

    //Function"Get Transaction amount
    public  String GetTransactionAmount()
    {
        return (driver.findElement(TransactionAmount).getAttribute("text"));
    }
    //Function"Get Card number
    public  String GetCardNumber()
    {
        return (driver.findElement(CardNumber).getAttribute("text"));
    }

    //open the first payment process
    public TransactionDetailsScreen OpenFirstPaymentProcess()
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(StatusField);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("Payment"))
            {
                element.click();
                break;
            }
        }break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return new TransactionDetailsScreen(driver);
    }
    public void WaitUntilInvisibilityOfHistoryScreen()
    {
        WebDriverWait wait =new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(HistoryTile));
    }
    public void WaitUntilvisibilityOfHistoryScreen()
    {
        WebDriverWait wait =new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(HistoryTile));
    }
    public boolean IsHistoryScreenEmpty()
    {
        WaitUntilvisibilityOfHistoryScreen();
        boolean check=false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
                for (WebElement element : list) {
                    if (element.getAttribute("text").equals("There are no transactions to show"))
                    {
                        check=true;
                        break;
                    }
                }break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        System.out.println(check);
        return check;
    }
    //Function9:Click on the NewTransaction Button
    public NewPaymentScreen ClickOnNewTransactionBtn()
    {
        driver.findElement(NewTransactionBtn).click();
        return new NewPaymentScreen(driver);
    }
    public FilterScreen ClickONFilterButton()
    {
        driver.findElement(FilterButton).click();
        return new FilterScreen(driver);
    }
    public ProfileScreen ClickOnProfileButton()
    {
        driver.findElement(ProfileButton).click();
        return new ProfileScreen(driver);
    }
    public boolean Check_Navigation_to_FilterScreen()
    {
        boolean check=false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
                for (WebElement element : list) {
                    if (element.getAttribute("text").equals("Filter transactions")) {
                        // User logins suucessfully and navigated to the new payment screen
                        check = true;
                    } else if (element.getAttribute("text").equals("Transaction history")) {
                        // User couldn't loginn
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
    public boolean Check_Navigation_to_ProfileScreen()
    {
        boolean check=false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
                for (WebElement element : list) {
                    if (element.getAttribute("text").equals("Profile")) {
                        // User logins suucessfully and navigated to the new payment screen
                        check = true;
                    } else if (element.getAttribute("text").equals("Transaction history")) {
                        // User couldn't loginn
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
    public boolean Check_Navigation_to_HistoryScreen()
    {
        boolean check=false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements);
                for (WebElement element : list) {
                    if (element.getAttribute("text").equals("Transaction history")) {
                        // User logins suucessfully and navigated to the new payment screen
                        check = true;
                    } else if (element.getAttribute("text").equals("Transaction Details")) {
                        // User couldn't loginn
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
    public boolean CheckThePresenceOfOnlyFilterdByTransactionTypeTransactions(String Type)
    {
        boolean check=true;
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(StatusField);
                for (WebElement element : list) {
                    if(Type.equals("Payment")) {
                        if (element.getAttribute("text").equals("Refund") || element.getAttribute("text").equals("Cancel")) {
                            check = false;
                            break;
                        }

                    }
                    else if(Type.equals("Cancel")) {
                        if (element.getAttribute("text").equals("Refund") || element.getAttribute("text").equals("Payment")) {
                            check = false;
                            break;
                        }

                    }
                    else if(Type.equals("Refund")) {
                        if (element.getAttribute("text").equals("Refund") || element.getAttribute("text").equals("Cancel")) {
                            check = false;
                            break;
                        }

                    }
                }
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return check;
    }

    public boolean CheckThePresenceOfOnlyFilterdByDate(int DesiredYear,int DesiredMonth,int DesiredDay)
    {
        boolean check=true;
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(Date);
                for (WebElement element : list) {
                    //Split Date
                    String[]Date =element.getText().split(" ");
                    System.out.println(Date.length);
                    //Convert from String to integer
                    //Convert month to int
                    int Month = 0;
                    String month=Date[0];
                    switch (month) {
                        case "Jan" -> Month = 1;
                        case "Feb" -> Month = 2;
                        case "Mar" -> Month = 3;
                        case "Apr" -> Month = 4;
                        case "May" -> Month = 5;
                        case "Jun" -> Month = 6;
                        case "Jul" -> Month = 7;
                        case "Aug" -> Month = 8;
                        case "Sep" -> Month = 9;
                        case "Oct" -> Month = 10;
                        case "Nov" -> Month = 11;
                        case "Dec" -> Month = 12;
                    }
                    //Convert Day to int
                    int Day=Integer.valueOf(Date[1]);
                    //Convert Year to int
                    int year=Integer.valueOf(Date[2].substring(0,4));

                        if ((year!=DesiredYear) || (Month!=DesiredMonth)||(Day!=DesiredDay)) {
                            check = false;
                            break;
                    }

                }
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return check;
    }
    public boolean CheckThePresenceOfOnlyFilterdByTransactionAmountRange(int start,int end)
    {
        boolean check=true;
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(TransactionAmount);

                for (WebElement element : list) {
                    //convert text of amount filed to int
                    int Amount=Integer.valueOf(element.getAttribute("text"));
                    if((Amount<start)||(Amount>end))
                    {
                            check = false;
                            break;
                    }
                }
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return check;
    }

}
