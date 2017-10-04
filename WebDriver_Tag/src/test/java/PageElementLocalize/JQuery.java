package PageElementLocalize;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Tools.BeginAndCloseForChrome;

/*
 * 8.10章节代码实例
 */
public class JQuery {
	// Define brower driver
	private WebDriver driver;

	// Define URL
	private String baseUrl;

	// Define JQuery mothed js
	JavascriptExecutor js;

	/*
	 * use JQuery method localize element
	 */
	@Test(groups = { "JQueryLocalize" })
	public void JQueryLocalize() throws Exception {
		// init Chrome Brower Driver
		BeginAndCloseForChrome bc = new BeginAndCloseForChrome("http://sogou.com/");
		driver = bc.setUp();

		js = (JavascriptExecutor) driver;

		injectjQueryIfNeeded();

		List<WebElement> eles = (List<WebElement>) js.executeScript("return jQuery.find('a')");

		for (int i = 0; i < eles.size(); i++) {
			System.out.println(i + ":" + eles.get(i).getText());
		}
		assertEquals(124, eles.size());
		bc.close();
	}

	private void injectjQueryIfNeeded() {
		if (!jQueryLoaded())
			injectjQuery();

	}

	/*
	 * judgment the jQuery injection yes or no
	 */
	public boolean jQueryLoaded() {
		boolean loaded;
		try {
			loaded = (boolean) js.executeScript("return" + "jQuery()!=null");
		} catch (WebDriverException e) {
			loaded = false;
		}

		return loaded;

	}

	/*
	 * injection jQuery
	 */
	public void injectjQuery() {
		js.executeScript("var headID=" + "document.getElementsByTagName(\"head\")[0];"
				+ "var newScript = document.createElement('script');" + "newScript.type = 'text/javascript';"
				+ "newScript.src =" + "'http://ajax.googleapis.com/ajax/" + "libs/jquery/1.7.2/jquery.min.js';"
				+ "headID.appendChild(newScript);");
	}
}
