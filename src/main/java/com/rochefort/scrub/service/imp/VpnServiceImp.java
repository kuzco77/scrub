package com.rochefort.scrub.service.imp;

import com.rochefort.scrub.repository.CustomLogRepository;
import com.rochefort.scrub.service.inf.CmdService;
import com.rochefort.scrub.service.inf.VpnService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VpnServiceImp implements VpnService {

    private final CustomLogRepository customLogRepository;
    private final CmdService cmdService;

    public VpnServiceImp(CustomLogRepository customLogRepository, CmdService cmdService) {
        this.customLogRepository = customLogRepository;
        this.cmdService = cmdService;
    }

    @Override
    public boolean turnOnVpn() throws IOException {
        List<String> command = new ArrayList<>();
        command.add("/bin/bash");
        command.add("-c");
        command.add("/usr/sbin/networksetup -connectpppoeservice \"GHDC VPN\"");
        cmdService.execute(command);
        return true;
    }

    @Override
    public boolean turnOffVpn() throws IOException {
        List<String> command = new ArrayList<>();
        command.add("/bin/bash");
        command.add("-c");
        command.add("/usr/sbin/networksetup -disconnectpppoeservice \"GHDC VPN\"");
        cmdService.execute(command);
        return true;
    }
}
