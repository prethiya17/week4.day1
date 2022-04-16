package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameDay1Ass3 {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Setting up the Webdriver
		WebDriverManager.chromedriver().setup();
		// Creating an instance of ChromeDriver
		ChromeDriver driver = new ChromeDriver();
		// Implicit Wait applied for driver methods
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Pass the URL
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		// Maximize the Window
		driver.manage().window().maximize();
		Thread.sleep(2000);
		// Switch to Frame
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//b[contains(text(),'Topic ')]/following-sibling::input")).sendKeys("Test");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Switch to Inner Frame
		driver.switchTo().frame(0);
		// Click the Inner Frame
		driver.findElement(By.xpath("//b[contains(text(),'Inner Frame Check box ')]/following-sibling::input")).click();
//Close the browser after every action performed and Take a Screenshot
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File(
				"C:/Users/gssprpra/OneDrive - Flex/Desktop/All Folders/Learnings_TL/Snaps/Screenshot.png");
		FileUtils.copyFile(source, destination);
		System.out.println("All Actions are performed and Screenshot Captured");
		driver.close();

	}

}
