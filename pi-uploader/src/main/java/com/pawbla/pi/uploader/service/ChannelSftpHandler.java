package com.pawbla.pi.uploader.service;

import com.jcraft.jsch.Session;

public interface ChannelSftpHandler {
    boolean createFolderInHomeDir(Session session, String folderName);
    boolean copyLocalToRemoteTmp(Session session, String source, String destination);
}
