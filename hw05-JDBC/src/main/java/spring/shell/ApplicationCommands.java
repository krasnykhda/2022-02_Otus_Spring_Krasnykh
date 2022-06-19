package spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import spring.service.AppRunner;


import java.util.ArrayList;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final AppRunner appRunner;

    @ShellMethod(value = "addBook", key = {"addBook", "ab"})
    public void addBook() {
        appRunner.insertBook();
    }


}
