package org.personal.mason.feop.oauth.account.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.personal.mason.feop.oauth.account.domain.AccountUser;
import org.personal.mason.feop.oauth.account.domain.UserAddress;
import org.personal.mason.feop.oauth.account.domain.UserEmail;
import org.personal.mason.feop.oauth.account.domain.UserIM;
import org.personal.mason.feop.oauth.account.domain.UserPhone;
import org.personal.mason.feop.oauth.account.domain.UserRecord;
import org.personal.mason.feop.oauth.account.domain.UserResource;
import org.personal.mason.feop.oauth.account.spi.AccountUserService;
import org.personal.mason.feop.oauth.account.spi.UserAddressService;
import org.personal.mason.feop.oauth.account.spi.UserEmailService;
import org.personal.mason.feop.oauth.account.spi.UserIMService;
import org.personal.mason.feop.oauth.account.spi.UserPhoneService;
import org.personal.mason.feop.oauth.account.spi.UserRecordService;
import org.personal.mason.feop.oauth.account.spi.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/")
public class AccountResource {

	private AccountUserService accountUserService;
	private UserAddressService userAddressService;
	private UserEmailService userEmailService;
	private UserIMService userIMService;
	private UserPhoneService userPhoneService;
	private UserRecordService userRecordService;
	private UserResourceService userResourceService;

	@Autowired
	public void setAccountUserService(AccountUserService accountUserService) {
		this.accountUserService = accountUserService;
	}

	@Autowired
	public void setUserAddressService(UserAddressService userAddressService) {
		this.userAddressService = userAddressService;
	}

	@Autowired
	public void setUserEmailService(UserEmailService userEmailService) {
		this.userEmailService = userEmailService;
	}

	@Autowired
	public void setUserIMService(UserIMService userIMService) {
		this.userIMService = userIMService;
	}

	@Autowired
	public void setUserPhoneService(UserPhoneService userPhoneService) {
		this.userPhoneService = userPhoneService;
	}

	@Autowired
	public void setUserRecordService(UserRecordService userRecordService) {
		this.userRecordService = userRecordService;
	}

