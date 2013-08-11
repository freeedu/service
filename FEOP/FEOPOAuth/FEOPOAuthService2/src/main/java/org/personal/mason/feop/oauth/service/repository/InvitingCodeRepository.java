package org.personal.mason.feop.oauth.service.repository;

import org.personal.mason.feop.oauth.service.domain.InvitingCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;
import java.util.List;
import java.lang.Boolean;

public interface InvitingCodeRepository extends JpaRepository<InvitingCode, Long> {

	List<InvitingCode> findByInviteCode(String invitecode);

	List<InvitingCode> findByUsed(Boolean used);
}
