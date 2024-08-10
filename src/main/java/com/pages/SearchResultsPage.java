package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends BasePage {

	private By noResultsMessage = By.xpath("//span[contains(text(),'No results')]");
	private By productTitles = By.cssSelector("span.a-size-medium.a-color-base.a-text-normal");
	private By addToCartButtons = By.cssSelector("input[id^='add-to-cart-button']");

	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

	public boolean isNoResultsMessageDisplayed() {
		return !driver.findElements(noResultsMessage).isEmpty();
	}

	public boolean isProductDisplayed(String productName) {
		return driver.findElements(productTitles)
				.stream()
				.anyMatch(element -> element.getText().contains(productName));
	}

	public void addProductToCart(int productIndex) {
		List<WebElement> products = driver.findElements(addToCartButtons);
		if (productIndex < products.size()) {
			products.get(productIndex).click();
		}
	}
}
