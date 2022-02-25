package com.rochefort.scrub.service.inf;

import java.io.IOException;

public interface LogService {
    boolean logInfo() throws IOException;
    void testLock() throws InterruptedException;
}
