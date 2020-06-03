package ios;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class IOSTCasesNativeSimulator {

	final String iosDemoApp = System.getProperty("user.dir") + File.separator + "sample-apps" + File.separator + "ios"
			+ File.separator + "UICatalog.app";
	IOSDriver<IOSElement> ios;

	@BeforeClass
	public void setup() throws MalformedURLException {

		// Builds your JSON-based request to send to your Appium Server
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		cap.setCapability(MobileCapabilityType.APP, iosDemoApp);

		/*
		 * http: protocol when providing URLs 127.0.0.1: Localhost IP Address of any
		 * machine 4723: The current port appium server is running on wd: webdriver hub:
		 * where the live connection will be
		 * 
		 */
		ios = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		ios.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterClass
	public void shutdown() {
		ios.quit();
	}

	@Test(enabled = false)
	public void iOSTestBasics() {

		ios.findElementByAccessibilityId("Alert Views").click();
		ios.findElementByXPath("//*[@value='Text Entry']").click();
		ios.findElementByClassName("XCUIElementTypeTextField").sendKeys("hello");
		ios.findElementByName("OK").click();
	}

	@Test(enabled = false)
	// https://www.swtestacademy.com/appium-mobile-actions-swipe-tap-press/
	public void iOSScrollTestCase() {
		Dimension size = ios.manage().window().getSize();
		int anchor = (int) (size.height * 0.6);
		int startPoint = (int) (size.width * 0.3);
		int endPoint = (int) (size.width * 0.5);

		new TouchAction(ios).press(point(startPoint, anchor)).waitAction(waitOptions(ofMillis(1000)))
				.moveTo(point(endPoint, anchor)).release().perform();
	}

	@Test(enabled = false)
	public void iosNavigateAndGetTextTestCase() {
		ios.findElementByAccessibilityId("Steppers").click();
		ios.findElementByAccessibilityId("Increment").click();
		ios.findElementByAccessibilityId("Increment").click();
		System.out.println(ios.findElementsByClassName("XCUIElementTypeStaticText").get(0).getText());

		System.out.println(ios.findElementsByClassName("XCUIElementTypeStaticText").get(1).getText());
		ios.findElementByAccessibilityId("Decrement").click();

		System.out.println(ios.findElementsByClassName("XCUIElementTypeStaticText").get(0).getText());
		ios.navigate().back();
	}

	@Test(enabled = true)
	public void iOSDatePickerTestCase() {
		ios.findElementByAccessibilityId("Picker View").click();
		ios.findElementByName("Green color component value").sendKeys("220");
		ios.findElementsByClassName("XCUIElementTypePickerWheel").get(0).sendKeys("55");
		ios.findElementByXPath("//*[@name='Blue color component value']").sendKeys("130");
	}

	@Test(enabled = false)
	public void Alerts() {
		//http://appium.io/docs/en/commands/interactions/touch/scroll/
		//ios..scrollTo("Alert Views").click(); //Deprecated!!

		//ios.findElementByName("Simple").click();

		//ios.switchTo().alert().accept();

	}
}
