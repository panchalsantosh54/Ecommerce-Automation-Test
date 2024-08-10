package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

	private By searchBox = By.id("twotabsearchtextbox");
	private By searchButton = By.id("nav-search-submit-button");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void searchForProduct(String productName) {
		driver.findElement(searchBox).sendKeys(productName);
		driver.findElement(searchButton).click();
	}
}
