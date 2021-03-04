package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taras.kushmyruk.model.Discipline;

public interface DisciplineRepository  extends JpaRepository<Discipline, Long> {
    Discipline findByDisciplineName(String disciplineName);
    
}
