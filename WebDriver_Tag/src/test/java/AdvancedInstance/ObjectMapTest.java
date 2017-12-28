package AdvancedInstance;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Tools.BeginAndCloseChrome;
import Tools.ObjectMap;

public class ObjectMapTest {
	WebDriver driver;

	/*
	 * 11.11章节对象库，使用Properties对象读取配置文件
	 */
	@Test(groups = { "testObjectMap" })
	public void testObjectMap() throws Exception {
		BeginAndCloseChrome bc = new BeginAndCloseChrome("http://sogou.com/");
		driver = bc.setUp();

		ObjectMap objectMap = new ObjectMap("src/test/java/ObjectMap.properties");

		Properties p = objectMap.properties;
		String s = p.getProperty("Sogou.HomePage.Search");
		System.out.println(s);

		WebElement search = driver.findElement(objectMap.getLocator("Sogou.HomePage.Search"));
		WebElement submit = driver.findElement(objectMap.getLocator("Sogou.HomePage.Submit"));

		search.sendKeys("自动化测试");
		submit.click();

	}
}
