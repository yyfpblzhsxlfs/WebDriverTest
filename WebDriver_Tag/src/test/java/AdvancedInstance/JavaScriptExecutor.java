package AdvancedInstance;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Tools.BeginAndCloseChrome;

public class JavaScriptExecutor {
	WebDriver driver;
	JavascriptExecutor js;

	/*
	 * 11.1章节,使用JavaScriptExecutor单击元素
	 */
	@Test(groups = ("testHandleiFrame"))
	public void testHandleiFrame() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();

		WebElement seachInputBox = driver.findElement(By.id("query"));
		WebElement seachButton = driver.findElement(By.id("stb"));

		seachInputBox.sendKeys("使用JavaScript语句进行页面元素单击");
		JavaScriptClick(seachButton);
		ba.close();
	}

	public void JavaScriptClick(WebElement element) {
		if (element.isEnabled() && element.isDisplayed())
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		else
			System.out.println("页面上的元素无法操作单击");
	}
}
