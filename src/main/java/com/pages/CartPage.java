package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

	private By cartQuantity = By.name("quantity");
	private By cartItemCount = By.cssSelector("span[id='nav-cart-count']");
	private By deleteButton = By.xpath("//input[@value='Delete']");

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public boolean isProductInCart() {
		return !driver.findElements(cartItemCount).isEmpty()
				&& Integer.parseInt(driver.findElement(cartItemCount).getText()) > 0;
	}

	public void updateProductQuantity(int quantity) {
		driver.findElement(cartQuantity).sendKeys(String.valueOf(quantity));
	}

	public void removeProductFromCart() {
		driver.findElement(deleteButton).click();
	}

	public boolean isCartEmpty() {
		return driver.findElement(cartItemCount).getText().equals("0");
	}
}
