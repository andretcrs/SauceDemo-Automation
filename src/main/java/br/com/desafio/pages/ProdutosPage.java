package br.com.desafio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutosPage extends BasePage {

    private By tituloPagina = By.className("title");
    private By filtroOrdenacao = By.className("product_sort_container");
    private By precosProdutos = By.className("inventory_item_price");
    private By itensInventario = By.className("inventory_item");
    private By iconeCarrinhoBadge = By.className("shopping_cart_badge");
    private By linkCarrinho = By.className("shopping_cart_link");

    public ProdutosPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validar se a página de produtos carregou")
    public String obterTitulo() {
        return obterTexto(tituloPagina);
    }

    @Step("Ordenar produtos por: {0}")
    public void ordenarProdutos(String opcaoVisivel) {
        WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(filtroOrdenacao));
        Select select = new Select(dropdown);
        select.selectByVisibleText(opcaoVisivel);
    }

    @Step("Obter lista de preços de todos os produtos")
    public List<Double> obterListaDePrecos() {
        List<WebElement> elementosPreco = driver.findElements(precosProdutos);
        return elementosPreco.stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    @Step("Verificar se todos os produtos estão visíveis")
    public int contarProdutos() {
        return driver.findElements(itensInventario).size();
    }

    @Step("Adicionar o produto {0} ao carrinho")
    public void adicionarAoCarrinho(String nomeProduto) {
        String idDinamico = "add-to-cart-" + nomeProduto.toLowerCase().replace(" ", "-");
        By locatorAdd = By.id(idDinamico);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locatorAdd)).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(iconeCarrinhoBadge));
    }

    @Step("Remover o produto {0} do carrinho")
    public void removerDoCarrinho(String nomeProduto) {
        String idDinamico = "remove-" + nomeProduto.toLowerCase().replace(" ", "-");
        By locatorRemove = By.id(idDinamico);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locatorRemove));
        clicarViaJS(locatorRemove);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(iconeCarrinhoBadge));
    }

    @Step("Obter quantidade de itens no ícone do carrinho")
    public String obterQuantidadeCarrinho() {
        List<WebElement> elementos = driver.findElements(iconeCarrinhoBadge);
        return elementos.isEmpty() ? "0" : elementos.get(0).getText();
    }

    @Step("Verificar se o badge do carrinho sumiu (vazio)")
    public boolean carrinhoEstaVazio() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(iconeCarrinhoBadge));
        } catch (Exception e) {
            return driver.findElements(iconeCarrinhoBadge).isEmpty();
        }
    }

    @Step("Clicar no ícone do carrinho para visualizar itens")
    public void irParaOCarrinho() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(linkCarrinho)).click();


        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("checkout")));
    }
    @Step("Adicionar um produto qualquer ao carrinho")
    public void adicionarQualquerProdutoAoCarrinho() {
        By primeiroProduto = By.xpath("(//button[text()='Add to cart'])[1]");

        esperarElementoEstarVisivel(primeiroProduto);
        driver.findElement(primeiroProduto).click();
    }
}