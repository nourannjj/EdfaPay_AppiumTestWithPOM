package Base;

import Screens.LoginScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public Scanner input;
    public String PIN="0116";

    public String message; public boolean expected=false; public String comment=null; public String check=null;
    public String arr[]=new String[2];
    public DesiredCapabilities caps = new DesiredCapabilities();
    public AndroidDriver driver;
    File propFile= new File("src\\main\\resources\\configuration.properties");
    FileInputStream inputstream;
    private final Properties props = new Properties();

    protected LoginScreen loginScreen;
    @BeforeClass
    public void BeforeMethod() throws IOException {
        inputstream = new FileInputStream(propFile);
        props.load(inputstream);

        caps.setCapability("DeviceName", props.getProperty("DEVICE_NAME"));
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, props.getProperty("APP_PACKAGE"));
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, props.getProperty("APP_ACTIVITY"));
        caps.setCapability("noReset",true);//Don't stop app.Don't clear app data.Don't uninstall app
        caps.setCapability("fullReset",false);//Don't stop app.Don't clear app.Don't uninstall app
        caps.setCapability("appWaitForLaunch", "false");//Wait for splash screen

        driver = new AndroidDriver(new URL(props.getProperty("ServerPath")), caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        loginScreen=new LoginScreen(driver);
    }
}
