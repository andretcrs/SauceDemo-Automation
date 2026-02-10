package br.com.desafio.pages;

import org.openqa.selenium.*;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {

    private final By campoNome = By.id("first-name");
    private final By campoSobrenome = By.id("last-name");
    private final By campoCep = By.id("postal-code");
    private final By botaoContinue = By.id("continue");
    private final By botaoFinish = By.id("finish");
    private final By mensagemSucesso = By.className("complete-header");
    private final By mensagemErro = By.cssSelector("[data-test='error']");
    private final By botaoIrParaCheckout = By.id("checkout");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    protected WebElement esperarElementoSerClicavel(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Step("Preencher informações de checkout e clicar em Continue")
    public void preencherInformacoes(String nome, String sobrenome, String cep) {
        esperarElementoEstarVisivel(campoNome);

        driver.findElement(campoNome).clear();
        escrever(campoNome, nome);

        driver.findElement(campoSobrenome).clear();
        escrever(campoSobrenome, sobrenome);

        driver.findElement(campoCep).clear();
        escrever(campoCep, cep);

        clicarViaJS(botaoContinue);
    }


    @Step("Clicar no botão Checkout para iniciar o formulário")
    public void irParaCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(botaoIrParaCheckout));
        clicar(botaoIrParaCheckout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(campoNome));
    }

    @Step("Clicar no botão Finish para finalizar a compra")
    public void finalizarCompra() {
        clicarViaJS(botaoFinish);
    }

    @Step("Obter mensagem de sucesso final")
    public String obterMensagemSucesso() {
        return obterTexto(mensagemSucesso);
    }

    @Step("Obter mensagem de erro")
    public String obterMensagemErro() {
        return obterTexto(mensagemErro);
    }

    @Step("Verificar se o ícone do carrinho está vazio")
    public boolean carrinhoEstaVazio() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("shopping_cart_badge")));
        } catch (TimeoutException e) {
            return false;
        }
    }
    private By iconeCarrinho = By.className("shopping_cart_link");
    private By botaoCheckout = By.cssSelector("[data-test='checkout']");

    @Step("Clicar no ícone do carrinho")
    public void clicarNoCarrinho() {
        clicarViaJS(iconeCarrinho);
    }

    @Step("Clicar no botão Checkout")
    public void clicarBotaoCheckout() {
        clicarViaJS(botaoCheckout);
    }
}