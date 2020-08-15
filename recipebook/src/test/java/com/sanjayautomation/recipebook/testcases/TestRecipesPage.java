package com.sanjayautomation.recipebook.testcases;

import org.testng.annotations.Test;

import com.sanjayautomation.recipebook.baseclass.BaseSetup;
import com.sanjayautomation.recipebook.pages.RecipesPage_POM;
import com.sanjayautomation.recipebook.utilities.TestUtilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;;


@Listeners(com.sanjayautomation.recipebook.utilities.TestNGListener.class)
public class TestRecipesPage extends BaseSetup {
	
	String baseUrl;
	RecipesPage_POM recipesPage;
	
  @BeforeMethod(groups= {"smoke", "regression"})
  public void setup() {	  	
	  initialize();
	  log.info("Application launched successfully");
	  recipesPage = new RecipesPage_POM(driver);
  }
  
  @BeforeTest(groups= {"dataDrivn"})
  public void setupBeforeTest() {	  	
	  initialize();
	  log.info("Application launched successfully");
	  recipesPage = new RecipesPage_POM(driver);
  }
  
  
  @Test(priority=1, groups={"smoke", "regression"})
  public void test_shouldDisplayRecipesList_whenUserInLandingPage() {
	  Assert.assertTrue(recipesPage.recipesListDisplayed());
	  log.info("Test Passed");
  }
 
  
  @Test(priority=2, groups={"regression"})
  public void test_shouldDisplayText_whenUserInLandingPage() {
	  String landingPageText = recipesPage.getLandingPageText();
	  Assert.assertEquals(landingPageText, "Please select a recipe!");
	  log.info("Test Passed");
  }
  
  
  @Test(priority=3, groups={"regression"})
  public void test_shouldAddNewRecipe_whenCorrectValuesEntered() throws Exception {
	  recipesPage.navigateToNewRecipe();
	  recipesPage.enterRecipeName("momo");
	  recipesPage.enterRecipeUrl("dummy momo image url");
	  recipesPage.enterRecipeDescription("testy nepali cuisine");
	  recipesPage.clickSaveButton();
	  String newRecipeName = recipesPage.getLastRecipeInTheList().getText();
	  Assert.assertEquals(newRecipeName, "momo");
	  log.info("Test Passed");
  }
  
  @DataProvider
  public static Object[][] getTestDataForNewRecipe() throws Exception {
	  Object data[][] = null;
	  try {
		  data = TestUtilities.getTestDataFromExcel("newRecipes");
	  } catch (Exception e) {
		  e.printStackTrace();
		  System.out.println("error getting data from excel file");
	  }
	  return data;
  }
  
  @Test(priority=4, groups= {"dataDrivn"}, dataProvider="getTestDataForNewRecipe")
  public void test_shouldAddMultipleNewRecipes_whenCorrectValuesEnteredOneAfterAnother(String name, String url, String description) throws Exception {
	  recipesPage.navigateToNewRecipe();
	  recipesPage.enterRecipeName(name);
	  recipesPage.enterRecipeUrl(url);
	  recipesPage.enterRecipeDescription(description);
	  recipesPage.clickSaveButton();
	  String newRecipeName = recipesPage.getLastRecipeInTheList().getText();
	  Assert.assertEquals(newRecipeName, name);
	  log.info("Test Passed");
	  
  }
  
  @AfterMethod(groups= {"smoke", "regression"})
  public void tearDown() throws Exception {
	  //Thread.sleep(2000);
	  driver.quit();
	  log.info("Browser terminated");
	  log.info("----------------------------------------------");
  }
  
  @AfterTest(groups="dataDrivn")
  public void tearDownAfterTest() throws Exception {
	  //Thread.sleep(2000);
	  driver.quit();
	  log.info("Browser terminated");
	  log.info("----------------------------------------------");
  }


}
