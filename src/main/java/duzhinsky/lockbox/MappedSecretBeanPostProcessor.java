package duzhinsky.lockbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.IOException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MappedSecretBeanPostProcessor implements BeanPostProcessor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final PayloadService payloadService;

    public MappedSecretBeanPostProcessor(PayloadService payloadService) {
        this.payloadService = payloadService;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
        if (bean.getClass().isAnnotationPresent(MappedSecret.class)) {
            MappedSecret secret = bean.getClass().getAnnotation(MappedSecret.class);
            String payload = payloadService.getSecretText(secret.secretId(), secret.version(), secret.key());
            ObjectReader reader = objectMapper.readerForUpdating(bean);
            try {
                reader.readValue(payload);
            } catch (IOException e) {
                throw new BeansException("Failed to map lockbox secret value", e) {};
            }
        }
        return bean;
    }


}
