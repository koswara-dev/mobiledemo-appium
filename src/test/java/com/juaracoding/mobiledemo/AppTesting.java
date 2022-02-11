package com.juaracoding.mobiledemo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.juaracoding.mobiledemo.page.ZaloraPage;
import com.juaracoding.mobiledemo.utils.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppTesting {

	private final DesiredCapabilities caps = new DesiredCapabilities();
    private AndroidDriver<MobileElement> driver;
    ExtentReports extent;
	ExtentTest logger;
    private ZaloraPage zaloraPage;
    
    @BeforeTest
    public void init() {
    	extent = new ExtentReports (System.getProperty("user.dir") + "/Reporting/ExtentReport.html", true);
    }

    @BeforeSuite
    public void setupDeviceCapabilities() {        
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("deviceName", "192.168.56.104:5555");
        caps.setCapability("appPackage", "com.zalora.android");
        caps.setCapability("appActivity", "pt.rocket.view.activities.MainFragmentActivity");
    }

    @BeforeMethod
    public void spinUpAndroidDriver() {
        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        zaloraPage = new ZaloraPage(driver);
    }

    @Test(description = "Search Function")
    public void test01SearchFunction() {
    	logger = extent.startTest("01 Search Function", "Description");
    	zaloraPage.searchProduct();
        logger.log(LogStatus.PASS, "User can search product");
    }
    
    @Test(description = "Checkout Product")
    public void test02CheckoutProduct() {
    	logger = extent.startTest("02 Checkout Product", "Description");
    	zaloraPage.checkoutProduct();
        logger.log(LogStatus.PASS, "User can checkout product");
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
    	if(result.getStatus() == ITestResult.FAILURE)
        {
            String screenShotPath = Utils.capture(driver, "screenShotName");
//            logger.log(LogStatus.FAIL, result.getThrowable());
            logger.log(LogStatus.FAIL, "Snapshot below: " + logger.addScreenCapture(screenShotPath));
        }
        extent.endTest(logger);
    }
    
    @AfterTest
    public void endreport() {
        extent.flush();
        extent.close();
    }
}
