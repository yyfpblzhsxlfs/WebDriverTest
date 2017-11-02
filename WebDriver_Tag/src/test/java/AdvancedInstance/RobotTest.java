package AdvancedInstance;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Tools.BeginAndCloseChrome;

public class RobotTest {
	WebDriver driver;
	JavascriptExecutor js;

	/*
	 * 11.10章节,使用Robot对象操作键盘
	 */
	@Test(groups = ("Robot"))
	public void Robot() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("sogou");
		driver = ba.setUp();
		// 显示判断搜索框是否出现
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("query")));

		// 调用封装好的函数setAndctrlVClipboardData，将文字关键字使用CTRL+V组合键粘贴到搜索框中
		setAndctrlVClipboardData("自动化测试");

		// 调用封装好的pressTabKey方法和pressEnterKey方法，模拟tab按键，使焦点跳到查询按钮上，并回车确认
		pressTabKey();
		Thread.sleep(3000);
		pressEnterKey();
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		assertEquals("自动化测试 - 搜狗搜索", driver.getTitle());
		ba.close();
	}

	/*
	 * 封装的粘贴函数，可以将函数的string参数值放入到剪切板中，然后使用robot对象的keyPress和keyRelease函数来模拟CTRL+
	 * V组合键完成粘贴
	 */
	public void setAndctrlVClipboardData(String str) throws AWTException {
		// 声明StringSelection对象，并使用函数的string参数完成实例化
		StringSelection stringSelection = new StringSelection(str);

		// 使用Toolkit对象的setContents方法将字符串放到剪切板中
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		// 声明Robot对象
		Robot robot = null;

		robot = new Robot();
		// 按下CTRL键和V键
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		// 释放V键和CTRL键
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

	}

	public void pressTabKey() throws AWTException {
		Robot robot = null;
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	public void pressEnterKey() throws AWTException {
		Robot robot = null;
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}
