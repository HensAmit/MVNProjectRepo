package mvnPackage.MVNProject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLink {
	public static void main(String args[]) throws InterruptedException, MalformedURLException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://supportu.tegrity.com");
		driver.findElement(By.id("TextFieldUserName")).sendKeys("supportuadmin");
		driver.findElement(By.id("TextFieldPassword")).sendKeys("Prod@1234");
		driver.findElement(By.id("ButtonLogin")).click();
		Thread.sleep(5000);
		
		List<WebElement> linksList = driver.findElements(By.tagName("a"));
		System.out.println("Total item in linksList "+linksList.size());
		ArrayList<WebElement> finalList = new ArrayList<WebElement>();
		for(WebElement e : linksList) {
			String href = e.getAttribute("href");
			if(!href.trim().equals("")) {
				finalList.add(e);
			}						
		}
		System.out.println("Total item in finalList "+finalList.size());
		System.out.println("Printing final list--->");
		for(WebElement e : finalList) {
//			System.out.println(e.getAttribute("href"));
			HttpURLConnection connection = (HttpURLConnection) new URL(e.getAttribute("href")).openConnection();
			connection.connect();
			String response=connection.getResponseMessage();
			System.out.println(e.getText()+"--->"+e.getAttribute("href")+"--->"+response+"--->"+connection.getResponseCode());
		}
	}
}
