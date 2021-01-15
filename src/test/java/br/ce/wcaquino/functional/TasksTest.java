package br.ce.wcaquino.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
	@Test
	public void deveSalvarTarefaComSucesso() {
		
		//clicar em Add Todo
		
		WebDriver driver = acessarAplicacao();
		try {
		
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descrição e data
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		// Salvar
		driver.findElement(By.id("saveButton")).click();
		
		//valida mensagem
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);

		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		
		//clicar em Add Todo
		
		WebDriver driver = acessarAplicacao();
		try {
		
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descrição e data
		//driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		// Salvar
		driver.findElement(By.id("saveButton")).click();
		
		//valida mensagem
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);

		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}


	@Test
	public void naoDeveSalvarTarefaSemData() {
		
		//clicar em Add Todo
		
		WebDriver driver = acessarAplicacao();
		try {
		
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descrição e data
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		//driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		// Salvar
		driver.findElement(By.id("saveButton")).click();
		
		//valida mensagem
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);

		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}

	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		
		//clicar em Add Todo
		
		WebDriver driver = acessarAplicacao();
		try {
		
		driver.findElement(By.id("addTodo")).click();
		
		//escrever descrição e data
		driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
		
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		
		// Salvar
		driver.findElement(By.id("saveButton")).click();
		
		//valida mensagem
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);

		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}

}
