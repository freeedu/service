package org.personal.mason.feop.oauth.account.spi;

import java.util.List;

import org.personal.mason.feop.oauth.account.domain.AccountUser;
import org.personal.mason.feop.oauth.account.domain.UserAddress;

public interface UserAddressService {

	UserAddress findDefaultAddress(AccountUser account);

	List<UserAddress> findAllAddresses(AccountUser account);

	void saveAddress(UserAddress userAddress);

	UserAddress findAddressById(Long addressId);

	void deleteAddress(UserAddress userAddress);

	UserAddress updateUserAddress(UserAddress userAddress);

	boolean switchDefault(UserAddress defaultAddress, UserAddress newDefaultAddress);

}
