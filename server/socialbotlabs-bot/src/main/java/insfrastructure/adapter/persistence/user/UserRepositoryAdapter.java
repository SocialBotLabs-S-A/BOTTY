package insfrastructure.adapter.persistence.user;

import domain.model.User;
import domain.ports.UserRepositoryPort;
import insfrastructure.adapter.jpa.user.UserEntity;

import java.util.Optional;
//This class is an adapter that implements the UserRepositoryPort interface.
//It allows us to use the JPA repository in the service layer.
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity saved = jpaUserRepository.save(entity);
        return toDomain(saved);
    }

    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFullName(),
                entity.getCompanyName(),
                entity.getCountry(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getPassword()
        );
    }

    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFullName(user.getFullName());
        entity.setCompanyName(user.getCompanyName());
        entity.setCountry(user.getCountry());
        entity.setPhone(user.getPhone());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }
}
