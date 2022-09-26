package Screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Scanner;

public class SupervisorListScreen {
    AndroidDriver driver;
    String check=null,comment=null;boolean expected=false;
    Scanner input;String[] arr;
    List<WebElement> list;
    public SupervisorListScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }
    private By supervisorList=By.id("sbs.softpos.edfaa:id/spinner_supervisor");
    private By elements5=By.xpath("//android.widget.TextView");
    private By desiredSup=By.xpath("//*[@text='supervisor2 Merchant8']");
    private By ContinueButton=By.xpath("//*[@text='Continue']");

    public boolean ChecktheSupervisorList(){
        WebDriverWait wait=new WebDriverWait(driver,10);
        driver.findElement(supervisorList).click(); // open the list
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ContinueButton));
        String Sup1 = "supervisor2 Merchant8";
        list=driver.findElements(elements5);
        boolean existed_sup = false;
        for (WebElement element : list) {
            existed_sup = ((element.getAttribute("text")).equals(Sup1));
            if(existed_sup==false)break;

        }
        //close the list
        driver.navigate().back();
        return existed_sup;
    }
    //Choose the Desired outlet
    public SupervisorPasswordScreen ChooseSup()
    {
        driver.findElement(supervisorList).click(); // open the list
        driver.findElement(desiredSup).click();//Sup2 is the desired outlet
        driver.findElement(ContinueButton).click();//Click on ContinueButton
        return new SupervisorPasswordScreen((AndroidDriver) driver);
    }

    public void WaitUntilVisibilityOfSupList()
    {
       WebDriverWait wait=new WebDriverWait(driver,5);
       wait.until(ExpectedConditions.invisibilityOfElementLocated(ContinueButton));
    }
}
