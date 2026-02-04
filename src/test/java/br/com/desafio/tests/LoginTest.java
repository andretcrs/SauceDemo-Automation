package br.com.desafio.tests;

import br.com.desafio.base.BaseTest;
import br.com.desafio.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginTest extends BaseTest {

    @Test(description = "Validar login com dados válidos")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validar se o usuário consegue realizar login com sucesso.")
    public void deveLogarComSucesso() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "A URL não condiz com a página de produtos após o login.");
    }

    @Test(description = "Validar login com dados inválidos")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validar mensagem de erro ao tentar logar com dados inválidos")
    public void deveExibirErroComSenhaInvalida() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.realizarLogin("standard_user", "senha_errada");

        String erro = loginPage.obterMensagemErro();
        Assert.assertTrue(erro.contains("Username and password do not match"),
                "Mensagem de erro incorreta ou não exibida.");
    }
}