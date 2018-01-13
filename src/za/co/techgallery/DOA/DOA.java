package za.co.techgallery.DOA;


import java.time.LocalDateTime;
import java.util.List;

public interface DOA {
    List<?> fetchAll();
    List<?> fetchById(Long id);
    boolean update(Long id, String description, LocalDateTime dueDate);
    boolean deleteById(Long id);
    boolean delete();
}
