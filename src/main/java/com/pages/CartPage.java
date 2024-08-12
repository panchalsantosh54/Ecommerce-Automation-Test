package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

	private By cartQuantityDropdown = By.xpath("//select[@class='a-native-dropdown a-declarative sc-update-quantity-select']");
	private By cartItemCount = By.xpath("//div[@id='nav-cart-count-container']/span[1]");
	private By deleteButton = By.xpath("//input[@value='Delete']");
	private By subTotalItem = By.xpath("//span[@id='sc-subtotal-label-buybox']");

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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartQuantityDropdown));
		
		WebElement quantityDropdownElement = driver.findElement(cartQuantityDropdown);
		quantityDropdownElement.click();
		
		Select dropdown = new Select(quantityDropdownElement);
		dropdown.selectByVisibleText("2");
		
		/*
		Thread.sleep(1500);
		String cartText = driver.findElement(By.xpath("//span[@id='sc-subtotal-label-buybox']")).getText();
		Assert.assertTrue(cartText.contains("Subtotal (2 items)"));
		*/

	}

	public int getProductQuantity() {
		WebElement quantityDropdownElement = driver.findElement(cartQuantityDropdown);

		  Select quantityDropdown = new Select(quantityDropdownElement); 
		  String selectedValue = quantityDropdown.getFirstSelectedOption().getText(); 
		  return Integer.parseInt(quantityDropdownElement.getText());
		 
	}

	public void removeProductFromCart() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(deleteButton));

		Thread.sleep(5000l);
		WebElement deleteButtons = driver.findElement(deleteButton);
		deleteButtons.click();
	}

	public boolean isCartEmpty() {
		return driver.findElement(cartItemCount).getText().equals("0");
	}
}
