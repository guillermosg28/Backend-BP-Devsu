package dev.guillermosg.msclients.infrastructure.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * The type Internationalization config.
 */
@Configuration
public class InternationalizationConfig {

    /**
     * Locale resolver locale resolver.
     *
     * @return the locale resolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        Locale localeEs = new Locale("es");
        // Set default Locale as ES
        slr.setDefaultLocale(localeEs);
        return slr;
    }

    /**
     * Message source resource bundle message source.
     *
     * @return the resource bundle message source
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        // name of the resource bundle
        source.setBasenames("i18n/messages");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}
