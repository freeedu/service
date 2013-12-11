package org.personal.mason.feop.oauth.service.utils;

import org.personal.mason.feop.oauth.service.domain.model.common.InvitingCode;

import java.util.ArrayList;
import java.util.List;

public class InvitingCodeGenerator {

    public static List<InvitingCode> generate(int number) {
        List<InvitingCode> codes = new ArrayList<InvitingCode>();
        for (int i = 0; i < number; i++) {
            InvitingCode code = new InvitingCode();
            code.setInviteCode(StringGenerator.generateCode());
            code.setUsed(false);
            codes.add(code);
        }
        return codes;
    }

}
