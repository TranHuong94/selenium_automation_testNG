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

public class Topic16_Upload_File {

	WebDriver driver;
	Actions action;
	Select select;
	String seleniumPath = "C:\\Users\\Huong\\git\\selenium-webdriver-java-testng\\upload\\Khai báo y tế.PNG";

	@BeforeClass
	public void beforeClass() {
	driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

	}

	@Test
	public void TC_01_One_File_One_Time() {
	driver.get("http://blueimp.github.io/jQuery-File-Upload/");
	WebElement upLoad = driver.findElement(By.xpath("//input[@type='file']"));
	//truyền đường dẫn đến file cần upload vào
	upLoad.sendKeys(seleniumPath);
	sleepInSecond(3);
	
	//verify load thành công
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Khai báo.PNG']")).isDisplayed());
	//Click start Upload 
	driver.findElement(By.xpath("//table[@class='table table-striped']//button[@class='btn btn-primary start']")).click();
	//verify upload thành công
	//Assert.assertTrue(driver.findElement(By.xpath("//a[text()= 'Khai báo.PNG']")).isDisplayed());
	}
	
	public void TC_02_Iframe() {
		
		
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