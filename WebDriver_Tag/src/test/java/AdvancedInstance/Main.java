package AdvancedInstance;

import java.util.Properties;

import Tools.ObjectMap;

public class Main {
	public static void main(String args[]) {
		ObjectMap map = new ObjectMap("src/test/java/ObjectMap.properties");
		Properties p = map.properties;
		String s = p.getProperty("Sogou.HomePage.Search");
		System.out.println(s);

	}
}
