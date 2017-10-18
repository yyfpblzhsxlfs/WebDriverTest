package AdvancedInstance;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.URL;

import Tools.BeginAndCloseChrome;
import Tools.BeginAndCloseFireFox;

public class DownLoadFile {
	public static String downloadFilePath = "E:\\ZIXUE\\DOWNLOAD";
	WebDriver driver;
	JavaScriptExecutor js;
	String URL = "http://ftp.mozilla.org/pub/firefox/releases/55.0b8/win32/zh-CN/";

	/*
	 * 11.5章节，下载文件
	 */
	@Test(groups = ("DownLoadFileTest"))
	public void DownLoadFileTest() throws Exception {
		System.setProperty("webdriver.gecko.driver", "src\\driver\\geckodriver-v0.15.0-win64.exe");
		driver = new FirefoxDriver(FirefoxDriverProfile());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(URL);

		driver.findElement(By.linkText("Firefox Setup 55.0b8.exe")).click();

		Thread.sleep(10000);
	}

	public static FirefoxProfile FirefoxDriverProfile() throws Exception {
		FirefoxProfile profile = new FirefoxProfile();
		// 设置FireFox的browser.download.folderList属性为2
		// 如果没有进行显示设定，默认为1，表示下载文件保存在“下载”文件夹中
		// 设定0，下载文件到用户桌面，设定2，保存到制定路径中
		profile.setPreference("browser.download.folderList", 2);

		// browser.download.manager.showWhenStarting默认属性是true
		// 设置true，下载时显示Firefox浏览器的文件下载窗口
		// 设置false，下载时不显示Firefox浏览器的文件下载窗口
		profile.setPreference("browser.download.manager.showWhenStarting", false);

		// browser.download.dir设置文件下载目录
		profile.setPreference("browser.download.dir", downloadFilePath);

		// browser.helperApps.neverAsk.openFile表示直接打开下载文件，不显示确认框
		// 默认值为空，下行代码行设定了多种文件的MIME类型
		// 如application/exe表示.exe类型文件，application/exe表示excel文件
		profile.setPreference("browser.helperApps.neverAsk.openFile",
				"application/octet-stream,application/exe,text/csv,application/pdf,application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel");

		// browser.helperApps.neverAsk.saveToDisk表示下载文件是否直接保存到磁盘
		// 默认值为空，下行代码行设定了多种文件的MIME类型
		// 如application/exe表示.exe类型文件，application/exe表示excel文件
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/octet-stream,application/exe,text/csv,application/pdf,application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/excel,text/x-c");

		// browser.helperApps.alwaysAsk.force对于位置的MIME类型文件弹出窗口
		// 默认true，设置false表示不会记录打开未知MIME类型文件的方式
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);

		// 下载.exe文件弹出警告，默认true，设置false不弹出警告
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);

		// 设定下载框在下载时会获取焦点，默认true，false表示不获取焦点
		profile.setPreference("browser.download.manager.focusWhenStarting", false);

		// 设置下载是否显示下载框，默认true，false表示隐藏
		profile.setPreference("browser.download.manager.useWindow", false);

		// 完成提示框，默认true，设定false表示下载完成后不显示下载完成提示框
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);

		// 下载完成后是否关闭下载框，默认true，设置false表示不关闭下载管理器
		profile.setPreference("browser.download.manager.closeWhenDone", false);

		return profile;

	}
}
