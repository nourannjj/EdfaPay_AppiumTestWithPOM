package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Scanner;

public class LoginScreen  {
    private AndroidDriver driver;
    List<WebElement> list;
    String check=null,comment=null,text=null;boolean expected=false;Scanner input;String[] arr;

    public LoginScreen(AndroidDriver driver)
    {
        this.driver=driver;
    }

    private By email_field=By.id("sbs.softpos.edfaa:id/input_email");
    private By password_field=By.id("sbs.softpos.edfaa:id/input_password");
    private By login_btn=By.id("sbs.softpos.edfaa:id/btn_login");
    private By forgetPasswordButton=By.id("sbs.softpos.edfaa:id/txt_forgotPassword");
    private By CancelAlertBtn=By.xpath("//android.widget.Button[@text='Cancel']");
    private By elements2=By.xpath("//android.widget.TextView");



    //Function1:insert email and password
    public void fillemailAndpasword(String email,String Password) {

        driver.findElement(email_field).sendKeys(email);
        driver.findElement(password_field).sendKeys(Password);
    }
    //Function2:click on the login button
    public OTPScreen click_on_loginbtn_for_Merchant() {
        driver.findElement(login_btn).click();
        return new OTPScreen(driver);
    }
    public NewPaymentScreen click_on_loginbtn_for_User()
    {
        driver.findElement(login_btn).click();
        return new NewPaymentScreen(driver);
    }
    //Function3:Is login button enabled
    public boolean Is_logintn_enabled() {
        return (driver.findElement(login_btn).isEnabled());

    }
    //Function4:Clear Email field and password field
    public void ClearEmailAndPass() {
        driver.findElement(email_field).clear();
        driver.findElement(password_field).clear();
    }
    //Function4:Check that it remain in loginScreen
    public String[] check_Remaining_At_LoginScreen(String Comment)
    {
        WebElement element;
        list=driver.findElements(elements2);
        // Get elements displayed in the screen
        for (int i=0;i<list.size();i++) {
            int attempts = 0;
            while(attempts < 2) {
                try {
                    element= (WebElement) driver.findElements(elements2).get(i);
                    text=element.getAttribute("text");

                    break;
                } catch(StaleElementReferenceException e) {
                }
                attempts++;
            }
            if (text.equals("Please, login to your account")) {
                check = "true";
                expected = true;
                System.out.println(check);
            } else if (text.equals("Enter your OTP code")) {//OTP
                comment = Comment;
                check = "false";
                expected = true;

            }
            if (!expected) {
                comment = "Unexpected output";
                check = "false";
            }
        }
        System.out.println(check);
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    public String[] check_Navigation_to_AlertScreen(String Comment)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements2);
        for (WebElement element : list) {
            if (element.getAttribute("text").equals("You'e not authorized to continue due to lack of information that need admin login first, Please contact your admin.")) {
                check = "true";
                expected = true;
                System.out.println(check);
            } else if (element.getAttribute("text").equals("New payment process")) {
                comment = Comment;
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
        System.out.println(check);
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    public String[] check_Navigation_to_LoginAlertScreen(String Comment)
    {
        int attempts = 0;
        while (attempts < 2) {
            try {
                list=driver.findElements(elements2);
                for (WebElement element : list) {
                    if (element.getAttribute("text").equals("Alert!")) {
                        check = "true";
                        expected = true;
                        System.out.println(check);
                    } else if (element.getAttribute("text").equals("New payment process")) {
                        comment = Comment;
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
        System.out.println(check);
        String array[]=new String[2];
        array[0]=check;array[1]=comment;
        return array;
    }
    //Function:Click on the Forget Password button
    public ForgetPasswordScreen ClickOnForgetPasswordButton()
    {
        System.out.println(driver.findElement(forgetPasswordButton).isDisplayed());
        driver.findElement(forgetPasswordButton).click();
        return new ForgetPasswordScreen(driver);
    }
    //Function:Check that User is suspended or not?
    public void CheckThatUserIsSuspended()
    {
        // Check that user is suspended
        boolean uSuspend = false;
        do {
            System.out.println("Did you suspend user1? if yes enter \"y\"");
            input = new Scanner(System.in);
            String Input = input.nextLine();
            uSuspend = Input.equals("y");
        } while (!uSuspend);
    }
    //Function: Check that merchant is suspended
    public void CheckThatMerhantIsSuspended()
    {
        boolean mSuspend = false;
        do {
            System.out.println("Did you suspend Merchant? if yes enter \"y\"");
            input = new Scanner(System.in);
            String Input = input.nextLine();
            mSuspend = Input.equals("y");
        } while (!mSuspend);
    }
    //Function:Check that merchant is activated after being suspended
    public void CheckThatMerchnatIsActivated()
    {
        System.out.println("Activate a suspended merchant");
        // Check that Merchant is Activated
        boolean mActive = false;
        do {
            System.out.println("Did you Activate Merchant? if yes enter \"y\"");
            input = new Scanner(System.in);
            String Input = input.nextLine();
            mActive = Input.equals("y");
        } while (!mActive);
    }
    //Function:Check that User Activated
    public void CheckThatUserIsActivated()
    {
        boolean m_uActive = false;
        do {
            System.out.println("Did you Activate Merchant and its user? if yes enter \"y\"");
            input = new Scanner(System.in);
            String Input = input.nextLine();
            m_uActive = Input.equals("y");
        } while (!m_uActive);

    }
    //Function:Check that User outlet is changed or not
    public void CheckThatUserOutletIsChanged()
    {
        boolean ChangeUserOutlet = false;
        do {
            System.out.println("Did you change user outlet? if yes enter \"y\"");
            input = new Scanner(System.in);
            String Input = input.nextLine();
            ChangeUserOutlet = Input.equals("y");
        } while (!ChangeUserOutlet);

    }
    //Function:Check that User Email is changed or not
    public void CheckThatUserEmailIsChanged()
    {
        boolean ChangeUserEmail = false;
        do {
            System.out.println("Did you change user email \"m8u1_edit@sbs.com\"? if yes enter \"y\"");
            input = new Scanner(System.in);
            String Input = input.nextLine();
            ChangeUserEmail = Input.equals("y");
        } while (!ChangeUserEmail);

    }
    //Function:Check that terminal is registered or not
    public boolean checkThatTerminalRegistered(String email,String Pass)
    {
        boolean flag;
        //Login with user credentials
        fillemailAndpasword(email,Pass);
        NewPaymentScreen newPaymentScreen=click_on_loginbtn_for_User();
        arr=check_Navigation_to_AlertScreen("nothing");
        //check not presence of alert screen
        //Means navigated to alert screen
        flag= !arr[0].equals("true");
        if(flag==false) driver.findElement(CancelAlertBtn).click();
        return flag;
    }
    public LoginScreen BackToLoginScreen(AndroidDriver driver)
    {
        String ScanCardScr="Scan Card",PiNScr="Card PIN",TransApprovscr="Payment approved",Receiptscr="Receipt",SendRecpscr="Send receipt",Historyscr="Transaction history",Detailsscr="Transaction Details",Profilescr="Profile",SupervisorListscr="Supervisor Name",ExpiredScr="Session expired",FilterScreen="Filter transactions",Alert="Alert!";
        //if not at login screen go back
        arr=check_Remaining_At_LoginScreen("nothing");
        int attempts = 0;
        while (attempts < 2)
        {
            try
            {
                list=driver.findElements(elements2);
            for (WebElement element : list) {
                if (element.getAttribute("text").equals(ScanCardScr)||element.getAttribute("text").equals(PiNScr) || element.getAttribute("text").equals(TransApprovscr) || element.getAttribute("text").equals(Receiptscr)  || element.getAttribute("text").equals(SupervisorListscr)||element.getAttribute("text").equals(ExpiredScr))
                {
                        driver.navigate().back();//Back to the newPayment screen
                    break;
                }
                else if(element.getAttribute("text").equals(SendRecpscr)||element.getAttribute("text").equals(FilterScreen)||element.getAttribute("text").equals(Detailsscr))
                {
                    driver.navigate().back();driver.navigate().back();
                }
            }break;
            }
            catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
            NewPaymentScreen newPaymentScreen=new NewPaymentScreen(driver);
            newPaymentScreen.ClickOnProfileBtn();//navigate to the profile screen
            ProfileScreen profileScreen=new ProfileScreen(driver);
            return (profileScreen.ClickOnlogoutBtn());//logout
        }


    }

