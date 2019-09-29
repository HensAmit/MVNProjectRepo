package mvnPackage.MVNProject;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ClientServices {
	public static WebDriverWait wait;
	public static void main(String[] args) throws Exception{
		System.setProperty("webdriver.chrome.driver","drivers//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/Amit_Hens/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 150);
		driver.get("https://login-aws-qa.tegrity.com/Service/OktaLogin.aspx");
        driver.findElement(By.xpath("(//input[contains(@id,'DomainColumn')])[1]")).sendKeys("test1-qa.tegrity.com");
        driver.findElement(By.xpath("(//input[contains(@id,'DomainColumn')])[1]")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='test1-qa']")).click();
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
        driver.findElement(By.xpath("//a[text()='View Course List']")).click();
	}
}
