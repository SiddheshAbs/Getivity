package Pageobjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class Clientpage {
WebDriver driver;


	@FindBy(how=How.XPATH, using="//a[normalize-space()='New Client']")
	WebElement new_client_btn;
	
	@FindBy(how=How.ID, using="address")
	WebElement client_address;
	
	@FindBy(how=How.XPATH, using="//button[text()='Save Client']")
	WebElement save_client_btn;
	
	@FindBy(how=How.XPATH, using="(//a[@href='/contacts/new'])[1]")
	WebElement wait_addcontact_btn;
	
	@FindBy(how=How.XPATH, using="//button[@type='submit']")
	WebElement save_contact;
	
	@FindBy(how=How.XPATH, using="(//a[contains(text(),'Edit')])[2]") //second 
	WebElement edit_contact_btn;
	
	@FindBy(how=How.XPATH, using="//button[text()='Update contact']")
	WebElement upd_contact_button;
	
	@FindBy(how=How.XPATH, using="(//a[contains(text(),'Edit')])[1]") //first client
	WebElement edit_client_btn;
	
	@FindBy(how=How.XPATH, using="/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]") 
	WebElement archive_client_btn;
	
	@FindBy(how=How.XPATH, using="(//button[@type='button'][normalize-space()='Restore'])[1]") //first restore button
	WebElement restore_client_btn;
	
	@FindBy(how=How.XPATH, using= "//a[text()='Back to active clients']")
	WebElement back_to_active_clients_btn;
	
	@FindBy(how=How.XPATH, using= "//button[text()='Update client']")
	WebElement upd_client_btn;
	
	@FindBy(how=How.XPATH, using= "(//a[contains(text(),'Edit')])[5]") //change number of edit button
	WebElement edit_last_client;
	
	@FindBy(how=How.XPATH, using= "//body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]")
	WebElement edit_last_client_btn ;
	
	@FindBy(how=How.XPATH, using= "//input[@aria-required='true']")
	WebElement  input_delete;
	
	@FindBy(how=How.XPATH, using= "(//button[normalize-space()='Delete Client'])[1]")
	WebElement  delete_btn;
	
	public Clientpage(WebDriver driver) //constructor // catch argument sent by class where landingpage object is created 
	{
		     //initialization      
	         this.driver = driver;
	         PageFactory.initElements(driver, this);
	}
		
	public void new_client() {
		
		new_client_btn.click();
	}
	
	public void client_details() {
		
		client_address.sendKeys(RandomStringUtils.randomAlphabetic(10));
		save_client_btn.click();
		
	}
	public void wait_for_contact_btn() {
		
		wait_addcontact_btn.click();
		
	}
	public void save_contact_btn() {
		
		save_contact.click();
	}
	public void edit_contact_btn() {
		
		edit_contact_btn.click();
	}
	public void upd_contact_btn() {
		
		upd_contact_button.click();
	}
	public void edit_client() {
		
		edit_client_btn.click();
	}
	public void archive_client() {
		
		archive_client_btn.click();
	}
	public void restore_client() {
		
		restore_client_btn.click();
		back_to_active_clients_btn.click();
	}
	public void update_client() {
		
		upd_client_btn.click();
	}
	public void edit_delete_client() {
		
		edit_last_client.click();
		edit_last_client_btn.click();
	}
	public void delete_client() {
		
		input_delete.sendKeys("DELETE");
		delete_btn.click();
	}
}

