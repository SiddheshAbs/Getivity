package Getivity;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Projects {
	

	String baseurl = "https://getivity-dev.com/signin";
	public WebDriver driver ; //declare outside of all methods 
	Properties prop;
	
	@BeforeMethod
	public void launchbrowser() throws InterruptedException, IOException {
		
	/*System.setProperty("webdriver.chrome.driver","C:\\Users\\ABS\\eclipse-workspace\\Automationpractice\\drivers\\chromedriver.exe");
	ChromeOptions options  = new ChromeOptions();
	options.setBinary("C:\\Users\\ABS\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
	driver = new ChromeDriver(options);*/
		
	FileInputStream f= new FileInputStream("C:\\Users\\ABS\\eclipse-workspace\\Automationpractice\\src\\Properties");
	prop= new Properties();
	prop.load(f);	
		
	System.setProperty("webdriver.chrome.driver","C:\\Users\\ABS\\Downloads\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.manage().deleteAllCookies();
	Thread.sleep(7000);
	driver.manage().window().maximize();
	driver.get(baseurl);
}
	@Test
	public void create_project_exisiting_client() throws InterruptedException {
		
		driver.findElement(By.id("email")).sendKeys("siddhesh.deokar+0009@acceleratebs.com");
		driver.findElement(By.id("password")).sendKeys("Sid*141#");
		driver.findElement(By.cssSelector(prop.getProperty("submit_btn"))).click();
		//login
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("signout_btn"))));
		driver.findElement(By.xpath("//p[text()='Disney']")).click();
		//Selecting workspace
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait1.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
		driver.findElement(By.xpath(prop.getProperty("manage_tab"))).click();
		//Navigating to Manage tab 
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        driver.findElement(By.xpath("//p[text()='Project']")).click();
        //Navigating to Projects tab
        
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@href='/projects/new']")).click();
        
        // Select the option using the visible text
        Thread.sleep(2000);
	    Select se = new Select(driver.findElement(By.xpath("//select[@name='clientID']")));
	    se.selectByIndex(3); //select client name of your choice
	    
	    String random_name = RandomStringUtils.randomAlphabetic(10);
	    driver.findElement(By.xpath("//input[@name='name']")).sendKeys(random_name);
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//input[@name='code']")).sendKeys(RandomStringUtils.randomNumeric(8));
	    Thread.sleep(2000);
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    //Locating element by link text and store in variable "Element"        		
	    WebElement Element =driver.findElement(By.xpath("(//button[text()='Save project'])[1]"));

        //Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();",Element);
        Thread.sleep(5000);
        driver.findElement(By.xpath("(//button[text()='Save project'])[1]")).click();
        //WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(30));
        //wait3.until(ExpectedConditions
        //        .presenceOfElementLocated(By.xpath("//p[text()='Projects']")));
        
        String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name = random_name + " has been removed."; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion 
	} 
/*	@Test
	public void create_project_new_client() throws InterruptedException {
		
		driver.findElement(By.id("email")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.cssSelector(prop.getProperty("submit_btn"))).click();
		//login
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("signout_btn"))));
		driver.findElement(By.xpath(prop.getProperty("workspace_name"))).click();
		//Selecting workspace
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait1.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
		driver.findElement(By.xpath(prop.getProperty("manage_tab"))).click();
		//Navigating to Manage tab 
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        driver.findElement(By.xpath("//p[text()='Project']")).click();
        //Navigating to Projects tab
        
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@href='/projects/new']")).click();
        
        driver.findElement(By.xpath("//button[normalize-space()='New Client']")).click();
        String client_name= RandomStringUtils.randomAlphabetic(10) + " abc" ;
        driver.findElement(By.xpath("//input[@name='newClientName']")).sendKeys(client_name);
        
	    
	    String random_name = RandomStringUtils.randomAlphabetic(10);
	    driver.findElement(By.xpath("//input[@name='name']")).sendKeys(random_name);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@name='code']")).sendKeys(RandomStringUtils.randomNumeric(8));
	    //Thread.sleep(2000);
	    
	    //JavascriptExecutor js = (JavascriptExecutor) driver;
	    //Locating element by link text and store in variable "Element"        		
	    //WebElement Element =driver.findElement(By.xpath("(//button[text()='Save project'])[1]"));

        //Scrolling down the page till the element is found		
        //js.executeScript("arguments[0].scrollIntoView();",Element);
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//button[text()='Save project'])[1]")).click();
        //WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(30));
        //wait3.until(ExpectedConditions
         //       .presenceOfElementLocated(By.xpath("//p[text()='Projects']")));
        //String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		//String name = random_name + " has been removed."; //concatenate
		
		//Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
} */
}