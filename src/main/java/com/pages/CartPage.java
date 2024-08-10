package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

	private By cartQuantityDropdown = By.name("quantity");
	private By cartItemCount = By.cssSelector("span[id='nav-cart-count']");
	private By deleteButton = By.cssSelector("img[alt='Delete']");

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public boolean isProductInCart() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCount)); 
	    return driver.findElements(cartItemCount).size() > 0;
		
//		return !driver.findElements(cartItemCount).isEmpty()
//				&& Integer.parseInt(driver.findElement(cartItemCount).getText()) > 0;
	}

	public void updateProductQuantity(int quantity) {
//		driver.findElement(cartQuantityDropdown).sendKeys(String.valueOf(quantity));

		WebElement quantityDropdownElement = driver.findElement(cartQuantityDropdown);
		Select quantityDropdown = new Select(quantityDropdownElement);
		quantityDropdown.selectByValue(String.valueOf(quantity));
	}

	public int getProductQuantity() {
		WebElement quantityDropdownElement = driver.findElement(cartQuantityDropdown);
		Select quantityDropdown = new Select(quantityDropdownElement);
		String selectedValue = quantityDropdown.getFirstSelectedOption().getText();
		return Integer.parseInt(selectedValue);
	}

	public void removeProductFromCart() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//	    wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
		
		WebElement deleteButtons = driver.findElement(deleteButton);
		deleteButtons.click();
	}

	public boolean isCartEmpty() {
		return driver.findElement(cartItemCount).getText().equals("0");
	}
}
