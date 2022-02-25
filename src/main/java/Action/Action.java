package Action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ConfigFileReader.ConfigFileReader;

public class Action {
	protected WebDriver driver;
	ConfigFileReader config = new ConfigFileReader();
	/*protected Action(){
		String Filepath = config.getDriverPath();
		
		System.setProperty("webdriver.chrome.driver",Filepath);
		driver = new ChromeDriver();
	}*/
	public void browserOpen(){
		String Filepath = config.getDriverPath();
		String url = config.getApplicationUrl();
		System.setProperty("webdriver.chrome.driver",Filepath);
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		System.out.println("Browser Successfully Open");
	}
	
	public void Login(){
		String userName = config.getUsername();
		String password = config.getPassword();
		driver.findElement(By.id("txtUsername")).sendKeys(userName);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		try 
		{
		     if(driver.findElement(By.id("btnLogin")).isDisplayed()) 
		     {
		    	 driver.findElement(By.id("btnLogin")).click();
		     }
		}
		catch (ElementClickInterceptedException e)
		{
			e.printStackTrace();
		    System.out.println("Login button is not display");
		}
		System.out.println("Application Sucessfully Login");
		
	}
	
	public void screenShot() throws IOException{
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(screenshot, new File("C:\\Users\\Nitin\\Pictures\\Screenshot.JPG"));
		 	System.out.println("Screenshot successfully");
		
	}
	
	public void timeOut() throws InterruptedException{
		Thread.sleep(4000);
	}
	
}
