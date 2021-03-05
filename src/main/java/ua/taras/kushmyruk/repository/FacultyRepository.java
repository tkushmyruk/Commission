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
    @Query("SELECT f FROM Faculty f LEFT JOIN FETCH f.requiredDisciplines LEFT JOIN FETCH f.facultyAddress WHERE f.facultyName = :facultyName")
    Faculty findByFacultyName(@Param("facultyName") String facultyName);

    List<Faculty> findByOrderByFacultyNameAsc();

    List<Faculty> findByOrderByFreePlacesAsc();




}
