package com.rochefort.scrub.service.imp;

import com.rochefort.scrub.entity.CustomLogEntity;
import com.rochefort.scrub.repository.CustomLogRepository;
import com.rochefort.scrub.service.inf.CmdService;
import com.rochefort.scrub.service.inf.LogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LogServiceImp implements LogService {

    @PersistenceContext
    EntityManager entityManager;

    @Value("${server.hostname}")
    private String hostname;

    private final CustomLogRepository customLogRepository;
    private final CmdService cmdService;

    public LogServiceImp(CustomLogRepository customLogRepository, CmdService cmdService) {
        this.customLogRepository = customLogRepository;
        this.cmdService = cmdService;
    }

    @Override
    public boolean logInfo() throws IOException {
        CustomLogEntity customLogEntity = new CustomLogEntity();
        customLogEntity.setDevice(hostname);
        List<String> command = new ArrayList<>();
        command.add("/bin/bash");
        command.add("-c");
        command.add("/sbin/ifconfig | grep ppp0 -A 2");
        String result = cmdService.execute(command);

        customLogEntity.setLog(result);
        customLogEntity = customLogRepository.save(customLogEntity);

        return false;
    }

    @Override
    public void testLock() throws InterruptedException {
        entityManager.find(CustomLogEntity.class, 10);

        TimeUnit.SECONDS.sleep(30);

        System.out.println("Done");
    }
}
