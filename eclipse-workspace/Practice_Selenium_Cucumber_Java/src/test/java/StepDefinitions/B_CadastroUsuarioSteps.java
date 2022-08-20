//########################### Cadastro usuário ############################

package StepDefinitions;

// Libraries
import java.time.Duration;
import java.util.Locale;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Feature: Cadastro de usuario

	

public class B_CadastroUsuarioSteps {
 
//	Scenario: Cadastro usuario com sucesso
	
//	Given operador logado no sistema
//	When acessar a tela de cadastro de usuario
//	And preencher todos os dados corretamente
//	Then o usuario devera ser cadastrado com sucesso
	
	WebDriver driver;
	String url_test = new String("http://20.0.0.54:8080/apex/f?p=466:100:");
			
	@Given("operador logado no sistema")
	public void operador_logado_no_sistema() {

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

	@When("acessar a tela de cadastro de usuario")
	public void acessar_a_tela_de_cadastro_de_usuario() {
		
// Acessa menu Produção > Cadastro > Cliente > Usuario
		WebElement producao = driver.findElement(By.id("jqmenu-17"));
			producao.click();
		WebElement cadastro = driver.findElement(By.xpath("//*[@id=\"P0_MENU_17_DISPLAY\"]/div/ul/li[2]/a/span[1]"));
			cadastro.click();		
		WebElement cliente = driver.findElement(By.xpath("//*[@id=\"P0_MENU_17_DISPLAY\"]/div/ul/li[2]/ul/li[4]/a/span[1]"));
			cliente.click();		
		WebElement usuario = driver.findElement(By.xpath("//*[@id=\"P0_MENU_17_DISPLAY\"]/div/ul/li[2]/ul/li[4]/ul/li[4]/a"));
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"P0_MENU_17_DISPLAY\"]/div/ul/li[2]/ul/li[4]/ul/li[4]/a")));
			usuario.click();
			
// Verifica se a página "Usuário - Lista" foi acessada P2060
		Assert.assertEquals("Usuário - Lista", driver.getTitle());	
		
// Clica no botão "Novo"
		WebElement btn_novo = driver.findElement(By.id("P2060_NOVO"));
	    	btn_novo.click();	
	    	
// Verifica se a página "Usuário - Validar CPF" foi acessada P2075
	    Assert.assertEquals("Usuário - Validar CPF", driver.getTitle());
	    
// Preencher CPF e clica em OK  -> CPF Fake 
	    Fake_CpfCnpjRg gerador = new Fake_CpfCnpjRg();
		String cpf = gerador.cpf(true); 
		
		driver.findElement(By.id("P2075_CPF"))
			.sendKeys(cpf);
		driver.findElement(By.id("P2075_CPF"))
			.sendKeys(Keys.RETURN);
		
// Verifica se a página "Usuário - Cadastro" foi acessada
		Assert.assertEquals("Usuário - Cadastro", driver.getTitle());
		
   	}

	@And("preencher todos os dados corretamente")
	public void preencher_todos_os_dados_corretamente() {
		
// Nome do usuário -> Nome fake aleatório
		Faker faker = new Faker(new Locale("pt-BR"));
		String name = faker.name().fullName();		
	    driver.findElement(By.id("P2062_NOME"))
	    	.sendKeys(name); // Inserindo nome fake

// Selecionando sexo	    
	    WebElement element1 =  driver.findElement(By.id("P2062_SEXO"));
	    Select combosexo = new Select(element1);
	    	combosexo.selectByIndex(1);
	    
// Data de nascimento    
	    driver.findElement(By.id("P2062_NASCIMENTO"))
	    	.sendKeys("02/07/1993");
	    
// Naturalidade	    
	    driver.findElement(By.id("P2062_NATURALIDADE"))
	    	.sendKeys("Curitiba");
	    
// ### Tratativa para aguardar o calendário "P2062_NASCIMENTO" desaparecer. ###
	    driver.findElement(By.id("P2062_ORGAO_EMISSOR"));
	    WebElement calendario1 = driver.findElement(By.id("P2062_ORGAO_EMISSOR"));
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.id("P2062_ORGAO_EMISSOR")));
			calendario1.click();
		
// Nacionalidade	    
	    WebElement element3 =  driver.findElement(By.id("P2062_NACIONALIDADE"));
	    Select nacionalidade = new Select(element3);
	    	nacionalidade.selectByIndex(1);
	    
// Número de RG -> RG Fake aleatório
	    Fake_CpfCnpjRg gerador = new Fake_CpfCnpjRg();
		String rg = gerador.rg(true);
	    driver.findElement(By.id("P2062_RG"))
	    	.sendKeys(rg);	
	    
// Seleciona UF do RG
	    WebElement element4 =  driver.findElement(By.id("P2062_UF_RG"));
	    Select uf_rg = new Select(element4);
	    	uf_rg.selectByValue("SP");
// Tipo RG 
	    driver.findElement(By.id("P2062_RG_TIPO"))
	    	.sendKeys("RG");
	    
// Orgão emissor    
	    WebElement element5 =  driver.findElement(By.id("P2062_ORGAO_EMISSOR"));
	    Select orgao_emissor = new Select(element5);
	    	orgao_emissor.selectByIndex(6);
	    	
// Data Emissão	    
	    driver.findElement(By.id("P2062_DATA_EMISSAO"))
	    	.sendKeys("09/03/2019");
	    
// Estado Civil	    
	    WebElement element6 =  driver.findElement(By.id("P2062_ESTADO_CIVIL"));
	    Select estado_civil = new Select(element6);
	    	estado_civil.selectByValue("C");
	    
