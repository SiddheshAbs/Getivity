package Pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Rolespage {
WebDriver driver;

@FindBy(how=How.XPATH, using="(//p[text()='Roles'])[1]")
WebElement nav_to_roles_tab;

@FindBy(how=How.XPATH, using="(//button[text()='New role'])[1]")
WebElement new_roles_tab;

@FindBy(how=How.XPATH, using="(//button[normalize-space()='Save role'])[1]")
WebElement save_role_btn;

@FindBy(how=How.XPATH, using="//div//div//div//div//div//div//div//div[2]//div[3]//button[1]")
WebElement edit_roles_btn;

@FindBy(how=How.XPATH, using="(//button[normalize-space()='Update role'])[1]")
WebElement update_roles_btn;

@FindBy(how=How.XPATH, using="(//span[@aria-hidden='true'])[3]")
WebElement single_user;

@FindBy(how=How.XPATH, using="(//span[@aria-hidden='true'])[2]")
WebElement first_user;

@FindBy(how=How.XPATH, using="(//span[@aria-hidden='true'])[3]")
WebElement second_user;

@FindBy(how=How.XPATH, using="(//span[@aria-hidden='true'])[4]")
WebElement third_user;

@FindBy(how=How.XPATH, using="(//button[@type='button'][normalize-space()='Delete'])[1]") //first role in list
WebElement delete_role_btn;

public Rolespage(WebDriver driver) //constructor // catch argument sent by class where landingpage object is created 
			{
				     //initialization      
			         this.driver = driver;
			         PageFactory.initElements(driver, this);
			}
				
			public void nav_to_roles() {
				
				nav_to_roles_tab.click();
			}
			public void new_roles() {
				
				new_roles_tab.click();
			}
			public void save_roles() {
				
				save_role_btn.click();
			}
			public void edit_roles() {
				
				edit_roles_btn.click();
			}
			public void update_roles() {
				
				update_roles_btn.click();
			}
			public void single_user_assign() {
				
				single_user.click();
			}
			public void multi_user_assign() {
				
				first_user.click();
				second_user.click();
				third_user.click();
			}
			public void delete_roles() {
				
				delete_role_btn.click();
			}
}
