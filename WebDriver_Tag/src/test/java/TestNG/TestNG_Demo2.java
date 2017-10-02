package TestNG;

import org.testng.annotations.*;

public class TestNG_Demo2 {
	@Test(groups = "run1")
	public void test4() {
		System.out.println("run1 Demo2  test4");
	}
	
	@Test(groups = "run2")
	public void test5() {
		System.out.println("run2 Demo2 test5");
	}
	
	@Test(groups = "run3")
	public void test6() {
		System.out.println("run3 Demo2 test6");
	}
}
