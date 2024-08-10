package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.SearchResultsPage;

public class EcommerceTests extends BaseTest {

	@Test
	public void testSearchNonExistingProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.searchForProduct("ld345tsxslfer");

		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		Assert.assertTrue(searchResultsPage.isNoResultsMessageDisplayed(), "No results message should be displayed");
	}

	@Test
	public void testSearchExistingProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.searchForProduct("Laptop");

		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		Assert.assertTrue(searchResultsPage.isProductDisplayed("Laptop"), "Product should be displayed on the page");
	}

	@Test
	public void testAddProductToCart() {
		HomePage homePage = new HomePage(driver);
		homePage.searchForProduct("Laptop");

		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		searchResultsPage.addProductToCart(3);

		CartPage cartPage = new CartPage(driver);
		Assert.assertTrue(cartPage.isProductInCart(), "Product should be added to the cart");
	}

	@Test
	public void testUpdateProductQuantityInCart() {
		HomePage homePage = new HomePage(driver);
		homePage.searchForProduct("Laptop");

		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		searchResultsPage.addProductToCart(3);

		CartPage cartPage = new CartPage(driver);
		cartPage.updateProductQuantity(2);
		Assert.assertEquals(cartPage.isProductInCart(), 2, "Cart should reflect the updated quantity");
	}

	@Test
	public void testRemoveProductFromCart() {
		HomePage homePage = new HomePage(driver);
		homePage.searchForProduct("Laptop");

		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		searchResultsPage.addProductToCart(3);

		CartPage cartPage = new CartPage(driver);
		cartPage.removeProductFromCart();
		Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty");
	}
}
