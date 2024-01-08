package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Projecttypespage {
WebDriver driver;

@FindBy(how=How.XPATH, using="//a[@href='/project-types']//p[contains(text(),'Project Types')]")
WebElement project_types_btn;

@FindBy(how=How.XPATH, using="//button[text()='New project type']")
WebElement new_project_type_btn;

@FindBy(how=How.CSS, using="button[type='submit']")
WebElement save_project_type_btn;

@FindBy(how=How.XPATH, using="(//button)[35]") //select tenth option
WebElement select_project_type_btn;

@FindBy(how=How.XPATH, using="(//button[@role='menuitem'][normalize-space()='Delete'])[10]") 
WebElement delete_project_type_btn;

@FindBy(how=How.CSS, using="div[data-focus-lock-disabled='false'] button:nth-child(1)") 
WebElement delete_confirm_btn;

@FindBy(how=How.XPATH, using="(//label)[14]") 
WebElement select_thirteenth_project_type ;

@FindBy(how=How.XPATH, using="(//label)[15]") 
WebElement select_fourteenth_project_type ;

@FindBy(how=How.XPATH, using="//button[text()='Delete selected types']") 
WebElement delete_multi_project_type_btn ;

@FindBy(how=How.CSS, using="div[data-focus-lock-disabled='false'] button:nth-child(1)") 
WebElement delete_multi_confirm_btn ;

@FindBy(how=How.XPATH, using="(//button)[38]") //Selecting eleventh checkbox
WebElement select_edit_project_type_btn;

@FindBy(how=How.XPATH, using="(//button[@role='menuitem'][normalize-space()='Edit'])[11]") 
WebElement edit_project_type_btn;

@FindBy(how=How.XPATH, using="//button[normalize-space()='Update type']") 
WebElement upd_project_type_btn;

	public Projecttypespage(WebDriver driver) //constructor // catch argument sent by class where landingpage object is created 
	{
		     //initialization      
	         this.driver = driver;
	         PageFactory.initElements(driver, this);
	}
		
	public void project_types() {
		
		project_types_btn.click();
	} 
	public void new_project_type() {
		
		new_project_type_btn.click();
	} 
	public void save_project_type() {
		
		save_project_type_btn.click();
	} 
	public void select_project_type() {
		
		select_project_type_btn.click();
	} 
	public void delete_project_type() {
		
		delete_project_type_btn.click();
		delete_confirm_btn.click();
	} 
	
	public void select_multi_project_types() {
		
	    select_thirteenth_project_type .click();
	    select_fourteenth_project_type .click();
	}
	public void delete_multi_project_types() {
		
	    delete_multi_project_type_btn.click();
	    delete_multi_confirm_btn.click();
	}
	public void select_edit_project_type() {
		
		select_edit_project_type_btn.click();
		edit_project_type_btn.click();
	} 
	public void edit_project_type() {
		
		upd_project_type_btn.click();
	
	}
}
