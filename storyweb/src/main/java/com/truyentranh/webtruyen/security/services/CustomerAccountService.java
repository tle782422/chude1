package com.truyentranh.webtruyen.security.services;

import com.truyentranh.webtruyen.security.core.exception.InvalidTokenException;
import com.truyentranh.webtruyen.security.core.exception.UnkownIdentifierException;

public interface CustomerAccountService {

    void forgottenPassword(final String userName) throws UnkownIdentifierException;
    void updatePassword(final String password, final String token) throws InvalidTokenException, UnkownIdentifierException;
    boolean loginDisabled(final String username);
}
