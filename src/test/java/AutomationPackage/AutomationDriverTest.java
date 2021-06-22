package AutomationPackage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationDriverTest {
	private static final long timeout = 30;
	private static final Object Element = null;
	WebElement element;
	public WebDriver driver;
	public WebDriver url;
	public Map<String, Object> vars;
	JavascriptExecutor js;
	public ExtentTest logger;
	ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	private ExtentTest extentTest;
	
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();		//setup a generic chrome webdriver - else it needs to be specified per user
		driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.navigate().to("https://www.ilabquality.com/careers/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);    //set the timeout of each Selenium transaction to 30 Seconds
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		htmlReporter = new ExtentHtmlReporter("extent.html"); //Starting a report
		extent = new ExtentReports(); //Create extent report and attach reporter(s)
		extent.attachReporter(htmlReporter);
	}
			
			 @Test
			  public void iLabWebsite() {
				 extentTest  = extent.createTest("iLabWebsite" , "Navigate to the iLabWebsite");
				 extentTest.log(Status.INFO,"Starting the test Case"); 
			    driver.get("https://www.ilabquality.com/careers/");
			    driver.manage().window().setSize(new Dimension(1936, 1056));
			    assertThat(driver.findElement(By.linkText("South Africa")).getText(), is("South Africa"));
			    extentTest.pass("Select South Africa");
			    driver.findElement(By.linkText("South Africa")).click();
			    assertThat(driver.findElement(By.linkText("Interns - BSC Computer Science, National Diploma: IT Development Graduates")).getText(), is("Interns - BSC Computer Science, National Diploma: IT Development Graduates"));
			    extentTest.pass("Click on first available job hyperlink");
			    driver.findElement(By.linkText("Interns - BSC Computer Science, National Diploma: IT Development Graduates")).click();
			    assertThat(driver.findElement(By.linkText("Apply Online")).getText(), is("Apply Online  "));
			    extentTest.pass("Select Apply Online button");
			    driver.findElement(By.cssSelector(".wpjb-icon-down-open")).click();
			    extentTest.pass("Click dropdown button");
			    driver.findElement(By.id("applicant_name")).click();
			    extentTest.pass("Enter the first name");
			    driver.findElement(By.id("applicant_name")).sendKeys("Nandipha");
			    extentTest.pass("Enter email address");
			    driver.findElement(By.id("email")).click();
			    driver.findElement(By.id("email")).sendKeys("automationAssessment@iLABQuality.com");
			    driver.findElement(By.id("phone")).click();
			    driver.findElement(By.id("phone")).sendKeys("0835687859");
			    driver.findElement(By.id("message")).click();
			    extentTest.pass("Enter the text");
			    driver.findElement(By.id("message")).sendKeys("It\'s one of the best software companies");
			    extentTest.pass("Click send message button");
			    driver.findElement(By.id("wpjb_submit")).click();
			  }
			 
			 @AfterClass 
				public void teardown() {
					driver.quit();
					extent.flush();
			 	}
			}