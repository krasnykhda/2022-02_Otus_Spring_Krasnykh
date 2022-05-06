package spring.service;

import org.springframework.lang.Nullable;

public interface MessageSourceService {

    @Nullable
    String getMessage(String code, @Nullable Object[] args);

}
