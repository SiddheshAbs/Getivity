package Getivity;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
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
import Pageobjects.Activitiespage;
import Pageobjects.Loginpage;
import Pageobjects.Managepage;

public class Activities {
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
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
		Managepage managepage = new Managepage(driver);
		managepage.navigate_to_manage_tab();
		//Navigating to Manage tab 
		
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait2.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn")))); 
        Activitiespage activitiespage = new Activitiespage(driver);
        activitiespage.nav_to_activities();
        //Navigate to Activities tab
        
        Thread.sleep(2000);
        activitiespage.add_new_activity();  
       
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
        activitiespage.common_activity();
        activitiespage.submit_activity(); 
	 	//Creating a common activity
		
		String e = driver.findElement(By.className("css-njbp03")).getText();
		
		String name= "Saved " + activity_name;
		
		Assert.assertEquals(e , name, "Success message do not match");
		//Assertion
        
        driver.close();  
			
 } 
	
  	@Test(dependsOnMethods= {"create_new_common_activity"})	
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
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
    	Managepage managepage = new Managepage(driver);
    	managepage.navigate_to_manage_tab();
    	//Navigating to Manage tab 
    		
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait2.until(ExpectedConditions
           .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        Activitiespage activitiespage = new Activitiespage(driver);
        activitiespage.nav_to_activities();
        //Navigate to Activities tab
       
        Thread.sleep(2000);
        activitiespage.add_new_activity();
            
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
        activitiespage.submit_activity();   
        //Creating a uncommon activity
             
        String e = driver.findElement(By.className("css-njbp03")).getText();
    		
    	String name= "Saved " + activity_name;
    		
    	Assert.assertEquals(e , name, "Success message do not match");
    		
        driver.close(); 
    } 
     
     @Test(dependsOnMethods= {"create_new_common_activity","create_new_uncommon_activity"})	
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
    		
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait1.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
    	Managepage managepage = new Managepage(driver);
    	managepage.navigate_to_manage_tab();
    	//Navigating to Manage tab 
    		
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait2.until(ExpectedConditions
            .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        Activitiespage activitiespage = new Activitiespage(driver);
        activitiespage.nav_to_activities();
        //Navigate to Activities tab
       
        Thread.sleep(2000);
        activitiespage.add_new_activity();
            
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
        activitiespage.add_activity_to_all_existing_projects();
        activitiespage.submit_activity(); 
        //creating a activity
            
        String e = driver.findElement(By.className("css-njbp03")).getText();
    		
    	String name= "Saved " + activity_name;
    		
    	Assert.assertEquals(e , name, "Success message do not match");
    		
        driver.close();            
}
    	
     @Test(dependsOnMethods= {"create_new_common_activity","create_new_uncommon_activity","create_new_activity_existingprojects"})	
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
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
    	Managepage managepage = new Managepage(driver);
    	managepage.navigate_to_manage_tab();
    	//Navigating to Manage tab 
    		
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait2.until(ExpectedConditions
               .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        Activitiespage activitiespage = new Activitiespage(driver);
        activitiespage.nav_to_activities();
        //Navigate to Activities tab
       
        Thread.sleep(2000);
        activitiespage.add_new_activity(); 
            
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
        activitiespage.billable_activity();
        activitiespage.submit_activity();
        //creating a activity
            
        String e = driver.findElement(By.className("css-njbp03")).getText();
    		
    	String name= "Saved " + activity_name;
    		
    	Assert.assertEquals(e , name, "Success message do not match");
    		
        driver.close();              
} 
      @Test(dependsOnMethods= {"create_new_common_activity","create_new_uncommon_activity","create_new_activity_existingprojects",
        	"create_billable_activity"})	
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
                .presenceOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/button[1]")));
    	Managepage managepage = new Managepage(driver);
    	managepage.navigate_to_manage_tab();
    	//Navigating to Manage tab 
    		
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait2.until(ExpectedConditions
              .presenceOfElementLocated(By.xpath(prop.getProperty("wait_addcontact_btn"))));
        Activitiespage activitiespage = new Activitiespage(driver);
        activitiespage.nav_to_activities();
            //Navigate to Activities tab
            
        Thread.sleep(2000);
        activitiespage.add_new_activity(); 
           
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys(activity_name);
        activitiespage.common_activity();
        activitiespage.add_activity_to_all_existing_projects();
        activitiespage.submit_activity();
        //Creating a common and existing project activity
                
        String e = driver.findElement(By.className("css-njbp03")).getText();
    		
    	String name= "Saved " + activity_name;
    		
    	Assert.assertEquals(e , name, "Success message do not match");
    		
        driver.close();
} 
 	 @Test(dependsOnMethods= {"create_new_common_activity","create_new_uncommon_activity","create_new_activity_existingprojects",
         "create_billable_activity","create_common_existing_activity"})
 	 public void delete_untracked_activity () throws InterruptedException  {
    		  	
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
        Activitiespage activitiespage = new Activitiespage(driver);
        activitiespage.nav_to_activities();
        //Navigate to Activities tab
       
        Thread.sleep(2000);
    	JavascriptExecutor js = (JavascriptExecutor) driver; 
    	js.executeScript("window.scrollBy(0,5000)", ""); //scroll down
    	activitiespage.select_untracked_activity();
        //clicking on twenty one actions button
            
        Thread.sleep(2000);
        String random_name = driver.findElement(By.xpath("(//div)[94]")).getText(); //seventh text
        activitiespage.delete_untracked_activity();
        //deleting activity
            
        Thread.sleep(1000);
    	String e = driver.findElement(By.xpath("//div[@class='css-njbp03']")).getText(); 
    	//locating success popup
    		
    	String name = random_name + " has been deleted"; //concatenate
    		
    	Assert.assertEquals(e , name, "Success message do not match");
    	//Assertion
        driver.close(); 
     
}  
	@Test(dependsOnMethods= {"create_new_common_activity","create_new_uncommon_activity","create_new_activity_existingprojects",
        	"create_billable_activity","create_common_existing_activity","delete_untracked_activity"})	
     public void archive_tracked_activity () throws InterruptedException  {
	
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
    	 Activitiespage activitiespage = new Activitiespage(driver);
         activitiespage.nav_to_activities();
    	   	//Navigate to Activities tab
    
    	 Thread.sleep(2000);
    	   	activitiespage.archive_actions();
    	   	String temp = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/p[1]")).getText();
    	   	activitiespage.archive_confirm();	
    	   	//Archiving a activity
    
    	   	String e = driver.findElement(By.className("css-njbp03")).getText();
    
    	   	String name= temp + " has been archived" ;
	
    	   	Assert.assertEquals(e , name, "Success message do not match");
    	   	driver.close();
    
} 		
      @Test(dependsOnMethods= {"create_new_common_activity","create_new_uncommon_activity","create_new_activity_existingprojects",
        	"create_billable_activity","create_common_existing_activity","delete_untracked_activity","archive_tracked_activity"})	
        public void restorearchived_tracked_activity () throws InterruptedException  {
        	
        	
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
            Activitiespage activitiespage = new Activitiespage(driver);
            activitiespage.nav_to_activities();
            //Navigate to Activities tab
            
            Thread.sleep(2000);
            activitiespage.restore_archived_activities();
            
            String e = driver.findElement(By.className("css-njbp03")).getText();
            
            String name=  "Activities have been restored" ;
        	
        	Assert.assertEquals(e , name, "Success message do not match");
        		
        	driver.close();    
       
       
        } 
        
}
        
