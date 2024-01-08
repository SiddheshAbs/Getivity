import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.Scanner;

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

import com.mifmif.common.regex.Main;

public class random {
	
	public static void main(String[] args) {
		
		String str,strrev="";
 	    Scanner sc=new Scanner(System.in); //input
System.out.print("Enter a string :");
       str=sc.next(); //read
for(int i=str.length()-1;i>=0;i--)
{
strrev=strrev+str.charAt(i);
System.out.println(strrev);
}
System.out.println("Reverse of a string is : "+strrev);
if(strrev.equalsIgnoreCase(str))
 {
	System.out.println("Entered string is palindrome");
}
   else
System.out.println("Entered string is not a palindrome number ");           
}
}