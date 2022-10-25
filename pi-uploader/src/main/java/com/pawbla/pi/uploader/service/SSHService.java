package com.pawbla.pi.uploader.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.ByteArrayOutputStream;

@Component
public class SSHService {

    private Session session;
    private ChannelExec channel;

    public void connect() {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession("pi", "192.168.1.210", 22);
            session.setPassword("raspberry");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println("---- 1");
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("ls -al");
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            System.out.println("--- 2");
            String responseString = responseStream.toString();
            System.out.println(responseString);
        } catch (JSchException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @PreDestroy
    public void close() {
        System.out.println("Pre destroy");
        if (session != null) {
            session.disconnect();
        }
        if (channel != null) {
            channel.disconnect();
        }
    }
}
