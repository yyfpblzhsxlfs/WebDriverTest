package PageElementLocalize;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import TestWebDriver.BrowerTest;
import Tools.BeginAndCloseChrome;
import Tools.Element;

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
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome();
		driver = ba.setUp();
		// 访问网页方法一
		baseUrl = "http://www.baidu.com/";
		driver.get(baseUrl);
		// 访问网页方法二
		baseUrl = "sogou";
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
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome();
		driver = ba.setUp();
		baseUrl = "sogou";
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
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();

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
	@Test(groups = ("clearAndSendTextToInputBoxText"))
	public void clearAndSendTextToInputBoxText() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/Input.html");
		driver = ba.setUp();

		WebElement input = driver.findElement(By.id("text"));

		String value = input.getAttribute("value");
		System.out.println("默认展示:" + value);
		// 清除输入框内容
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
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/XClick.html");
		driver = ba.setUp();

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
	 * 单选下拉框
	 */
	@Test(groups = ("checkSelectText"))
	public void checkSelectText() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/Select.html");
		driver = ba.setUp();

		/* 单选下拉框操作 */
		// 定位单选下拉框
		Select select = new Select(driver.findElement(By.name("fruit_1")));

		// 验证下拉框是否为多选，是多选返回true，单选返回false
		assertFalse(select.isMultiple());

		// 验证当前选项文本展示是否为桃子
		assertEquals("桃子", select.getFirstSelectedOption().getText());

		// 使用索引方法选中第四项，验证文本是否为"泥猴桃"
		select.selectByIndex(3);
		assertEquals("泥猴桃", select.getFirstSelectedOption().getText());

		// 使用Value属性定位"山楂"
		select.selectByValue("shanzha");
		assertEquals("山楂", select.getFirstSelectedOption().getText());

		// 使用文本方式定位"荔枝"
		select.selectByVisibleText("荔枝");
		assertEquals("荔枝", select.getFirstSelectedOption().getText());

		/* 验证下拉列表预期 */
		// 将数组转换为List
		List<String> expect_options = Arrays.asList(new String[] { "桃子", "西瓜", "橘子", "泥猴桃", "山楂", "荔枝" });
		// 获取网页上下拉列表所有元素文字
		List<String> actual_options = new ArrayList<String>();
		for (WebElement e : select.getOptions()) {
			actual_options.add(e.getText());
		}

		// 验证两个List结果一致
		assertEquals(actual_options, expect_options);

		ba.close();
	}

	/*
	 * 多选下拉框
	 */
	@Test(groups = ("operateMultipleOptionDropList"))
	public void operateMultipleOptionDropList() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/Select.html");
		driver = ba.setUp();

		// 定位多选框
		Select select = new Select(driver.findElement(By.name("fruit_2")));

		// 验证选中的select是否为多选，非拿回True是多选
		assertTrue(select.isMultiple());
		// 选择索引3的泥猴桃、value值为shanzha的山楂和文本为荔枝
		select.selectByIndex(3);
		select.selectByValue("shanzha");
		select.selectByVisibleText("荔枝");
		Thread.sleep(3000);

		// 取消全部选项
		select.deselectAll();
		Thread.sleep(3000);

		// 再次选择索引3的泥猴桃、value值为shanzha的山楂和文本为荔枝
		select.selectByIndex(3);
		select.selectByValue("shanzha");
		select.selectByVisibleText("荔枝");
		Thread.sleep(3000);

		// 使用反向方法取消选中
		select.deselectByIndex(3);
		select.deselectByValue("shanzha");
		select.deselectByVisibleText("荔枝");
		Thread.sleep(3000);

		ba.close();
	}

	/*
	 * 单选项
	 */
	@Test(groups = ("operateRadio"))
	public void operateRadio() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/Select.html");
		driver = ba.setUp();
		// 定位草莓选项
		WebElement radioOption = driver.findElement(By.xpath("//input[@value='berry1']"));

		// 验证草莓选项是否被选中，如果没有被选择，则选中
		if (!radioOption.isSelected())
			radioOption.click();

		// 判断草莓是否被选中
		assertTrue(radioOption.isSelected());

		// 定位所有name值是fruit的标签，并存储到List中
		List<WebElement> list = driver.findElements(By.name("fruit1"));

		// 便利所有选项的value，如果有watermelon，则选中
		for (WebElement e : list) {
			if (e.getAttribute("value").equals("watermelon1")) {
				if (!e.isSelected())
					e.click();
				assertTrue(e.isSelected());
				break;
			}
		}
		ba.close();

	}

	/*
	 * 多选项
	 */
	@Test(groups = ("operateCheckBox"))
	public void operateCheckBox() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/Select.html");
		driver = ba.setUp();

		// 定位value值为orange的元素
		WebElement orange = driver.findElement(By.xpath("//input[@value='orange2']"));

		// 判断该元素是否选中，如果没有选中，则选中
		if (!orange.isSelected())
			orange.click();

		// 验证该orange选中
		assertTrue(orange.isSelected());
		Thread.sleep(3000);
		// 该选项选中后，取消选中
		if (orange.isSelected())
			orange.click();
		// 验证该orange取消选中
		assertFalse(orange.isSelected());

		// 查找所有name值为fruit，并选中
		List<WebElement> list = driver.findElements(By.xpath("//input[@name='fruit2']"));

		// 让所有name值为fruit的选项被选中
		for (WebElement e : list) {
			if (!e.isSelected())
				e.click();

			assertTrue(e.isSelected());
		}

		ba.close();
	}

	/*
	 * kill浏览器进程
	 */
	@Test(groups = ("operateWindowsProcess"))
	public void operateWindowsProcess() throws Exception {
		BrowerTest firefoxBrower = new BrowerTest();
		BrowerTest ieBrower = new BrowerTest();
		BrowerTest chromeBrower = new BrowerTest();
		// 三个参数firefox、ie、chrome
		firefoxBrower.browerTest("firefox");
		ieBrower.browerTest("ie");
		chromeBrower.browerTest("chrome");

		firefoxBrower.kill();
		ieBrower.kill();
		chromeBrower.kill();
	}

	/*
	 * 浏览器截图
	 */
	@Test(groups = ("captureScreenInCurrentWindow"))
	public void captureScreenInCurrentWindow() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();

		// 截屏，并将图片转换为File格式
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("E:/ZIXUE/web/WindowBrower/" + "test" + ".png"));

		ba.close();
	}

	/*
	 * 检查界面文本内容
	 */
	@Test(groups = ("isElementTextPresent"))
	public void isElementTextPresent() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/checkText.html");
		driver = ba.setUp();

		WebElement text = driver.findElement(By.xpath("//p[1]"));

		String contentText = text.getText();
		// 全匹配
		assertEquals(contentText, "Selenium自动化测试之路起步");
		// 部分匹配
		assertTrue(contentText.contains("自动化"));
		// 前匹配
		assertTrue(contentText.startsWith("Selenium"));
		// 后匹配
		assertTrue(contentText.endsWith("起步"));

		ba.close();
	}

	/*
	 * js执行
	 */
	@Test(groups = ("executeJavaScript"))
	public void executeJavaScript() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("https://www.baidu.com/");
		driver = ba.setUp();

		// 声明一个JS执行器对象
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// 调用执行器方法执行JS
		String title = (String) js.executeScript("return document.title");
		// 验证获取标题是否正确
		assertEquals(title, "百度一下，你就知道");

		// 返回按钮上的文字
		String buttonText = (String) js.executeScript("var button = document.getElementById('su');return button.value");
		System.out.println(buttonText);
		assertEquals("百度一下", buttonText);
		ba.close();
	}

	/*
	 * 拖拽页面元素
	 */
	@Test(groups = ("dragPapeElement"))
	public void dragPapeElement() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/dragPepeElement.html");
		driver = ba.setUp();

		WebElement draggable = driver.findElement(By.id("draggable"));

		for (int i = 0; i < 5; i++) {
			new Actions(driver).dragAndDropBy(draggable, 0, 10).build().perform();
		}

		for (int i = 0; i < 5; i++) {
			new Actions(driver).dragAndDropBy(draggable, 10, 0).build().perform();
		}

		ba.close();
	}

	/*
	 * 模拟键盘操作
	 */
	@Test(groups = ("clickKyes"))
	public void clickKyes() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();

		Actions action = new Actions(driver);
		// 按下Ctrl键
		action.keyDown(Keys.CONTROL);
		// 按下Shift键
		action.keyDown(Keys.SHIFT);
		// 按下Alt键
		action.keyDown(Keys.ALT);

		// 释放Ctrl键
		action.keyUp(Keys.CONTROL);
		// 释放Shift键
		action.keyUp(Keys.SHIFT);
		// 释放Alt键
		action.keyUp(Keys.ALT);

		// 模拟输入大写ABCDEFG
		action.keyDown(Keys.SHIFT).sendKeys("abcdefg").perform();

		String text = driver.findElement(By.id("query")).getAttribute("value");

		assertEquals("ABCDEFG", text);
		ba.close();
	}

	/*
	 * 模拟键盘操作
	 */
	@Test(groups = ("rightClickMouse"))
	public void rightClickMouse() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();

		Actions action = new Actions(driver);

		action.contextClick(driver.findElement(By.id("query"))).perform();
		Thread.sleep(3000);

		ba.close();
	}

	/*
	 * 模拟鼠标悬浮
	 */
	@Test(groups = ("roverOnElement"))
	public void roverOnElement() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/roverOnElement.html");
		driver = ba.setUp();

		WebElement link1 = driver.findElement(By.id("link1"));
		WebElement link2 = driver.findElement(By.id("link2"));

		Actions action = new Actions(driver);

		action.moveToElement(link1).perform();
		Thread.sleep(3000);

		action.moveToElement(link2).perform();
		Thread.sleep(3000);
		ba.close();
	}

	/*
	 * 模拟鼠标在制定元素上单击左键和释放操作
	 */
	@Test(groups = ("mouseClickAndRelease"))
	public void mouseClickAndRelease() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/mouseClickAndRelease.html");
		driver = ba.setUp();

		WebElement div = driver.findElement(By.xpath("//div[@id='div1']"));

		Actions action = new Actions(driver);

		action.clickAndHold(div).perform();
		Thread.sleep(3000);
		action.release(div).perform();
		Thread.sleep(3000);

		assertEquals("鼠标左键被按下;已经被按下的鼠标鼠标左键被释放抬起;", div.getText());

		WebElement input = driver.findElement(By.xpath("//input[@value='清除信息']"));
		input.click();
		assertEquals("", div.getText());

		action.click(div).perform();
		assertEquals("鼠标左键被按下;已经被按下的鼠标鼠标左键被释放抬起;单击动作发生;", div.getText());
		Thread.sleep(3000);

		ba.close();
	}

	/*
	 * 查看页面元素属性
	 */
	@Test(groups = ("getWebElementAttribute"))
	public void getWebElementAttribute() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();

		WebElement input = driver.findElement(By.id("query"));

		String inputString = "要输入的内容";

		input.sendKeys(inputString);

		String assertInput = input.getAttribute("value");

		assertEquals(assertInput, inputString);

		ba.close();
	}

	/*
	 * 查看页面元素CSS属性
	 */
	@Test(groups = ("getWebElementCssAttribute"))
	public void getWebElementCssAttribute() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();

		WebElement input = driver.findElement(By.id("query"));

		String inputWidth = input.getCssValue("width");

		assertEquals(inputWidth, "499px");

		ba.close();
	}

	/*
	 * 隐式等待
	 */
	@Test(groups = ("testImpilicWait"))
	public void testImpilicWait() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();

		// 隐式等待设置,默认为0,设置后,作用在整个driver生命周期
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			WebElement inputText = driver.findElement(By.id("query"));
			WebElement searchButton = driver.findElement(By.id("stb"));

			inputText.sendKeys("inputText is found");
			searchButton.click();
		} catch (NoSuchElementException e) {
			fail("inputText is not found");
		}
		ba.close();
	}

	/*
	 * 显式等待,ExpectedConditions类中子类的方法,可以进行显式等待的判断
	 */
	@Test(groups = ("testExplicitWait"))
	public void testExplicitWait() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/testExplicitWait.html");
		driver = ba.setUp();

		// 声明一个webdriverwait对象,设定出发条件的最长等待时间10秒
		WebDriverWait wait = new WebDriverWait(driver, 10);
		// 判断标题中是否包含水果
		boolean isTrue = wait.until(ExpectedConditions.titleContains("水果"));
		assertTrue(isTrue);
		isTrue = false;
		// 获取下拉列表桃子选项,验证该选项是否处于选中状态
		WebElement select = driver.findElement(By.id("peach"));
		wait.until(ExpectedConditions.elementToBeClickable(select));
		isTrue = select.isSelected();
		assertTrue(isTrue);

		// 获取复选框对象是否可见,并且是否可被单击
		WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
		wait.until(ExpectedConditions.elementToBeClickable(checkbox));
		assertTrue(checkbox.isEnabled());

		// 判断p标签是否已显示
		WebElement p1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p")));
		assertEquals("请选择你爱吃的水果", p1.getText());

		// 判断p标签中是否包含"爱吃的水果"字样
		isTrue = false;
		isTrue = wait.until(ExpectedConditions.textToBePresentInElement(p1, "爱吃的水果"));
		assertTrue(isTrue);

		ba.close();
	}

	/*
	 * 自定义显式等待
	 */
	@Test(groups = ("testExplicitWaitZIDINGYI"))
	public void testExplicitWaitZIDINGYI() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/testExplicitWait.html");
		driver = ba.setUp();
		// 显示等待验证是否可以获取到txt文本框,如果可以获取到,继续下面的操作
		WebElement textInputBox = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath("//*[@type='text']"));
			}
		});
		assertEquals("今年夏天西瓜特别甜", textInputBox.getAttribute("value"));

		// 获取p标签,判断p标签中是否包含"爱吃"关键字,如果有,继续下面的操作
		boolean containTextFlag = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath("//p")).getText().contains("爱吃");
			}
		});
		assertTrue(containTextFlag);

		// 验证文本输入框是否课件,如果课件,继续下面的操作
		boolean inputTextVisibleFlag = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath("//*[@type='text']")).isDisplayed();
			}

		});
		assertTrue(inputTextVisibleFlag);
		ba.close();
		// 验证界面ajax加载,无对应测试页面
		// boolean ajaxRequestFinishFlag = (new WebDriverWait(driver,
		// 10)).until(new ExpectedCondition<Boolean>() {
		// public Boolean apply(WebDriver d) {
		// JavascriptExecutor js = (JavascriptExecutor) d;
		// return (Boolean) js.executeScript("return jQueery.active==0");
		// }
		// });
	}

	/*
	 * 判断界面元素是否存在
	 */
	@Test(groups = ("TestIsElementPresent"))
	public void TestIsElementPresent() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();
		Element e = new Element();
		if (e.IsElementPresent(driver, By.id("query"))) {
			WebElement searchInputBox = driver.findElement(By.id("query"));

			if (searchInputBox.isEnabled() == true) {
				String inputStr = "sogou首页输入框被成功找到";
				searchInputBox.sendKeys(inputStr);
				assertEquals(inputStr, driver.findElement(By.id("query")).getAttribute("value"));
			}
		} else {
			fail("search Input Box is not found");
		}

		ba.close();
	}

	/*
	 * 使用title属性识别和操作新弹出的浏览器窗口
	 */
	@Test(groups = ("identifyPopUpWindowByTitle"))
	public void identifyPopUpWindowByTitle() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/identifyPopUpWindowByTitle.html");
		driver = ba.setUp();
		// 获得当前网页的句柄
		String parentWindowHandle = driver.getWindowHandle();
		// 进入另一个网页
		driver.findElement(By.xpath("//a")).click();
		// 获取所有网页句柄
		Set<String> allWindowHandles = driver.getWindowHandles();
		if (!allWindowHandles.isEmpty()) {
			for (String windowHandle : allWindowHandles) {
				try {
					if (driver.switchTo().window(windowHandle).getTitle().equals("搜狗搜索引擎 - 上网从搜狗开始")) {
						String seachSendKeys = "sogou首页浏览器窗口被找到";
						driver.findElement(By.id("query")).sendKeys(seachSendKeys);
						assertEquals(driver.findElement(By.id("query")).getAttribute("value"), seachSendKeys);
					}
				} catch (NoSuchWindowException e) {
					e.printStackTrace();
				}
			}
		}

		driver.switchTo().window(parentWindowHandle);
		assertEquals(driver.getTitle(), "你喜欢水果");
		for (String windowHandle : allWindowHandles)
			driver.switchTo().window(windowHandle).close();

	}

	/*
	 * 使用页面文字识别和操作新弹出的浏览器窗口
	 */
	@Test(groups = ("identifyPopUpWindowByPageSource"))
	public void identifyPopUpWindowByPageSource() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/identifyPopUpWindowByTitle.html");
		driver = ba.setUp();
		// 获得当前网页的句柄
		String parentWindowHandle = driver.getWindowHandle();
		// 进入另一个网页
		driver.findElement(By.xpath("//a")).click();
		// 获取所有网页句柄
		Set<String> allWindowHandles = driver.getWindowHandles();

		if (!allWindowHandles.isEmpty()) {
			for (String windowHandle : allWindowHandles) {
				try {
					if (driver.switchTo().window(windowHandle).getPageSource().contains("搜狗搜索")) {
						String seachSendKeys = "sogou首页浏览器窗口被找到";
						driver.findElement(By.id("query")).sendKeys(seachSendKeys);
						assertEquals(driver.findElement(By.id("query")).getAttribute("value"), seachSendKeys);
					}
				} catch (NoSuchWindowException e) {
					e.printStackTrace();
				}
			}
		}

		driver.switchTo().window(parentWindowHandle);
		assertEquals(driver.getTitle(), "你喜欢水果");

		for (String windowHandle : allWindowHandles)
			driver.switchTo().window(windowHandle).close();

	}

	/*
	 * 操作网页的alert
	 */
	@Test(groups = ("testHandleAlert"))
	public void testHandleAlert() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/testHandleAlert.html");
		driver = ba.setUp();

		WebElement button = driver.findElement(By.xpath("//input"));
		button.click();
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();
		assertEquals("这是一个alert弹窗", alert.getText());

		alert.accept();

		Thread.sleep(5000);

		ba.close();

	}

	/*
	 * 操作网页的prompt
	 */
	@Test(groups = ("testHandlePrompt"))
	public void testHandlePrompt() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/testHandlePrompt.html");
		driver = ba.setUp();

		WebElement button = driver.findElement(By.xpath("//input"));
		button.click();
		Thread.sleep(3000);

		// 验证prompt内容
		Alert alert = driver.switchTo().alert();
		assertEquals("这是一个prompt弹出窗", alert.getText());
		Thread.sleep(3000);

		// 输入内容
		alert.sendKeys("输入内容");
		Thread.sleep(3000);

		// 点击确定按钮
		alert.accept();
		Thread.sleep(3000);

		// 点击取消按钮
		button.click();
		Thread.sleep(3000);
		alert.dismiss();
		Thread.sleep(3000);

		ba.close();

	}

	/*
	 * 操作Frame中的页面元素
	 */
	@Test(groups = ("testHandleFrame"))
	public void testHandleFrame() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/testHandleFrame.html");
		driver = ba.setUp();

		driver.switchTo().frame("leftframe");
		WebElement leftFrame = driver.findElement(By.xpath("//p"));
		String leftStr = leftFrame.getText();
		assertEquals(leftStr, "这是左侧frame页面上的文字");

		driver.switchTo().defaultContent();
		driver.switchTo().frame("middleframe");
		WebElement middleFrame = driver.findElement(By.xpath("//p"));
		String middleStr = middleFrame.getText();
		assertEquals(middleStr, "这是中间frame页面上的文字");

		driver.switchTo().defaultContent();
		driver.switchTo().frame("rightframe");
		WebElement rightFrame = driver.findElement(By.xpath("//p"));
		String rightStr = rightFrame.getText();
		assertEquals(rightStr, "这是右侧frame页面上的文字");

		// 索引从0开始
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		WebElement indexMiddleFrame = driver.findElement(By.xpath("//p"));
		String indexMiddleStr = indexMiddleFrame.getText();
		assertEquals(indexMiddleStr, "这是中间frame页面上的文字");

		ba.close();

	}

	/*
	 * 操作Frame中的HTML源码操作网页
	 */
	@Test(groups = ("testHandleFrameByPageSource"))
	public void testHandleFrameByPageSource() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/testHandleFrame.html");
		driver = ba.setUp();

		List<WebElement> frames = driver.findElements(By.tagName("frame"));

		boolean isTag = false;

		for (WebElement frame : frames) {
			driver.switchTo().frame(frame);
			if (driver.getPageSource().contains("中间frame")) {
				assertEquals("这是中间frame页面上的文字", driver.findElement(By.tagName("p")).getText());
				isTag = true;
				break;
			} else
				driver.switchTo().defaultContent();
		}

		if (!isTag)
			fail("middleFrame is not find");

		driver.switchTo().defaultContent();
		ba.close();

	}

	/*
	 * 操作IFrame中的HTML源码操作网页
	 */
	@Test(groups = ("testHandleIFrame"))
	public void testHandleIFrame() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/testHandleFrame.html");
		driver = ba.setUp();

		driver.switchTo().frame(0);
		WebElement iframe = driver.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe);
		assertEquals("这是iframe页面上的文字", driver.findElement(By.tagName("p")).getText());

		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		WebElement indexMiddleFrame = driver.findElement(By.xpath("//p"));
		String indexMiddleStr = indexMiddleFrame.getText();
		assertEquals(indexMiddleStr, "这是中间frame页面上的文字");

		ba.close();

	}

	/*
	 * 操作浏览器的cookie
	 */
	@Test(groups = ("testCookie"))
	public void testCookie() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();
		Set<Cookie> cookies = driver.manage().getCookies();
		System.out.println("------------------------");
		System.out.println(String.format("Domain -> name -> value -> expiry -> path"));

		for (Cookie cookie : cookies) {
			System.out.println(String.format("%s -> %s -> %s -> %s -> %s", cookie.getDomain(), cookie.getName(),
					cookie.getValue(), cookie.getExpiry(), cookie.getPath()));
		}

		driver.manage().deleteCookieNamed(".www.sogou.com");
		cookies = driver.manage().getCookies();
		System.out.println("------------------------");
		System.out.println(String.format("Domain -> name -> value -> expiry -> path"));

		for (Cookie cookie : cookies) {
			System.out.println(String.format("%s -> %s -> %s -> %s -> %s", cookie.getDomain(), cookie.getName(),
					cookie.getValue(), cookie.getExpiry(), cookie.getPath()));
		}

		driver.manage().deleteAllCookies();
		cookies = driver.manage().getCookies();
		System.out.println("------------------------");
		System.out.println(String.format("Domain -> name -> value -> expiry -> path"));

		for (Cookie cookie : cookies) {
			System.out.println(String.format("%s -> %s -> %s -> %s -> %s", cookie.getDomain(), cookie.getName(),
					cookie.getValue(), cookie.getExpiry(), cookie.getPath()));
		}

		ba.close();

	}

	/*
	 * 操作浏览器iframe
	 */
	@Test(groups = ("testIframe"))
	public void testIframe() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/testHandleFrameToIframe.html");
		driver = ba.setUp();

		driver.switchTo().frame("leftframe");
		WebElement leftFrame = driver.findElement(By.xpath("//p"));
		String leftStr = leftFrame.getText();
		assertEquals(leftStr, "这是左侧frame页面上的文字");

		driver.switchTo().defaultContent();
		driver.switchTo().frame("middleframe");
		WebElement middleFrame = driver.findElement(By.xpath("//p"));
		String middleStr = middleFrame.getText();
		assertEquals(middleStr, "这是中间frame页面上的文字");

		driver.switchTo().defaultContent();
		driver.switchTo().frame("rightframe");
		WebElement rightFrame = driver.findElement(By.xpath("//p"));
		String rightStr = rightFrame.getText();
		assertEquals(rightStr, "这是右侧frame页面上的文字");

		// 索引从0开始
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		WebElement indexMiddleFrame = driver.findElement(By.xpath("//p"));
		String indexMiddleStr = indexMiddleFrame.getText();
		assertEquals(indexMiddleStr, "这是中间frame页面上的文字");

		ba.close();

	}
}
