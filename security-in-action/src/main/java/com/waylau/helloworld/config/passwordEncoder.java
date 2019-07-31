package com.waylau.helloworld.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class passwordEncoder  implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
