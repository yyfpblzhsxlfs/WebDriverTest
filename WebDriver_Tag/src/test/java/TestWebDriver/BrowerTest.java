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
			System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (Brower.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "src\\driver\\IEDriverServer.exe");
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
}
