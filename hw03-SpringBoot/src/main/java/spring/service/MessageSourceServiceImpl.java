package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
public class MessageSourceServiceImpl implements MessageSourceService{

    @Autowired
    private final MessageSource messageSource;

    private final String LanguageTag;

    private Locale locale;

    public MessageSourceServiceImpl(MessageSource messageSource,@Value("${question-source.localization.LanguageTag}") String languageTag) {
        this.messageSource = messageSource;
        this.LanguageTag = languageTag;
        this.locale = Locale.forLanguageTag(LanguageTag);
    }

    @Nullable
    public String getMessage(String code, @Nullable Object[] args) {
        return this.messageSource.getMessage(code, args, this.locale);
    }

}
