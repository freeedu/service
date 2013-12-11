package org.personal.mason.feop.oauth.service.domain.repository.common;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoepUserRepository extends JpaRepository<FoepUser, Long> {

    @Query("From FoepUser Where email = :ctx Or userName = :ctx")
        //@Query("From FoepUser Where email = :ctx Union From FoepUser Where userName = :ctx")
    List<FoepUser> findByUserNameOrEmail(@Param("ctx") String compareStr);

    List<FoepUser> findByUserId(String userid);

}
