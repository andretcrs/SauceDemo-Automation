package br.com.desafio.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // 1. Desabilita as funcionalidades de segurança que geram o pop-up da imagem
            // PasswordLeakDetection é a flag específica para o aviso de senha vazada
            options.addArguments("--disable-features=SafeBrowsingPasswordCheck,PasswordLeakDetection,SafeBrowsing");
            options.addArguments("--disable-safebrowsing");
            options.addArguments("--remote-allow-origins=*");

            // 2. Configurações de preferências para desativar o gerenciador de senhas do perfil
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("password_manager_leak_detection", false);
            // Desabilita pop-ups de geolocalização, notificações, etc.
            prefs.put("profile.default_content_setting_values.notifications", 2);

            options.setExperimentalOption("prefs", prefs);

            // 3. Evita que o Chrome "lembre" do estado anterior entre os testes
            options.addArguments("--incognito"); // Modo anônimo é crucial para não acumular lixo de sessão
            options.addArguments("--no-first-run");
            options.addArguments("--no-default-browser-check");

            // 4. Limpa a flag de automação para o navegador não se comportar de forma "estranha"
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);

            if ("true".equals(System.getProperty("headless"))) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies();
                driver.quit();
            } catch (Exception e) {
            } finally {
                driver = null;
            }
        }
    }
}