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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidTestCases {

	final String androidDemoApp = System.getProperty("user.dir") + File.separator + "sample-apps" + File.separator
			+ "android" + File.separator + "api-demos.apk";
	AndroidDriver<AndroidElement> droid;

	@BeforeClass
	public void setup() throws MalformedURLException {

		// Builds your JSON-based request to send to your Appium Server
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2-Emulator");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, androidDemoApp);

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

	@Test(enabled = false)
	public void AndroidTestBasics() {

		// Step 1
		droid.findElementByXPath("//android.widget.TextView[@text='Preference']").click();

		// Step 2
		droid.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();

		// Step 3
		droid.findElementById("android:id/checkbox").click();

		// Step 4
		droid.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();

		// Step 5
		droid.findElementByClassName("android.widget.EditText").sendKeys("Howdy");

		// Step 6
		droid.findElementsByClassName("android.widget.Button").get(1).click();

		// Honorable Mentions
		// droid.findElementByAndroidUIAutomator("text(\"Views\")").click();
		// new UiSelector().property(value)
		// System.out.println(droid.findElementsByAndroidUIAutomator("new
		// UiSelector().clickable(true)").size());
	}

	@Test(enabled = false)
	public void AndroidTestGestures() {
		droid.findElementByAndroidUIAutomator("text(\"Views\")").click();

		WebElement expandListElement = droid.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");

		// Tap:
		TouchAction ta = new TouchAction(droid);
		ta.tap(tapOptions().withElement(element(expandListElement))).perform();

		droid.findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")").click();

		WebElement peopleNamesElement = droid.findElementByAndroidUIAutomator("text(\"People Names\")");

		// LongPress or PressAndHold
		ta.longPress(longPressOptions().withElement(element(peopleNamesElement)).withDuration(ofSeconds(2))).release()
				.perform();

		System.out.println(droid.findElementById("android:id/title").isDisplayed());

	}

	@Test(enabled = false)
	public void AndroidTestSwipe() {

		droid.findElementByAndroidUIAutomator("text(\"Views\")").click();

		droid.findElementByAndroidUIAutomator("text(\"Date Widgets\")").click();

		droid.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();

		droid.findElementByXPath("//*[@content-desc='9']").click();

		// Swipe Left (Long Press + Move)
		WebElement startTime = droid.findElementByXPath("//*[@content-desc='15']");
		WebElement endTime = droid.findElementByXPath("//*[@content-desc='45']");

		TouchAction ta = new TouchAction(droid);
		ta.longPress(longPressOptions().withElement(element(startTime)).withDuration(ofSeconds(2)))
				.moveTo(element(endTime)).release().perform();
	}

	@Test(enabled = false)
	public void AndroidTestScrolling() {

		droid.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		droid.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");

	}

	@Test(enabled = false)
	public void AndroidTestDragAndDrop() {
		droid.findElementByXPath("//android.widget.TextView[@text='Views']").click();
		droid.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();
		WebElement source = droid.findElementsByClassName("android.view.View").get(0);
		WebElement destination = droid.findElementsByClassName("android.view.View").get(1);

		TouchAction t = new TouchAction(droid);
		// longpress(source)/move(destination)//release
		// t.longPress(longPressOptions().withElement(element(source))).moveTo(element(destination)).release().perform();
		t.longPress(element(source)).moveTo(element(destination)).release().perform();
	}

}
