package spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import spring.service.LibraryService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final LibraryService libraryService;

    @ShellMethod(value = "addBook", key = {"addBook", "ab"})
    public String addBook() {
        libraryService.insertBook();
        return "Книга " + "добавлена";
    }
    @ShellMethod(value = "addComment", key = {"addComment", "ac"})
    public String addComment() {
        libraryService.addComment();
        return "Комментарий " + "добавлен";
    }

    @ShellMethod(value = "getAll", key = {"getAll", "ga"})
    public void getAll() {
        libraryService.getAll();
    }

    @ShellMethod(value = "getById", key = {"getId", "gi"})
    public void getId() {
        libraryService.getById();
    }

    @ShellMethod(value = "deleteById", key = {"delId", "di"})
    public void delId() {
        libraryService.deleteById();
    }
}
