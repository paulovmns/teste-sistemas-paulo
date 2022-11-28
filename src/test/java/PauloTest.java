import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@TestInstance(Lifecycle.PER_CLASS)
public class PauloTest {

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
  public void openNewPage(){  // OK
    webdriver.get("https://www.letras.mus.br/");
    Assertions.assertEquals("https://www.letras.mus.br/",
        webdriver.getCurrentUrl());
  }

  @Test
  public void openPopBrasilPlaylist(){   // OK
    webdriver.get("https://www.letras.mus.br/");
    WebElement botao = webdriver.findElement(
        By.xpath("//*[@id=\"cnt_top\"]/div[8]/div/a[2]"));
    botao.click();
  }

  @Test
  public void openSertanejoSection(){   // OK
    webdriver.get("https://www.letras.mus.br/");
    WebElement botaoFB = webdriver.findElement(By.xpath("//*[@id=\"cnt_top\"]/div[4]/div/div/a[4]"));
    botaoFB.click();
    Assertions.assertEquals("https://www.letras.mus.br/estilos/sertanejo/",
        webdriver.getCurrentUrl());
  }

  @Test
  public void escreverRockNoInputdeBusca (){   // OK
    webdriver.get("https://www.letras.mus.br/");

    WebElement search = webdriver.findElement(By.id("main_suggest"));

    search.sendKeys("rock");
  }

  @Test
  public void findElementsTest (){
    webdriver.get("https://www.letras.mus.br/"); // OK

    List<WebElement> elements = webdriver.findElements(By.className("js-tab-link"));
    System.out.println("Numero total de elementos class => js-tab-link: " +elements.size());
    Assertions.assertTrue(elements.size() < 10);

  }

  @Test
  public void retornarURLPesquisa (){  // OK
    webdriver.get("https://www.letras.mus.br/");

    WebElement search = webdriver.findElement(By.id("main_suggest"));

    search.sendKeys("forro");
    search.submit();
    String url = "https://www.letras.mus.br/?q=samba#gsc.tab=0&gsc.q=samba";
    Assertions.assertFalse(url == webdriver.getCurrentUrl());
  }

  @Test
  public void selectByIndexTest (){ // OK

    webdriver.get("https://www.lyrics.com/contact.php/");
    WebElement objetoSelect = webdriver.findElement(By.xpath("//*[@id=\"fld-subject\"]"));
    Select select1 = new Select(objetoSelect);
    select1.selectByIndex(2);
    Assertions.assertEquals("Business development", "Business development");

  }

  @Test
  public void pesquisarSambaNoInputdeBusca (){  // OK
    webdriver.get("https://www.letras.mus.br/");

    WebElement search = webdriver.findElement(By.id("main_suggest"));

    search.sendKeys("samba");
    search.submit();

  }

  @Test
  public void enviarLetraBotao(){   // OK
    webdriver.get("https://www.letras.mus.br/");
    WebElement botaoFB = webdriver.findElement(By.xpath("//*[@id=\"js-user-send\"]/a"));
    boolean condicao = botaoFB.isSelected();
    Assertions.assertFalse(condicao);
  }

  @Test
  public void passagemPeloBotaoAcademy(){  //  OK
    webdriver.get("https://www.letras.mus.br/");
    Actions actions = new Actions(webdriver);
    WebElement botao = webdriver.findElement(
        By.xpath("//*[@id=\"js-go-academy\"]/a"));
    actions.moveToElement(botao).perform();
  }

  @Test
  public void botaoFunkHabilitado(){  //  OK
    webdriver.get("https://www.letras.mus.br/");
    Actions actions = new Actions(webdriver);
    WebElement botao = webdriver.findElement(
            By.xpath("//*[@id=\"cnt_top\"]/div[4]/div/div/a[5]"));
    actions.moveToElement(botao).perform();
    Assertions.assertTrue(botao.isEnabled());
  }

  }


