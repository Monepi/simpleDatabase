package za.co.techgallery;

import za.co.techgallery.DOA.TaskDOA;
import za.co.techgallery.Model.Task;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        TaskDOA taskDOA = new TaskDOA();
        List<Task> tasks = taskDOA.fetchAll();

        for (Task task: tasks) {
            System.out.println(task.toString());
        }

    }
}