// ###  Tratativa para aguardar o calendário "P2062_DATA_EMISSAO" desaparecer.  ###	    
	    driver.findElement(By.id("P2062_NOME_RECADO"));
	    WebElement calendario2 = driver.findElement(By.id("P2062_NOME_RECADO"));
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.id("P2062_NOME_RECADO")));
			calendario2.click();
		
// Nome da mãe -> Nome fake aleatório	
		String name2 = faker.name().fullName();
		driver.findElement(By.id("P2062_FILIACAO_MATERNA"))
			.sendKeys(name2);
		
// Nome do pai -> Nome fake aleatório
		String name3 = faker.name().fullName();
		driver.findElement(By.id("P2062_FILIACAO_PATERNA"))
			.sendKeys(name3);
		
// Dados telefone residêncial		
		WebElement element7 =  driver.findElement(By.id("P2062_OPERADORA_RES"));
	    Select operadora_res = new Select(element7);
	    	operadora_res.selectByValue("1");
	    
	    driver.findElement(By.id("P2062_DDD_RES"))
	    	.sendKeys("41");
	    driver.findElement(By.id("P2062_FONE_RES"))
	    	.sendKeys("36798300");
	    
// Dados telefone celular    
		WebElement element8 =  driver.findElement(By.id("P2062_OPERADORA_CEL"));
	    Select operadora_cel = new Select(element8);
	    	operadora_cel.selectByValue("6");
	    
	    driver.findElement(By.id("P2062_DDD_CEL"))
	    	.sendKeys("42");
	    driver.findElement(By.id("P2062_FONE_CEL"))
	    	.sendKeys("988362727");
	    
// Dados telefone comercial	
	    WebElement element9 =  driver.findElement(By.id("P2062_OPERADORA_COM"));
	    Select operadora_com = new Select(element9);
	    	operadora_com.selectByValue("4");
	    
	    driver.findElement(By.id("P2062_DDD_COM"))
	    	.sendKeys("45");
	    driver.findElement(By.id("P2062_FONE_COM"))
	    	.sendKeys("36547800");
	    
// Dados telefone de recado
	    WebElement element10 =  driver.findElement(By.id("P2062_OPERADORA_REC"));
	    Select operadora_rec = new Select(element10);
	    	operadora_rec.selectByValue("4");
	    
	    driver.findElement(By.id("P2062_DDD_REC"))
	    	.sendKeys("48");
	    driver.findElement(By.id("P2062_FONE_REC"))
	    	.sendKeys("36533430");
	    
// Nome Recado -> Nome fake aleatório
	    String name4 = faker.name().fullName();
	    driver.findElement(By.id("P2062_NOME_RECADO"))
	    	.sendKeys(name4);
	    
// Insere e-mail
	    driver.findElement(By.id("P2062_EMAIL"))
	    	.sendKeys("teste_automatizado@infoxnet.com.br");
	    
// Insere número do pis
	    driver.findElement(By.id("P2062_NRO_PIS"))
	    	.sendKeys("2345234770");
	    
// Desativar pagamento mínimo	    
	    WebElement element11 =  driver.findElement(By.id("P2062_DESATIVAR_PAGTO_MIN"));
	    Select pgto_min = new Select(element11);
	    	pgto_min.selectByValue("1");
	    
// Bloquear envio EPC	    
	    WebElement element12 =  driver.findElement(By.id("P2062_BLOQUEIO_ENVIO_EPC"));
	    Select bloq_env_epc = new Select(element12);
	    	bloq_env_epc.selectByValue("1");
	    
// Bloquear saque   
	    WebElement element13 =  driver.findElement(By.id("P2062_BLOQUEAR_SAQUE"));
	    Select bloq_saq = new Select(element13);
	    	bloq_saq.selectByValue("1");
	    
// Bloquear pagamento de contas
	    WebElement element14 =  driver.findElement(By.id("P2062_BLOQUEAR_PAGTO_CONTAS"));
	    Select bloq_pgto_conta = new Select(element14);
	    	bloq_pgto_conta.selectByValue("1");
	    
// Bloquear empréstimo pessoal
	    WebElement element15 =  driver.findElement(By.id("P2062_BLOQUEAR_EMP_PESSOAL"));
	    Select bloq_emp_pes = new Select(element15);
	    	bloq_emp_pes.selectByValue("1");
	    
// Optante por fatura digital
	    WebElement element16 =  driver.findElement(By.id("P2062_OPT_FATURA_DIGITAL"));
	    Select opt_fat_dig = new Select(element16);
	    	opt_fat_dig.selectByValue("1");
	    
// Grau de instrução
	    WebElement element17 =  driver.findElement(By.id("P2062_GRAU_INSTRUCAO"));
	    Select grau_inst = new Select(element17);
	    	grau_inst.selectByValue("2");
	    WebElement finaliza = driver.findElement(By.id("OK"));
	    	finaliza.click();
	}

	@Then("o usuario devera ser cadastrado com sucesso")
	public void o_usuario_devera_ser_cadastrado_com_sucesso() {
		
// Clica no botão "Não" no pop-up "Deseja lançar um novo usuário?"
	    WebElement user_nao = driver.findElement(By.id("button-1007"));
	    	user_nao.click();
	    	
// Verifica se a página "Usuário - Ficha" foi acessada
		Assert.assertEquals("Usuário - Ficha", driver.getTitle());
	    driver.quit();
	}
}