package com.sanjayautomation.recipebook.testcases;

import org.testng.annotations.Test;

import com.sanjayautomation.recipebook.baseclass.BaseSetup;
import com.sanjayautomation.recipebook.pages.RecipesPage_POM;
import com.sanjayautomation.recipebook.pages.ShoppingListPage_PageFactory;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

//@Listeners(main.utilities.TestNGListener.class)
public class TestShoppingListPage extends BaseSetup {
	
	RecipesPage_POM recipesPage;
	ShoppingListPage_PageFactory shoppingListPage;
	
	
  @BeforeMethod
  public void beforeClass() {	  	
	  initialize();
	  log.info("Application launched successfully");
	  recipesPage = new RecipesPage_POM(driver);
	  shoppingListPage = new ShoppingListPage_PageFactory(driver);
  }
  
  @Test(priority=1)
  public void test_shouldAddNewIngredient_whenValidNameAndAmountEntered() {
	  recipesPage.clickOnShoppingListTab();
	  shoppingListPage.enterIngredientName("masala");
	  shoppingListPage.enterIngredientAmount(4);
	  shoppingListPage.clickAddButton();
	  String[] newIngredient = shoppingListPage.getLastIngredientInTheList().split(" ");
	  Assert.assertEquals(newIngredient[0], "masala");
	  log.info("Test passed");
  }
  
  @Test(priority=2)
  public void test_shouldDisableAddButton_WhenOnlyNameIsEngered() {
	  recipesPage.clickOnShoppingListTab();
	  shoppingListPage.enterIngredientName("masala");
	  Assert.assertFalse(shoppingListPage.getAddButton().isEnabled());
	  log.info("Test passed");
  }
  
  @Test(priority=3)
  public void test_shouldDisableAddButton_WhenAmountIsNotNumber() {
	  recipesPage.clickOnShoppingListTab();
	  shoppingListPage.enterIngredientName("masala");
	  shoppingListPage.enterIngredientName("abcd");
	  Assert.assertFalse(shoppingListPage.getAddButton().isEnabled());
	  log.info("Test passed");
  }
  
  @AfterMethod
  public void tearDown() {
	  driver.quit();
	  log.info("Browser terminated");
	  log.info("----------------------------------------------");
  }
  

}

