package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.xmlbeans.impl.soap.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaDay1Assign5 {

	public static void main(String[] args) throws InterruptedException {
		//Setting up the Webdriver	
	    WebDriverManager.chromedriver().setup();
	  //Disabling Notifications
	    ChromeOptions options=new ChromeOptions();
	    options.addArguments("--disable-notifications");
		//Creating an instance of ChromeDriver
		ChromeDriver driver=new ChromeDriver(options);
	       
		
		//Implicit Wait applied for driver methods	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Pass the URL
		driver.get("https://www.nykaa.com/");
		//Maximize the Window
		driver.manage().window().maximize();
		
		//Mouseover on Brands and Search L'Oreal Paris
		Actions builder=new Actions(driver);
		WebElement eleBrands = driver.findElement(By.xpath("//a[text()='brands']"));
		
		//Mouse Hover
		builder.moveToElement(eleBrands).perform();
		
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).click();
		//WebElement ele2 = driver.findElement(By.xpath("//a[contains(text(),'L'Oreal Paris')]"));
		WebElement ele2 = driver.findElement(By.xpath("//*[@id=\"scroller-container\"]/div[7]/a"));
		builder.moveToElement(ele2).click().perform();
		
		//Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title = driver.getTitle();
		boolean title1 = title.contains("L'Oreal Paris");
		Thread.sleep(2000);
		System.out.println("The Title is Loreal paris:"+" "+title1);
		//Click sort By and select customer top rated
		driver.findElement(By.xpath("//*[@id='filter-sort']/div/div/button")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
		//Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		Thread.sleep(3000);
		
		//Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		//check whether the Filter is applied with Shampoo
		List<WebElement> findElements = driver.findElements(By.xpath("//span[text()='Shampoo']"));
		for(WebElement s:findElements) {
			String text = s.getText();
			boolean shamp = text.contains("Shampoo");
			System.out.println("The Title has Shampoo:"+shamp);
		}
		
		//Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//img[@alt=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();
		Thread.sleep(2000);
		
		//GO to the new window and select size as 175ml
		Set<String> windowHandles = driver.getWindowHandles();
		//Convert Set to List
		List<String> WindowList=new ArrayList(windowHandles);
		//Get into New Window 
		String shamp = WindowList.get(1);
		driver.switchTo().window(shamp);

		 
		Select dropdown=new Select(driver.findElement(By.xpath("//select[@title='SIZE']")));
		dropdown.selectByIndex(1);
		System.out.println("Option for Size as 175ml Selected");
		Thread.sleep(1000);
		
		//Print the MRP of the product
		System.out.println("Before Selecting the MRP");
		WebElement price = driver.findElement(By.xpath("//span[contains(text(),'150')]"));
		String text = price.getText();
		System.out.println("The price of the shampoo is:"+text);
		//Click on ADD to BAG
		driver.findElement(By.xpath("//span[contains(text(),'ADD TO BAG')]")).click();
		
		//Go to Shopping Bag 
		System.out.println("Before Selecting Shopping Bag");
		driver.findElement(By.xpath("//button[@class='css-g4vs13']//*[name()='svg']")).click();
		System.out.println("After Selecting Shopping Bag");
		Thread.sleep(1000);
		
		//Switch to Shopping Bag Page as it is inside the Frame
		driver.switchTo().frame(0);
		System.out.println("Into Shopping Bag Window");
        Thread.sleep(3000);
        
		//Print the Grand Total amount
		WebElement GrandTotal = driver.findElement(By.xpath("//span[text()='Grand Total'][1]/following::div"));
       //WebElement GrandTotal = driver.findElement(By.xpath("//button[@class='css-g4vs13']//*[name()='svg']"));
		String GrandTotal1 = GrandTotal.getText();
		System.out.println("The GrandTotal of the Shampoo in Cart Page is"+GrandTotal.getText());
		
		//Click Proceed
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		//button[@class='css-g4vs13']//*[name()='svg']
		//Click on Continue as Guest
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		Thread.sleep(1000);
		//Check if this grand total is the same in step 14
		WebElement FinalGrandTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div"));
		String FinalGrandTotal1 = FinalGrandTotal.getText();
		
		System.out.println("The Final GrandTotal Value in Address page is:"+FinalGrandTotal1);
		System.out.println("Before if Loop");
		
		if(GrandTotal1.matches(FinalGrandTotal1)) {
			System.out.println("Both the Shopping GrandTotal and Adress Page GrandTotals are same");
		}
		else {
			System.out.println("Not the same Value");
		}
	}

}
