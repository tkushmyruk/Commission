package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.taras.kushmyruk.model.Faculty;
import ua.taras.kushmyruk.model.StudentOrder;

import java.util.List;

@Repository
public interface FacultyRepository  extends JpaRepository<Faculty, String> {
    Faculty findByFacultyName(String facultyName);


    List<Faculty> findByOrderByFacultyNameAsc();


    List<Faculty> findByOrderByFreePlacesAsc();



}
