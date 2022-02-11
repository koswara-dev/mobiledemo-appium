package com.juaracoding.mobiledemo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ZaloraPage {

	private final By inputProduct = By.id("com.zalora.android:id/extended_search_button");
	private final By pilihIndex1 = By.xpath("//android.widget.TextView[contains(@index, 1)]");
	private final By btnYes = By.id("com.zalora.android:id/positiveButton");
	private final By pilihProduk1 = By.xpath("//android.widget.ImageView[contains(@index, 1)]");
	private final By btnBuyNow = By.id("com.zalora.android:id/tv_buy_now");
	private final By inputEmail = By.id("com.zalora.android:id/edit_text_email");
	private final By inputPassword = By.id("com.zalora.android:id/edit_text_password");
	private final By btnLogin = By.id("com.zalora.android:id/login_button");

    private final AndroidDriver<MobileElement> driver;

    public ZaloraPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }
    
    public void waiting() {
    	try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    public void searchProduct() {
    	waiting();
        driver.findElement(inputProduct).click();
        driver.findElement(pilihIndex1).click();
        driver.findElement(btnYes).click();
    }
    
    public void checkoutProduct() {
    	waiting();
        driver.findElement(inputProduct).click();
        driver.findElement(pilihIndex1).click();
        driver.findElement(btnYes).click();
    	driver.findElement(pilihProduk1).click();
    	driver.findElement(btnBuyNow).click();
    	driver.findElement(inputEmail).sendKeys("test");
    	driver.findElement(inputPassword).sendKeys("test");
    	driver.findElement(btnLogin).click();
    }
}
