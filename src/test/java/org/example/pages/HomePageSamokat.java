package org.example.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePageSamokat {

	private WebDriver driver;

	//Вопросы о важном -> вопрос (Кликабелен. Используется для получения списка.)
	private By question = By.xpath(".//div[@data-accordion-component = 'AccordionItemButton']");

	//Вопросы о важном -> ответ (Используется для получения списка.)
	private By answer = By.xpath(".//div[@data-accordion-component = 'AccordionItemPanel']");

	public HomePageSamokat(WebDriver driver) {
		this.driver = driver;
	}

	public String getAnswerTextByIndex(int index) {
		waitForLoadAnswerData(index);
		return driver.findElements(answer).get(index).getText();
	}

	public void clickQuestionByIndex(int index) {
		driver.findElements(question).get(index).click();
	}

	public List<WebElement> getQuestionsList() {
		return driver.findElements(question);
	}

	public void waitForLoadAnswerData(int index) {
		new WebDriverWait(driver, 10).until(driver -> (driver.findElements(answer).get(index).getText() != null
				&& !driver.findElements(answer).get(index).getText().isEmpty()));
	}
}
