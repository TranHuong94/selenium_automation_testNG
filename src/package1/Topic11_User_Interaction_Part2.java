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

public class Topic11_User_Interaction_Part2 {

	WebDriver driver;
	Actions action;
	

	@BeforeClass
	public void beforeClass() {
	driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

	}


	public void TC_07_Right_click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
        sleepInSecond(3);
		//verify Quit display
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());
		//hover to Quit
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
	    sleepInSecond(3);
		//verify Quit (visible, hover)
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover')]")).isDisplayed());
		//click to Quit
		action.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
	    sleepInSecond(3);
	    //kiểm tra hiển thị "clicked: quit"
	    Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
	    //click vào "clicked: quit"
	    driver.switchTo().alert().accept();
	    sleepInSecond(3);
	    //verify quit is not display
	    Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());
	}
	@Test
	public void TC_08_Drag_And_Drop_Element() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		
		WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()= 'You did great!']")).isDisplayed());
		
		//verify color
		Assert.assertEquals(getHexaValue(driver.findElement(By.id("droptarget")).getCssValue("background-color")), "#03a9f4");
		
	}
	
	
	public void TC_03_Click_and_Hold_Random() {
		
		
	}
	
	//chuyển color từ hệ Grb sang hexa
	public String getGrbValue (String hexaValue) {
		return Color.fromString(hexaValue).asRgb();
	}
	
	////chuyển color từ hệ hexa sang Grb
	public String getHexaValue (String RgbValue) {
		return Color.fromString(RgbValue).asHex();
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