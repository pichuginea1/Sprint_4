package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePageSamokat {

	private WebDriver driver;

	//Вопросы о важном -> вопросы (Используется для получения списка.)
	private By questions = By.xpath(".//div[@data-accordion-component = 'AccordionItemButton']");

	//Вопросы о важном -> ответы (Используется для получения списка.)
	private By answers = By.xpath(".//div[@data-accordion-component = 'AccordionItemPanel']");

	//Кнопка "Заказать" (Используется для получения списка.)
	private By orderButtons = By.xpath(".//button[text() = 'Заказать']");

	public HomePageSamokat(WebDriver driver) {
		this.driver = driver;
	}

	//Возвращает текст ответа по индексу
	public String getAnswerTextByIndex(int index) {
		waitForLoadAnswerData(index);
		return driver.findElements(answers).get(index).getText();
	}

	//Кликает на вопрос по индексу и раскрывает форму с текстом ответа
	public void clickQuestionByIndex(int index) {
		driver.findElements(questions).get(index).click();
	}

	//Получение списка элементов с вопросами
	public List<WebElement> getQuestionsList() {
		return driver.findElements(questions);
	}

	//Ожидает появления текста ответа по индексу
	public void waitForLoadAnswerData(int index) {
		new WebDriverWait(driver, 10).until(driver -> (driver.findElements(answers).get(index).getText() != null
				&& !driver.findElements(answers).get(index).getText().isEmpty()));
	}

	//Скролл к одной из кнопок "Заказать" по индексу и клик на неё
	public void scrollToOrderButtonByIndexAndClickIt(int index) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElements(orderButtons).get(index));
		driver.findElements(orderButtons).get(index).click();
	}
}
