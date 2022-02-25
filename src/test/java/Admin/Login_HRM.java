package Admin;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Action.Action;
import ExeleFile.ExcelUtils;


public class Login_HRM extends Action {
	/*Login_HRM() {
		super();
		// TODO Auto-generated constructor stub
	}
*/
	ExcelUtils excelutils = new ExcelUtils();
	@BeforeClass	
	public void loginFunc() throws InterruptedException, IOException {

		browserOpen();
		//screenShot();
		Login();
		//screenShot();
	
	}
	@Test
	public void HRMfunc() throws InterruptedException, IOException {
		Thread.sleep(4000);
		// Verify the current URL and check if it contains the string
		// “Dashboard”.
	/*	String strUrl = driver.getCurrentUrl();
		System.out.println("Current Url is:" + strUrl);*/
		// Go to Admin Tab
		driver.findElement(By.xpath("//*[@id='menu_admin_viewAdminModule']/b")).click();
		System.out.println("Successfully click on Admin tab");
		// Go to the Job tab and check ‘Job Titles’ is there or not
		WebElement ele = driver.findElement(By.id("menu_admin_Job"));
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		screenShot();
		// Click on job Title
		driver.findElement(By.xpath("//*[@id='menu_admin_viewJobTitleList']")).click();
		System.out.println("Successfully click on job title");
		screenShot();
	}
	@Test
	public void addJobTital() throws IOException, InterruptedException {
		// Click on Add button
		Thread.sleep(3000);
		driver.findElement(By.id("btnAdd")).click();
		System.out.println("Click on Add Button");
		screenShot();
		// Add job
		WebElement JobTitle = driver.findElement(By.id("jobTitle_jobTitle"));
		WebElement JobDescription = driver.findElement(By.id("jobTitle_jobDescription"));
		WebElement Note = driver.findElement(By.id("jobTitle_note"));
		// calling the ExcelUtils class method to initialize the workbook and
		// sheet

		String excelFilePath = "C:\\Users\\Nitin\\workspace\\CRM project\\src\\main\\resources\\Test.xlsx";
		excelutils.setExcelFile(excelFilePath, "STUDENT_DATA");
		for (int i = 1; i <= excelutils.getRowCountInSheet(); i++) {
			// Enter the values read from Excel in JobTitle,JobDescription,Note
			JobTitle.sendKeys(excelutils.getCellData(i, 0));
			JobDescription.sendKeys(excelutils.getCellData(i, 1));
			Note.sendKeys(excelutils.getCellData(i, 2));
			screenShot();
			// click on save button
			driver.findElement(By.id("btnSave")).click();
		}
	}
	@AfterClass
	public void logout() throws InterruptedException, IOException{	
			// log out
			Thread.sleep(4000);
			driver.findElement(By.id("welcome")).click();
			Thread.sleep(3000);
			//screenShot();
			driver.findElement(By.xpath("//*[@id='welcome-menu']/ul/li[3]/a")).click();
			System.out.println("Sucessfully logout");
			//screenShot();
			// close Browser
			Thread.sleep(6000);
			driver.quit();
		}
	}
	