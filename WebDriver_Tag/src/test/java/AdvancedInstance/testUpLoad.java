package AdvancedInstance;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Tools.BeginAndCloseChrome;

public class testUpLoad {
	WebDriver driver;
	JavascriptExecutor js;

	/*
	 * 11.6章节,sendKeys方法上传文件
	 */
	@Test(groups = ("upload"))
	public void upload() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/upload.html");
		driver = ba.setUp();

		WebElement fileInputBox = driver.findElement(By.id("file"));
		fileInputBox.sendKeys("E:/ZIXUE/text/a.txt");
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("filesubmit")));

		WebElement fileSubmitButton = driver.findElement(By.id("filesubmit"));
		fileSubmitButton.click();

		wait.until(ExpectedConditions.titleContains("file upload success!"));
		ba.close();
	}

	/*
	 * 11.7章节,使用第三方工具方法上传文件
	 */
	@Test(groups = ("uploadA3x"))
	public void uploadA3x() throws Exception {
		BeginAndCloseChrome ba = new Tools.BeginAndCloseChrome("file:///E:/ZIXUE/web/upload.html");
		driver = ba.setUp();

		WebElement fileInputBox = driver.findElement(By.id("file"));
		fileInputBox.click();

		/*
		 * test.exe中包含的代码如下： #include <Constants.au3>
		 * 
		 * Send("E:\ZIXUE\text\a.txt") Send("{ENTER}") Send("{ENTER}")
		 */
		Runtime.getRuntime().exec("./src/driver/test.exe");
		Thread.sleep(10000);

		// 显示等待方法，声明一个显示等待对象
		WebDriverWait wait = new WebDriverWait(driver, 10);
		// 显示等待判断提交按钮是否处于可点击状态
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("file")));

		WebElement fileSubmitButton = driver.findElement(By.id("filesubmit"));
		fileSubmitButton.click();

		// 显示等待的判断代码，判断上传文件成功后，页面是否跳转到了文件上传成功界面。通过titleContains函数判断跳转后的页面title是否复合期望
		wait.until(ExpectedConditions.titleContains("file upload success!"));
		ba.close();
	}
}
