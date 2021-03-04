package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taras.kushmyruk.model.Passport;

public interface PassportRepository  extends JpaRepository<Passport, Long>{
}
