package Getivity;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Teams {


	String baseurl = "https://getivity-dev.com/signin";
	public WebDriver driver ; //declare outside of all methods 
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Properties prop;
	
	
	@BeforeMethod
	public void launchbrowser() throws InterruptedException, IOException {
		
	FileInputStream f= new FileInputStream("C:\\Users\\ABS\\eclipse-workspace\\Automation\\src\\Properties");
	prop= new Properties();
	prop.load(f);	
	
	//System.setProperty("webdriver.chrome.driver","C:\\Users\\ABS\\eclipse-workspace\\Automationpractice\\drivers\\chromedriver.exe");
	//ChromeOptions options  = new ChromeOptions();
	//options.setBinary("C:\\Users\\ABS\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
	//driver = new ChromeDriver(options);
		
	System.setProperty("webdriver.chrome.driver","C:\\Users\\ABS\\Downloads\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.manage().deleteAllCookies();
	Thread.sleep(7000);
	driver.manage().window().maximize();
	driver.get(baseurl);
	
	}
	@Test
	public void member_invite_team_without_role_capacity_skip_project() throws InterruptedException {
		
		
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
		driver.findElement(By.xpath("(//div)[9]")).click();
		//Navigating to Manage tab 
		
		driver.findElement(By.xpath("//a[@href='/team']//p[contains(text(),'Team')]")).click();
		//Navigate to team tab
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Add Person']")).click();
		String fname = RandomStringUtils.randomAlphabetic(10);
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fname);
		String lname = RandomStringUtils.randomAlphabetic(10);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lname);
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);  
		String email = "siddhesh.deokar+"+ randomInt +"@acceleratebs.com";
		//random email generator
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		
		driver.findElement(By.xpath("//button[text()='Invite and continue']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//inviting  team
		
		Thread.sleep(2000);
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String random_name = "Success! We've emailed " + email + " an invitation to join your team."; //concatenate
		
		Assert.assertEquals(e , random_name, "Success message do not match");
		//Assertion
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Save Permission and continue']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Skip']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[text()='Update info']")).click();
		
		String upd_fname =  driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value");
		String upd_lname =	driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value");
		
		//Save permissions
		Thread.sleep(2000);
		String f = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String random_name1 = upd_fname + " " + upd_lname + " has been updated"; //concatenate
		
		Assert.assertEquals(f , random_name1, "Success message do not match");
		
		driver.close();		
}
/*	@Test(dependsOnMethods= {"member_invite_team_without_role_capacity_skip_project"})
	public void admin_invite_team_without_role_capacity_skip_project() throws InterruptedException {
		
		
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
		driver.findElement(By.xpath("(//div)[9]")).click();
		//Navigating to Manage tab 
		
		driver.findElement(By.xpath("//a[@href='/team']//p[contains(text(),'Team')]")).click();
		//Navigate to team tab
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Add Person']")).click();
		String fname = RandomStringUtils.randomAlphabetic(10);
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fname);
		String lname = RandomStringUtils.randomAlphabetic(10);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lname);
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);  
		String email = "siddhesh.deokar+"+ randomInt +"@acceleratebs.com";
		//random email generator
		
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		
		driver.findElement(By.xpath("//button[text()='Invite and continue']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//inviting  team
		
		Thread.sleep(2000);
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String random_name = "Success! We've emailed " + email + " an invitation to join your team."; //concatenate
		
		Assert.assertEquals(e , random_name, "Success message do not match");
		//Assertion
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='Administrator']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Save Permission and continue']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Skip']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[text()='Update info']")).click();
		
		String upd_fname =  driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value");
		String upd_lname =	driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value");
		
		//Save permissions
		Thread.sleep(2000);
		String f = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String random_name1 = upd_fname + " " + upd_lname + " has been updated"; //concatenate
		
		Assert.assertEquals(f , random_name1, "Success message do not match");
		
		driver.close();
} 
	
	@Test(dependsOnMethods= {"member_invite_team_without_role_capacity_skip_project","admin_invite_team_without_role_capacity_skip_project"})
	public void manager_with_permission_invite_team_without_role_capacity_skip_project() throws InterruptedException {
		
		
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
		driver.findElement(By.xpath("(//div)[9]")).click();
		//Navigating to Manage tab 
		
		driver.findElement(By.xpath("//a[@href='/team']//p[contains(text(),'Team')]")).click();
		//Navigate to team tab
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Add Person']")).click();
		String fname = RandomStringUtils.randomAlphabetic(10);
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fname);
		String lname = RandomStringUtils.randomAlphabetic(10);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lname);
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);  
		String email = "siddhesh.deokar+"+ randomInt +"@acceleratebs.com";
		//random email generator
		
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		
		driver.findElement(By.xpath("//button[text()='Invite and continue']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//inviting  team
		
		Thread.sleep(2000);
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String random_name = "Success! We've emailed " + email + " an invitation to join your team."; //concatenate
		
		Assert.assertEquals(e , random_name, "Success message do not match");
		//Assertion
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[normalize-space()='Manager']")).click();
		driver.findElement(By.xpath("(//span[@aria-hidden='true'])[4]")).click();
		driver.findElement(By.xpath("(//span[@aria-hidden='true'])[5]")).click();
		driver.findElement(By.xpath("(//span[@aria-hidden='true'])[6]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Save Permission and continue']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Skip']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Update info']")).click();
		
		String upd_fname =  driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value");
		String upd_lname =	driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value");
		
		//Save permissions
		Thread.sleep(2000);
		String f = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String random_name1 = upd_fname + " " + upd_lname + " has been updated"; //concatenate
		
		Assert.assertEquals(f , random_name1, "Success message do not match");
		
		driver.close();
		} */
	
}
