package com.rochefort.scrub.service.inf;

import java.io.IOException;

public interface VpnService {
    boolean turnOnVpn() throws IOException;
    boolean turnOffVpn() throws IOException;
}
