package tfBilling;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddDeposit {
	
	WebDriver driver;
	
	   @Before
		public void launchBrowser() {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			 driver = new ChromeDriver();

			driver.get("https://www.techfios.com/billing/?ng=admin/");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}
	   
		@Test
		public  void Create_New_Accnt() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver,5);
			
			driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
			driver.findElement(By.id("password")).sendKeys("abc123");
			driver.findElement(By.name("login")).click();
			  //driver.findElement(By.linkText("Bank & Cash")).click();
			
			   //driver.findElement(By.xpath("//i[@class='fa fa-university']")).click();
			driver.findElement(By.xpath("//span[text()='Bank & Cash']")).click();
			
			driver.findElement(By.xpath("//a[text()='New Account']")).click();
			
			driver.findElement(By.cssSelector("input#account")).sendKeys("Deposit");
			
			driver.findElement(By.xpath("//input[@id='description']")).sendKeys("Travel");
			
			driver.findElement(By.cssSelector("input[id='balance']")).sendKeys("2000");
			
			driver.findElement(By.id("account_number")).sendKeys("023456789");
			
			driver.findElement(By.xpath("//input[@id='contact_person']")).sendKeys("Marks");
			
			driver.findElement(By.xpath("//input[@id='contact_phone']")).sendKeys("123-456-9876");
			
			//driver.findElement(By.id("ib_url")).sendKeys("xyz");
			
			driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
			
			JavascriptExecutor js =  (JavascriptExecutor) driver;
			  // this will scroll the web page till end
			js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			Thread.sleep(3000);
			
			WebElement user = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("did298"))));
			user.click();
			
}
		@After
		public  void tearDown() {
			//driver.close();
			
		}

}
