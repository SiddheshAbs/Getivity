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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pageobjects.Clientpage;
import Pageobjects.Loginpage;
import Pageobjects.Managepage;
public class Clients {
	
	
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
		Managepage managepage = new Managepage(driver);
		managepage.navigate_to_manage_tab();
		//Navigating to Manage tab 
		
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//a[normalize-space()='View archived clients']")));
        Clientpage clientpage = new Clientpage(driver);
        clientpage.new_client();
        
        String random_name= RandomStringUtils.randomAlphabetic(10);
    	driver.findElement(By.xpath(prop.getProperty("client_name_input"))).sendKeys(random_name); 
    	clientpage.client_details(); 
		//add client details with random string generator and create client

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
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
		Managepage managepage = new Managepage(driver);
		managepage.navigate_to_manage_tab();
		//Navigating to Manage tab 
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
	    wait2.until(ExpectedConditions
	            .presenceOfElementLocated(By.xpath("(//a[@href='/contacts/new'])[1]")));
	    Clientpage clientpage = new Clientpage(driver);  
	    clientpage.wait_for_contact_btn();
	    //click on contact button
	    
	    // Select the option using the visible text
	    Select se = new Select(driver.findElement(By.id("ClientID")));
	    se.selectByIndex(1);
	    String fname = RandomStringUtils.randomAlphabetic(10);
	    String lname = RandomStringUtils.randomAlphabetic(10);
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fname); 
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lname); 
		
		//Locating element by link text and store in variable "Element"        		
        WebElement Element = driver.findElement(By.xpath(prop.getProperty("save_contact")));

        // Scrolling down the page till the element is found		
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",Element);
        clientpage.save_contact_btn();
		//Creating a new contact
        
    	String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name= fname + " " + lname + " has been added."; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
		
        driver.close();
	    } 
	
	@Test(dependsOnMethods= {"createclient","createcontact"})
	
	public void update_contact() throws InterruptedException {
	
				
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
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
	    wait2.until(ExpectedConditions
	    .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
	    Clientpage clientpage = new Clientpage(driver);  
	    clientpage.edit_contact_btn();
		//Navigate to edit page
		     
		WebElement gp = driver.findElement(By.id("firstName"));
		gp.clear();
		WebElement gp1 = driver.findElement(By.id("lastName"));
		gp1.clear();
		Thread.sleep(2000);
		//Clearing text
		  
		String fname = RandomStringUtils.randomAlphabetic(10);
		driver.findElement(By.id("firstName")).sendKeys(fname);
		String lname = RandomStringUtils.randomAlphabetic(10);
		driver.findElement(By.id("lastName")).sendKeys(lname);
		clientpage.upd_contact_btn();
		//updating contact
		     
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name= fname + " " + lname + " has been updated."; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
		
		driver.close();
	
}
	@Test(dependsOnMethods= {"createclient","createcontact","update_contact"})
	public void archive_client() throws InterruptedException {
		
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
			
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        Clientpage clientpage = new Clientpage(driver);
        clientpage.edit_client();
		//Clicking on Edit button of first client
		
		String fname = driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value");
		
		Thread.sleep(1000);
		clientpage.archive_client();
		//Archiving client
		
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name = fname + " has been archived."; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
		driver.close();
		} 
	
	@Test(dependsOnMethods= {"createclient","createcontact","update_contact","archive_client"})
	public void restore_archive_client() throws InterruptedException {
			
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
		
		driver.findElement(By.xpath(prop.getProperty("View_archived_clients_btn"))).click();
		Thread.sleep(2000);
		String random_name = driver.findElement(By.xpath("(//h5[1])[2]")).getText(); //save first archived client name 
		
		Clientpage clientpage = new Clientpage(driver);
        clientpage.restore_client();
		//Restoring the client
		
		String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name = random_name + " has been restored."; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
		
		driver.close();
	 	} 

	@Test(dependsOnMethods= {"createclient","createcontact","update_contact","archive_client","restore_archive_client"})
	public void update_client() throws InterruptedException {
		
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
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        Clientpage clientpage = new Clientpage(driver);
        clientpage.edit_client();
        //clicking on edit button
		
        WebElement gp = driver.findElement(By.id("name"));
        gp.clear();
        //Clearing text
        
        String random_name = RandomStringUtils.randomAlphabetic(10);
        driver.findElement(By.id("name")).sendKeys(random_name);
        clientpage.update_client(); 
        //updating client
        
    	String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name = random_name + " has been updated."; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
		
        driver.close();
        
	} 
	
	@Test(dependsOnMethods= {"createclient","createcontact","update_contact","archive_client","restore_archive_client","update_client"})
	public void delete_client() throws InterruptedException {
		
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
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        Clientpage clientpage = new Clientpage(driver);
        clientpage.edit_delete_client();
        //clicking on edit button
		
		String random_name = driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value");
		
		Thread.sleep(1000);
		clientpage.delete_client();
        //deleting client
        
    	String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
		//locating success popup
		
		String name = random_name + " has been removed."; //concatenate
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
        driver.close();
	} 
}

