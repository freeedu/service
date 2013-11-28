package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.InvitingCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitingCodeRepository extends JpaRepository<InvitingCode, Long> {

    List<InvitingCode> findByInviteCode(String invitecode);

    List<InvitingCode> findByUsed(Boolean used);
}
