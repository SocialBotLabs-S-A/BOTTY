package insfrastructure.adapter.persistence.user;

import insfrastructure.adapter.jpa.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//This interface extends JpaRepository, which provides basic CRUD operations.
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
