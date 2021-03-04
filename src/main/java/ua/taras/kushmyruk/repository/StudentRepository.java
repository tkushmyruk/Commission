package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taras.kushmyruk.model.StudentOrder;
import ua.taras.kushmyruk.model.StudentPersonalInfo;

public interface StudentRepository  extends JpaRepository<StudentPersonalInfo, Long> {

}
