package br.com.desafio.tests;

import br.com.desafio.base.BaseTest;
import br.com.desafio.pages.LoginPage;
import br.com.desafio.pages.ProdutosPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutosTest extends BaseTest {
    @Test(description = "Validar ordenação de preços do menor para o maior")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validar ordenação dos preços dos produtos do menor para o maior  ")
    public void deveOrdenarPorPrecoMenorParaMaior() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.realizarLogin("standard_user", "secret_sauce");

        ProdutosPage produtosPage = new ProdutosPage(driver);

        produtosPage.ordenarProdutos("Price (low to high)");

        List<Double> precosExibidos = produtosPage.obterListaDePrecos();

        List<Double> precosEsperados = new ArrayList<>(precosExibidos);
        Collections.sort(precosEsperados);
        Assert.assertEquals(precosExibidos, precosEsperados, "A ordenação de preços está incorreta!");
    }
}