package Screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TypeOfTransactionScreen {
    private AndroidDriver driver;
    public TypeOfTransactionScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By TransactionType=By.xpath("//android.widget.CheckBox");

    private By CancelButton=By.id("sbs.softpos.edfaa:id/txt_cancel");
    private By OKButton=By.id("sbs.softpos.edfaa:id/txt_apply");

    public FilterScreen ChooseAll()
    {
        WebElement element=(WebElement)driver.findElements(TransactionType).get(0);
        element.click();
        driver.findElement(OKButton).click();
        return new FilterScreen(driver);
    }
    public FilterScreen ChoosePayment()
    {
        WebElement element=(WebElement)driver.findElements(TransactionType).get(1);
        element.click();
        driver.findElement(OKButton).click();
        return new FilterScreen(driver);
    }
    public FilterScreen ChooseCancel()
    {
        WebElement element=(WebElement)driver.findElements(TransactionType).get(2);
        element.click();
        driver.findElement(OKButton).click();
        return new FilterScreen(driver);
    }
    public FilterScreen ChooseRefund()
    {
        WebElement element=(WebElement)driver.findElements(TransactionType).get(3);
        element.click();
        driver.findElement(OKButton).click();
        return new FilterScreen(driver);
    }
    public FilterScreen CloseScreen()
    {
        driver.findElement(CancelButton).click();
        return new FilterScreen(driver);
    }
}
