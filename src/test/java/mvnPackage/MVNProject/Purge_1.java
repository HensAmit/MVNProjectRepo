package mvnPackage.MVNProject;

import java.io.File;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Purge_1 {
	WebDriver driver;
	WebElement statusDropdown;
	public static WebDriverWait wait;
	public static final String url = "https://login-aws-qa.tegrity.com/Service/OktaLogin.aspx";
	public static final String univFullName = "test2-qa.tegrity.com";
	public static final String univName = "test2-qa";
		
	@Test
	public void purgeCourses() throws Exception{		
		System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/Amit_Hens/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 150);
		driver.get(url);
		driver.findElement(By.xpath("(//input[contains(@id,'DomainColumn')])[1]")).sendKeys(univName);
        driver.findElement(By.xpath("(//input[contains(@id,'DomainColumn')])[1]")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='"+univName+"']")).click();
        Thread.sleep(2000);
        String defaultWindow = driver.getWindowHandle();
        String univWindow = null;
        Set<String> windowSet = driver.getWindowHandles();
        for(String temp : windowSet){
        	if(!temp.equals(defaultWindow)){
        		univWindow = temp;
        	}
        }
        driver.switchTo().window(univWindow);
        Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('Main_Dashboard1_RadGridInstitutes_ctl00_ctl04_LinkButtonInstitution').click()");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//a[text()='View Course List']")).click();
		Thread.sleep(3000);
		
		while(true){
			statusDropdown = driver.findElement(By.id("gs_Status"));
			Select select = new Select(statusDropdown);
			select.selectByVisibleText("None");
			Thread.sleep(5000);
			driver.findElement(By.id("cb_gridData")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[text()='Course Tasks']")).click();
			Thread.sleep(1000);
			try{
				driver.findElement(By.xpath("//a[@title='Purge Course(s)']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[contains(@class,'pristine')])[2]")));
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
					break;
				}
				driver.findElement(By.xpath("//button[text()='Ok']")).click();
				Thread.sleep(5000);
			}
			catch(Throwable t) {
				takeScreenShot();
				break;
			}
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
}
