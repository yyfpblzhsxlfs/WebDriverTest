package AdvancedInstance;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Tools.BeginAndCloseChrome;
import Tools.Element;

public class ScrollBar {
	WebDriver driver;
	JavascriptExecutor js;
	Element e;

	@BeforeMethod(groups = ("Scroll"))
	public void BeforeMethod() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("http://v.sogou.com");
		driver = ba.setUp();
		e = new Element();
		System.out.println("JS");
	}

	/*
	 * 11.8章节操作web页面滚动条
	 */
	@Test(groups = ("Scroll"), priority = 1)
	public void ScrollingToBottomofApage() throws Exception {
		// 使用Javascript的scrollTo函数和document.body.scrollHeight参数，将页面的滚动条滑动到页面最下方
		String jsStr = "window.scrollTo(0,document.body.scrollHeight)";

		e.JavascriptSet(driver, jsStr);

		Thread.sleep(3000);

	}

	@Test(groups = ("Scroll"), priority = 2)
	public void ScrollingToElementofApage() throws Exception {
		// 由于被测页面变更，目前没有找到好的界面进行测试，所以该用例执行时会失败

		// 进入搜索视频界面的id为main_frame的frame页面
		driver.switchTo().frame("main_frame");
		// 在frame中定位h2标签为“电视剧分类”的元素
		WebElement element = driver.findElement(By.xpath("//h2[text()='电视剧分类']"));

		// 使用Javascript的scrollIntoView()方法，滚动页面到制定位置
		String jsStr = "arguments[0].scrollIntoView();";
		e.JavascriptSet(driver, jsStr, element);

		Thread.sleep(3000);

	}

	@Test(groups = ("Scroll"), priority = 3)
	public void ScrollingByCoordinatesofApage() throws Exception {
		// 使用Javascript的scrollTo()方法，使用0和800的横纵坐标参数，将页面滚动条纵向下滑800个像素
		String jsStr = "window.scrollBy(0,800);";
		e.JavascriptSet(driver, jsStr);

		Thread.sleep(3000);

	}

	@AfterMethod(groups = ("Scroll"))
	public void afterMethod() {
		driver.quit();
	}

}
