package mvnPackage.MVNProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo3 {
	public static void main(String args[]){
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
		eventDriver.register(new MyEventListener());
		eventDriver.manage().window().maximize();
		eventDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		eventDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 150);		
		eventDriver.get("https://supportu.tegrity.com/#/login");
		eventDriver.findElement(By.id("TextFieldUserName")).sendKeys("supportuadmin");
		eventDriver.findElement(By.id("TextFieldPassword")).sendKeys("Prod@1234");
		
	}
}
