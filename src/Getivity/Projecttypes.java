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
import Pageobjects.Managepage;
import Pageobjects.Projecttypespage;

public class Projecttypes {
		
		String baseurl = "https://getivity-dev.com/signin";
		public WebDriver driver ; //declare outside of all methods 
		Properties prop;
		
		@BeforeMethod
		public void launchbrowser() throws InterruptedException, IOException {
			
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\ABS\\eclipse-workspace\\Automationpractice\\drivers\\chromedriver.exe");
		//ChromeOptions options  = new ChromeOptions();
		//options.setBinary("C:\\Users\\ABS\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
		//driver = new ChromeDriver(options);
			
		FileInputStream f= new FileInputStream("C:\\Users\\ABS\\eclipse-workspace\\Automation\\src\\Properties");
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
	public void create_project_type	() throws InterruptedException {
		
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
		Managepage managepage = new Managepage(driver);
		managepage.navigate_to_manage_tab();
		//Navigating to Manage tab 
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
	      wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
	    Projecttypespage projecttypespage = new Projecttypespage(driver);
	    projecttypespage.project_types();    
	    //Navigating to project types tab
	     
	    Thread.sleep(2000);
	    projecttypespage.new_project_type();
	    String random_name= RandomStringUtils.randomAlphabetic(10);
	    driver.findElement(By.id("name")).sendKeys(random_name);
	    projecttypespage.save_project_type();
	    //creating new project type
	     
	    Thread.sleep(1000);
	    String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText();
			
		String project_name= "Saved " + random_name;
			
		Assert.assertEquals(e , project_name, "Success message do not match");
		//Assertion
	    
	    driver.close(); 
	     
	}
	
	@Test(dependsOnMethods= {"create_project_type",})	
	public void delete_single_project_type	() throws InterruptedException {

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
		Managepage managepage = new Managepage(driver);
		managepage.navigate_to_manage_tab();
		//Navigating to Manage tab 
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
	    wait2.until(ExpectedConditions
	       .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
	    Projecttypespage projecttypespage = new Projecttypespage(driver);
		projecttypespage.project_types();     
	    //Navigating to project types tab
	    
	    Thread.sleep(2000);
	    JavascriptExecutor js = (JavascriptExecutor) driver; 
	    js.executeScript("window.scrollBy(0,600)", ""); //scroll down
	    
	    Thread.sleep(2000);
	    projecttypespage.select_project_type();
	    String random_name =  driver.findElement(By.xpath("(//div)[101]")).getText(); 
	    projecttypespage.delete_project_type();
	    //delete
	    
	    Thread.sleep(1000);
	    String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name= random_name + " has been deleted"; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
	    driver.close();
	    	    
}
	@Test(dependsOnMethods= {"create_project_type","delete_single_project_type"})
	public void delete_multi_project_type	() throws InterruptedException {
		
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
		Managepage managepage = new Managepage(driver);
		managepage.navigate_to_manage_tab();
		//Navigating to Manage tab 
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
	    wait2.until(ExpectedConditions
	    		.presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
	     Projecttypespage projecttypespage = new Projecttypespage(driver);
	     projecttypespage.project_types();   
	     //Navigating to project types tab
	     
	    Thread.sleep(2000);
	    JavascriptExecutor js = (JavascriptExecutor) driver; 
	    js.executeScript("window.scrollBy(0,600)", ""); //scroll down
	    
	    Thread.sleep(2000);
	    projecttypespage.select_multi_project_types();
	    String count= "2";
	    
	    Thread.sleep(2000);
	    js.executeScript("window.scrollBy(0,-600)",""); //scroll up
	    Thread.sleep(2000);
	    projecttypespage.delete_multi_project_types();
	    //Deleting multiple project types
	    
	    Thread.sleep(1000);
	    String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name = count + " project types have been deleted."; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
		
	    driver.close();
 } 

	@Test(dependsOnMethods= {"create_project_type","delete_single_project_type","delete_multi_project_type"})
	public void edit_project_type	() throws InterruptedException {
	
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
		Managepage managepage = new Managepage(driver);
		managepage.navigate_to_manage_tab();
		//Navigating to Manage tab 
	
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        Projecttypespage projecttypespage = new Projecttypespage(driver);
	    projecttypespage.project_types();   
        //Navigating to project types tab
    
        Thread.sleep(2000);
	    JavascriptExecutor js = (JavascriptExecutor) driver; 
	    js.executeScript("window.scrollBy(0,600)", ""); //scroll down
	    
	    projecttypespage.select_edit_project_type();
	    String proj_name = RandomStringUtils.randomAlphabetic(8);
	 
	 	Thread.sleep(2000);
	 	WebElement gp = driver.findElement(By.xpath("//input[@id='name']"));
	 	gp.clear();
	 
	 	Thread.sleep(2000);
	 	driver.findElement(By.xpath("//input[@id='name']")).sendKeys(proj_name);
	 
	 	//Locating element by link text and store in variable "Element"        		
	 	WebElement Element =driver.findElement(By.xpath("//button[normalize-space()='Update type']"));
	 	// Scrolling down the page till the element is found		
	 	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",Element);
	 	
	 	projecttypespage.edit_project_type();
	 	//Updating project type name
	    
	 	Thread.sleep(1000);
	 	String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
			
		String name = proj_name + " has been updated"; //concatenate
			
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
	 	
	 	driver.close();
     
}
	@Test(dependsOnMethods= {"create_project_type","delete_single_project_type","delete_multi_project_type","edit_project_type"})
	public void create_project_type_again	() throws InterruptedException {
		
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
		Managepage managepage = new Managepage(driver);
		managepage.navigate_to_manage_tab();
		//Navigating to Manage tab 
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
	    wait2.until(ExpectedConditions
	                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
	    Projecttypespage projecttypespage = new Projecttypespage(driver);
		projecttypespage.project_types();    
	    //Navigating to project types tab
	     
	    Thread.sleep(2000);
	    projecttypespage.new_project_type();
	    String random_name= RandomStringUtils.randomAlphabetic(10);
	    driver.findElement(By.id("name")).sendKeys(random_name);
	    projecttypespage.save_project_type();
	    //creating new project type
	     
	    Thread.sleep(1000);
	    String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText();
			
		String project_name= "Saved " + random_name;
			
		Assert.assertEquals(e , project_name, "Success message do not match");
		//Assertion
	    
	    driver.close();
	     
	} 
}
