package ru.duzhinsky.lockbox.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import ru.duzhinsky.lockbox.SecretPayloadManager;
import yandex.cloud.sdk.auth.provider.CredentialProvider;

public class SecretAnnotationProcessor implements BeanPostProcessor {

    private final SecretPayloadManager secretPayloadManager;

    private final CredentialProvider credentialProvider;

    @Autowired
    public SecretAnnotationProcessor(
        SecretPayloadManager secretPayloadManager,
        CredentialProvider credentialProvider
    ) {
        this.secretPayloadManager = secretPayloadManager;
        this.credentialProvider = credentialProvider;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
        this.scanDataAccessAnnotation(bean, beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
        return bean;
    }

    protected void scanDataAccessAnnotation(Object bean, String beanName) {
        this.configureFieldInjection(bean);
    }

    private void configureFieldInjection(Object bean) {
        Class<?> managedBeanClass = bean.getClass();
        FieldCallback fieldCallback =
            new SecretFieldCallback(secretPayloadManager, credentialProvider, bean);
        ReflectionUtils.doWithFields(managedBeanClass, fieldCallback);
    }
}
