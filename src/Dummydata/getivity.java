package Dummydata;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pageobjects.Loginpage;

public class getivity {

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
	//Clients 
	@Test//(invocationCount = 20)		
	public void createclient () throws InterruptedException  {
		
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
		driver.findElement(By.xpath(prop.getProperty("manage_tab"))).click();
		//Navigating to Manage tab 
		
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        driver.findElement(By.cssSelector(prop.getProperty("new_client_btn"))).click();
        
        String random_name= RandomStringUtils.randomAlphabetic(10);
    	driver.findElement(By.xpath(prop.getProperty("client_name_input"))).sendKeys(random_name); 
		driver.findElement(By.id("address")).sendKeys(RandomStringUtils.randomAlphabetic(10)); 
		//random string generator
		
		driver.findElement(By.xpath(prop.getProperty("save_client_btn"))).click();
		// find the hyperlink using its link text and create a new client
		
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name= random_name + " has been added."; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
		
		driver.close();
		
} 
	
	@Test(dependsOnMethods= {"createclient"})	
	public void createcontact () throws InterruptedException  {
		
		
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
                .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
		driver.findElement(By.xpath(prop.getProperty("manage_tab"))).click();
		//Navigating to Manage tab 
		
		 WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
	        wait2.until(ExpectedConditions
	                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
	     
	     
	     for(int i = 1; i <= 1; i++){      
		        
			    driver.findElement(By.xpath(prop.getProperty("wait_addcontact_btn"))).click();  
			    
			    // Select the option using the visible text
			  
			    Select se = new Select(driver.findElement(By.id("ClientID")));
			    se.selectByIndex(i);
			    
			    String fname = RandomStringUtils.randomAlphabetic(10);
			    String lname = RandomStringUtils.randomAlphabetic(10);
			    
			    Random randomGenerator = new Random();
				int randomInt = randomGenerator.nextInt(1000);  
				String email = "siddhesh.deokar+"+ randomInt +"@acceleratebs.com";
				//random email generator
				
				driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fname); 
				driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lname); 
				driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email); 
				
				//Locating element by link text and store in variable "Element"        		
		        WebElement Element =driver.findElement(By.xpath(prop.getProperty("save_contact")));
			    
		        // Scrolling down the page till the element is found		
		        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",Element);
		        driver.findElement(By.xpath(prop.getProperty("save_contact"))).click();
				//Creating a new contact
		        
		    	String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
				//locating success popup
				
				String name= fname + " " + lname + " has been added."; //concatenate
				
