import java.util.Date;
import java.util.Map;

public abstract class BaseGateway implements PaymentGateway {
    protected Map<String, String> config;
    protected String gatewayName;

    public BaseGateway(String gatewayName, Map<String, String> config) {
        this.gatewayName = gatewayName;
        this.config = config;
    }

    protected void log(String message) {
        System.out.println("[" + new Date() + "] [" + gatewayName + "] " + message);
    }

}
