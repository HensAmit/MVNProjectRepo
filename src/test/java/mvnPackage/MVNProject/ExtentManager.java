package mvnPackage.MVNProject;

import java.io.File;
import java.util.Date;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	@Test
	public static ExtentReports getInstance(){
		Date date = new Date();
		String originalDateFormat = date.toString();
		String modifiedDateFormat = originalDateFormat.replace(":","_").replace(" ","_");
		String fileName = modifiedDateFormat+".html";
		String reportFilePath = "reports//"+fileName;
		ExtentReports report = new ExtentReports(reportFilePath,true,DisplayOrder.NEWEST_FIRST);
		File reportsConfigFile = new File("ReportsConfig.xml");
		report.loadConfig(reportsConfigFile);
		report.addSystemInfo("TestNG Version","6.14.3")
		.addSystemInfo("WebDriver Version","3.141.59");
		return report;
	}
}
