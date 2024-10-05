package com.messages.demo.services;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AnchoBandaService {
        private int anchoBanda;

    @Scheduled(fixedDelay = 300, timeUnit = TimeUnit.NANOSECONDS)
    public void actualizarAnchoBanda() {
        this.anchoBanda = ThreadLocalRandom.current().nextInt(100, 1001);
    }

    public int getAnchoBanda() {
        return anchoBanda;
    }
}
