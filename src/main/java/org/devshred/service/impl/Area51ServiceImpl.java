package org.devshred.service.impl;

import org.devshred.service.Area51Service;
import org.springframework.stereotype.Service;

@Service
public class Area51ServiceImpl implements Area51Service {
    @Override
    public String greeting() {
        return "Hallo Welt.";
    }
}
