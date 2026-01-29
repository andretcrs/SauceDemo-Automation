package br.com.desafio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class LoginPage extends br.com.desafio.pages.BasePage {

    private By campoUsuario = By.id("user-name");
    private By campoSenha   = By.id("password");
    private By botaoLogin   = By.id("login-button");
    private By mensagemErro  = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Realizar login com o usuário {0}")
    public void realizarLogin(String usuario, String senha) {
        escrever(campoUsuario, usuario);
        escrever(campoSenha, senha);
        clicar(botaoLogin);
    }

    @Step("Validar se a mensagem de erro está visível")
    public String obterMensagemErro() {
        return obterTexto(mensagemErro);
    }
}