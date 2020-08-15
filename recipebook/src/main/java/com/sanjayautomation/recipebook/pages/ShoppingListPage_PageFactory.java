package com.sanjayautomation.recipebook.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingListPage_PageFactory {
	
	// Page Factory:
	
	@FindBy(id="name")
	WebElement nameInputField;
	
	@FindBy(name="amount")
	WebElement amountInputField;
	
	@FindBy(xpath="//button[contains(text(),'Add')]")
	WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Clear')]")
	WebElement clearButton;
	
	@FindBy(xpath="//a[@class='list-group-item' and position()=last()]")
	WebElement lastIngredientInTheList;
	
	
	// Initializing the Page Objects:
	public ShoppingListPage_PageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	// Actions:
	
	public void enterIngredientName(String ingredientName) {
		nameInputField.sendKeys(ingredientName);
	}
	
	public void enterIngredientAmount(int ingredientAmount) {
		amountInputField.sendKeys(String.valueOf(ingredientAmount));
	}
	
	public void clickAddButton() {
		addButton.click();
	}
	
	
	// Getters:
	
	public String getLastIngredientInTheList() {
		return lastIngredientInTheList.getText();
	}
	
	public WebElement getAddButton() {
		return addButton;
	}
	
}
