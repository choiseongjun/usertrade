package org.test.usedtrade.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.usedtrade.user.entity.UserInfoEntity;

@Repository
public interface UserRepository extends JpaRepository<UserInfoEntity,Long> {
//    User findByUserId(String userId);
    UserInfoEntity findByEmail(String email);

    int countByEmail(String email);
}
