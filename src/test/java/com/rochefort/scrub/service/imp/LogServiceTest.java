package com.rochefort.scrub.service.imp;

import com.rochefort.scrub.service.inf.LogService;
import com.rochefort.scrub.service.inf.VpnService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spock.lang.Specification;

@SpringBootTest
class LogServiceTest extends Specification {
    private final LogService logService;

    LogServiceTest(LogService logService) {
        this.logService = logService;
    }

    @Test
    public void test() throws InterruptedException {
        logService.testLock();

        System.out.println("Hi");
    }
}
