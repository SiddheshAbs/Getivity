package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	
	WebDriver driver;
	
	@FindBy(how=How.ID, using="email")
	WebElement username;
	
	@FindBy(how=How.ID, using="password")
	WebElement password;
	
	@FindBy(how=How.CSS, using="button[type='submit']")
	WebElement submit;
	
	@FindBy(how=How.XPATH, using="//p[text()='America region']")
	WebElement workspace_name;
	
	public Loginpage(WebDriver driver) // catch argument sent by class where landingpage object is created 
	{
		     //initialization      
	         this.driver = driver; 
	         PageFactory.initElements(driver, this);
	}
		
	public void entercredentials() {
		
	username.sendKeys("siddhesh.deokar@acceleratebs.com");
	password.sendKeys("Sid*141#");
	submit.click();
	}
	
	public void choose_workspace() {
		
	workspace_name.click();	
	}
	
}


