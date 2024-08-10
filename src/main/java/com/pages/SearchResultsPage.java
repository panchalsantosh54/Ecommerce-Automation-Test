package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage extends BasePage {

	private By noResultsMessage = By.xpath("//span[contains(text(),'No results')]");
	private By productLink = By.xpath("//span[@class='a-size-medium a-color-base a-text-normal'][contains(text(),'HP Laptop 15s, 12th Gen Intel Core i5-1235U, 15.6-')]");
	private By addToCartButton = By.xpath("//div[@class='a-section a-spacing-none a-padding-none']//input[@id='add-to-cart-button']");
	private By addCartBtn = By.xpath("//a[@href='/cart?ref_=sw_gtc']");

	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

	public boolean isNoResultsMessageDisplayed() {
		return !driver.findElements(noResultsMessage).isEmpty();
	}

	public boolean isProductDisplayed(String productName) {
		return driver.findElements(productLink)
				.stream()
				.anyMatch(element -> element.getText().contains(productName));
	}

	public void addProductToCart(int productIndex) {
		List<WebElement> products = driver.findElements(addToCartButton);
		if (productIndex < products.size()) {
			products.get(productIndex).click();
		}
	}
	
	/*
	public void addCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(addCartBtn));
		WebElement cartBtn = driver.findElement(addCartBtn);
		cartBtn.click();
	}
	*/
}
