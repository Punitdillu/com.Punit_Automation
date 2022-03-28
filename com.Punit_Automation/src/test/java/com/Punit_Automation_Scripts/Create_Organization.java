package com.Punit_Automation_Scripts;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Create_Organization {

		@Test
		public void create_organization() {
			Random ran=new Random();
			int x=ran.nextInt(1000);
			String orgnam="Apple"+x;
			String browser=System.getProperty("browser");
			String url=System.getProperty("url");
			String username=System.getProperty("username");
			String password=System.getProperty("password");
			WebDriver driver=null;
			
			if(browser.equals("chrome")) {
			
				WebDriverManager.chromedriver().setup();
				 driver=new ChromeDriver();
			}
			else if(browser.equals("firefox")) {
				
				WebDriverManager.firefoxdriver().setup();
				 driver=new FirefoxDriver();
			}
			else{
				
				WebDriverManager.edgedriver().setup();
				 driver=new EdgeDriver();
			}
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.get(url);
				driver.findElement(By.name("user_name")).sendKeys(username);
				driver.findElement(By.name("user_password")).sendKeys(password);
				driver.findElement(By.id("submitButton")).click();
				driver.findElement(By.xpath("//a[text()='Organizations']")).click();
				driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
				 WebElement wb1 = driver.findElement(By.xpath("//input[@name=\"accountname\"]"));
				 wb1.sendKeys(orgnam);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				System.out.println(wb1.getText());
				driver.navigate().refresh();
				driver.findElement(By.xpath("//a[text()='Organizations']")).click();
				for(;;) 
				{
					try 
				{
				driver.findElement(By.xpath("//a[text()='"+orgnam+"' and @title='Organizations']")).click();
				break;
				}
				catch(Exception e)
				{
					driver.findElement(By.xpath("//img[@src='themes/images/next.gif']")).click();
				}
				}
				driver.findElement(By.xpath("//input[@title='Delete [Alt+D]']")).click();
				driver.switchTo().alert().accept();
				driver.quit();
		}
}
