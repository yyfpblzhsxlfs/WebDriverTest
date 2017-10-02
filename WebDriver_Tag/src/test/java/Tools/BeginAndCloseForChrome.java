package Tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BeginAndCloseForChrome {
	WebDriver driver;

	public WebDriver setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Chrome浏览器初始化完毕");

		return driver;
	}

	public void close() {
		driver.close();
		System.out.println("Chrome浏览器关闭完成");
	}
}
