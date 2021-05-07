package package1;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic14_Window_Tab {

	WebDriver driver;
	Actions action;
	Select select;

	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Only_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn/");

		// before click
		String parentID = driver.getWindowHandle();

		System.out.print("ID of Page A =" + parentID);

		// click to facebook icon
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();

		// after click

		Set<String> allIDs = driver.getWindowHandles();

		// switch to facebook

		for (String id : allIDs) {
			System.out.println("All ID = " + id);
			// nếu id không bằng allIDs thì switch vào
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}

		// verify URL is corrected of facebook

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

		// back to home

		for (String id : allIDs) {
			System.out.println("All ID = " + id);
			// nếu id không bằng allIDs thì switch vào
			if (id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}
		// verify URL of Kyna

		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");

	}

	public void TC_02_Only_Two_Window_Or_Tab() {
		// TC_02 dùng cách viết ngắn hơn TC_01
		// sử dụng hàm "public void switchToWindowByID(String windowID)"
		driver.get("https://kyna.vn/");

		// before click
		// lấy ra ID của trang hiện tại
		String kynaID = driver.getWindowHandle();

		System.out.print("ID of Page A =" + kynaID);

		// click to facebook icon
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();

		// switch to facebook

		switchToWindowByID(kynaID);
		String facebookID = driver.getWindowHandle();

		// verify URL is corrected of facebook

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

		// back to kyna
		switchToWindowByID(facebookID);

		// verify URL of Kyna

		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");

	}

	@Test
	public void TC_03_Greter_Than_Two_Window_Tab() {
		driver.get("https://kyna.vn/");
		// click to facebook
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		// switch to facebook
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		sleepInSecond(2);

		// verify URL is corrected of facebook

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

		// switch to Kyna
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
		// verify URL of Kyna
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");

		// click to Youtube
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
		// switch to Youtube
		switchToWindowByTitle("Kyna.vn - YouTube");
		sleepInSecond(2);

		// verify URL is corrected of youtube

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/channel/UCsYlsiaTIKYxcapupuyOirA");

		// switch to Kyna
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
		// verify URL of Kyna
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		
		
		// click to PRIMUS
		driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='PRIMUS']")).click();
		// switch to PRIMUS
		switchToWindowByTitle("PRIMUS Homepage");
		sleepInSecond(2);

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

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}