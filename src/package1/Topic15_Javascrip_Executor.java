package package1;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_Javascrip_Executor {

	WebDriver driver;
	WebDriverWait explicitWait;

	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// System.setProperty("webdriver.chrome.driver",
		// ".\\BrowserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;// add kiểu tường minh
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	public void TC_Click_Hidden_Element() {
		// vào trang
		navigateToUrlByJS(driver, "http://live.demoguru99.com/");
		// chỉ lấy ra domain
		String homePage = (String) executeForBrowser(driver, "return document.domain");
		// verify
		Assert.assertEquals(homePage, "live.demoguru99.com");

		// highlight
		highlightElement(driver, "//a[text()='Mobile']");
		// click vào Mobile
		clickToElementByJS(driver, "//a[text()='Mobile']");

		highlightElement(driver,
				"//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS(driver,
				"//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		highlightElement(driver, "//span[text()='Samsung Galaxy was added to your shopping cart.']");
		Assert.assertTrue(getInnerText(driver).contains("Samsung Galaxy was added to your shopping cart."));

		highlightElement(driver, "//a[text()='Customer Service']");
		clickToElementByJS(driver, "//a[text()='Customer Service']");

		highlightElement(driver, "//input[@id='newsletter']");
		scrollToElement(driver, "//input[@id='newsletter']");
		// input
		sendkeyToElementByJS(driver, "//input[@id='newsletter']", "2automation@gmail.com");

		highlightElement(driver, "//button[@title='Subscribe']");
		clickToElementByJS(driver, "//button[@title='Subscribe']");

		highlightElement(driver, "//span[text()='Thank you for your subscription.']");
		// verify chứa text
		Assert.assertTrue(getInnerText(driver).contains("Thank you for your subscription."));

		// vào trang
		navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");
		// chỉ lấy ra domain
		String domainGuru = (String) executeForBrowser(driver, "return document.domain");
		// verify
		Assert.assertEquals(domainGuru, "demo.guru99.com");

	}
	@Test
	public void TC_02_HTML5() {
		navigateToUrlByJS( driver, "https://login.ubuntu.com/");
		
		
		clickToElementByJS( driver, "//button[@data-qa-id='login_button']");
		Assert.assertEquals(getElementHTML5ValidationMessage( driver, "//input[@class='textType']"), expected);
	}

	public void TC_03_Greter_Than_Two_Window_Tab() {

	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

//hàm để get ra ID cho TH có 2 window, tab
	public void switchToWindowByID(String windowID) {
		// lấy hết tất cả các ID của window/ tab đang có
		Set<String> allIDs = driver.getWindowHandles();
		// dùng vòng lặp duyệt qua từng giá trị

		for (String id : allIDs) {
			// mỗi lần duyệt qua 1 giá trị thì kiểm tra điều kiện
			// nếu như nó ko bằng với giá trị đang so sánh
			if (!id.equals(windowID)) {
				// switch vào cái ID đó
				driver.switchTo().window(id);
				sleepInSecond(2);

			}
		}
	}

//hàm để get ra title cho trường hợp nhiều hơn 2 window, tab
	public void switchToWindowByTitle(String expectedWindowTitle) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equals(expectedWindowTitle)) {
				break;

			}
		}
	}

	// hàm đóng tất cả các tab trừ tab parent
	public void closeAllWindowWithoutParent(String windowID) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
			}
		}
	}

	// document.title, document.domain, document.URL...
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	// hàm để lấy đoạn text
	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	// verify đoạn text nào đó có nằm trong text mà get về k
	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

// vào trang nào đó
	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}
	//hàm dùng cho selenium (ít bị fail)
	public void sendkeyToElementBySelenium(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
		
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementHTML5ValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element;
	}

    //hàm random Email
	public String getEmailRandom() {
    	Random rand = new Random();
    	return "automation" + rand + "@gmail.com";
    }
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
