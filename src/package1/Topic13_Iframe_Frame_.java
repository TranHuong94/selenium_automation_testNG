package package1;

import static org.testng.Assert.assertTrue;

import java.util.List;
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

public class Topic13_Iframe_Frame_ {

	WebDriver driver;
	Actions action;
	Select select;

	@BeforeClass
	public void beforeClass() {
	//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

	}


	public void TC_01_Fixed_Popup() {
	//switch vao iframe
		//cách dùng index-số 4 là số thứ tự matching note chứa iframe. kiểu frame là int, k nên dùng, lấy stt từ 0
		driver.switchTo().frame(4);
		//ID hoặc name, kiểu frame là string, trong trg hợp có ID hoặc name là cố định, ít dùng
		//driver.switchTo().frame(arg0)
		// dùng webElement, nên dùng
		//driver.switchTo().frame(arg0)
	
	
	}
	@Test
	public void TC_02_Iframe() {
		driver.get("https://kyna.vn/");
		//switch to iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='_1drq']")).getText(), "169K likes");
		// back to home
		driver.switchTo().defaultContent();
		//
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")));
		driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("John");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("099777777");
		select = new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		
	}
	

		
		


public void TC_04_Double_Click() {
		
		
}
	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
	
		}
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}    
}