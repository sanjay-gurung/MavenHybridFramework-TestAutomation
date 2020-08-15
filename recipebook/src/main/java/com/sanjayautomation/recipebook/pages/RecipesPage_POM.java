package com.sanjayautomation.recipebook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sanjayautomation.recipebook.utilities.TestUtilities;

public class RecipesPage_POM {
	WebDriver driver;
	
	// WebElements:
	
	By navbarHeader = By.xpath("/html/body/app-root/app-header/nav/div/div[1]/a");
	
	By shoppingListTab = By.xpath("//a[contains(text(), 'Shopping List')]");
	
	By text = By.xpath("//h3[contains(text(),'Please select a recipe!')]");
	
	By newRecipeButton = By.xpath("//button[contains(.,'New Recipe')]");
	
	By recipesList = By.xpath("//h4[@class='list-group-item-heading']");
	
	By nameInputBox = By.id("name");
	
	By imageUrlInputBox = By.id("imagePath");
	
	By descriptionInputBox = By.id("description");
	
	By cancelButton = By.cssSelector("button.btn:nth-child(2)");
	
	By menuItem = By.xpath("//h4[contains(text(), 'momo')]");
	
	By saveButton = By.xpath("//button[contains(text(), 'Save')]");
	
	By lastRecipeInTheList = By.xpath("//div[@class='col-xs-12' and position()=last()]//app-recipe-item//h4");
	
	
	// Constructor
	public RecipesPage_POM(WebDriver driver) {
		this.driver = driver;
	}
	

	// Getters:
	
	public String getLandingPageText() {
		return driver.findElement(text).getText();
	}
	
	public WebElement getLastRecipeInTheList() {
		TestUtilities.waitForElementToBeVisible(driver, lastRecipeInTheList, 3);  //calling Explicit Wait method from TestUtilities class
		return driver.findElement(lastRecipeInTheList);
	}
		
	
	// Actions:
	
	public boolean recipesListDisplayed() {
		return driver.findElement(recipesList).isDisplayed();
	}
	
	public void navigateToNewRecipe() {
		driver.findElement(newRecipeButton).click();
	}
	
	public void enterRecipeName(String recipeName) {
		driver.findElement(nameInputBox).sendKeys(recipeName);
	}
	
	public void enterRecipeUrl(String recipeUrl) {
		driver.findElement(imageUrlInputBox).sendKeys(recipeUrl);
	}
	
	public void enterRecipeDescription(String recipeDescription) {
		driver.findElement(descriptionInputBox).sendKeys(recipeDescription);
	}
	
	public void clickSaveButton() {
		driver.findElement(saveButton).click();
	}
	
	public void clickOnShoppingListTab() {
		driver.findElement(shoppingListTab).click();
	}
	
	
}
