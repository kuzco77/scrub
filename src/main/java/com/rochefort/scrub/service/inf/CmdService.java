package com.rochefort.scrub.service.inf;

import java.io.IOException;
import java.util.List;

public interface CmdService {
    String execute(List<String> command) throws IOException;
}
