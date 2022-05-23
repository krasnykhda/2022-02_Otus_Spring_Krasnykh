package spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import spring.service.TestService;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    private final TestService testService;
    private String userName;

    @ShellMethod(value = "Help2", key = {"h", "help2"})
    public String help() {
        List<String> commandList = new ArrayList<>();
        commandList.add("1. l or login - login user");
        commandList.add("2. run - start test");
        commandList.add("3. stat - get statistic of all tests");
        return commandList.toString();
    }

    @ShellMethod(value = "Statistic", key = {"stat", "st"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String getStat() {
        return "Данная функция будет реализована позже";
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Start test", key = {"run", "start", "go"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public void startTest() {
        testService.run(userName);
    }

    private Availability isCommandAvailable() {
        return userName == null ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }

}
