package com.woowahan.junweb.Repository;

import com.woowahan.junweb.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUserId(String userId);
}
