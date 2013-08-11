package org.personal.mason.feop.oauth.service.spi;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.InvitingCode;

public interface InvitingCodeService {

	public List<InvitingCode> generateInvitingCodes(int number);

	InvitingCode findWithCode(String code);
	
	List<InvitingCode> findAll();

	void delete(InvitingCode code);

	void delete(Long id);
}
