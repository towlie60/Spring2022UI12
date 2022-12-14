package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver;
    private Driver() {
    }

    //this will be the class that will totally control our browser and driver
    //it will make sure that there is only one driver instance running at a time

    //in order to prevent anyone from creating a driver instance from anywhere
    //we create private constructor

    //!!! We created this class using Singleton Design Pattern


    //it will not allow you driver = new Driver()


    //this class will contain two methods;
    //1 - for creating a driver
    public static WebDriver getDriver() {
        //before creating a driver we need to make sure there is no driver running
        //first of all, we have to check that our driver is null
        if (driver==null) {
            //here we will create a new fresh driver based on the browser property
            switch (CofigReader.getProperty("browser").toLowerCase()) {
                default:
                    //here is set up for Chrome
                    driver= ChromeWebDriver.loadChromeDriver();
                    break;
                case "firefox":
                    //here will be a code to set up firefox browser
                    driver = FirefoxWebDriver.loadFirefoxDriver();
                    break;
                case "safari":
                    //here is set up for safari
                    driver = new SafariDriver();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    break;
                case "ie":
                    //here is set up for ie
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    break;
            }
        }

        // if it is not null we simply return existing driver
        return driver;
    }


    //2 - for closing your driver

    public static void closeDriver(){
        try {
            if(driver!=null) {
                driver.close();
                driver.quit();
                driver = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            //if(driver !=null)
        }
    }


}
