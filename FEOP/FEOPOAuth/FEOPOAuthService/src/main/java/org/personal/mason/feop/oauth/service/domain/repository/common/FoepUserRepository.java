package org.personal.mason.feop.oauth.service.domain.repository.common;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoepUserRepository extends JpaRepository<FoepUser, Long> {

//    @Query("From FoepUser Where email = :ctx Or userName = :ctx Or phone = :ctx")
//        //@Query("From FoepBasicUser Where email = :ctx Union From FoepBasicUser Where userName = :ctx")
//    List<FoepUser> findByUserNameOrEmailOrPhone(@Param("ctx") String compareStr);
//
    @Query("From FoepUser Where email = :ctx Or phone = :ctx Or userName = :ctx")
    List<FoepUser> findByEmailOrPhoneOrUserName(@Param("ctx") String compare);

    List<FoepUser> findByUserId(String userid);



}
