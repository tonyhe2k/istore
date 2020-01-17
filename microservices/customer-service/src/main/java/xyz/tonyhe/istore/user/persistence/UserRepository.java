package xyz.tonyhe.istore.user.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xyz.tonyhe.istore.user.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
