package spring.service;

import org.springframework.lang.Nullable;

public interface MessageSourceService {


    String getMessage(String code,  Object...args);

}
