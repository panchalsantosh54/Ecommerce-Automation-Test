package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

	private By cartQuantityDropdown = By.xpath("//span[@class='a-button-text a-declarative']");
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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCount));

		WebElement quantityDropdownElement = driver.findElement(cartQuantityDropdown);
		quantityDropdownElement.click();

		/*
		 * Select quantityDropdown = new Select(quantityDropdownElement);
		 * quantityDropdown.selectByValue(String.valueOf(quantity));
		 */
//		quantityDropdown.selectByVisibleText(String.valueOf(quantity));
	}

	public int getProductQuantity() {
		WebElement quantityDropdownElement = driver.findElement(cartQuantityDropdown);
		
		/*
		String quantityText = quantityDropdownElement.getText().trim();
		System.out.println("Quantity text retrieved: " + quantityText);

		try {
			return Integer.parseInt(quantityText);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to parse quantity: " + quantityText);
		}
		*/

		  Select quantityDropdown = new Select(quantityDropdownElement); 
		  String selectedValue = quantityDropdown.getFirstSelectedOption().getText(); 
		  return Integer.parseInt(quantityDropdownElement.getText());
		 
	}

	public void removeProductFromCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));

		WebElement deleteButtons = driver.findElement(deleteButton);
		deleteButtons.click();
	}

	public boolean isCartEmpty() {
		return driver.findElement(cartItemCount).getText().equals("0");
	}
}
