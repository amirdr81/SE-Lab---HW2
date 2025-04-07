import java.util.Date;

public abstract class BaseGateway implements PaymentGateway {
    protected ConfigurationManager configManager;
    protected String gatewayName;

    public BaseGateway(String gatewayName, ConfigurationManager configManager) {
        this.gatewayName = gatewayName;
        this.configManager = configManager;
    }

    protected void log(String message) {
        System.out.println("[" + new Date() + "] [" + gatewayName + "] " + message);
    }
}
