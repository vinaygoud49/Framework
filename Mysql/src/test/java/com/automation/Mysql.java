package com.automation;

	import java.sql.Connection;
	import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.Test;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class Mysql {
		

		@Test
		public void init() throws Exception {

			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			
			driver.get("https://opensource-demo.orangehrmlive.com");
			
			String userName = null;
			String password = null;
			String firstname = null;
			String lastname = null;
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vinay","root","root");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from employeedata where username='Admin'");
					
					 while(resultSet.next()) {
					 userName = resultSet.getString(1);
			         password=resultSet.getString(2);
			         firstname=resultSet.getString(3);
			         lastname=resultSet.getString(4);
					 }
			connection.close();
			
			driver.findElement(By.id("txtUsername")).sendKeys(userName);
			driver.findElement(By.id("txtPassword")).sendKeys(password);
			driver.findElement(By.id("btnLogin")).click();
			driver.findElement(By.id("menu_pim_viewPimModule")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("menu_pim_addEmployee")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("firstName")).sendKeys(firstname);
			driver.findElement(By.id("lastName")).sendKeys(lastname);
			driver.findElement(By.id("btnSave")).click();
			driver.findElement(By.id("welcome")).click();
			driver.findElement(By.linkText("Logout")).click();
			driver.quit();
		}

		
	}


	



