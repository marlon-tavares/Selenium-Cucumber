package StepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class A_LoginSteps {
	
		private final WebDriver driver = new ChromeDriver();

//########################### Verifica Login ############################
	
//	  Scenario: Verifica login com sucesso
//	    Given o usuario na tela de login
//	    When preencher usuario e senha validos
//	    And clicar no botao de login
//	    Then devera ser redirecionado para a pagina de usuario logado
  
	String url_test = new String("http://20.0.0.54:8080/apex/f?p=466:100:");
	
	@Given("o usuario na tela de login")
	public void tela_de_login(){	
		driver.manage().window().maximize();
		driver.get(url_test);	
		Assert.assertEquals("Login", driver.getTitle()); 
	}

	@When("preencher usuario e senha validos")
	public void usuario_e_senha_validos(){
		driver.findElement(By.id("P100_USERNAME"))
			.sendKeys("Marlon");
		driver.findElement(By.id("P100_PASSWORD"))
			.sendKeys("1");
	}

	@And("clicar no botao de login")
	public void botao_de_login(){
		WebElement botao = driver.findElement(By.id("LOGIN"));
		botao.click();
	}

	@Then("devera ser redirecionado para a pagina de usuario logado")
	public void usuario_logado(){
		Assert.assertEquals("Painel de Controle", driver.getTitle());
		driver.quit();
	} 
	
//	  Scenario: Verifica falha no login
//    Given o usuario na tela de login
//    When preencher usuario ou senha invalidos
//    And clicar no botao de login
//    Then devera apresentar pop-up de erro no login
	
	@When("preencher usuario ou senha invalidos")
	public void usuario_e_senha_invalidos(){
		driver.findElement(By.id("P100_USERNAME")).sendKeys("Teste");
		driver.findElement(By.id("P100_PASSWORD")).sendKeys("errado");
	}
	
	@Then("devera apresentar pop-up de erro no login")
	public void popup_erro(){
		WebElement msg_erro = driver.findElement(By.id("uNotificationMessage"));
		 Assert.assertEquals(true, msg_erro.isDisplayed());
		 driver.quit();
	}	
}