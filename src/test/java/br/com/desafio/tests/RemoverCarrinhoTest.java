package br.com.desafio.tests;

import br.com.desafio.base.BaseTest;
import br.com.desafio.pages.LoginPage;
import br.com.desafio.pages.ProdutosPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class RemoverCarrinhoTest extends BaseTest {

    @Test(description = "Validar a remoção de um item do carrinho na tela de listagem")
    @Severity(SeverityLevel.NORMAL)
    @Description("Este teste verifica se, ao clicar em 'Remove', o contador do carrinho desaparece.")
    public void deveRemoverItemDoCarrinhoComSucesso() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.realizarLogin("standard_user", "secret_sauce");

        ProdutosPage produtosPage = new ProdutosPage(driver);
        String nomeProduto = "Sauce Labs Backpack";

        produtosPage.adicionarAoCarrinho(nomeProduto);

        Assert.assertEquals(produtosPage.obterQuantidadeCarrinho(), "1",
                "O item deveria ter sido adicionado ao carrinho antes da remoção.");

        produtosPage.removerDoCarrinho(nomeProduto);

        Assert.assertTrue(produtosPage.carrinhoEstaVazio(),
                "O ícone do carrinho ainda exibe um número, mas deveria estar vazio.");
    }
}