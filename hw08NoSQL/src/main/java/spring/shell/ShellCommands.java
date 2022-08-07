package spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import spring.service.IOService;
import spring.service.LibraryService;

@ShellComponent
public class ShellCommands {

    public ShellCommands(LibraryService libraryService, IOService ioService) {
        this.libraryService = libraryService;
        this.ioService = ioService;
    }

    private final LibraryService libraryService;
    private final IOService ioService;
    @ShellMethod(value = "addBook", key = {"addBook", "ab"})
    public String addBook() {
        var bookName = ioService.readLn("Введите название книги");
        var authorId = ioService.readLn("Введите ID автора");
        var genreID = ioService.readLn("Введите ID жанра");
        libraryService.insertBook(bookName, authorId, genreID);
        return "Книга " + "добавлена";
    }

    @ShellMethod(value = "addComment", key = {"addComment", "ac"})
    public String addComment() {
        var bookId = ioService.readLn("Введите ID книги для добавления комментария");
        var commentName = ioService.readLn("Введите комментарий");
        libraryService.addComment(bookId, commentName);
        return "Комментарий " + "добавлен";
    }

    @ShellMethod(value = "getAll", key = {"getAll", "ga"})
    public void getAll() {
        libraryService.getAll();
    }
    @ShellMethod(value = "getAllComments", key = {"getAllComments", "gac"})
    public void getCommentByBookId() {
        var bookId = ioService.readLn("Введите Id книги");
        libraryService.getCommentsByBookId(bookId);
    }

    @ShellMethod(value = "getById", key = {"getId", "gi"})
    public void getId() {
        var id = ioService.readLn("Введите идентификатор книги");
        libraryService.getById(id);
    }

    @ShellMethod(value = "deleteById", key = {"delId", "di"})
    public void delId() {
        var id = ioService.readLn("Введите идентификатор удалямой книги");
        libraryService.deleteById(id);
    }

    @ShellMethod(value = "addAuthor", key = {"adda", "aa"})
    public void addAuthor() {
        var authorName = ioService.readLn("Введите имя автора");
        libraryService.addAuthor(authorName);
    }
    @ShellMethod(value = "delAuthor", key = {"dela", "da"})
    public void delAuthor() {
        libraryService.delAuthor();
    }

    @ShellMethod(value = "addGenre", key = {"addg", "ag"})
    public void addGenre() {
        libraryService.addGenre();
    }
    @ShellMethod(value = "deleteComment", key = {"delcomment", "dc"})
    public void deleteComment() {
        var id = ioService.readLn("Введите идентификатор удаляемого комментария");
        libraryService.delComment(id);
    }
}
