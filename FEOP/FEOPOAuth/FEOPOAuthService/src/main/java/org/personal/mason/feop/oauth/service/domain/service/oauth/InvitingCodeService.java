package org.personal.mason.feop.oauth.service.domain.service.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.InvitingCode;

import java.util.List;

public interface InvitingCodeService {

    public List<InvitingCode> generateInvitingCodes(int number);

    InvitingCode findWithCode(String code);

    List<InvitingCode> findAll();

    void delete(InvitingCode code);

    void delete(Long id);
}
