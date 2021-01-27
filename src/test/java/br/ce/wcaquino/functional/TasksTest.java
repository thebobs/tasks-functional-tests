package br.ce.wcaquino.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), cap);

		driver.navigate().to("http://172.29.160.1:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		
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
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		
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
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		
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
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		
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
