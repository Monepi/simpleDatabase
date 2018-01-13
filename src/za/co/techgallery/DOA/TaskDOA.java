package za.co.techgallery.DOA;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import za.co.techgallery.Model.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

public class TaskDOA implements DOA{

    private Sql2o sql2o = null;

    public TaskDOA() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");
            prop.load(input);

            this.sql2o = new Sql2o(
                    prop.getProperty("dataSource"),
                    prop.getProperty("dataSourceUsername"),
                    prop.getProperty("dataSourcePassword")
            );

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public List<Task> fetchAll() {
        String sql = "SELECT id, description, duedate FROM tasks";

        try(Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Task.class);
        }
    }

    @Override
    public List<Task> fetchById(Long id) {
        String sql = "SELECT id, description, duedate FROM tasks WHERE id = :id";

        try(Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Task.class);
        }
    }

    @Override
    public boolean update(Long id, String description, LocalDateTime dueDate) {
        String sql = "update tasks set description = :description, duedate = :dueDate where id = :id";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("description", description)
                    .addParameter("dueDate", dueDate)
                    .addParameter("id", id)
                    .executeUpdate();
            return true;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "delete from tasks where id = :id";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            return true;
        }
    }

    @Override
    public boolean delete() {
        String sql = "delete from tasks";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
            return true;
        }
    }
}
