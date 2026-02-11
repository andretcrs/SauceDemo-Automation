<h1>🛒 SauceDemo Automation Challenge</h1>

<h2> Sobre o Projeto</h2>
<p>
Este repositório contém a automação do fluxo de checkout do site
<strong>Swag Labs (SauceDemo)</strong>. O projeto foi desenvolvido utilizando
<strong>Java</strong> e <strong>Selenium WebDriver</strong>, aplicando as melhores práticas
de engenharia de software e o padrão <strong>Page Object Model (POM)</strong>.
</p>

<p>
O objetivo principal é garantir a integridade do processo de compra,
desde o login até a confirmação do pedido, de forma resiliente e escalável.
</p>

<hr/>

<h2> Objetivos de Aprendizado &amp; Implementação</h2>
<ul>
  <li>Implementar o padrão <strong>Page Object Model (POM)</strong> para separação de responsabilidades.</li>
  <li>Gestão dinâmica de instâncias através de uma <strong>DriverFactory</strong>.</li>
  <li>Utilização do <strong>Selenium Manager</strong> para gestão automática de binários.</li>
  <li>Geração de dados sintéticos para testes com <strong>DataFaker</strong>.</li>
  <li>Criação de relatórios detalhados com <strong>Allure Report</strong>.</li>
  <li>Tratamento de esperas explícitas e resiliência de cliques com
      <strong>JavaScriptExecutor</strong>.
  </li>
</ul>

<hr/>

<h2> Tecnologias Utilizadas</h2>
<ul>
  <li><strong>Java 21</strong></li>
  <li><strong>Selenium WebDriver 4</strong></li>
  <li><strong>TestNG</strong> (Framework de Testes)</li>
  <li><strong>Maven</strong> (Gerenciador de Dependências)</li>
  <li><strong>Allure Report</strong> (Relatórios de Execução)</li>
  <li><strong>DataFaker</strong> (Massa de dados dinâmica)</li>
  <li><strong>ChromeOptions</strong> (Tuning de navegador)</li>
</ul>

<hr/>

<h2> Estrutura do Projeto</h2>
<p>
A organização segue a separação entre código de suporte (<code>main</code>)
e scripts de teste (<code>test</code>):
</p>

<pre>
<code>
src
├── main
│   └── java/br.com.desafio
│       ├── factory/      # DriverManager: Gestão do ciclo de vida do Driver
│       ├── pages/        # Page Objects: Mapeamento de elementos e ações
│       └── utils/        # Helpers: Sanitização de dados e utilitários
└── test
    └── java/br.com.desafio
        ├── base/         # Setup, Teardown e Captura de Evidências (Screenshots)
        └── tests/        # Cenários de Teste (Login, Carrinho, Checkout)
</code>
</pre>

<hr/>

<h2> Soluções de Engenharia Aplicadas</h2>
<ul>
  <li>
    <strong>BasePage</strong>: Centraliza o uso de <code>WebDriverWait</code> e
    <code>JavascriptExecutor</code>, garantindo que a automação interaja com elementos
    mesmo em caso de sobreposições leves da UI.
  </li>
  <li>
    <strong>Anti-Popups</strong>: Configuração de <code>ChromeOptions</code> para desativar
    o <em>SafeBrowsingPasswordCheck</em>, evitando que alertas nativos do Chrome
    bloqueiem a execução.
  </li>
  <li>
    <strong>Sanitização de Dados</strong>: Uso de <code>Regex</code> para limpar strings
    e garantir que campos como CEP recebam apenas valores numéricos,
    respeitando as máscaras do site.
  </li>
  <li>
    <strong>Evidências Automáticas</strong>: Captura automática de screenshots
    através do <code>BaseTest</code> sempre que um teste falha.
  </li>
</ul>

<hr/>

<h2> Pré-requisitos</h2>
<ul>
  <li>Java 21 ou superior instalado</li>
  <li>Maven configurado no <code>PATH</code></li>
  <li>Google Chrome instalado</li>
  <li>Allure Commandline instalado para visualização dos relatórios</li>
</ul>

<hr/>

<h2> Executando os Testes</h2>

<p><strong>Clone o repositório:</strong></p>
<pre>
<code>
git clone &lt;url-do-seu-repositorio&gt;
</code>
</pre>

<p><strong>Execute os testes via Maven:</strong></p>
<pre>
<code>
mvn clean test
</code>
</pre>

<hr/>

<h2> Relatórios com Allure</h2>
<p>Para visualizar os resultados com gráficos e capturas de tela das falhas:</p>

<p><strong>Gerar e abrir em tempo real:</strong></p>
<pre>
<code>
allure serve allure-results
</code>
</pre>

<p><strong>Exportar relatório estático:</strong></p>
<pre>
<code>
allure generate allure-results --clean -o allure-report
allure open allure-report
</code>
</pre>

<hr/>

<h2> Boas Práticas Aplicadas</h2>
<ul>
  <li><strong>POM (Page Object Model)</strong>: Facilidade de manutenção</li>
  <li><strong>Singleton / Factory</strong>: Controle rigoroso da instância do navegador</li>
  <li><strong>Data-Driven Basics</strong>: Dados aleatórios para evitar testes viciados</li>
  <li><strong>Clean Code</strong>: Métodos com responsabilidade única e nomes semânticos</li>
</ul>
