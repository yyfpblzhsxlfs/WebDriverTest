package PageElementLocalize;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Tools.BeginAndCloseForChrome;

/*
 * 8.11章节实例
 */
public class TableElement {
	// Define brower driver
	private WebDriver driver;

	// Define URL
	private String baseUrl;

	@Test(groups = "LocateTable")
	public void LocateTable() throws Exception {
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome("file:///E:/ZIXUE/web/table.html");
		driver = ba.setUp();

		WebElement table = driver.findElement(By.id("table"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		assertEquals(5, rows.size());

		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for (WebElement col : cols) {
				System.out.print(col.getText() + "\t");
			}
			System.out.println();
		}
		ba.close();
	}

	/*
	 * 定位表格中的某个单元格
	 */

	@Test(groups = "LocateTableTr")
	public void LocateTableTr() throws Exception {
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome("file:///E:/ZIXUE/web/table.html");
		driver = ba.setUp();
		/*
		 * 以下两种定位方法任选， 一种是一次直接定位到所需标签
		 * 一种是两次定位，在第二次定位时，使用//表示不识别具体位置，使用./表示从当前位置继续定位
		 */
		WebElement table1 = driver.findElement(By.xpath("//*[@id='table']/tbody/tr[2]/td[2]"));
		String str1 = table1.getText();

		WebElement table2 = driver.findElement(By.xpath("//*[@id='table']"));
		String str2 = table2.findElement(By.xpath("./tbody/tr[2]/td[2]")).getText();

		assertEquals("1000元", str1);
		assertEquals("1000元", str2);

		System.out.println(str1);
		System.out.println(str2);
		ba.close();
	}

	/*
	 * 定位表格中的某个单元格
	 */
	@Test(groups = "LocateTableCheckBox")
	public void LocateTableCheckBox() throws Exception {
		BeginAndCloseForChrome ba = new Tools.BeginAndCloseForChrome("file:///E:/ZIXUE/web/tableChcekBox.html");
		driver = ba.setUp();
		WebElement WebchcekBox = driver.findElement(By.xpath("//table[@id='table']/tbody/tr[3]/td[1]/input[2]"));

		if (!WebchcekBox.isSelected()) {
			WebchcekBox.click();
		}
		assertTrue(WebchcekBox.isSelected());

		ba.close();
	}
}
