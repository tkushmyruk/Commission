package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.taras.kushmyruk.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
