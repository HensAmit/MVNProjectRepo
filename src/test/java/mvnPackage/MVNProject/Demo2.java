package mvnPackage.MVNProject;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 150);		
		driver.get("https://supportu.tegrity.com/#/login");
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("document.getElementById('TextFieldUserName').value='prodinstforqa'");
		js.executeScript("arguments[0].value='prodinstforqa'",driver.findElement(By.id("TextFieldUserName")));
		js.executeScript("document.getElementById('TextFieldPassword').value='Prod@1234'");
		Thread.sleep(3000);
		js.executeScript("arguments[0].click()",driver.findElement(By.id("ButtonLogin")));
		
		
//		Actions actions = new Actions(driver);
//		WebElement loginButton = driver.findElement(By.id("ButtonLogin"));
//		actions.moveToElement(loginButton).click().build().perform();
//		System.setProperty("webdriver.chrome.driver","drivers//chromedriver.exe");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("user-data-dir=C:/Users/Amit_Hens/AppData/Local/Google/Chrome/User Data");
//		options.addArguments("--start-maximized");
//		WebDriver driver = new ChromeDriver(options);
//		driver.get("http://omayo.blogspot.com");
	}
}
