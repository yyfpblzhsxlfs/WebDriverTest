package AdvancedInstance;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Tools.BeginAndCloseChrome;

public class setAttributePackage {
	WebDriver driver;
	JavascriptExecutor js;

	/*
	 * 11.3章节,设置一个界面对象的属性值
	 */
	@Test(groups = ("setAttributeValue"))
	public void setAttributeValue() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/setAttributePackage.html");
		driver = ba.setUp();

		// 修改value值
		WebElement input = driver.findElement(By.tagName("input"));
		String setAttributeValue = "改变文本框中显示的文字";
		setAttribute(driver, input, "value", setAttributeValue);
		assertEquals(setAttributeValue, input.getAttribute("value"));

		// 修改size值
		String setAttributeSize = "10";
		setAttribute(driver, input, "size", setAttributeSize);
		assertEquals(setAttributeSize, input.getAttribute("size"));

		// 删除文本框中的size属性,size属性默认20
		removeAttribute(driver, input, "size");
		assertEquals("20", input.getAttribute("size"));
		ba.close();
	}

	public void setAttribute(WebDriver driver, WebElement element, String attributeName, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, attributeName, value);
	}

	public void removeAttribute(WebDriver driver, WebElement element, String attributeName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute(arguments[1],arguments[2])", element, attributeName);
	}
}
