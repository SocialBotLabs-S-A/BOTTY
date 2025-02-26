package domain.ports;

import domain.model.User;

import java.util.Optional;
//defines an interface for user persistence.
//it allows the business logic (in the service)
// not to depend on how persistence is implemented (JPA, JDBC, etc.).
public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
    User save(User user);
}
