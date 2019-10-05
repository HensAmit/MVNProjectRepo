package mvnPackage.MVNProject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadFile {
	public static void main(String args[]) throws Exception {
//		//set the profile
//		FirefoxProfile profile = new FirefoxProfile();
//		profile.setPreference("browser.download.folderList", 2);
//		String downloadPath = System.getProperty("user.dir")+"\\downloads";
//		profile.setPreference("browser.download.dir", downloadPath);
//		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip");
//		
//		//set the profile to the option object
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(profile);
//		
//		//pass the option object to the driver constructor
//		WebDriverManager.firefoxdriver().setup();
//		WebDriver driver = new FirefoxDriver(options);
//		driver.manage().window().maximize();
//		driver.get("http://omayo.blogspot.in/p/page7.html");		
//		driver.findElement(By.linkText("ZIP file")).click();		
//		//Give time to download
//		Thread.sleep(5000);
//		
//		File file = new File(downloadPath);
//		if(file.exists()) {
//			System.out.println("File downloaded successfully");
//		} else {
//			System.out.println("File downloading failed");
//		}		
		
		
		ChromeOptions options = new ChromeOptions();
		Map<String,Object> preferences = new HashMap<String,Object>();
		preferences.put("profile.default_content_settings.popups", 0);
		String downloadPath = System.getProperty("user.dir")+"\\downloads";
		preferences.put("download.default_directory",downloadPath);
		options.setExperimentalOption("prefs", preferences);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://omayo.blogspot.com/p/page7.html");
		
		driver.findElement(By.linkText("ZIP file")).click();
		
		
	}

}
