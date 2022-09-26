package Screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.Scanner;

public class FilterScreen {
    private AndroidDriver driver;
    public FilterScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By TypeOfTransaction=By.id("sbs.softpos.edfaa:id/input_types");
    private By TransactionNumber=By.id("sbs.softpos.edfaa:id/input_transactionNumber");
    private By DateOfOperation=By.id("sbs.softpos.edfaa:id/input_dateOfOperation");
    private By StartAmount=By.id("sbs.softpos.edfaa:id/input_startAmount");
    private By EndAmount=By.id("sbs.softpos.edfaa:id/input_endAmount");
    private By ApplyFilterButton=By.id("sbs.softpos.edfaa:id/btn_apply");

    public void chooseTypeOfTransaction(String Type)
    {
        //Open transaction type list
        driver.findElement(TypeOfTransaction).click();
        TypeOfTransactionScreen typeOfTransactionScreen=new TypeOfTransactionScreen(driver);
        switch (Type) {
            case "All" -> typeOfTransactionScreen.ChooseAll();
            case "Cancel" -> typeOfTransactionScreen.ChooseCancel();
            case "Refund" -> typeOfTransactionScreen.ChooseRefund();
            case "Payment" -> typeOfTransactionScreen.ChoosePayment();
        }

    }
    public void EnterTransactionNumber(String TxNum)
    {
        driver.findElement(TransactionNumber).sendKeys(TxNum);
    }
    public void ChooseDate(int DesiredYear,int DesiredMonth,int DesiredDay)
    {
        Scanner input;
        //open date screen
        driver.findElement(DateOfOperation).click();
        DateOfOperationScreen dateOfOperationScreen=new DateOfOperationScreen(driver);
        dateOfOperationScreen.Choose_Year_Month_Day(DesiredYear,DesiredMonth,DesiredDay);
    }
    public void ChooseStartOfRange(String Start)
    {
        driver.findElement(StartAmount).sendKeys(Start);
    }
    public void ChooseEndOfRange(String End)
    {
        driver.findElement(EndAmount).sendKeys(End);
    }
    public HistoryScreen ClickOnApplyFilter()
    {
        driver.findElement(ApplyFilterButton).click();
        return new HistoryScreen(driver);
    }


}
