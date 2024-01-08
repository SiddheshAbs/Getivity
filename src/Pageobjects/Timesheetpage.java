package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Timesheetpage {

	WebDriver driver;
	
	@FindBy(how=How.XPATH, using="(//button[@type='button'])[12]") 
	WebElement add_entry_btn;
								       
	@FindBy(how=How.XPATH, using="//body/div/div[@data-focus-lock-disabled='false']/div/section[@role='dialog']/div/div/form[@autocomplete='off']/div/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]") 
	WebElement project_btn;
	
	@FindBy(how=How.XPATH, using="//div[contains(text(),'sid123')]")   //changes to be made here accordingly
	WebElement choose_project;
	
	@FindBy(how=How.XPATH, using="(//div[@role='group'])[2]")   
	WebElement activity_btn;
	
	@FindBy(how=How.XPATH, using="//div[contains(text(),'New activity')]")   //changes to be made here accordingly
	WebElement choose_activity;
	
	@FindBy(how=How.XPATH, using="//input[@placeholder='00']")   
	WebElement add_hrs;
	
	@FindBy(how=How.XPATH, using="//body//div//div[@role='group']//div//div//div//div//div//div[2]")   
	WebElement mins_dropdown;
	
	@FindBy(how=How.XPATH, using="(//div[contains(text(),'06')])[1]")   
	WebElement add_mins;
	
	@FindBy(how=How.XPATH, using="(//textarea[@placeholder='Notes'])[1]")   
	WebElement add_notes;
	
	@FindBy(how=How.XPATH, using="//button[text()='Save Entry']")   
	WebElement save_entry_btn;
	
	@FindBy(how=How.XPATH, using="(//button[@type='button'][normalize-space()='Edit'])[1]")  //first entry
	WebElement edit_entry_btn;
	
	@FindBy(how=How.XPATH, using="//body/div/div[@data-focus-lock-disabled='false']/div/section[@role='dialog']/div/div/form[@autocomplete='off']/div/div[1]/div[1]/div[1]/div[1]/div[2]")    
	WebElement new_project_btn;
	
	@FindBy(how=How.XPATH, using="(//div[contains(text(),'Project 1')])[1]")    //changes to be made here accordingly
	WebElement new_project;
	
	@FindBy(how=How.XPATH, using="//div[@data-focus-lock-disabled='false']//div[2]//div[1]//div[1]//div[1]//div[2]") 
	WebElement new_activity_btn;
	
	@FindBy(how=How.XPATH, using="(//div[contains(text(),'Design update')])[1]")    //changes to be made here accordingly
	WebElement new_activity;
	
	@FindBy(how=How.XPATH, using="//body/div/div[@data-focus-lock-disabled='false']/div/section[@role='dialog']/div/div/form[@autocomplete='off']/div/div/div/div/div/div/div[2]") 
	WebElement change_mins_dropdown;
	
	@FindBy(how=How.XPATH, using="//div[contains(text(),'36')]")    //changes to be made here accordingly
	WebElement change_mins;
	
	@FindBy(how=How.XPATH, using="(//button[text()='Update Entry'])[1]")    
	WebElement update_entry;
	
	@FindBy(how=How.XPATH, using="(//button[normalize-space()='Delete'])[1]")    
	WebElement delete_entry_btn;
	
	@FindBy(how=How.XPATH, using="//body/div/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]")    
	WebElement datepicker_btn;
	
	@FindBy(how=How.XPATH, using="//div[contains(text(),'27')]")  //change if necessary  
	WebElement datepicker_date;
	
	public Timesheetpage(WebDriver driver) //constructor // catch argument sent by class where timesheetpage object is created 
	{
		     //initialization      
	         this.driver = driver;
	         PageFactory.initElements(driver, this);
	}         
	         public void click_on_addentry_btn() {
					
					add_entry_btn.click();
				}
	         public void project_btn() {
					
					project_btn.click();
					
				}
	         public void choose_project() {
					
	        	 	choose_project.click();
				}
	         public void choose_activity() {
					
	        	 	activity_btn.click();
	        	 	choose_activity.click();
				} 
	         public void add_time() {
					
	        	 	add_hrs.sendKeys("02");
	        	 	mins_dropdown.click();
	        	 	add_mins.click();
				}
	         public void notes() {
	        	 
	        	 add_notes.sendKeys("Testing");
	         }
	         public void save_entry() {
					
	        	save_entry_btn.click();
	        	 	
				} 
	         public void edit_entry() {
					
	        	 	edit_entry_btn.click();
	        	 	
				} 
	         public void choose_new_project() {
					
	        	 	new_project_btn.click();
	        	 	new_project.click();
				} 
	         public void change_activity() {
					
	        	 	new_activity_btn.click();
	        	 	new_activity.click();
				} 
	         public void change_time() {
					
	        	 	add_hrs.sendKeys("05");
	        	 	change_mins_dropdown.click();
	        	 	change_mins.click();
				}
	         public void update_notes() {
					
	        	 	add_notes.sendKeys("Testing edit functionality");
	        	}
	         public void save_edited_entry() {
					
	        	 	update_entry.click();
				}
	         public void delete_entry() {
					
	        	 	delete_entry_btn.click();
				}
	         public void datepicker() {
					
	        	 	datepicker_btn.click();
	        	 	datepicker_date.click();
				}
	}

