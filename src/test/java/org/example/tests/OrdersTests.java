package org.example.tests;

import org.example.pages.HomePageSamokat;
import org.example.pages.OrderPageSamokat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class OrdersTests {

	private WebDriver driver;

	private final int index;
	private final String name;
	private final String lastName;
	private final String address;
	private final String metroStation;
	private final String phoneNumber;
	private final String orderDate;
	private final String daysOfRent;
	private final String colour;
	private final String comment;

	public OrdersTests(int index, String name, String lastName, String address, String metroStation, String phoneNumber, String orderDate, String daysOfRent, String colour, String comment) {
		this.index = index;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.metroStation = metroStation;
		this.phoneNumber = phoneNumber;
		this.orderDate = orderDate;
		this.daysOfRent = daysOfRent;
		this.colour = colour;
		this.comment = comment;
	}

	@Parameterized.Parameters()
	public static Object[][] getData() {
		return new Object[][]{
				{0, "Евгений", "Пичугин", "Типанова 25-65", "Сокольники", "+79111355556", "21.10.2022", "сутки", "серая безысходность", "Уникальный комментарий " + System.currentTimeMillis()},
				{1, "Иван", "Петров", "Ленина д.33, кв. 2", "Лубянка", "+79211366665", "31.12.2022", "семеро суток", "чёрный жемчуг", "Неуникальный комментарий"},
		};
	}

	@Before
	public void setUp() {
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.get("https://qa-scooter.praktikum-services.ru/");
	}

	@Test
	public void checkOrder() {
		HomePageSamokat homePageSamokat = new HomePageSamokat(driver);
		OrderPageSamokat orderPageSamokat = new OrderPageSamokat(driver);

		homePageSamokat.scrollToOrderButtonByIndexAndClickIt(index);
		orderPageSamokat.order(name, lastName, address, metroStation, phoneNumber, orderDate, daysOfRent, colour, comment);

		Assert.assertTrue(orderPageSamokat.getConfirmationOrderText().contains("Заказ оформлен"));
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
