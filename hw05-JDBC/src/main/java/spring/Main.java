package spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import spring.dao.AuthorDao;
import spring.domain.Author;
import java.sql.SQLException;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(Main.class);

        AuthorDao dao = context.getBean(AuthorDao.class);

        System.out.println("All count " + dao.count());

        dao.insert(new Author(2, "ivan"));

        System.out.println("All count " + dao.count());

        Author ivan = dao.getById(2);

        System.out.println("Ivan id: " + ivan.getId() + " name: " + ivan.getName());

        System.out.println(dao.getAll());

        Console.main(args);
    }
}
