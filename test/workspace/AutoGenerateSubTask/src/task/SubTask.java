package task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SubTask {

	public static void main(String[] args) {
		
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(p);
		String username=(String) p.get("name");
		String password=(String)p.get("password");
		
		System.out.println(p.get("name"));
		System.out.println(p.get("password"));
		
		WebDriver driver = initWebDriver();
		new SubTask().StartCreate(driver);
		
		
	}

	private static WebDriver initWebDriver() {
		System.setProperty("webdriver.chrome.driver", new File(
				"chromedriver.exe").getAbsolutePath());// 指定驱动路径
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// System.setProperty("webdriver.firefox.bin",
		// "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		// WebDriver driver = new FirefoxDriver();
		return driver;
	}

	public void StartCreate(WebDriver driver) {
		System.out.println("start chrome browser...");

		driver.get("http://www.baidu.com/");

		try {
			List<List<Object>>  storyIds= ReadExcel.readExcel(new File("storyIds.xlsx"));
			for (int i = 0; i<storyIds.size(); i++) {
				List<Object> storyId = storyIds.get(i);
				System.out.println("storyIds第"+i+"行数据");
				for (Object object : storyId) {
					System.out.println("storyId::"+object);
				}
				
			}
			System.out.println("storyIds"+storyIds);
			List<List<Object>>  subTasks= ReadExcel.readExcel(new File("subtask.xlsx"));
			System.out.println("subTasks"+subTasks);
			
			for (int i = 0; i<subTasks.size(); i++) {
				List<Object> subTask = subTasks.get(i);
				System.out.println("subTasks第"+i+"行数据");
				for (Object object : subTask) {
					System.out.println("subTask::"+object);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement txtbox = driver.findElement(By.name("wd"));
		txtbox.sendKeys("Glen");

		WebElement btn = driver.findElement(By.id("su"));
		btn.click();

		System.out.println("start chrome browser succeed...");
	}
	
	
}