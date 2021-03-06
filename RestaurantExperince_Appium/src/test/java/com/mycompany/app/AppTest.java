package com.mycompany.app;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AppTest {
	AppiumDriver driver;

	@BeforeSuite
	public void setUp() throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Nexus 5X API 27"); // Android Emulator
		capabilities.setCapability("app", System.getProperty("user.dir") + "/apps/Restaurant.apk");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("platformName", "Android");
		driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void addItemtoCart() {

		WebElement menu = new WebDriverWait(driver, Duration.ofSeconds(100)).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc='Item1 Menu']")));
		menu.click();

		WebElement menu1 = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=' Menu1']")));
		menu1.click();

		WebElement addChickentoCart = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//android.view.View[@content-desc='Add Chicken to shopping cart.']")));
		addChickentoCart.click();

		WebElement successMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(By.id("android:id/message")));

		Assert.assertTrue(successMessage.isDisplayed());

		driver.findElement(By.id("android:id/button1")).click();

	}

}