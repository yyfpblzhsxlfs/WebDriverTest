package Tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BeginAndCloseIE {
	WebDriver driver;
	private String URL = "";

	public BeginAndCloseIE() {
		super();
	}

	public BeginAndCloseIE(String URL) throws Exception {
		if (URL.equals("sogou"))
			this.URL = "http://www.sogou.com/";
		else
			this.URL = URL;
	}

	public WebDriver setUp() throws Exception {
		String arch = System.getProperty("os.arch");
		if (arch.equals("x86"))
			System.setProperty("webdriver.ie.driver", "src\\driver\\IEDriverServer_32.exe");
		else if (arch.equals("amd64"))
			System.setProperty("webdriver.ie.driver", "src\\driver\\IEDriverServer_64.exe");
		else
			System.out.println("IE启动失败，系统为：" + arch);
		driver = new InternetExplorerDriver();
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
