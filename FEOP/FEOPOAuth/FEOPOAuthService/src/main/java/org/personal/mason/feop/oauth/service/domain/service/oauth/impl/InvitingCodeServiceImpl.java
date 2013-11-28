package org.personal.mason.feop.oauth.service.domain.service.oauth.impl;

import org.personal.mason.feop.oauth.service.domain.model.oauth.InvitingCode;
import org.personal.mason.feop.oauth.service.domain.repository.oauth.InvitingCodeRepository;
import org.personal.mason.feop.oauth.service.domain.service.oauth.InvitingCodeService;
import org.personal.mason.feop.oauth.service.utils.InvitingCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvitingCodeServiceImpl implements InvitingCodeService {

    private InvitingCodeRepository invitingCodeRepository;

    @Autowired
    public void setInvitingCodeRepository(InvitingCodeRepository invitingCodeRepository) {
        this.invitingCodeRepository = invitingCodeRepository;
    }

    @Override
    @Transactional
    public List<InvitingCode> generateInvitingCodes(int number) {
        List<InvitingCode> codes = InvitingCodeGenerator.generate(number);
        return invitingCodeRepository.save(codes);
    }

    @Override
    @Transactional
    public InvitingCode findWithCode(String code) {
        List<InvitingCode> codes = invitingCodeRepository.findByInviteCode(code);
        return (codes != null && codes.size() > 0) ? codes.get(0) : null;
    }

    @Override
    @Transactional
    public void delete(InvitingCode code) {
        invitingCodeRepository.delete(code);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        invitingCodeRepository.delete(id);
    }

    @Override
    @Transactional
    public List<InvitingCode> findAll() {
        return invitingCodeRepository.findByUsed(false);
    }

}
