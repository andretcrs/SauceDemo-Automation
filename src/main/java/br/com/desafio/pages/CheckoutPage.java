package br.com.desafio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        escrever(campoNome, nome);
        escrever(campoSobrenome, sobrenome);
        escrever(campoCep, cep);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        esperarElementoSerClicavel(botaoContinue);
        clicarViaJS(botaoContinue);
    }


    @Step("Clicar no botão Checkout para iniciar o formulário")
    public void irParaCheckout() {
        clicar(botaoIrParaCheckout);
        esperarElementoEstarVisivel(campoNome);
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
        return driver.findElements(By.className("shopping_cart_badge")).isEmpty();
    }
}