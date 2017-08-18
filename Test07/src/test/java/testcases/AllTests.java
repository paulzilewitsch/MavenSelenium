package testcases;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCarCredit.class })
public class AllTests 
{
	@BeforeClass
	public static void setUp()
	{
		System.out.println("\nTests starten\n");
	}

	@AfterClass
	public static void tearDown()
	{
		System.out.println("\nTests sind durchgelaufen\n");
	}
}