	@Autowired
	public void setUserResourceService(UserResourceService userResourceService) {
		this.userResourceService = userResourceService;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/save")
	public AccountUser createAccount(AccountUser accountUser) {
		accountUserService.createAccount(accountUser);
		return accountUser;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/update")
	public AccountUser updateAccount(AccountUser accountUser) {
		return accountUserService.updateAccount(accountUser);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}")
	public AccountUser findAccount(@PathParam("id") Long id) {
		return accountUserService.findById(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account")
	public AccountUser findAccountWithUserId(@QueryParam("userid") String userId) {
		return accountUserService.findUserByUserId(userId);
	}

	/*
	 * Address Section
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/address/default")
	public UserAddress getDefaultAddress(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userAddressService.findDefaultAddress(account);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/addresses")
	public List<UserAddress> getAddresses(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userAddressService.findAllAddresses(account);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/address/save")
	public UserAddress addAddress(@PathParam("id") Long id, UserAddress userAddress) {
		AccountUser account = accountUserService.findById(id);
		userAddress.setAccountUser(account);
		if (account.getAddresses().isEmpty()) {
			userAddress.setPrimary(true);
		} else {
			userAddress.setPrimary(false);
		}
		userAddressService.saveAddress(userAddress);
		return userAddress;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/address/delete")
	public int deleteAddress(@PathParam("id") Long id, @QueryParam("id") Long addressId) {
		UserAddress address = userAddressService.findAddressById(addressId);
		if (address == null) {
			return OperationType.NotFoundTarget.getOperationResult();
		}
		if (address.getAccountUser() == null || address.getAccountUser().getId().longValue() != id.longValue()) {
			return OperationType.InvalidOperation.getOperationResult();
		}

		userAddressService.deleteAddress(address);
		return OperationType.CorrectFinish.getOperationResult();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/address/update")
	public UserAddress updateAddress(@PathParam("id") Long id, UserAddress userAddress) {
		UserAddress address = userAddressService.findAddressById(userAddress.getId());
		if (address == null || address.getId().longValue() != id.longValue()) {
			return null;
		}

		return userAddressService.updateUserAddress(userAddress);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/address/set")
	public boolean setDefaultAddress(@PathParam("id") Long id, @QueryParam("id") Long addressId) {
		AccountUser account = accountUserService.findById(id);
		UserAddress defaultAddress = userAddressService.findDefaultAddress(account);
		UserAddress newDefaultAddress = userAddressService.findAddressById(addressId);
		return userAddressService.switchDefault(defaultAddress, newDefaultAddress);
	}

	/*
	 * Mail Section
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/mail/default")
	public UserEmail getDefaultEmail(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userEmailService.findDefaultEmail(account);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/emails")
	public List<UserEmail> getEmails(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userEmailService.findAllEmails(account);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/email/save")
	public UserEmail addEmail(@PathParam("id") Long id, UserEmail userEmail) {
		AccountUser account = accountUserService.findById(id);
		userEmail.setAccountUser(account);
		if (account.getEmails().isEmpty()) {
			userEmail.setPrimary(true);
		} else {
			userEmail.setPrimary(false);
		}
		userEmailService.saveEmail(userEmail);
		return userEmail;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/email/delete")
	public int deleteEmail(@PathParam("id") Long id, @QueryParam("id") Long emailId) {
		UserEmail email = userEmailService.findEmailById(emailId);
		if (email == null) {
			return OperationType.NotFoundTarget.getOperationResult();
		}
		if (email.getAccountUser() == null || email.getAccountUser().getId().longValue() != id.longValue()) {
			return OperationType.InvalidOperation.getOperationResult();
		}

		userEmailService.deleteEmail(email);
		return OperationType.CorrectFinish.getOperationResult();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/email/update")
	public UserEmail updateEmail(@PathParam("id") Long id, UserEmail userEmail) {
		UserEmail email = userEmailService.findEmailById(userEmail.getId());
		if (email == null || email.getId().longValue() != id.longValue()) {
			return null;
		}

		return userEmailService.updateUserEmail(email);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/email/set")
	public boolean setDefaultEmail(@PathParam("id") Long id, @QueryParam("id") Long emailId) {
		AccountUser account = accountUserService.findById(id);
		UserEmail defaultEmail = userEmailService.findDefaultEmail(account);
		UserEmail newDefaultEmail = userEmailService.findEmailById(emailId);
		return userEmailService.switchDefault(defaultEmail, newDefaultEmail);
	}

	/*
	 * IM Section
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/im/default")
	public UserIM getDefaultIM(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userIMService.findDefaultIM(account);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/ims")
	public List<UserIM> getIMs(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userIMService.findAllIMs(account);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/im/save")
	public UserIM addIM(@PathParam("id") Long id, UserIM userIM) {
		AccountUser account = accountUserService.findById(id);
		userIM.setAccountUser(account);
		if (account.getEmails().isEmpty()) {
			userIM.setPrimary(true);
		} else {
			userIM.setPrimary(false);
		}
		userIMService.saveIM(userIM);
		return userIM;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/im/delete")
	public int deleteIM(@PathParam("id") Long id, @QueryParam("id") Long imId) {
		UserIM im = userIMService.findIMById(imId);
		if (im == null) {
			return OperationType.NotFoundTarget.getOperationResult();
		}
		if (im.getAccountUser() == null || im.getAccountUser().getId().longValue() != id.longValue()) {
			return OperationType.InvalidOperation.getOperationResult();
		}

		userIMService.deleteIM(im);
		return OperationType.CorrectFinish.getOperationResult();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/im/update")
	public UserIM updateIM(@PathParam("id") Long id, UserIM userIM) {
		UserIM im = userIMService.findIMById(userIM.getId());
		if (im == null || im.getId().longValue() != id.longValue()) {
			return null;
		}

		return userIMService.updateUserIM(im);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/im/set")
	public boolean setDefaultIM(@PathParam("id") Long id, @QueryParam("id") Long imId) {
		AccountUser account = accountUserService.findById(id);
		UserIM defaultIm = userIMService.findDefaultIM(account);
		UserIM newDefaultIm = userIMService.findIMById(imId);
		return userIMService.switchDefault(defaultIm, newDefaultIm);
	}

	/*
	 * Phone Section
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/phone/default")
	public UserPhone getDefaultPhone(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userPhoneService.findDefaultPhone(account);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/phones")
	public List<UserPhone> getPhones(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userPhoneService.findAllPhones(account);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/phone/save")
	public UserPhone addPhone(@PathParam("id") Long id, UserPhone userPhone) {
		AccountUser account = accountUserService.findById(id);
		userPhone.setAccountUser(account);
		if (account.getPhones().isEmpty()) {
			userPhone.setPrimary(true);
		} else {
			userPhone.setPrimary(false);
		}
		userPhoneService.savePhone(userPhone);
		return userPhone;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/phone/delete")
	public int deletePhone(@PathParam("id") Long id, @QueryParam("id") Long phoneId) {
		UserPhone phone = userPhoneService.findPhoneById(phoneId);
		if (phone == null) {
			return OperationType.NotFoundTarget.getOperationResult();
		}
		if (phone.getAccountUser() == null || phone.getAccountUser().getId().longValue() != id.longValue()) {
			return OperationType.InvalidOperation.getOperationResult();
		}

		userPhoneService.deletePhone(phone);
		return OperationType.CorrectFinish.getOperationResult();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/phone/update")
	public UserPhone updatePhone(@PathParam("id") Long id, UserPhone userPhone) {
		UserPhone phone = userPhoneService.findPhoneById(userPhone.getId());
		if (phone == null || phone.getId().longValue() != id.longValue()) {
			return null;
		}

		return userPhoneService.updateUserPhone(phone);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/phone/set")
	public boolean setDefaultPhone(@PathParam("id") Long id, @QueryParam("id") Long phoneId) {
		AccountUser account = accountUserService.findById(id);
		UserPhone defaultPhone = userPhoneService.findDefaultPhone(account);
		UserPhone newDefaultPhone = userPhoneService.findPhoneById(phoneId);
		return userPhoneService.switchDefault(defaultPhone, newDefaultPhone);
	}

	/*
	 * Record Section
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/records")
	public List<UserRecord> getRecords(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userRecordService.findAllRecords(account);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/record/save")
	public UserRecord addRecord(@PathParam("id") Long id, UserRecord userRecord) {
		AccountUser account = accountUserService.findById(id);
		userRecord.setAccountUser(account);
		userRecordService.saveRecord(userRecord);
		return userRecord;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/record/delete")
	public int deleteRecord(@PathParam("id") Long id, @QueryParam("id") Long recordId) {
		UserRecord record = userRecordService.findRecordById(recordId);
		if (record == null) {
			return OperationType.NotFoundTarget.getOperationResult();
		}
		if (record.getAccountUser() == null || record.getAccountUser().getId().longValue() != id.longValue()) {
			return OperationType.InvalidOperation.getOperationResult();
		}

		userRecordService.deleteRecord(record);
		return OperationType.CorrectFinish.getOperationResult();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/record/update")
	public UserRecord updateRecord(@PathParam("id") Long id, UserRecord userRecord) {
		UserRecord record = userRecordService.findRecordById(userRecord.getId());
		if (record == null || record.getId().longValue() != id.longValue()) {
			return null;
		}

		return userRecordService.updateUserRecord(record);
	}

	/*
	 * Resource Section
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/resources")
	public List<UserResource> getResources(@PathParam("id") Long id) {
		AccountUser account = accountUserService.findById(id);
		return userResourceService.findAllResources(account);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/resource/save")
	public UserResource addResource(@PathParam("id") Long id, UserResource userResource) {
		AccountUser account = accountUserService.findById(id);
		userResource.setAccountUser(account);
		userResourceService.saveResource(userResource);
		return userResource;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/resource/delete")
	public int deleteResource(@PathParam("id") Long id, @QueryParam("id") Long resourceId) {
		UserResource resource = userResourceService.findResourceById(resourceId);
		if (resource == null) {
			return OperationType.NotFoundTarget.getOperationResult();
		}
		if (resource.getAccountUser() == null || resource.getAccountUser().getId().longValue() != id.longValue()) {
			return OperationType.InvalidOperation.getOperationResult();
		}

		userResourceService.deleteResource(resource);
		return OperationType.CorrectFinish.getOperationResult();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/account/{id}/resource/update")
	public UserResource updateResource(@PathParam("id") Long id, UserResource userResource) {
		UserResource resource = userResourceService.findResourceById(userResource.getId());
		if (resource == null || resource.getId().longValue() != id.longValue()) {
			return null;
		}

		return userResourceService.updateUserResource(resource);
	}

}
