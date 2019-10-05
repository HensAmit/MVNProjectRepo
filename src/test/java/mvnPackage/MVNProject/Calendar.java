package mvnPackage.MVNProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Calendar {
	public static void main(String args[]) throws ParseException {
//		String dateString = "15/8/1947";
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date formatedDate = sdf.parse(dateString);
//		System.out.println(formatedDate);
		
//		String stringDay = (new SimpleDateFormat("dd")).format(formatedDate);
//		System.out.println(stringDay);
//		int intDay = Integer.parseInt(stringDay);
//		System.out.println(intDay+100);
		
//		String month = (new SimpleDateFormat("MMMM")).format(formatedDate);
//		System.out.println(month);
		
//		String year = (new SimpleDateFormat("yy")).format(formatedDate);
//		System.out.println(year);
		
//		String displayedMonth = "July";
//		Date date = new SimpleDateFormat("MMMM").parse(displayedMonth);
//		String month = new SimpleDateFormat("MM").format(date);
//		System.out.println(month);
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
//		options.addArguments("user-data-dir=C:\\Users\\Amit_Hens\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://omayo.blogspot.com/p/page6.html");
		driver.switchTo().frame("dateFrame").switchTo().frame(driver.findElement(By.className("demo-frame")));
		driver.findElement(By.id("datepicker")).click();
		
		String dateSelect = "02.07.2017";
		Date formatedDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateSelect);
		String sDay = new SimpleDateFormat("dd").format(formatedDate);
		int day = Integer.parseInt(sDay);
		String sMonth = new SimpleDateFormat("MM").format(formatedDate);
		int month = Integer.parseInt(sMonth);
		String sYear = new SimpleDateFormat("yyyy").format(formatedDate);
		int year = Integer.parseInt(sYear);
		
		//navigate to year
		while(true) {
			String currentYearString = driver.findElement(By.className("ui-datepicker-year")).getText();
			int currentYear = Integer.parseInt(currentYearString);
			
			if(currentYear==year) {
				break;
			} else if(currentYear>year) {
				driver.findElement(By.xpath("//span[text()='Prev']")).click();
			} else if(currentYear<year) {
				driver.findElement(By.xpath("//span[text()='Next']")).click();
			}		
		}
		
		//navigate to month and click on date
		while(true) {
			String currentMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
			Date date = new SimpleDateFormat("MMMM").parse(currentMonth);
			int iMonth=Integer.parseInt(new SimpleDateFormat("MM").format(date));
			
			if(iMonth==month) {
				String dayXpath = "//a[text()='"+day+"']";
				driver.findElement(By.xpath(dayXpath)).click();
				break;
			} else if(iMonth>month) {
				driver.findElement(By.xpath("//span[text()='Prev']")).click();
			} else if(iMonth<month) {
				driver.findElement(By.xpath("//span[text()='Next']")).click();
			}
		}		
		
	}
}
