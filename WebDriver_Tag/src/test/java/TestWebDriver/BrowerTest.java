/*
 * 验证不同浏览器，并使用BrowerTest.xml文件驱动TestNG执行
 */
package TestWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

/*
 * 第9章节实例
 */
public class BrowerTest {
	public WebDriver driver;
	String baseUrl = "http://www.sogou.com";

	@Parameters("brower")
	@BeforeClass
	public void browerTest(String Brower) {
		if (Brower.equals("firefox")) {
			String arch = System.getProperty("os.arch");
			if (arch.equals("x86"))
				System.setProperty("webdriver.gecko.driver", "src\\driver\\geckodriver-v0.15.0-win32.exe");
			else if (arch.equals("amd64"))
				System.setProperty("webdriver.gecko.driver", "src\\driver\\geckodriver-v0.15.0-win64.exe");
			else
				System.out.println("火狐启动失败，系统为：" + arch);
			driver = new FirefoxDriver();
		} else if (Brower.equals("ie")) {
			String arch = System.getProperty("os.arch");
			if (arch.equals("x86"))
				System.setProperty("webdriver.ie.driver", "src\\driver\\IEDriverServer_32.exe");
			else if (arch.equals("amd64"))
				System.setProperty("webdriver.ie.driver", "src\\driver\\IEDriverServer_64.exe");
			else
				System.out.println("IE启动失败，系统为：" + arch);
			driver = new InternetExplorerDriver();
		} else if (Brower.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.out.println("未识别的浏览器");
		}
		driver.get(baseUrl);

	}

	@Parameters("brower")
	@Test
	public void test(String Brower) {
		System.out.println(Brower);
	}

//	@AfterClass
	public void down() {
		driver.close();
	}
}
