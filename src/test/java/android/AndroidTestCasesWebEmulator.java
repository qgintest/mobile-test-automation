package android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidTestCasesWebEmulator {

	AndroidDriver<AndroidElement> droid;

	@BeforeClass
	public void setup() throws MalformedURLException {

		// Builds your JSON-based request to send to your Appium Server
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2-Emulator");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		cap.setCapability("chromedriverExecutable",
				"C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver\\win\\chromedriver.exe");

		/*
		 * http: protocol when providing URLs 127.0.0.1: Localhost IP Address of any
		 * machine 4723: The current port appium server is running on wd: webdriver hub:
		 * where the live connection will be
		 * 
		 */
		droid = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		droid.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterClass
	public void shutdown() {
		droid.quit();
	}

	@Test(enabled = true)
	public void AndroidTestBasics() {
		droid.get("http://google.com");
	}

	@Test(enabled = false)
	public void AndroidWebBasicsTest() {
		droid.get("http://cricbuzz.com");
		droid.findElementByXPath("//a[@href='#menu']").click();
		droid.findElementByCssSelector("a[title='Cricbuzz Home']").click();
		System.out.println(droid.getCurrentUrl());
		
		JavascriptExecutor jse = (JavascriptExecutor) droid;
		jse.executeScript("window.scrollBy(0,480)", "");
		
		Assert.assertTrue(
				droid.findElement(By.xpath("//h4[text()='Top Stories']")).getAttribute("class").contains("header"));
	}

}
