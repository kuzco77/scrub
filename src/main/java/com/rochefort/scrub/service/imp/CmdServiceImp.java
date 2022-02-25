package com.rochefort.scrub.service.imp;

import com.rochefort.scrub.service.inf.CmdService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CmdServiceImp implements CmdService {

    @Override
    public String execute(List<String> command) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        line = r.lines().collect(Collectors.joining());
        System.out.println(line);
        return line;
    }
}
