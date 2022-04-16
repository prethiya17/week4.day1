package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContactDay1Assign {

	public static void main(String[] args) throws InterruptedException {
		// Setting up the Webdriver
		WebDriverManager.chromedriver().setup();
		// Creating an instance of ChromeDriver
		ChromeDriver driver = new ChromeDriver();
		// Implicit Wait applied for driver methods
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Pass the URL
		driver.get("http://leaftaps.com/opentaps/control/login");
		// Maximize the Window
		driver.manage().window().maximize();
		// Identify the Username field
		driver.findElement(By.id("username")).sendKeys("democsr");
		// Identify Pass Field
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		// click on login
		driver.findElement(By.className("decorativeSubmit")).click();
		// Click on CRM
		driver.findElement(By.linkText("CRM/SFA")).click();
		// Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		// Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		// Click on Widget of From Contact
		driver.findElement(By.xpath("//input[@id='partyIdFrom']/following::img")).click();
		// Get Parent Reference
		String parent = driver.getWindowHandle();
		// Traversing to another Window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList(windowHandles);

		String FindContact = windowList.get(1);
		System.out.println(FindContact);
		// Switching to FInd Contact Window
		driver.switchTo().window(FindContact);
		System.out.println("Before Selecting From Contact");
		Thread.sleep(5000);
		// 8. Click on First Resulting Contact
		driver.findElement(By.xpath("//a[@class='linktext'][1]")).click();

		// Return to previous Window
		driver.switchTo().window(parent);
		System.out.println(parent);
		// Switching to Contact Window
		System.out.println("After Selecting From Contact");
		System.out.println(driver.getTitle());
		// 9. Click on Widget of To Contact
		driver.findElement(By.xpath("//a[contains(@href,'partyIdTo')]")).click();

		// Switching to FInd Contact
		Set<String> windowHandles1 = driver.getWindowHandles();/// get windows reference in set
		List<String> windowsList1 = new ArrayList<String>(windowHandles1);// convert set to list

		String FindContact1 = windowsList1.get(1);// get result window1 reference
		// Switching to FInd Contact Window
		driver.switchTo().window(FindContact1);// switch to result window1
		Thread.sleep(3000);
		System.out.println("resulting window1 title:" + driver.getTitle());// get result window title

		System.out.println("Before Selecting To Contact");
		// 10. Click on Second Resulting Contact
		driver.findElement(By.xpath("//div[2]/table/tbody/tr[1]/td[1]/div/a[@class='linktext']")).click();

		// Switching to Contact Window
		// Return to previous Window

		driver.switchTo().window(parent);

		System.out.println("After Selecting To Contact:" + "" + parent);

		driver.findElement(By.xpath("//form[@name='MergePartyForm']//a[@class='buttonDangerous']")).click();
		System.out.println("After Clicking Merge Contact button");
		// Switch to Alert
		Alert alert = driver.switchTo().alert();
		// 12. Accept the Alert
		alert.accept();

		// 13. Verify the title of the page
		String title = driver.getTitle();
		// Print the title and verify the Title
		System.out.println(title);
		if (title.contains("Merge Contacts")) {
			System.out.println("We are in Merge Contacts Page");
		} else {
			System.out.println("Not in Merge Contacts Page");
		}

	}

}
