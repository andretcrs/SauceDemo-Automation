package br.com.desafio.tests;

import br.com.desafio.base.BaseTest;
import br.com.desafio.pages.CheckoutPage;
import br.com.desafio.pages.LoginPage;
import br.com.desafio.pages.ProdutosPage;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Locale;

public class CheckoutTest extends BaseTest {

    private Faker faker = new Faker(new Locale("pt-BR"));

    @Test(description = "Validar finalização de compra com sucesso")
    public void deveFinalizarCompraComSucesso() {
        new LoginPage(driver).realizarLogin("standard_user", "secret_sauce");

        ProdutosPage produtosPage = new ProdutosPage(driver);
        produtosPage.adicionarAoCarrinho("Sauce Labs Backpack");

        produtosPage.irParaOCarrinho();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.irParaCheckout();

        String cepLimpo = faker.address().zipCode().replaceAll("[^0-9]", "");

        checkoutPage.preencherInformacoes(
                faker.name().firstName(),
                faker.name().lastName(),
                cepLimpo
        );
        checkoutPage.finalizarCompra();

        String mensagemSucesso = checkoutPage.obterMensagemSucesso();
        Assert.assertEquals(mensagemSucesso, "Thank you for your order!",
                "O checkout não foi finalizado. Verifique se o botão Finish foi clicado.");
    }

    @Test(description = "Validar erro ao tentar avançar sem preencher o sobrenome")
    public void deveExibirErroAoFaltarSobrenome() {
        new LoginPage(driver).realizarLogin("standard_user", "secret_sauce");

        driver.get("https://www.saucedemo.com/checkout-step-one.html");

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        String nome = faker.name().firstName();

        checkoutPage.preencherInformacoes(nome, "", "12345");

        String erro = checkoutPage.obterMensagemErro();

        Assert.assertTrue(erro.contains("Last Name is required"),
                "A mensagem de erro esperada não apareceu. Encontrado: " + erro);
    }
}