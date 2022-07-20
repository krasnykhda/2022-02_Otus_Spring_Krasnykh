package spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import spring.service.IOService;
import spring.service.LibraryService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private final LibraryService libraryService;
    private final IOService ioService;
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
    @ShellMethod(value = "getCommentsByBookId", key = {"getCommentsByBookId", "gc"})
    public void getCommentsByBookId() {
        var id = ioService.readLn("Введите идентификатор книги");
        libraryService.getCommentsByBookId(Long.parseLong(id));
    }

    @ShellMethod(value = "getById", key = {"getId", "gi"})
    public void getId() {
        var id = ioService.readLn("Введите идентификатор книги");
        libraryService.getById(Long.parseLong(id));
    }

    @ShellMethod(value = "deleteById", key = {"delId", "di"})
    public void delId() {
        var id = ioService.readLn("Введите идентификатор удаляемой книги");
        libraryService.deleteById(Long.parseLong(id));
    }

    @ShellMethod(value = "addAuthor2", key = {"adda", "aa"})
    public void addAuthor2() {
        libraryService.addAuthor();
    }

    @ShellMethod(value = "addGenre", key = {"addg", "ag"})
    public void addGenre() {
        libraryService.addGenre();
    }
}
