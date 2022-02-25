package com.rochefort.scrub;

import com.rochefort.scrub.entity.CustomTerminalConfigEntity;
import com.rochefort.scrub.repository.CustomTerminalConfigRepository;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
public class ScrubApplication {

    private static CustomTerminalConfigRepository customTerminalConfigEntityRepository;

    @Autowired
    public ScrubApplication(CustomTerminalConfigRepository customTerminalConfigEntityRepository) {
        this.customTerminalConfigEntityRepository = customTerminalConfigEntityRepository;
    }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(ScrubApplication.class, args);
        context.close();
    }

//    public static boolean listFolderStructure(String username, String password,
//                                           String host, int port, String command) throws Exception {
//
//        Session session = null;
//        ChannelExec channel = null;
//
//        try {
//            session = new JSch().getSession(username, host, port);
//            session.setPassword(password);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.connect();
//
//            if (session.isConnected()) {
//                return true;
//            } else {
//                return false;
//            }
//
////            channel = (ChannelExec) session.openChannel("exec");
////            channel.setCommand(command);
////            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
////            channel.setOutputStream(responseStream);
////            channel.connect();
////
////            while (channel.isConnected()) {
////                Thread.sleep(100);
////            }
////
////            String responseString = new String(responseStream.toByteArray());
////            System.out.println(responseString);
//        } finally {
//            if (session != null) {
//                session.disconnect();
//            }
//            if (channel != null) {
//                channel.disconnect();
//            }
//        }
//    }

    public static boolean listFolderStructure(String username, String password,
                                           String host, int port, long defaultTimeoutSeconds, String command) {

        SshClient client = SshClient.setUpDefaultClient();
        client.start();

        try (ClientSession session = client.connect(username, host, port)
                .verify(defaultTimeoutSeconds, TimeUnit.MILLISECONDS).getSession()) {
            return true;
//            session.addPasswordIdentity(password);
//            session.auth().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);
//
//            try (ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
//                 ClientChannel channel = session.createChannel(Channel.CHANNEL_SHELL)) {
//                channel.setOut(responseStream);
//                try {
//                    channel.open().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);
//                    try (OutputStream pipedIn = channel.getInvertedIn()) {
//                        pipedIn.write(command.getBytes());
//                        pipedIn.flush();
//                    }
//
//                    channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED),
//                            TimeUnit.SECONDS.toMillis(defaultTimeoutSeconds));
//                    String responseString = new String(responseStream.toByteArray());
//                    System.out.println(responseString);
//                } finally {
//                    channel.close(false);
//                }
//            }
        } catch (IOException e) {
            return false;
        } finally {
            client.stop();
        }
    }
}
