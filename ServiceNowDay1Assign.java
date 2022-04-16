package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowDay1Assign {

	public static void main(String[] args) throws InterruptedException {
		// Setting up the Webdriver
		WebDriverManager.chromedriver().setup();
		// Creating an instance of ChromeDriver
		ChromeDriver driver = new ChromeDriver();
		// Implicit Wait applied for driver methods
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Pass the URL
		driver.get("https://dev122476.service-now.com/");
		// Maximize the Window
		driver.manage().window().maximize();
		driver.switchTo().frame(0);
		// Identify the Username field
		driver.findElement(By.id("user_name")).sendKeys("admin");
		// Identify the Password
		driver.findElement(By.id("user_password")).sendKeys("Testleaf@123");
		driver.findElement(By.id("sysverb_login")).click();// Click on Login
		// Search “incident “ Filter Navigator
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident");
		Thread.sleep(3000);
		// Click on All option
		driver.findElement(By.xpath(
				"//a[@id='b55b4ab0c0a80009007a9c0f03fb4da9']//div[@class='sn-widget-list-title'][normalize-space()='All']"))
				.click();
		Thread.sleep(3000);

		// Click on New button
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
		Thread.sleep(3000);
		System.out.println("Before New incident Page");
		// Select a value for Caller and Enter value for short_description

		System.out.println("Into New Incident Frame page");
		String incidentNumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println("The Incident Number is:" + "" + incidentNumber);

		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']")).click();

		Thread.sleep(3000);
		// Caller List opens in the New window,so window handles need to be processed
		Set<String> searchSet = driver.getWindowHandles();// Window handles as a Set
		List<String> searchList = new ArrayList<String>(searchSet);// Convert Set to List
		String parentWin = searchList.get(0);// Get the Parent Window
		String searchWin = searchList.get(1);// Get the Child Window

		driver.switchTo().window(searchWin);// Switch to Child Window
		// Click a Caller from the List
		driver.findElement(By.xpath("//tbody[@class='list2_body']/tr[1]/td[3]/a")).click();
		Thread.sleep(1000);
		// Switching to New Incident Creation Window
		driver.switchTo().window(parentWin);
		System.out.println("Switching to New Incident after selecting Caller");
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		// Provide value in description field
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Test");
		// Click on Submit button
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();
		Thread.sleep(3000);

		// Search the Incident in Inclident Page
		WebElement searchIncident = driver
				.findElement(By.xpath("//span[contains(text(),'Press Enter')]/following-sibling::input"));
		searchIncident.sendKeys(incidentNumber);
		System.out.println("Before Searching the Incident");
		searchIncident.sendKeys(Keys.ENTER);
		System.out.println("After Searching the Incident");
		Thread.sleep(3000);
		WebElement searchResult = driver.findElement(
				By.xpath("//td[contains(@class,'list_decoration_cell col-control col-small col-center')]"));
		// Validate that whether Incident is available
		if (searchResult.getSize() != null) {
			System.out.println("Incident Number available");
		} else {
			System.out.println("Incident Number unavailable");
		}
	}

}
