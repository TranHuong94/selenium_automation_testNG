package package1;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic12_Popup {

	WebDriver driver;
	Actions action;
	

	@BeforeClass
	public void beforeClass() {
	driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

	}


	public void TC_01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@id='Loginform']"))).perform();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		
		//verify popup display
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']/div")).isDisplayed());
	 
		//close pupup
		driver.findElement(By.xpath("//button[@onclick='ResetForm()']")).click();
		sleepInSecond(3);
		//verify popup no display
		
	Assert.assertFalse(driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']/div")).isDisplayed());
	
	
	}
	
	public void TC_02_Popup_In_DOM() {
		driver.get("https://bni.vn/");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='sgpb-popup-dialog-main-div-theme-wrapper-1 sg-animated sgpb-slideInUp']/div")).isDisplayed());
		 //close popup
		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='sgpb-popup-dialog-main-div-theme-wrapper-1 sg-animated sgpb-slideInUp']/div")).isDisplayed());
		
		
	}
	
	@Test
	public void TC_03_Click_and_Hold_Random() {
        driver.get("https://blog.testproject.io/");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed());
		 //close popup
		//driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();	
		
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