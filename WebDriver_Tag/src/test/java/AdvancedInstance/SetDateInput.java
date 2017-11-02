package AdvancedInstance;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Tools.BeginAndCloseChrome;
import Tools.Element;

public class SetDateInput {
	WebDriver driver;
	JavascriptExecutor js;

	/*
	 * 11.4章节,操作日期选择器上的日期
	 */
	@Test(groups = ("otherMonths"))
	public void otherMonths() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/otherMonths.html");
		driver = ba.setUp();
		String inputDataStr = "12/31/2015";
		// 找到日期控件，请输入日期
		WebElement dataInputBox = driver.findElement(By.id("datepicker"));
		dataInputBox.sendKeys(inputDataStr);

		// 使用js获取日期控件显示结果，进行断言
		Element elementTool = new Element();
		String getDataStr = (String) elementTool.JavascriptReturn(driver,
				"return document.getElementById('datepicker').value");
		assertEquals(inputDataStr, getDataStr);

		ba.close();
	}
}
