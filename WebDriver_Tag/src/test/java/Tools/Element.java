package Tools;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Element {
	public boolean IsElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public Object JavascriptReturn(WebDriver driver, String jsStr, Object... objects) {
		Object returnOBJ = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (objects != null) {
			switch (objects.length) {
			case 0:
				returnOBJ = js.executeScript(jsStr);
				break;
			case 1:
				returnOBJ = js.executeScript(jsStr, objects[0]);
				break;
			case 2:
				returnOBJ = js.executeScript(jsStr, objects[0], objects[1]);
				break;
			case 3:
				returnOBJ = js.executeScript(jsStr, objects[0], objects[1], objects[2]);
				break;
			case 4:
				returnOBJ = js.executeScript(jsStr, objects[0], objects[1], objects[2], objects[3]);
				break;
			default:
				System.out.println("JS的Object参数是" + objects.length + "，JS参数超限，无法执行JS执行器");
				break;
			}
		}

		return returnOBJ;
	}

	public void JavascriptSet(WebDriver driver, String jsStr, Object... objects) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (objects != null) {
			switch (objects.length - 1) {
			case 0:
				js.executeScript(jsStr, objects[0]);
				break;
			case 1:
				js.executeScript(jsStr, objects[0], objects[1]);
				break;
			case 2:
				js.executeScript(jsStr, objects[0], objects[1], objects[2]);
				break;
			case 3:
				js.executeScript(jsStr, objects[0], objects[1], objects[2], objects[3]);
				break;
			default:
				System.out.println("JS的Object参数是" + objects.length);
				break;
			}
		}
	}

	// var startDate = document.getElementById ("datepicker").value;
	//
	// alert(startDate.value);

}
