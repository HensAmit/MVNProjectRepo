package mvnPackage.MVNProject;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicTable {
	static WebDriver driver = null;
	public static void main(String args[]) {
		String companyName="Eicher Motors";
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get("https://money.rediff.com/gainers/bsc/daily/groupa");
		int rNum = returnRowNumber(companyName);
		int currentPriceColNum = returnColumnNumber("Current Price (Rs)");
		System.out.println(driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr["+rNum+"]/td["+currentPriceColNum+"]")).getText());
	}
	
	public static int returnRowNumber(String companyName) {
		List<WebElement> compList = driver.findElements(By.xpath("//table[@class='dataTable']/tbody//td["+returnColumnNumber("Company")+"]"));
		for(int i=0; i<compList.size(); i++) {
			if(compList.get(i).getText().equals(companyName)) {
				return ++i;
			}
		}
		return -1;
	}
	
	public static int returnColumnNumber(String cName) {
		List<WebElement> colHeadings = driver.findElements(By.xpath("//table[@class='dataTable']//th"));
		for(int i=0; i<colHeadings.size(); i++) {
			if(colHeadings.get(i).getText().equals(cName)) {
				return ++i;
			}
		}
		return -1;
	}
}
