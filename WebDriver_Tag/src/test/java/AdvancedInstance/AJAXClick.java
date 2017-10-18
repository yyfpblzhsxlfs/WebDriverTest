package AdvancedInstance;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Tools.BeginAndCloseChrome;

public class AJAXClick {
	WebDriver driver;
	JavascriptExecutor js;

	/*
	 * 11.2章节在AJAX方式的浮动框中，点击某个关键字选项
	 */
	@Test(groups = ("AJAXClickText"))
	public void AJAXClickText() throws Exception {
		String actualStr = null;
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();

		driver.findElement(By.id("query")).click();

		List<WebElement> searchInputBox = driver.findElements(By.xpath("//*[@id='vl']/div[1]/ul/li"));
		for (WebElement e : searchInputBox) {
			if (e.getText().contains("大学生")) {
				System.out.println(e.getText());
				actualStr = e.getText();
				e.click();
				break;
			}
		}

		assertEquals(actualStr, "大学生一跃两楼高");

		ba.close();
	}
}
