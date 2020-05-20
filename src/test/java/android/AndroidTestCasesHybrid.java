package android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidTestCasesHybrid {

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
		
		droid.get("dummysite");

	}

	@AfterClass
	public void shutdown() {
		droid.quit();
	}

	@Test(enabled = false)
	public void AndroidWebBasicsTest() {

		//Start at Native view performing various actions
		//TouchAction t=new TouchAction(droid);

		//t.tap(tapOptions().withElement(element(checkbox))).perform();

		droid.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

		//Change context
		Set<String> contexts=droid.getContextHandles();

		for(String contextName :contexts)

		{

		System.out.println(contextName);

		}
		
		//Change context to web view of the app
		droid.context("WEBVIEW_com.androidsample.generalstore");

		droid.findElement(By.name("q")).sendKeys("hello");

		droid.findElement(By.name("q")).sendKeys(Keys.ENTER);

		droid.pressKey(new KeyEvent(AndroidKey.BACK));

		//Change context back to Native view
		droid.context("NATIVE_APP");
	}
}
