package StepDefinitions;
import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class C_Venda {

// --------------------------------------------------------------------------------------------------	

// Variáveis gerais utilizadas no teste:
	String url_test           = new String("http://20.0.0.54:8080/apex/f?p=466:100:");
	String doc_credenciado    = "TEST/MARLON - CREDENCIADO";
	String cpf_usuario        = "082.997.310-97";
	String cartao_usuario     = "6376 02XX XXXX 4905";
	
// Variáveis de configurações da venda:
	String valor_venda        = "2";
	String qtd_parcelas       = "1";
	String tipo_parcelamento  = "0"; // 0 - A vista / 1 - Parc. loja / 2 - Parc. ADM / 5 - Saque
	
// Varáveis de validação da venda no cupom:
	String cp_valor        =    "VALOR       : 2,00";
	String cp_encargos     =    "ENCARGOS    : 10,00";
	String cp_valor_total  =    "VALOR TOTAL   : 12,00";
	
// --------------------------------------------------------------------------------------------------	
	WebDriver driver;
	
	@Given("operador logado deseje realizar uma venda manual")
	public void operador_logado_deseje_realizar_uma_venda_manual() {
		// Inicia browser
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				
		// Acessa página inicial do FWCARD
				driver.get(url_test);	
				
		// Verifica se a página de login foi acessada
				Assert.assertEquals("Login", driver.getTitle());
				
		// Preenche nome de usuário e senha
				driver.findElement(By.id("P100_USERNAME"))
					.sendKeys("Marlon");
				driver.findElement(By.id("P100_PASSWORD"))
					.sendKeys("1");
				
		// CLica no botão de login
				WebElement botao = driver.findElement(By.id("LOGIN"));
					botao.click();
					
		// Verifica se a Homepage de usuário logado foi acessada
				Assert.assertEquals("Painel de Controle", driver.getTitle());
	}
	
	@When("acessar a tela de venda")
	public void acessar_a_tela_de_venda() {
// Acessa menu CRM > Produção > Lançamento de compra
		WebElement crm = driver.findElement(By.id("jqmenu-3"));
			crm.click();
		WebElement producao = driver.findElement(By.xpath("//*[@id=\"P0_MENU_3_DISPLAY\"]/div/ul/li[3]/a"));
			producao.click();
			WebElement cliente = driver.findElement(By.linkText("Lançamento de compra"));
			cliente.click();
	}
	
	@And("preencher os dados da venda")
	public void preencher_os_dados_da_venda() {
// Clica no pop-up do credenciado
		WebElement credenciado_popup = driver.findElement(By.xpath("//*[@id=\"P7100_CREDENCIADO_holder\"]/tbody/tr/td[2]/a/img"));
			credenciado_popup.click();
//		System.out.println(driver.getWindowHandle());			
//		System.out.println(driver.getWindowHandles());
// Troca de janela
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
//Pesquisa pelo credenciado da compra
		driver.findElement(By.id("SEARCH"))
			.sendKeys(doc_credenciado);
		driver.findElement(By.id("SEARCH"))
			.sendKeys(Keys.RETURN);
		WebElement confirma_cred = driver.findElement(By.xpath("/html/body/form/div[2]/a"));
			confirma_cred.click();
// Preenche cpf doc usuario
		driver.switchTo()
			.window((String)driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.id("P7100_CPF"))
			.sendKeys(cpf_usuario);
		driver.findElement(By.id("P7100_CPF"))
			.sendKeys(Keys.TAB);
		
// Seleciona o produto da compra		
//		new WebDriverWait(driver, Duration.ofSeconds(3))
//			.until(ExpectedConditions.elementToBeClickable(By.id("P7100_CARTAO")));
		WebElement teste1 = driver.findElement(By.id("P7100_CPF"));
			teste1.click();
		WebElement element1 =  driver.findElement(By.id("P7100_CARTAO"));
	    Select cartao = new Select(element1);
//	    	cartao.selectByValue("AAAnHMAAJAAE2DnAAE");
	    	cartao.selectByIndex(1);
//	    	cartao.selectByVisibleText(cartao_usuario);
	    	
// Clica em ok
	    new WebDriverWait(driver, Duration.ofSeconds(3))
	    	.until(ExpectedConditions.elementToBeClickable(By.id("P7100_ID_PRODUTO")));	
	    WebElement btn_ok = driver.findElement(By.id("OK"));
			btn_ok.click();	
			
// Preenche o valor da compra			
		driver.findElement(By.id("P2108_VALOR"))
			.sendKeys(valor_venda);
		
// Preenche a quantidade de parcelas
		driver.findElement(By.id("P2108_PARCELA"))
		.sendKeys(qtd_parcelas);
		
// Seleciona o tipo de parcelamento 

		WebElement element2 =  driver.findElement(By.id("P2108_TIPO_PARCELAMENTO"));
	    Select parcelamento = new Select(element2);
	    	parcelamento.selectByValue(tipo_parcelamento);
	    	
// Seleciona o plano de venda
// Id do plano = valor
	    new WebDriverWait(driver, Duration.ofSeconds(3))
		    .until(ExpectedConditions.elementToBeClickable(By.id("P2108_PLANO_COMPRA_CADASTRO")));
	    WebElement element3 =  driver.findElement(By.id("P2108_PLANO_COMPRA_CADASTRO"));
		Select plan_venda = new Select(element3);
		    plan_venda.selectByValue("169");	
	    	
// Clica em OK
		WebElement ok_lançamento = driver.findElement(By.id("OK_LANCAMENTO"));
			ok_lançamento.click();	   

			
	}

	@And("clicar em finalizar")
	public void clicar_em_finalizar() {
// Verifica se está na tela de aprovação da compra
		
		//Implementar
		
// Finalizar a venda
		WebElement ok_confirmacao = driver.findElement(By.id("OK_CONFIRMACAO"));
			ok_confirmacao.click();	
			

	}

	@Then("a venda devera ser efetuada e apresentado o cupom")
	public void a_venda_devera_ser_efetuada_e_apresentado_o_cupom() {
		
// Valida que a venda foi efetuada com sucesso - Página do cupom
		Assert.assertEquals("Confirmação", driver.getTitle());
		
// Valida o valor da venda		
		Assert.assertEquals(true, driver.getPageSource().contains(cp_valor));
		
// Valida encargos
		Assert.assertEquals(true, driver.getPageSource().contains(cp_encargos));
		
// Valida o valor total
		Assert.assertEquals(true, driver.getPageSource().contains(cp_valor_total));
		driver.quit();
	}

}
