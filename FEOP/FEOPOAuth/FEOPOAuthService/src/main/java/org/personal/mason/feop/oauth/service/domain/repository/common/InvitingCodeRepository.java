package org.personal.mason.feop.oauth.service.domain.repository.common;

import org.personal.mason.feop.oauth.service.domain.model.common.InvitingCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitingCodeRepository extends JpaRepository<InvitingCode, Long> {

    List<InvitingCode> findByInviteCode(String invitecode);

    List<InvitingCode> findByUsed(Boolean used);
}
