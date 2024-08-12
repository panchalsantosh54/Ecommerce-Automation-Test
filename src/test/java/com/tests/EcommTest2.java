package com.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EcommTest2 extends BaseTest {

	@Test
	public void searchNonExistingProduct() {

		driver.findElement(By.xpath("//div[@id='nav-search-dropdown-card']"));

		Select dropdown = new Select(
				driver.findElement(By.cssSelector("select[aria-describedby='searchDropdownDescription']")));

		dropdown.selectByVisibleText("Amazon Devices");

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("ld345tsxslfer");

		driver.findElement(By.id("nav-search-submit-button")).click();

		String resultText = driver.findElement(By.xpath("//div[@tabindex='0']/div[1]")).getText();

		Assert.assertEquals(resultText, "No results for ld345tsxslfer.");

	}

	@Test
	public void searchExistingProduct() {

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Laptop");

		driver.findElement(By.id("nav-search-submit-button")).click();

		boolean isProductDisplayed = driver
				.findElements(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[6]")).size() > 0;

		Assert.assertTrue(isProductDisplayed);

	}

	@Test
	public void addProductToCart() throws InterruptedException {

		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Laptop");

		driver.findElement(By.id("nav-search-submit-button")).click();

		JavascriptExecutor sw = (JavascriptExecutor) driver;

		// Assign up to Which Path we wan to down

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		WebElement w = driver.findElement(By.xpath("//div[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[6]"));

		// Scroll down up to particular path

		sw.executeScript("arguments[0].scrollIntoView(true)", w);

		// w.click();

		// driver.findElements(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[6]"));
		// // Select 4th result

		driver.findElement(By.linkText(
				"HP Laptop 15s, 12th Gen Intel Core i5-1235U, 15.6-inch (39.6 cm), FHD, 16GB DDR4, 512GB SSD, Intel Iris Xe Graphics, 720p HD Camera, Backlit KB, Thin & Light (Win 11, Silver, 1.69 kg), fy5009TU"));

		driver.findElement(By.xpath("//button[@id=\"a-autoid-5-announce\"]")).click();

		Thread.sleep(2000);

		// driver.findElement(By.xpath("//input[@id=\"add-to-cart-button\"]")).click();

		String cartText = driver.findElement(By.xpath("//div[@class='a-row puis-atcb-remove-group']/span[1]"))
				.getText();

		Assert.assertTrue(cartText.contains("1 in cart"));

	}

	@Test
	public void updateQuantityInCart() throws InterruptedException {

		addProductToCart();

		driver.findElement(By.xpath("//div[@id='nav-cart-count-container']/span[1]")).click();

		driver.findElement(By.xpath("//i[@class='a-icon a-icon-dropdown']/preceding-sibling::span")).click();

		WebElement dropdownElement = driver
				.findElement(By.xpath("//select[@class='a-native-dropdown a-declarative sc-update-quantity-select']"));

		Select dropdown = new Select(dropdownElement);

		dropdown.selectByVisibleText("2");

		// driver.findElement(By.id("update-cart")).click();

		Thread.sleep(1500);

		String cartText = driver.findElement(By.xpath("//span[@id='sc-subtotal-label-buybox']")).getText();

		Assert.assertTrue(cartText.contains("Subtotal (2 items)"));

	}

	@Test
	public void removeProductFromCart() throws InterruptedException {

		addProductToCart();

		driver.findElement(By.xpath("//div[@id='nav-cart-count-container']/span[1]")).click();

		driver.findElement(By.xpath("//input[@value='Delete']")).click();

		String cartText = driver.findElement(By.xpath("//div[@class='a-row sc-cart-header']/div/h1")).getText();

		Assert.assertTrue(cartText.contains("Your Amazon Cart is empty."));

	}

}