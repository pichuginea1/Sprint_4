package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageSamokat {

	private WebDriver driver;

	//Поле "Имя"
	private By nameField = By.cssSelector("[placeholder = '* Имя']");

	//Поле "Фамилия"
	private By lastNameField = By.cssSelector("[placeholder = '* Фамилия']");

	//Поле "Адрес"
	private By addressField = By.cssSelector("[placeholder = '* Адрес: куда привезти заказ']");

	//Поле "Метро"
	private By metroStationField = By.xpath(".//input[@class = 'select-search__input']");

	//Поле "Номер телефона"
	private By phoneNumberField = By.cssSelector("[placeholder = '* Телефон: на него позвонит курьер']");

	//Кнопка "Далее"
	private By nextButton = By.xpath(".//button[text()='Далее']");

	//Поле "Дата доставки самоката"
	private By orderDateField = By.cssSelector("[placeholder = '* Когда привезти самокат']");

	//Стрелка раскрывающая список поля "Срок Аренды"
	private By daysOfRentFieldArrow = By.className("Dropdown-arrow");

	//Чекбокс -> Цвет самоката - "чёрный жемчуг"
	private By blackColourCheckbox = By.xpath(".//input[@type = 'checkbox' and @id = 'black']");

	//Чекбокс -> Цвет самоката - "серая безысходность"
	private By greyColourCheckbox = By.xpath(".//input[@type = 'checkbox' and @id = 'grey']");

	//Поле "Комментарий
	private By commentField = By.cssSelector("[placeholder = 'Комментарий для курьера']");

	//Кнопка "Заказать"
	private By orderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

	//Диалоговое окно "Хотите оформить заказ?"
	private By confirmOrderDialog = By.cssSelector(".Order_ModalHeader__3FDaJ");

	//Кнопка "Да" в диалогом окне "Хотите оформить заказ?"
	private By confirmOrderButton = By.xpath(".//button[text()='Да']");

	//Диалоговое окно "Ваш заказ оформлен"
	private By orderConfirmedDialog = By.cssSelector(".Order_Modal__YZ-d3");

	public OrderPageSamokat(WebDriver driver) {
		this.driver = driver;
	}

	//Выбирает элемент из списка станции метро
	public void selectMetroStation(String value) {
		driver.findElement(metroStationField).sendKeys(value);
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.ENTER)).perform();
	}

	public void selectDaysOfRent(String daysOfRent) {
		driver.findElement(daysOfRentFieldArrow).click();
		new WebDriverWait(driver, 1);
		driver.findElement(By.xpath(".//div[text() = '" + daysOfRent + "']")).click();
	}

	//Заполняет все поля для заказа самоката
	public void order(String name, String lastname, String address, String metroStation, String phoneNumber,
					  String orderDate, String daysOfRent, String colour, String comment) {

		driver.findElement(nameField).sendKeys(name);
		driver.findElement(lastNameField).sendKeys(lastname);
		driver.findElement(addressField).sendKeys(address);
		driver.findElement(phoneNumberField).sendKeys(phoneNumber);
		selectMetroStation(metroStation);

		driver.findElement(nextButton).click();

		driver.findElement(orderDateField).sendKeys(orderDate);
		selectDaysOfRent(daysOfRent);
		if (colour.equals("чёрный жемчуг")) {
			driver.findElement(blackColourCheckbox).click();
		} else {
			driver.findElement(greyColourCheckbox).click();
		}
		driver.findElement(commentField).sendKeys(comment);

		driver.findElement(orderButton).click();

		new WebDriverWait(driver, 10).until(driver -> (driver.findElement(confirmOrderDialog).isDisplayed()
				&& driver.findElement(confirmOrderButton).isDisplayed()));

		driver.findElement(confirmOrderButton).click();

		new WebDriverWait(driver, 10).until(driver -> (driver.findElement(orderConfirmedDialog).isDisplayed()));
	}

	//Получение текста заказа
	public String getConfirmationOrderText() {
		return driver.findElement(orderConfirmedDialog).getText();
	}
}
