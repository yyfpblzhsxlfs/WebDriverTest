package PageElementLocalize;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Tools.BeginAndCloseForChrome;
/*
 * 8.2、8.3、8.4、8.5、8.8章节实例
 */
public class LocalizeByTag {

	// Define brower driver
	private WebDriver driver;

	// Define URL
	private String baseUrl;

	/*
	 * Page element localize for HTML tag attribute by ID
	 */
	@Test(groups = { "TagByID" })
	public void TagByID() throws Exception {
		// init driver for Chrome Brower Driver
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();

		// Define the URL is local file path
		baseUrl = "file:///E:/ZIXUE/web/ByTag.html";

		// Access local file path for baseUrl
		driver.get(baseUrl);

		// Find element on the file by id to WebElement
		WebElement username = driver.findElement(By.id("id_username"));
		WebElement password = driver.findElement(By.id("id_password"));
		WebElement input = driver.findElement(By.id("id_sumbit"));

		// WebElement input value
		username.sendKeys("hello");
		password.sendKeys("selenium");

		// WebElement cleck by id is sumbit
		input.click();

		System.out.println(driver.getTitle());

		ba.close();

	}

	/*
	 * Page element localize for HTML tag attribute by Name
	 */
	@Test(groups = { "TagByName" })
	public void TagByName() throws Exception {
		// init driver for Chrome Brower Driver
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();

		// Define the URL is local file path
		baseUrl = "file:///E:/ZIXUE/web/ByTag.html";

		// Access local file path for baseUrl
		driver.get(baseUrl);

		// Find element on the file by name to WebElement
		WebElement username = driver.findElement(By.name("name_username"));
		WebElement password = driver.findElement(By.name("name_password"));
		WebElement input = driver.findElement(By.name("name_sumbit"));

		// WebElement input value
		username.sendKeys("hello");
		password.sendKeys("selenium");

		// WebElement cleck by id is sumbit
		input.click();

		System.out.println(driver.getTitle());

		ba.close();

	}

	/*
	 * Page element localize for HTML tag attribute by LinkText . And this
	 * method is accurate
	 */
	@Test(groups = { "TagByLinkText" })
	public void TagByLinkText() throws Exception {
		// init driver for Chrome Brower Driver
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();

		// Define the URL is local file path
		baseUrl = "file:///E:/ZIXUE/web/ByTag.html";

		// Access local file path for baseUrl
		driver.get(baseUrl);

		// Find element on the file by LinkText to WebElement
		WebElement sogou = driver.findElement(By.linkText("sogou搜索"));

		// WebElement cleck by linktext is link
		sogou.click();

		System.out.println(driver.getTitle());

		ba.close();

	}

	/*
	 * Page element localize for HTML tag attribute by LinkText . And this
	 * method is vague
	 */
	@Test(groups = { "TagBypartialLinkText" })
	public void TagBypartialLinkText() throws Exception {
		// init driver for Chrome Brower Driver
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();

		// Define the URL is local file path
		baseUrl = "file:///E:/ZIXUE/web/ByTag.html";

		// Access local file path for baseUrl
		driver.get(baseUrl);

		// Find element on the file by LinkText to WebElement
		WebElement sogou = driver.findElement(By.partialLinkText("360"));

		// WebElement cleck by linktext is link
		sogou.click();

		System.out.println(driver.getTitle());

		ba.close();
	}
	
	/*
	 * Page element localize for HTML tag attribute by XPATH . 
	 */
	@Test(groups = { "TagByXPath" })
	public void TagByXPath() throws Exception {
		// init driver for Chrome Brower Driver
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();

		// Define the URL is local file path
		baseUrl = "file:///E:/ZIXUE/web/ByXPath.html";

		// Access local file path for baseUrl
		driver.get(baseUrl);

		// Find element on the file by XPath to WebElement
		WebElement baidu = driver.findElement(By.xpath("//a[text()='百度搜索']"));

		baidu.click();
		
		System.out.println(driver.getTitle());

		ba.close();
	}
}
