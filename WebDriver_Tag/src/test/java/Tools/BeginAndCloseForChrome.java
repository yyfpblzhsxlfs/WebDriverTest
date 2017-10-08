package Tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BeginAndCloseForChrome {
	WebDriver driver;
	private String URL = "";

	public BeginAndCloseForChrome() {
		super();
	}

	public BeginAndCloseForChrome(String URL) throws Exception {
		if (URL.equals("sogou"))
			this.URL = "http://www.sogou.com/";
		else
			this.URL = URL;
	}

	public WebDriver setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Chrome浏览器初始化完毕");
		if (!URL.equals(""))
			driver.get(URL);
		return driver;
	}

	public void close() {
		driver.close();
		System.out.println("Chrome浏览器关闭完成");
	}
}