				Assert.assertEquals(e , name, "Success message do not match");
				//Assertion
				Thread.sleep(2000);
			   }
		        driver.close();
	} 
	
	//Roles
	@Test(dependsOnMethods= {"createclient","createcontact"})//,invocationCount = 10)	
	public void create_role() throws InterruptedException {
		
		
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
                .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
		driver.findElement(By.xpath("(//div)[9]")).click();
		//Navigating to Manage tab 
		
		driver.findElement(By.xpath("(//p[text()='Roles'])[1]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div//div//div//div//div//div//div//div[2]//div[3]//button[1]")));
		//Navigating to the roles tab
		
		
		driver.findElement(By.xpath("(//button[text()='New role'])[1]")).click();
		
		Thread.sleep(2000);
		String random_name = RandomStringUtils.randomAlphabetic(10);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(random_name);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[normalize-space()='Save role'])[1]")).click();
		//Creating role
		
		
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		
		String name= "Saved " + random_name;
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
        
        driver.close();  
	}
	
	
	@Test(dependsOnMethods= {"createclient","createcontact","create_role"})//,invocationCount = 5)
	public void create_and_assign_role() throws InterruptedException {
		
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
                .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
		driver.findElement(By.xpath("(//div)[9]")).click();
		//Navigating to Manage tab 
		
		driver.findElement(By.xpath("(//p[text()='Roles'])[1]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div//div//div//div//div//div//div//div[2]//div[3]//button[1]")));
		//Navigating to the roles tab
        
       
		driver.findElement(By.xpath("(//button[text()='New role'])[1]")).click();
		
		Thread.sleep(2000);
		String random_name = RandomStringUtils.randomAlphabetic(10);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(random_name);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[@aria-hidden='true'])[10]")).click(); //selecting user
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[normalize-space()='Save role'])[1]")).click();
		//Creating role and asigning it
        
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText();
		
		String name= "Saved " + random_name ;
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion 
		
		driver.close();
		}

	@Test(dependsOnMethods= {"createclient","createcontact","create_role","create_and_assign_role"})//,invocationCount = 5)
	public void create_assign_multiple_roles() throws InterruptedException {
		
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
                .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
		driver.findElement(By.xpath("(//div)[9]")).click();
		//Navigating to Manage tab 
		
		driver.findElement(By.xpath("(//p[text()='Roles'])[1]")).click();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div//div//div//div//div//div//div//div[2]//div[3]//button[1]")));
		//Navigating to the roles tab
        
        Thread.sleep(2000);
      	driver.findElement(By.xpath("(//button[text()='New role'])[1]")).click();
      		
      	Thread.sleep(2000);
      	String random_name = RandomStringUtils.randomAlphabetic(10);
      		
      	Thread.sleep(2000);
      	driver.findElement(By.xpath("//input[@name='name']")).sendKeys(random_name);
      		
      	Thread.sleep(2000);
      	driver.findElement(By.xpath("(//span[@aria-hidden='true'])[10]")).click();
      	driver.findElement(By.xpath("(//span[@aria-hidden='true'])[11]")).click();
      	//driver.findElement(By.xpath("(//span[@aria-hidden='true'])[4]")).click();
      		
      	JavascriptExecutor js = (JavascriptExecutor) driver;
      	js.executeScript("window.scrollBy(0,350)", "");
      		
      	Thread.sleep(2000);
      	driver.findElement(By.xpath("(//button[normalize-space()='Save role'])[1]")).click();
      	//Creating role and assigning it
        
      	String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText();
		
		String name= "Saved " + random_name ;
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion 
		
		driver.close();
} 
	//Project types
	@Test(dependsOnMethods= {"createclient","createcontact","create_role","create_and_assign_role","create_assign_multiple_roles"})
	//,invocationCount = 20)
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
                .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
		driver.findElement(By.xpath(prop.getProperty("manage_tab"))).click();
		//Navigating to Manage tab 
		
		 WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
	        wait2.until(ExpectedConditions
	                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
	     driver.findElement(By.xpath(prop.getProperty("project_types_link"))).click();    
	     //Navigating to project types tab
	     
	     Thread.sleep(2000);
	     driver.findElement(By.xpath(prop.getProperty("new_project_type_btn"))).click();
	     String random_name= RandomStringUtils.randomAlphabetic(10);
	     driver.findElement(By.id("name")).sendKeys(random_name);
	     driver.findElement(By.cssSelector(prop.getProperty("save_project_type_btn"))).click();
	     //creating new project type
	     
	    
	     String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText();
			
	     String project_name= "Saved " + random_name;
			
	     Assert.assertEquals(e , project_name, "Success message do not match");
	     //Assertion
	     
	     driver.close();
	     
	} 
	
	//Activities
	@Test(dependsOnMethods= {"createclient","createcontact","create_role","create_and_assign_role","create_assign_multiple_roles","create_project_type"})
	//,invocationCount = 5)
	public void create_new_common_activity () throws InterruptedException  {
		
		String activity_name = RandomStringUtils.randomAlphabetic(10);
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
                .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
		driver.findElement(By.xpath("(//div)[9]")).click();
		//Navigating to Manage tab 
		
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        driver.findElement(By.xpath("(//p[contains(text(),'Activities')])[2]")).click();
        //Navigate to Activities tab
        
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[class=\"chakra-button css-cf8jat\"]")).click();
       
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
        System.out.println("Random activity name is " + activity_name);
        driver.findElement(By.xpath("(//span[@aria-hidden='true'])[3]")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
	 	//Creating a common activity
		
		String e = driver.findElement(By.className("css-njbp03")).getText();
		
		String name= "Saved " + activity_name;
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
        
        driver.close();  
			
        }
	
    
        @Test(dependsOnMethods= {"createclient","createcontact","create_role","create_and_assign_role","create_assign_multiple_roles",
        "create_project_type","create_new_common_activity"})//,invocationCount = 5)
    	public void create_new_uncommon_activity () throws InterruptedException  {
    		
        	String activity_name = RandomStringUtils.randomAlphabetic(10);
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
                    .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
    		driver.findElement(By.xpath("(//div)[9]")).click();
    		//Navigating to Manage tab 
    		
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait2.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
            driver.findElement(By.xpath("(//p[contains(text(),'Activities')])[2]")).click();
            //Navigate to Activities tab
       
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button[class=\"chakra-button css-cf8jat\"]")).click();
           
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
            System.out.println("Random activity name is " + activity_name);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            //Creating a uncommon activity
             
            String e = driver.findElement(By.className("css-njbp03")).getText();
    		
    		String name= "Saved " + activity_name;
    		
    		Assert.assertEquals(e , name, "Success message do not match");
    		
            driver.close(); 
    }
     
        @Test(dependsOnMethods= {"createclient","createcontact","create_role","create_and_assign_role","create_assign_multiple_roles",
        "create_project_type","create_new_common_activity","create_new_uncommon_activity"})//,invocationCount = 5)
    	public void create_new_activity_existingprojects () throws InterruptedException  {
    		
        	String activity_name = RandomStringUtils.randomAlphabetic(10);
        	Loginpage loginpage= new Loginpage(driver);
    		loginpage.entercredentials();
    		//login
    		
    		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath(prop.getProperty("signout_btn"))));
            loginpage.choose_workspace();
    		//Selecting workspace
    		
    		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait2.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
    		driver.findElement(By.xpath("(//div)[9]")).click();
    		//Navigating to Manage tab 
    		
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait1.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
            driver.findElement(By.xpath("(//p[contains(text(),'Activities')])[2]")).click();
            //Navigate to Activities tab
       
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button[class=\"chakra-button css-cf8jat\"]")).click();  
            
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
            System.out.println("Random activity name is " + activity_name);
            driver.findElement(By.xpath("//label[3]//span[1]")).click();
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            //creating a activity
            
            String e = driver.findElement(By.className("css-njbp03")).getText();
    		
    		String name= "Saved " + activity_name;
    		
    		Assert.assertEquals(e , name, "Success message do not match");
    		
            driver.close();            
	}
        @Test(dependsOnMethods= {"createclient","createcontact","create_role","create_and_assign_role","create_assign_multiple_roles",
        "create_project_type","create_new_common_activity","create_new_uncommon_activity","create_new_activity_existingprojects"})//,invocationCount = 5)
    	public void create_billable_activity () throws InterruptedException  {
    		
        	String activity_name = RandomStringUtils.randomAlphabetic(10);
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
                    .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
    		driver.findElement(By.xpath("(//div)[9]")).click();
    		//Navigating to Manage tab 
    		
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait2.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
            driver.findElement(By.xpath("(//p[contains(text(),'Activities')])[2]")).click();
            //Navigate to Activities tab
       
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button[class=\"chakra-button css-cf8jat\"]")).click();  
            
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
            System.out.println("Random activity name is " + activity_name);
            driver.findElement(By.xpath("(//span[@aria-hidden='true'])[2]")).click();
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            //creating a activity
            
            String e = driver.findElement(By.className("css-njbp03")).getText();
    		
    		String name= "Saved " + activity_name;
    		
    		Assert.assertEquals(e , name, "Success message do not match");
    		
            driver.close();              
}
  
        @Test(dependsOnMethods= {"createclient","createcontact","create_role","create_and_assign_role","create_assign_multiple_roles",
        "create_project_type","create_new_common_activity","create_new_uncommon_activity","create_new_activity_existingprojects",
        "create_billable_activity"})//,invocationCount = 5)
    	public void create_common_existing_activity () throws InterruptedException  {
    		
    		String activity_name = RandomStringUtils.randomAlphabetic(10);
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
                    .presenceOfElementLocated(By.xpath("(//button[normalize-space()='Submit week for approval'])[1]")));
    		driver.findElement(By.xpath("(//div)[9]")).click();
    		//Navigating to Manage tab 
    		
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait2.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
            driver.findElement(By.xpath("(//p[contains(text(),'Activities')])[2]")).click();
            //Navigate to Activities tab
            
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button[class=\"chakra-button css-cf8jat\"]")).click();
           
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
            System.out.println("Random activity name is " + activity_name);
            driver.findElement(By.xpath("(//span[@aria-hidden='true'])[3]")).click(); //common activity checkbox
            driver.findElement(By.xpath("(//span[@aria-hidden='true'])[4]")).click();
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            //Creating a common and existing project activity
                
            String e = driver.findElement(By.className("css-njbp03")).getText();
    		
    		String name= "Saved " + activity_name;
    		
    		Assert.assertEquals(e , name, "Success message do not match");
    		
            driver.close();
}     
    } 

