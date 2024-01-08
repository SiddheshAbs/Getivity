package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Managepage {
WebDriver driver;
	
	@FindBy(how=How.XPATH, using="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")
	WebElement manage_tab;
	
	
	
	public Managepage(WebDriver driver) //constructor // catch argument sent by class where landingpage object is created 
	{
		     //initialization      
	         this.driver = driver; 
	         PageFactory.initElements(driver, this);
	}
		
	public void navigate_to_manage_tab() {
		
	manage_tab.click();
	}
	
	
}
