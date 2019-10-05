package mvnPackage.MVNProject;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RandomDemo {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://omayo.blogspot.com");
		int linkCount = driver.findElements(By.xpath("//div[@class='widget LinkList']//ul//a")).size();
		int randomNumber = generateRandomNumber(linkCount)+1;
		System.out.println(randomNumber);
		driver.findElement(By.xpath("(//div[@class='widget LinkList']//ul//a)["+randomNumber+"]")).click();
	}
	
	public static int generateRandomNumber(int bound) {
		Random random = new Random();
		return random.nextInt(bound);
	}
}
