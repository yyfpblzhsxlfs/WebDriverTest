package TestNG;

import org.testng.annotations.*;

public class TestNG_Demo1 {
	@Test(groups = "run2",dependsOnMethods={"test2"})
	public void test1() {
		System.out.println("run2 Demo1 test1");
	}
	
	@Test(groups = "run1")
	public void test2() {
		System.out.println("run1 Demo1 test2");
	}
	
	@Test(groups = "run3")
	public void test3() {
		System.out.println("run3 Demo1 test3");
	}
}
