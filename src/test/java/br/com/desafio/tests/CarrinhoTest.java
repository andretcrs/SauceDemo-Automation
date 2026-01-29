package br.com.desafio.tests;

import br.com.desafio.base.BaseTest;
import br.com.desafio.pages.LoginPage;
import br.com.desafio.pages.ProdutosPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class CarrinhoTest extends BaseTest {

    @Test(description = "Validar que o contador do carrinho incrementa ao adicionar um produto")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Este teste verifica se, ao clicar em 'Add to Cart', o ícone do carrinho exibe a quantidade correta de itens.")
    public void deveAdicionarItemAoCarrinhoComSucesso() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.realizarLogin("standard_user", "secret_sauce");

        ProdutosPage produtosPage = new ProdutosPage(driver);

        String nomeProduto = "Sauce Labs Bolt T-Shirt";
        produtosPage.adicionarAoCarrinho(nomeProduto);

        String quantidadeNoCarrinho = produtosPage.obterQuantidadeCarrinho();
        Assert.assertEquals(quantidadeNoCarrinho, "1", "A quantidade no badge do carrinho está incorreta!");

    }
}