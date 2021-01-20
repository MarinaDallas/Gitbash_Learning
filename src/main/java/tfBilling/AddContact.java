package tfBilling;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import org.openqa.selenium.support.ui.WebDriverWait;

public class AddContact {

	WebDriver driver;

	@Before
	public void init() {
		//System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
		driver = new FirefoxDriver();


		driver.get("https://www.techfios.com/billing/?ng=admin/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void customerAddandValidation() throws InterruptedException {

		Assert.assertEquals("Page Title is not found", "Login - iBilling", driver.getTitle());
		System.out.println(driver.getTitle());

		WebElement UserName_Field = driver.findElement(By.id("username"));
		WebElement Password_Field = driver.findElement(By.id("password"));
		WebElement SignIn_Button = driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"));

		UserName_Field.sendKeys("demo@techfios.com");
		Password_Field.sendKeys("abc123");
		SignIn_Button.click();

		 
		WebElement Dashboard_Page = driver.findElement(By.xpath("//h2[contains(text(),' Dashboard ')]"));
		Assert.assertEquals("Dashboard Page not found", "Dashboard", Dashboard_Page.getText());
		System.out.println(Dashboard_Page.getText());

		WebElement Customers_Button = driver.findElement(By.xpath("//a[descendant::span[contains(text(),'Customers')]]"));
		Customers_Button.click();

		WebElement Add_Customer_Button = driver.findElement(By.xpath("//li[child::a[contains(text(),'Add Customer')]]"));
		Add_Customer_Button.click();

		WebElement AddContact_Form = driver.findElement(By.xpath("//h5[contains(text(),'Add Contact')]"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(AddContact_Form));

		Assert.assertEquals("Add Conatct Form not found", "Add Contact", AddContact_Form.getText());
		System.out.println(AddContact_Form.getText());

		WebElement FullName_Field = driver.findElement(By.xpath("//input[@id='account']"));
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		wait1.until(ExpectedConditions.visibilityOf(FullName_Field));
		FullName_Field.sendKeys("Marina Afroz");

		WebElement Dropdown_Company = driver.findElement(By.xpath("//select[@id='cid']"));

		Select sel = new Select(Dropdown_Company);
		sel.selectByVisibleText("Techfios");

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abcd@gmail.com");
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("334-345-1234");
		driver.findElement(By.id("address")).sendKeys("123 Nice Ave");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Green");
		driver.findElement(By.xpath("//input[@id='state']")).sendKeys("SmoothLand");
		driver.findElement(By.xpath("//input[@id='zip']")).sendKeys("45676");

		WebElement Dropdown_Country = driver.findElement(By.xpath("//select[@id='country']"));
		Select sel1 = new Select(Dropdown_Country);
		sel1.selectByVisibleText("United Kingdom");

		WebElement Dropdown_Group = driver.findElement(By.xpath("//select[@id='group']"));
		Select sel_Group = new Select(Dropdown_Group);
		sel_Group.selectByVisibleText("AUG 2020");

		driver.findElement(By.xpath("//button[@id='submit']")).click();

		WebElement Contacts_Page = driver.findElement(By.xpath("//h2[contains(text(),' Contacts ')]"));
		Assert.assertEquals("Contacts page not found", "Contacts", Contacts_Page.getText());
		System.out.println(Contacts_Page.getText());

		WebElement Dashboard_Element = driver.findElement(By.xpath("//a[descendant::span[contains(text(),'Dashboard')]]"));
		Dashboard_Element.click();

		Customers_Button = driver.findElement(By.xpath("//a[descendant::span[contains(text(),'Customers')]]"));
		Customers_Button.click();

		WebElement ListCustomers_sidePanel = driver.findElement(By.xpath("//a[contains(text(),'List Customers')]"));
		WebDriverWait List_wait = new WebDriverWait(driver, 20);
		List_wait.until(ExpectedConditions.visibilityOf(ListCustomers_sidePanel));
		ListCustomers_sidePanel.click();

		WebElement Search_TextField = driver.findElement(By.xpath("//input[@id='foo_filter']"));
		WebDriverWait Search_wait = new WebDriverWait(driver, 10);
		Search_wait.until(ExpectedConditions.visibilityOf(Search_TextField));
		Search_TextField.sendKeys("Marina Afroz");

		WebElement Validate_Name = driver.findElement(By.xpath("//tr/descendant::td[3]/child::a[contains(text(),'Marina Afroz')]"));
		Assert.assertEquals("Contact Name not found", "Marina Afroz", Validate_Name.getText());
		System.out.println(Validate_Name.getText());

		Thread.sleep(3000);

	}

	@After
	public void teardown() {
		 driver.close();
	}
}
