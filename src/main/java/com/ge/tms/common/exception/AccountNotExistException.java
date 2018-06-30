package com.ge.tms.common.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ge.tms.util.AppUtil;

/**
 * @author Nitin K.
 * Custom Exception class thrown for a User Account Not Exist.
 */
public class AccountNotExistException extends UsernameNotFoundException {

    public AccountNotExistException() {
        super(AppUtil.getMessage("accountNotExistException"));
    }

    public AccountNotExistException(String msg) {
        super(msg);
    }
}
