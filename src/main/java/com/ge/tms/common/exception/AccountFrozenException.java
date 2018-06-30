package com.ge.tms.common.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ge.tms.util.AppUtil;


/**
 * @author Nitin K.
 * Custom Exception class thrown for a Frozen User Account.
 */
public class AccountFrozenException extends UsernameNotFoundException {

    public AccountFrozenException() {
        super(AppUtil.getMessage("accountFrozenException"));
    }

    public AccountFrozenException(String msg) {
        super(msg);
    }
}
