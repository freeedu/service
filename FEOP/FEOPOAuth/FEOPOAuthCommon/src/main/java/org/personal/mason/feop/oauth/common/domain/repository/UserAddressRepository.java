package org.personal.mason.feop.oauth.common.domain.repository;

import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

	List<UserAddress> findByAccountUser(AccountUser accountuser);

	List<UserAddress> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);

}
