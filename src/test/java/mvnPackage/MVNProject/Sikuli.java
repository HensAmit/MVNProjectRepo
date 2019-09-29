package mvnPackage.MVNProject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sikuli {
	
	ExtentReports eReport;
	ExtentTest eTest;
	@Test
	public void testLogin() throws Exception{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 180);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://test1-qa.tegrity.com/#/login");
		driver.findElement(By.id("TextFieldUserName")).sendKeys("mins1");;
		driver.findElement(By.id("TextFieldPassword")).sendKeys("111");
		driver.findElement(By.id("ButtonLogin")).click();
		driver.findElement(By.xpath("//a[text()='_webcast-test']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CourseTask")));
		driver.findElement(By.id("CourseTask")).click();
		driver.findElement(By.id("UploadAudioFile")).click();		
		Screen screen = new Screen();
		Pattern alert = new Pattern("\\SikuliImages\\alert.JPG");
		screen.wait(alert, 10);
		screen.click(alert);
		
		Pattern EmailAddressImage = new Pattern("\\LoginImages\\EmailAddressImage.png");
		screen.type(EmailAddressImage,"ravi.kiran1@gmail.com");
		Pattern uploadButton = new Pattern("\\LoginImages\\EmailAddressImage.png");
		Pattern fileNameTextBox = new Pattern("\\LoginImages\\EmailAddressImage.png");
		Pattern openButton = new Pattern("\\LoginImages\\EmailAddressImage.png");
		screen.click(uploadButton);
		screen.type(fileNameTextBox,System.getProperty("user.dir")+"\\files\\sample.jpg");
		screen.click(openButton);
	}
		
}
