package basicSyntax.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.timeout}")
    private int timeout;

    public void printConfig() {
        System.out.println(appName);
        System.out.println(timeout);
    }
}
