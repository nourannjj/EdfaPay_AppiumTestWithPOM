package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProfileScreen {
    private AndroidDriver driver;
    public ProfileScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By logoutBTN=By.id("sbs.softpos.edfaa:id/btn_logout");
    private By UserNameField=By.id("sbs.softpos.edfaa:id/input_userName");
    private By OutletNameFiled=By.id("sbs.softpos.edfaa:id/input_outletName");
    private By TSNFiled=By.id("sbs.softpos.edfaa:id/input_tsn");
    private By LanguageFiled=By.xpath("//*[@text='ENGLISH']");
    private By Arabic=By.xpath("//*[@text='ARABIC']");
    private By English=By.xpath("//*[@text='ENGLISH']");
    public LoginScreen ClickOnlogoutBtn()
    {
        driver.findElement(logoutBTN).click();
        return new LoginScreen(driver);
    }
    public boolean CheckUserNameField(String Name)
    {
        return (driver.findElement(UserNameField).getAttribute("text").equals(Name));
    }
    public boolean CheckOutletNameField(String Outlet)
    {
        return (driver.findElement(OutletNameFiled).getAttribute("text").equals(Outlet));
    }
    public boolean CheckTSNField(String TSN)
    {
        return (driver.findElement(TSNFiled).getAttribute("text").equals(TSN));
    }
    public NewPaymentScreen ChangeLanguageToArabic()
    {
        //open language list
        driver.findElement(LanguageFiled).click();
        //choose Arabic
        driver.findElement(Arabic).click();
        return new NewPaymentScreen(driver);
    }
    public NewPaymentScreen ChangeLanguageToEnglish()
    {
        //open language list
        driver.findElement(Arabic).click();
        //choose Arabic
        driver.findElement(English).click();
        NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
        newPaymentScreen.WaitUntilVisibilityOfPaymentScreen();
        return newPaymentScreen;
    }

}
