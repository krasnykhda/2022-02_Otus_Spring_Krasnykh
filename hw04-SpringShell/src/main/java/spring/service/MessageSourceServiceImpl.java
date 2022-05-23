package spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class MessageSourceServiceImpl implements MessageSourceService {


    private final MessageSource messageSource;
    private final Locale locale;

    public MessageSourceServiceImpl(MessageSource messageSource, @Value("${question-source.localization.LanguageTag}") String languageTag) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(languageTag);
    }

    public String getMessage(String code, Object... args) {
        return this.messageSource.getMessage(code, args, this.locale);
    }

}
