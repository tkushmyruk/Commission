package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.taras.kushmyruk.model.Faculty;
import ua.taras.kushmyruk.model.StudentOrder;

import java.util.List;

public interface StudentOrderRepository extends JpaRepository<StudentOrder, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM e_student_order WHERE is_accepted = true AND faculty_id = :facultyId")
    List<StudentOrder> findByAcceptedStudentOrders(Long facultyId);
}
