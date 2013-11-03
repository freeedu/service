package org.personal.mason.feop.oauth.common.domain.spi.impl;

import java.util.Arrays;
import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserAddress;
import org.personal.mason.feop.oauth.common.domain.repository.UserAddressRepository;
import org.personal.mason.feop.oauth.common.domain.spi.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserAddressServiceImpl implements UserAddressService {

	private UserAddressRepository userAddressRepository;

	@Autowired
	public void setUserAddressRepository(UserAddressRepository userAddressRepository) {
		this.userAddressRepository = userAddressRepository;
	}

	@Override
	@Transactional
	public UserAddress findDefaultAddress(AccountUser account) {
		List<UserAddress> defaultAddresses = userAddressRepository.findByAccountUserAndPrimary(account, true);
		return defaultAddresses.isEmpty() ? null : defaultAddresses.get(0);
	}

	@Override
	@Transactional
	public List<UserAddress> findAllAddresses(AccountUser account) {
		return userAddressRepository.findByAccountUser(account);
	}

	@Override
	@Transactional
	public void saveAddress(UserAddress userAddress) {
		userAddressRepository.save(userAddress);
	}

	@Override
	@Transactional
	public UserAddress findAddressById(Long addressId) {
		return userAddressRepository.findOne(addressId);
	}

	@Override
	@Transactional
	public void deleteAddress(UserAddress userAddress) {
		userAddressRepository.delete(userAddress);
	}

	@Override
	@Transactional
	public UserAddress updateUserAddress(UserAddress userAddress) {
		return userAddressRepository.saveAndFlush(userAddress);
	}

	@Override
	@Transactional
	public boolean switchDefault(UserAddress defaultAddress, UserAddress newDefaultAddress) {
		try {
			defaultAddress.setPrimary(false);
			newDefaultAddress.setPrimary(true);
			userAddressRepository.save(Arrays.asList(defaultAddress, newDefaultAddress));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
