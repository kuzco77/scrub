package com.rochefort.scrub.service;

import com.rochefort.scrub.entity.CustomTerminalConfigEntity;
import com.rochefort.scrub.repository.CustomTerminalConfigRepository;
import com.rochefort.scrub.service.inf.LogService;
import com.rochefort.scrub.service.inf.VpnService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class StartUpService implements CommandLineRunner {
    private final VpnService vpnService;
    private final CustomTerminalConfigRepository customTerminalConfigRepository;
    private final LogService logService;

    public StartUpService(VpnService vpnService, CustomTerminalConfigRepository customTerminalConfigRepository, LogService logService) {
        this.vpnService = vpnService;
        this.customTerminalConfigRepository = customTerminalConfigRepository;
        this.logService = logService;
    }

    @Override
    public void run(String... args) throws Exception {

        logService.testLock();
        logService.logInfo();

        if (powerShouldOn()) {
            vpnService.turnOnVpn();
        } else {
            vpnService.turnOffVpn();
        }
    }

    private boolean powerShouldOn() {
        CustomTerminalConfigEntity customTerminalConfigEntity = customTerminalConfigRepository.findFirstByCode("POWER")
                .orElse(null);

        if (customTerminalConfigEntity != null) {
            if (customTerminalConfigEntity.getStatus() == 1) return true;
        } else {
            return false;
        }

        return true;
    }
}
