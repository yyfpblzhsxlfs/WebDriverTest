package AdvancedInstance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

import net.bytebuddy.implementation.bytecode.Throw;

public class ObjectMap {
	Properties properties;

	public ObjectMap(String propFile) {
		properties = new Properties();

		try {
			FileInputStream inputStream = new FileInputStream(propFile);
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			System.out.println("文件读取错误");
		}
	}

	public By getLocator(String ElementNameInpropFile) throws Exception {
		// 根据变量ElementNameInpropFile，从属性配置文件中读取对应配置对象
		String locator = properties.getProperty(ElementNameInpropFile);

		// 将配置对象中的定位类型存储在locatorType变量中，将表达式存储在locatorValue变量中
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];

		System.out.println(locatorType + " : " + locatorValue);

		// 根据locatorType内容返回By对象
		if (locatorType.toLowerCase().equals("id")) {
			return By.id(locatorValue);
		} else if (locatorType.toLowerCase().equals("name")) {
			return By.name(locatorValue);
		} else if (locatorType.toLowerCase().equals("classname") || locatorType.toLowerCase().equals("class")) {
			return By.className(locatorValue);
		} else if (locatorType.toLowerCase().equals("tagname") || locatorType.toLowerCase().equals("tag")) {
			return By.tagName(locatorValue);
		} else if (locatorType.toLowerCase().equals("linktext") || locatorType.toLowerCase().equals("link")) {
			return By.linkText(locatorValue);
		} else if (locatorType.toLowerCase().equals("partiallinktext")) {
			return By.partialLinkText(locatorValue);
		} else if (locatorType.toLowerCase().equals("cssselector") || locatorType.toLowerCase().equals("css")) {
			return By.cssSelector(locatorValue);
		} else if (locatorType.toLowerCase().equals("xpath")) {
			return By.xpath(locatorValue);
		} else {
			throw new Exception("输入的locator type 未在程序中定义：" + locatorType);
		}
	}
}
