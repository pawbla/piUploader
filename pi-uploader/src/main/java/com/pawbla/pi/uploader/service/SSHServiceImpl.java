package com.pawbla.pi.uploader.service;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.pawbla.pi.uploader.view.elements.PasswordTextArea;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SSHServiceImpl implements SSHService {
    private static final Logger LOG = LogManager.getLogger(SSHServiceImpl.class);

    private final String user;
    private final String host;
    private final int port;
    private final PasswordTextArea passwordTextArea;
    private final JSch jsch;
    private final ChannelSftpHandler sftpHandler;
    private Session session;

    public SSHServiceImpl(@Value("${rpi.username}")String user, @Value("${rpi.host}") String host, @Value("${rpi.port}")Integer port,
                          PasswordTextArea passwordTextArea, ChannelSftpHandler sftpHandler) {
        this.user = user;
        this.host = host;
        this.port = port;
        this.passwordTextArea = passwordTextArea;
        this.sftpHandler = sftpHandler;
        jsch = new JSch();
    }

    @Override
    public boolean openSSHSession() {
        LOG.info("Open SSH session");
        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(String.valueOf(passwordTextArea.getPassword()));
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

        } catch (JSchException e) {
            LOG.warn("Session cannot be established {}", e.getMessage());
            return false;
        }

        return false;
    }

    @Override
    public void closeSSHSession() {
        session.disconnect();
    }

    @Override
    public boolean copyFileViaSSH(String source, String destination) {
        LOG.info("Copy from {} to remote {}", source, destination);
        return sftpHandler.copyLocalToRemoteTmp(session, source, destination);
    }

    @Override
    public boolean copyFolderViaSSH(String source, String destination) {
        LOG.info("Copy all files from folder {} to remote {}", source, destination);
        return sftpHandler.copyLocalToRemoteTmp(session, source.concat("*"), destination);
    }

    @Override
    public boolean createFolderRpi(String folderName) {
        LOG.info("Create folder: {} in rpi home", folderName);
        return true;
    }
}
