package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameDay1Assign4 {

	public static void main(String[] args) throws IOException {
		// Setting up the Webdriver
		WebDriverManager.chromedriver().setup();
		// Creating an instance of ChromeDriver
		ChromeDriver driver = new ChromeDriver();
		// Implicit Wait applied for driver methods
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Pass the URL
		driver.get("http://leafground.com/pages/frame.html");
		// Maximize the Window
		driver.manage().window().maximize();
		driver.switchTo().frame(0);
		// driver.manage().
		driver.findElement(By.xpath("//button[@id='Click']")).click();

		// Get the Screenshot of the "Click me" button
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File(
				"C:/Users/gssprpra/OneDrive - Flex/Desktop/All Folders/Learnings_TL/Snaps/Screenshot.png");
		FileUtils.copyFile(source, destination);
		System.out.println("All Actions are performed and Screenshot Captured");

		// Find the Number of Frames
		List<WebElement> iframes = driver.findElements(By.xpath("//iframe"));

		System.out.println("The Number of iframes are:" + "" + iframes.size());

	}

}
