import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    
    static WebDriver driver = null;  
    private static String htmlFile = "C:\\Users\\pedro.sarmento\\Repository\\QPPSW\\Trabalho2\\src\\index.html";      
    private static String indexPage; 
    
    @BeforeClass
    public static void setUp() {               
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pedro.sarmento\\Repository\\QPPSW\\chromedriver.exe");           
        driver = new ChromeDriver();                
        driver.get(htmlFile);
        indexPage = driver.getWindowHandle();
    }    
    
    @Test
    public void testTituloPagina(){        
        String titulo = driver.getTitle();
        String tituloExperado = "Trabalho 2-1";
        Assert.assertEquals(titulo, tituloExperado);
    }
    
    @Test
    public void testCamposInvalidos(){            
        WebElement nomeValue = driver.findElement(By.id("nome"));
        WebElement enderecoValue = driver.findElement(By.id("endereco"));
        WebElement sexoValue = driver.findElement(By.id("sexo"));
        WebElement idadeValue = driver.findElement(By.id("idade"));               
        //
        WebElement salvarBtn = driver.findElement(By.id("botao_salvar"));
        WebElement resultado = driver.findElement(By.id("resultado"));
        
        //Errors
        String errorNome = "Preencha o campo nome";
        String errorEndereco = "Preencha o campo endereco";
        String errorSexo = "Selecione um valor para o campo sexo";
        String errorIdadeEmpty = "Preencha o campo idade, somente com numeros";
        String errorIdadeType = "Preencha o campo idade com valores acima de 0";
        
        //Test Nome
        nomeValue.sendKeys("");
        salvarBtn.click();                             
        Assert.assertEquals(errorNome, resultado.getText());
        
        //Test Endereço
        nomeValue.sendKeys("a");
        enderecoValue.sendKeys("");
        salvarBtn.click();                             
        Assert.assertEquals(errorEndereco, resultado.getText());
        
        //Test Sexo        
        enderecoValue.sendKeys("a");
        sexoValue.sendKeys("...");
        salvarBtn.click();                             
        Assert.assertEquals(errorSexo, resultado.getText());
        
        //Test Idade Vazia        
        sexoValue.sendKeys("m");
        idadeValue.sendKeys("");
        salvarBtn.click();                             
        Assert.assertEquals(errorIdadeEmpty, resultado.getText());  
        
        //Test Idade < 0                
        idadeValue.sendKeys("-1");
        salvarBtn.click();                             
        Assert.assertEquals(errorIdadeType, resultado.getText());         
    }
    
    @Test
    public void testCamposValidos(){       
        WebElement nomeValue = driver.findElement(By.id("nome"));
        WebElement enderecoValue = driver.findElement(By.id("endereco"));
        WebElement sexoValue = driver.findElement(By.id("sexo"));
        WebElement idadeValue = driver.findElement(By.id("idade"));               
        //
        WebElement salvarBtn = driver.findElement(By.id("botao_salvar"));                                   
        
        //Clear
        nomeValue.clear();
        enderecoValue.clear();
        sexoValue.sendKeys("...");
        idadeValue.clear();
        
        //Keys
        nomeValue.sendKeys("Pedro");
        enderecoValue.sendKeys("Rio Claro");
        sexoValue.sendKeys("m");
        idadeValue.sendKeys("19");        
        salvarBtn.click();                                    
        
        //         
        String textAlert =  driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("Cadastro realizado com sucesso", textAlert);
    }
    
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }    
}