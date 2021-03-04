package ua.taras.kushmyruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.taras.kushmyruk.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select u from User u Left Join FETCH  u.studentOrders WHERE u.userId = :id")
    User findUserByIdWithStudentOrder(@Param("id") Long id);
}
