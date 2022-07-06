package spring.shell;

import lombok.RequiredArgsConstructor;
import org.h2.tools.Console;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import spring.service.AppRunner;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final AppRunner appRunner;

    @ShellMethod(value = "addBook", key = {"addBook", "ab"})
    public String addBook() {
        appRunner.insertBook();
        return "Книга " + "добавлена";
    }

    @ShellMethod(value = "getAll", key = {"getAll", "ga"})
    public void getAll() {
        appRunner.getAll();
    }

    @ShellMethod(value = "getById", key = {"getId", "gi"})
    public void getId() {
        appRunner.getById();
    }

    @ShellMethod(value = "deleteById", key = {"delId", "di"})
    public void delId() {
        appRunner.deleteById();
    }
}
