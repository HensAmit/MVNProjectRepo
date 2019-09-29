package mvnPackage.MVNProject;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Purge {
	WebDriver driver;
	WebElement statusDropdown;
	public static WebDriverWait wait;
	
	@Test
	public void purgeCourses() throws Exception{		
		System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 150);
		driver.get("https://supportu.tegrity.com/#/login");
		driver.findElement(By.id("TextFieldUserName")).sendKeys("supportuadmin");
		driver.findElement(By.id("TextFieldPassword")).sendKeys("Prod@1234");
		driver.findElement(By.id("ButtonLogin")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='View Course List']")).click();
		Thread.sleep(3000);
		
		//purge the 1st page list
		purge();
		
		//purge 2nd to last page list
		while(!driver.findElement(By.id("next_gridDataPager")).getAttribute("class").contains("disabled")){
			driver.findElement(By.xpath("//span[contains(@class,'seek-next')]")).click();
			Thread.sleep(2000);
			purge();
		}		
	}
	
	public void purge() throws Exception{
		statusDropdown = driver.findElement(By.id("gs_Status"));
		Select select = new Select(statusDropdown);
		select.selectByVisibleText("None");
		Thread.sleep(5000);
		driver.findElement(By.id("cb_gridData")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Course Tasks']")).click();
		Thread.sleep(1000);
		if(driver.findElement(By.xpath("//a[@title='Purge Course(s)']")).isEnabled()){
			driver.findElement(By.xpath("//a[@title='Purge Course(s)']")).click();
			Thread.sleep(2000);		
			driver.findElement(By.xpath("(//input[contains(@class,'pristine')])[2]")).sendKeys("DELETE");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@title='Purge']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ok']")));
			Thread.sleep(5000);
			if(driver.findElement(By.id("ModalDialogHeader")).getText().equals("Success")){
				System.out.println("PURGE WAS SUCCESSFUL");
			} else{
				System.out.println("PURGE WAS FAILURE");
				takeScreenShot();
				Assert.fail("PURGE WAS FAILURE");				
			}
			driver.findElement(By.xpath("//button[text()='Ok']")).click();
			Thread.sleep(5000);
		}
	}
	
	public void takeScreenShot(){
		Date d = new Date();
		String screenshotFileName = d.toString().replaceAll(":", "_").replaceAll(" ", "_")+".png";
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(screenshotFile, new File("screenshots//"+screenshotFileName));
		} catch(Throwable t){
			t.printStackTrace();
		}		
	}
	
	@AfterTest
	public void close(){
		driver.quit();
	}

}
