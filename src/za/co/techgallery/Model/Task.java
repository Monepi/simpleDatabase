package za.co.techgallery.Model;

import lombok.Data;

import java.util.Date;

@Data
public class Task {
    private Long id;
    private String description;
    private Date dueDate;
}
