package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.taras.kushmyruk.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
