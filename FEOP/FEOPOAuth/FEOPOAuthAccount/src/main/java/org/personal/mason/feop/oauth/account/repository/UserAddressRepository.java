package org.personal.mason.feop.oauth.account.repository;

import java.util.List;

import org.personal.mason.feop.oauth.account.domain.AccountUser;
import org.personal.mason.feop.oauth.account.domain.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

	List<UserAddress> findByAccountUser(AccountUser accountuser);

	List<UserAddress> findByAccountUserAndPrimary(AccountUser accountUser, Boolean primary);

}
