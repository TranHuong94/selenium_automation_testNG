package package1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic11_User_Interaction_Part1 {

	WebDriver driver;
	Actions action;
	

	@BeforeClass
	public void beforeClass() {
	driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

	}

	
	public void TC_01_Hover_Mouse() {
		driver.get("https://hn.telio.vn/");
		//hover(di chuột vào)
		action.moveToElement(driver.findElement(By.xpath("//main[@id='maincontent']//span[text()='Đồ uống']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//main[@id='maincontent']//a[text()='Cà phê']")).isDisplayed());
		driver.findElement(By.xpath("//main[@id='maincontent']//a[text()='Cà phê']")).click();

	}
	
	public void TC_02_Click_and_Hold() {
		
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Assert.assertEquals(numberSelect.size(), 12);
		action.clickAndHold(numberSelect.get(0)).moveToElement(numberSelect.get(3)).release().perform();
		//lấy ra những số đc chọn
		List<WebElement> numberSelect1 = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberSelect1.size(), 4);
		for (WebElement number : numberSelect1) {
			System.out.println(number.getText());
		}
	}
	
	
	public void TC_03_Click_and_Hold_Random() {
		
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		//nhấn phim Crtl xuống
		action.keyDown(Keys.CONTROL).perform();
		//chọn các số 2 7 9 10
		action.click(numberSelect.get(2)).click(numberSelect.get(8)).click(numberSelect.get(10)).click(numberSelect.get(11)).perform();
	//nhả phím Ctrl ra
		action.keyUp(Keys.CONTROL).perform();
		//lấy ra những số được chọn
		List<WebElement> numberSelect1 = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberSelect1.size(), 4);
		for (WebElement number : numberSelect1) {
			System.out.println(number.getText());
			//sendkey của chuột: send phím ENTER vào element email của đăng nhập
			//action.sendKeys(driver.findElement(By.xpath("//input[@id='email']")),Keys.ENTER).perform();
		}
		
	
	}
	@Test
public void TC_04_Double_Click() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
		
}
}