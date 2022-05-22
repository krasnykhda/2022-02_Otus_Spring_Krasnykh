package spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "question-source")
public class AppSettings {
    private final String fileName;
    private final String language;
    private final String languageTag;
    private final int numberOfCorrectAnswers;

    public AppSettings(String fileName, String language, String languageTag, int numberOfCorrectAnswers) {
        this.fileName = fileName;
        this.language = language;
        this.languageTag = languageTag;
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
    }

    public String getFileNameWithLanguage() {
        return fileName.substring(0, fileName.length() - 4)
                + "_" + language
                + fileName.substring(this.fileName.length() - 4);


    }

    public String getLanguageTag() {
        return languageTag;
    }

    public int getNumberOfCorrectAnswers() {
        return numberOfCorrectAnswers;
    }
}
