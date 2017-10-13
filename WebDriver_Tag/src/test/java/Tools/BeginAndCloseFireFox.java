package Tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BeginAndCloseFireFox {
	WebDriver driver;
	private String URL = "";

	public BeginAndCloseFireFox() {
		super();
	}

	public BeginAndCloseFireFox(String URL) throws Exception {
		if (URL.equals("sogou"))
			this.URL = "http://www.sogou.com/";
		else
			this.URL = URL;
	}

	public WebDriver setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "src\\driver\\geckodriver-v0.15.0-win64.exe");
		driver = new FirefoxDriver();
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
