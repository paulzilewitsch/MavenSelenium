package testcases;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import com.hp.lft.sdk.web.Page;
import com.hp.lft.verifications.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase 
{
	private Browser browser;

    public LeanFtTest() 
    {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception 
    {
        instance = new LeanFtTest();
        globalSetup(LeanFtTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception 
    {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception 
    {
    	browser = BrowserFactory.launch(BrowserType.FIREFOX);
//    	browser.navigate("https://www.proficom.de/");	
    }

    @After
    public void tearDown() throws Exception 
    {
    	browser.close();
    }

    @Test
    public void test() throws GeneralLeanFtException 
    {
//    	browser.sync();
    	String title = browser.getTitle();
    	Verify.areEqual("Mozilla Firefox-Startseite", title);
    }

}