import com.sun.source.tree.AssertTree;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

@TestInstance(Lifecycle.PER_CLASS)
public class NewPageTest {

  WebDriver webdriver;

  @BeforeAll
  public void setupAll(){
    System.setProperty("webdriver.chrome.driver",
        "src/test/resources/chromedriver.exe");
  }

  @BeforeEach
  public void setup(){
    webdriver = new ChromeDriver();
    webdriver.manage().window().maximize();
  }

  @AfterEach
  public void closeDriver(){
    webdriver.close();
  }


  @Test
  public void openNewPage(){
    webdriver.get("https://www.google.com/");
    Assertions.assertEquals("https://www.google.com/",
        webdriver.getCurrentUrl());
  }

  @Test
  public void openFacebookPage(){
    webdriver.get("https://fescfafic.edu.br/");
    WebElement botao = webdriver.findElement(
        By.xpath("//*[@id=\"wrap\"]/div/div/section[1]/div/div/div[2]/div/div/div[2]/div/div/span[1]/a/i"));
    botao.click();
  }

  @Test
  public void openFacebookPageUnicap(){
    webdriver.get("https://globo.com");
    WebElement botaoFB = webdriver.findElement(By.xpath("//*[@id=\"header-section\"]/div[1]/div[4]/div[2]/a[3]"));
    botaoFB.click();
    Assertions.assertEquals("https://valor.globo.com/?utm_source=globo.com&utm_medium=header",
        webdriver.getCurrentUrl());
  }

  @Test
  public void filtraResultadosPaginaTest(){
    webdriver.get("http://tede2.unicap.br:8080/browse?type=dateissued");
    WebElement objetoSelect = webdriver.findElement(By.xpath("//*[@id=\"browse_controls\"]/form/select[3]"));
    Select select1 = new Select(objetoSelect);
    select1.selectByIndex(0);
    WebElement botaoEnviar = webdriver.findElement(By.xpath("//*[@id=\"browse_controls\"]/form/input[2]"));
    botaoEnviar.submit();
    WebElement tabela = webdriver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[3]/table/tbody"));
    List<WebElement> linhas = tabela.findElements(By.xpath("./tr"));
    Assertions.assertEquals(5, linhas.size()-1);
  }

  @Test
  public void openPaginaFantastico (){
    webdriver.get("https://globo.com");

    WebElement search = webdriver.findElement(By.id("header-search-input"));

    search.sendKeys("fantastico");
    search.submit();
  }

  @Test
  public void abrirPaginaTest2(){
    webdriver.get("https://globo.com");
    Actions actions = new Actions(webdriver);
    WebElement botao = webdriver.findElement(
        By.xpath("//*[@id=\"header-section\"]/div[1]/div[4]/div[2]/a[4]"));
    actions.moveToElement(botao).perform();
  }

  @Test
  public void openInstagramPage(){
    webdriver.get("https://portal.unicap.br/");
    WebElement facebookPageButton = webdriver.findElement(By.id("fragment-exym-01-link"));
    facebookPageButton.click();
    Assertions.assertEquals("https://www.instagram.com/faculdadefafic/?hl=pt-br", webdriver.getCurrentUrl());
    webdriver.close();
  }

  @Test
  public void tooltipTest(){
    webdriver.get("https://www.globo.com/");
    Actions actions = new Actions(webdriver);

    WebElement ge = webdriver.findElement(By.xpath("//*[@id=\"header-section\"]/div/div[4]/div[2]/a[4]"));
    Assertions.assertEquals("esporte", ge.getAttribute("title"));
    actions.moveToElement(ge).perform();
    Assertions.assertTrue(ge.isEnabled());
  }

  @Test
  public void abrirPaginaTest(){
    webdriver.get("http://tede2.unicap.br:8080/browse?type=dateissued");
    WebElement botaoSelect = webdriver.findElement(By.xpath("/html/body/main/div[3]/div[2]/form/select[3]"));
    Select select = new Select(botaoSelect);
    select.selectByIndex(0);
    WebElement botaoSubmit = webdriver.findElement(By.xpath("//*[@id=\"browse_controls\"]/form/input[2]"));
    botaoSubmit.submit();

    WebElement tabela = webdriver.findElement(By.xpath("/html/body/main/div[3]/div[3]/table/tbody"));
    List<WebElement> elements = tabela.findElements(By.xpath("./tr"));

    Assertions.assertEquals(6, elements.size());
  }



}
