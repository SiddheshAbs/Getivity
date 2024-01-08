package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Activitiespage {
	WebDriver driver;
	
@FindBy(how=How.XPATH, using="(//p[contains(text(),'Activities')])[2]")
WebElement nav_to_activities_tab;

@FindBy(how=How.CSS, using="button[class=\"chakra-button css-cf8jat\"]")
WebElement add_new_activity_btn;

@FindBy(how=How.XPATH, using="(//span[@aria-hidden='true'])[3]")
WebElement common_activity_checkbox;

@FindBy(how=How.XPATH, using="//button[@type='submit']")
WebElement submit_activity_btn;

@FindBy(how=How.XPATH, using="//label[3]//span[1]")
WebElement add_activity_to_all_existing_projects_checkbox;

@FindBy(how=How.XPATH, using="(//span[@aria-hidden='true'])[2]")
WebElement billable_activity_checkbox;

@FindBy(how=How.XPATH, using="(//button[@type='button'])[38]") //Seventh actions button
WebElement select_actions_btn;

@FindBy(how=How.XPATH, using="(//button[@role='menuitem'][normalize-space()='Delete'])[7]") 
WebElement delete_btn;

@FindBy(how=How.XPATH, using="//button[normalize-space()=\"Delete Activities\"]") 
WebElement delete_confirm;

@FindBy(how=How.XPATH, using="(//button[@type='button'])[13]") //second actions button
WebElement archive_actions_btn;

@FindBy(how=How.XPATH, using="(//button[@role='menuitem'][normalize-space()='Archive'])[2]") 
WebElement archive_btn;

@FindBy(how=How.XPATH, using="(//button[normalize-space()='Archive Activities'])[1]") 
WebElement archive_confirm_btn;

@FindBy(how=How.XPATH, using="(//a[normalize-space()='View archived Activities'])[1]") 
WebElement view_archived_activities_btn;

@FindBy(how=How.XPATH, using="(//span[@aria-hidden='true'])[3]") //first activity 
WebElement select_archived_activity;

@FindBy(how=How.XPATH, using="(//button[normalize-space()='Restore selected activities'])[1]") 
WebElement restore_btn;

@FindBy(how=How.XPATH, using="(//button[normalize-space()='Back to active activities'])[1]") 
WebElement back_to_activities_btn;

	public Activitiespage(WebDriver driver) //constructor // catch argument sent by class where landingpage object is created 
	{
		     //initialization      
	         this.driver = driver;
	         PageFactory.initElements(driver, this);
	}
		
	public void nav_to_activities() {
		
		nav_to_activities_tab.click();
	}
	public void add_new_activity() {
		
		add_new_activity_btn.click();
	}
	public void common_activity() {
		
		common_activity_checkbox.click();
	}
	public void submit_activity() {
		
		submit_activity_btn.click();
	}
	public void add_activity_to_all_existing_projects() {
		
		add_activity_to_all_existing_projects_checkbox.click();
	}
	public void billable_activity() {
		
		billable_activity_checkbox.click();
	}
	public void select_untracked_activity() {
		
		select_actions_btn.click();
	}
	public void delete_untracked_activity() {
		
		delete_btn.click();
		delete_confirm.click();
	}
	public void archive_actions() {
		
		archive_actions_btn.click();
		archive_btn.click();
	}
	public void archive_confirm() {
		
		archive_confirm_btn.click();
	}
	public void restore_archived_activities() {
		
		view_archived_activities_btn.click();
		select_archived_activity.click();
		restore_btn.click();
		back_to_activities_btn.click();
	}
}
