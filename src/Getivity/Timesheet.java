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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pageobjects.Loginpage;
import Pageobjects.Timesheetpage;

public class Timesheet {
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
	public void create_entry () throws InterruptedException  {
		
		Loginpage loginpage= new Loginpage(driver);
		loginpage.entercredentials();
		//login
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("signout_btn"))));
        loginpage.choose_workspace();
		//Selecting workspace
		
		
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait1.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
		Timesheetpage timesheetpage = new Timesheetpage(driver);
		timesheetpage.click_on_addentry_btn();
       
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[contains(text(),'Activities')]"))); 
        //waiting for Activities dropdown
        
        timesheetpage.project_btn();
        timesheetpage.choose_project();  //changes to be made here accordingly
        //choosing project
        
        Thread.sleep(2000);
        timesheetpage.choose_activity(); //changes to be made here accordingly
        //choosing activity
        
        //String hours =  RandomStringUtils.randomAlphabetic(2);
        timesheetpage.add_time();
        //adding time
        
        //String random =  RandomStringUtils.randomAlphabetic(10);
        timesheetpage.notes();
        timesheetpage.save_entry();
        //Adding a entry
        
        String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText();
		
		String name= "sid123 has been added";  //changes to be made here accordingly
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion 
        
        driver.close();  
       
}  
	@Test(dependsOnMethods= {"create_entry"})
	public void edit_entry () throws InterruptedException  {
		
		Loginpage loginpage= new Loginpage(driver);
		loginpage.entercredentials();
		//login
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("signout_btn"))));
        loginpage.choose_workspace();
		//Selecting workspace
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("(//button[@type='button'][normalize-space()='Edit'])[1]")));
        Timesheetpage timesheetpage = new Timesheetpage(driver);
        timesheetpage.edit_entry();
		//clicking on first edit button 
		
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait3.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[contains(text(),'New activity')]")));
		
      
        timesheetpage.choose_new_project(); //changes to be made here accordingly
		String random_name = driver.findElement(By.xpath("//body/div/div[@data-focus-lock-disabled='false']/div/section[@role='dialog']/div/div/form[@autocomplete='off']/div/div[1]/div[1]/div[1]/div[1]/div[1]")).getText();
		//change project
		
		Thread.sleep(2000);
		timesheetpage.change_activity(); //changes to be made here accordingly
		//change Activity
		
		Thread.sleep(2000);
		WebElement gp = driver.findElement(By.xpath("//input[@placeholder='00']"));
		gp.clear();
		timesheetpage.change_time();
		//changing time
		
		Thread.sleep(2000);
		WebElement gp3 = driver.findElement(By.xpath("(//textarea[@placeholder='Notes'])[1]"));
		gp3.clear();
		//String random =  RandomStringUtils.randomAlphabetic(10);
		timesheetpage.update_notes();
	    //changing notes
	    
	    Thread.sleep(2000);
	    timesheetpage.save_edited_entry();
	    //updating a entry
	    
	    String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText();
		
		String name= random_name + " has been added";
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion 
       
	    driver.close();
		
}   

	@Test(dependsOnMethods= {"create_entry","edit_entry"})
	public void delete_entry () throws InterruptedException  {
		
		Loginpage loginpage= new Loginpage(driver);
		loginpage.entercredentials();
		//login
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("signout_btn"))));
        loginpage.choose_workspace();
		//Selecting workspace
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
	        wait1.until(ExpectedConditions
	                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
	    Timesheetpage timesheetpage = new Timesheetpage(driver);
	    timesheetpage.edit_entry();
	    //driver.findElement(By.xpath("(//button[@type='button'][normalize-space()='Edit'])[1]")).click();
		String random_name = driver.findElement(By.xpath("//body/div/div[@data-focus-lock-disabled='false']/div/section[@role='dialog']/div/div/form[@autocomplete='off']/div/div[1]/div[1]/div[1]/div[1]/div[1]")).getText();
		timesheetpage.delete_entry();
		//driver.findElement(By.xpath("(//button[normalize-space()='Delete'])[1]")).click();
		//deleting a entry
		
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText();
			
		String name= random_name + " has been deleted";
			
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion 
		
		driver.close();
} 
	
	@Test(dependsOnMethods= {"create_entry","edit_entry","delete_entry"})
	public void add_entry_different_date() throws InterruptedException	{
		
		Loginpage loginpage= new Loginpage(driver);
		loginpage.entercredentials();
		//login
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("signout_btn"))));
        loginpage.choose_workspace();
		//Selecting workspace
		

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait1.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
        Timesheetpage timesheetpage = new Timesheetpage(driver);
        timesheetpage.datepicker();
        //choosing different dates
		
		Thread.sleep(5000);
		timesheetpage.click_on_addentry_btn();
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[contains(text(),'Activities')]"))); 
        //waiting for Activities dropdown
        
        timesheetpage.project_btn();
        timesheetpage.choose_project();  //changes to be made here accordingly  
        //choosing project
        
        Thread.sleep(2000);
        timesheetpage.choose_activity(); //changes to be made here accordingly 
        //choosing activity
        
        //String hours =  RandomStringUtils.randomAlphabetic(2);
        timesheetpage.add_time();
        //adding time
        
        String random =  RandomStringUtils.randomAlphabetic(10);
        driver.findElement(By.xpath("(//textarea[@placeholder='Notes'])[1]")).sendKeys(random);
        
        timesheetpage.save_entry();
        //Adding a entry
        
        String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText();
		
		String name= "sid123 has been added";
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion 
        
        driver.close(); 
	}
}