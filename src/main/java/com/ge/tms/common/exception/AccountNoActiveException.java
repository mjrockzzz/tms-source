package com.ge.tms.common.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ge.tms.util.AppUtil;

/**
 * @author Nitin K.
 * Custom Exception class thrown for an Inactive User Account.
 */
public class AccountNoActiveException extends UsernameNotFoundException {

    public AccountNoActiveException() {
        super(AppUtil.getMessage("accountNoActiveException"));
    }
    public AccountNoActiveException(String msg) {
        super(msg);
    }
}
