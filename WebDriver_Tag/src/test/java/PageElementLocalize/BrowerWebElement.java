package PageElementLocalize;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.openqa.selenium.Point;

import Tools.BeginAndCloseForChrome;

/*
 * 第10章实例
 */
public class BrowerWebElement {
	private WebDriver driver;
	private String baseUrl;

	/*
	 * 浏览器刷新等基本操作
	 */
	@Test(groups = ("BrowerNavigate"))
	public void BrowerNavigate() throws Exception {
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();
		// 访问网页方法一
		baseUrl = "http://www.baidu.com/";
		driver.get(baseUrl);
		// 访问网页方法二
		baseUrl = "http://www.sogou.com/";
		driver.navigate().to(baseUrl);

		// 返回上一个浏览界面
		driver.navigate().back();

		// 进入下一个浏览界面
		driver.navigate().forward();

		// 刷新当前网页
		driver.navigate().refresh();
	}

	/*
	 * 操作浏览器窗口
	 */
	@Test(groups = ("operateBrower"))
	public void operateBrower() throws Exception {
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();
		baseUrl = "http://www.sogou.com/";
		// 声明一个坐标点
		Point point = new Point(150, 150);

		// 声明窗口大小
		Dimension demension = new Dimension(500, 500);

		// setPositon方法表示设定浏览器在屏幕上的位置是point对象的坐标点
		driver.manage().window().setPosition(point);

		// setSize方法表示设定浏览器窗口大小
		driver.manage().window().setSize(demension);

		System.out.println(driver.manage().window().getPosition());
		System.out.println(driver.manage().window().getSize());
		Thread.sleep(5000);
		driver.get(baseUrl);
		// 最大化窗口
		driver.manage().window().maximize();
		Thread.sleep(5000);
		ba.close();
	}

	/*
	 * 获取网页部分信息
	 */
	@Test(groups = ("getWebMsg"))
	public void getWebMsg() throws Exception {
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();
		baseUrl = "http://www.sogou.com/";
		driver.get(baseUrl);
		// 获取页面title
		String title = driver.getTitle();
		System.out.println("网页title:" + title);

		// 获取页面源代码
		String pagecode = driver.getPageSource();
		System.out.println("网页源码:\n" + pagecode);

		// 获取当前网页URL
		String url = driver.getCurrentUrl();
		System.out.println("当前界面URL:\n" + url);

		ba.close();

	}

	/*
	 * 测试网页元素操作输入框,清空输入框,在输入框重新输入内容
	 */
	@Test(groups = ("Input"))
	public void Input() throws Exception {
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();
		baseUrl = "file:///E:/ZIXUE/web/Input.html";
		driver.get(baseUrl);
		WebElement input = driver.findElement(By.id("text"));

		String value = input.getAttribute("value");
		System.out.println("默认展示:" + value);
		// 清楚输入框内容
		input.clear();
		Thread.sleep(3000);

		input.sendKeys("新输入内容");
		Thread.sleep(3000);
		value = input.getAttribute("value");

		System.out.println("重新输入内容:" + value);
		ba.close();
	}

	/*
	 * 单击、双击界面元素
	 */
	@Test(groups = ("XClick"))
	public void XClick() throws Exception {
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();
		baseUrl = "file:///E:/ZIXUE/web/XClick.html";
		driver.get(baseUrl);

		// 点击button按钮,改变输入框文字
		WebElement button = driver.findElement(By.id("button"));
		button.click();
		// 获取改变后的输入框内容
		WebElement value = driver.findElement(By.id("text"));
		String strValue = value.getAttribute("value");

		// 获取双击输入框元素
		WebElement inputBox = driver.findElement(By.id("inputBox"));

		// 声明Action对象,模拟鼠标操作
		Actions builder = new Actions(driver);

		// 双击
		builder.doubleClick(inputBox).build().perform();
	
		assertEquals("background: red;", inputBox.getAttribute("style"));
		assertEquals("改变啦!", strValue);
		ba.close();
	}
	
	/*
	 * 单选下拉框、多选下拉框、单选项、多选项操作
	 */
	@Test(groups = ("Select"))
	public void Select() throws Exception {
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome();
		driver = ba.setUp();
		baseUrl = "file:///E:/ZIXUE/web/XClick.html";
		driver.get(baseUrl);

		// 点击button按钮,改变输入框文字
		WebElement button = driver.findElement(By.id("button"));
		
		button.click();
		// 获取改变后的输入框内容
		WebElement value = driver.findElement(By.id("text"));
		String strValue = value.getAttribute("value");

		// 获取双击输入框元素
		WebElement inputBox = driver.findElement(By.id("inputBox"));

		// 声明Action对象,模拟鼠标操作
		Actions builder = new Actions(driver);

		// 双击
		builder.doubleClick(inputBox).build().perform();
	
		assertEquals("background: red;", inputBox.getAttribute("style"));
		assertEquals("改变啦!", strValue);
		ba.close();
	}
	
	
}
