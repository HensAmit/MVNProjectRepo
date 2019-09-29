package mvnPackage.MVNProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo1 {
	public static void main(String[] args) throws Exception {
//		ProfilesIni allProfiles = new ProfilesIni();
//		FirefoxProfile profile1 = allProfiles.getProfile("Profile1");
//		profile1.setPreference("dom.webnotifications.enabled", false);
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(profile1);
//		
//		WebDriverManager.firefoxdriver().setup();
//		WebDriver driver = new FirefoxDriver(options);
//		driver.manage().window().maximize();
//		driver.get("https://jabong.com");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--start-maximized");
		options.addArguments("user-data-dir=C:\\Users\\AMIT HENS\\AppData\\Local\\Google\\Chrome\\User Data\\");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://jabong.com");
		
		
	}
}
