package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InformacoesUsuarioTest {
    private WebDriver navegador;

   @Before
   public void setUp() {
       // Abrindo o navegador
       System.setProperty("webdriver.chrome.driver","C:\\Users\\Barese\\drivers\\chromedriver.exe");
       navegador = new ChromeDriver();
       navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       navegador.manage().window().maximize();

       // Navegando para a página do Taskit !
       navegador.get("http://www.juliodelima.com.br/taskit");

       // Clicar no link que possui no texto "Sign in"
       navegador.findElement(By.linkText("Sign in")).click();

       // Identificando o formulário de login
       WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));
       // Digitar no campo name "login" que está dentro do formulário de id "signinbox" o texto "barese0001"
       formularioSignInBox.findElement(By.name("login")).sendKeys("barese0001");

       // Digitar no campo name "password" que está dentro do formulário de id "signinbox" o texto "barese10"
       formularioSignInBox.findElement(By.name("password")).sendKeys("barese10");
       // Clicar no link com o texto "SIGN IN"
       navegador.findElement(By.linkText("SIGN IN")).click();

       // Clicar em um link que possui a class "me"
       navegador.findElement(By.className("me")).click();

       // Clicar em um link que possui o texto "MORE DATE ABOUT YOU"
       navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
   }

   // @Test
    public void testAdicionarUmaInformacaoAdicionalDousuario (){


       // Clicar no botão através do seu xpath "//button[@data-target="addmoredata"]
            navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

       // Identificar a popup onde está o formulário de id addmoredata
            WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

       // Na combo de name "type" escolher a opção "Phone"
            WebElement campoType = popupAddMoreData.findElement(By.name("type"));
            new Select(campoType).selectByVisibleText("Phone");

       // No campo de name "contact" digitar "+5585999222888"
            popupAddMoreData.findElement(By.name("contact")).sendKeys("5585999222888");

       // Clicar no link de text "SAVE" que está na popup
            popupAddMoreData.findElement(By.linkText("SAVE")).click();

       // Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
            WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
            String mensagem = mensagemPop.getText();
            Assert.assertEquals("Your contact has been added!", mensagem);
    }

    @Test
    public void removerUmContatoDeUmUsuario(){
       // Clicar no elemento pelo seu xpath //span[text()="55912345678"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"55912345678\"]/following-sibling::a")).click();

       // Confirmar a janela JavaScript
        navegador.switchTo().alert().accept();

       // Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals("Rest in peace, dear phone!", mensagem);

       // Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

       // Clicar no link com texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();









    }







    @After
    public void tearDown(){
        // Fechar o navegador
       // navegador.quit();
    }
}
