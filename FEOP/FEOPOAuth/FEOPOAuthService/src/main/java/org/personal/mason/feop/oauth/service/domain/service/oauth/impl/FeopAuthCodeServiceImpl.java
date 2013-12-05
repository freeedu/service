package org.personal.mason.feop.oauth.service.domain.service.oauth.impl;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthCode;
import org.personal.mason.feop.oauth.service.domain.repository.oauth.OauthCodeRepository;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopAuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeopAuthCodeServiceImpl implements FeopAuthCodeService {

    private OauthCodeRepository oauthCodeRepository;

    @Autowired
    public void setOauthCodeRepository(OauthCodeRepository oauthCodeRepository) {
        this.oauthCodeRepository = oauthCodeRepository;
    }

    @Override
    @Transactional
    public void save(OauthCode oauthCode) {
        oauthCodeRepository.save(oauthCode);
    }

    @Override
    @Transactional
    public OauthCode findOauthCodeByCode(String code) {
        List<OauthCode> codes = oauthCodeRepository.findByCode(code);
        return codes.isEmpty() ? null : codes.get(0);
    }

    @Override
    @Transactional
    public void delete(OauthCode oauthCode) {
        oauthCodeRepository.delete(oauthCode);
    }

    @Override
    public void deleteByCode(String code) {
        List<OauthCode> oauthCodes = oauthCodeRepository.findByCode(code);
        if(oauthCodes != null && !oauthCodes.isEmpty()){
            oauthCodeRepository.delete(oauthCodes);
        }
    }
}
