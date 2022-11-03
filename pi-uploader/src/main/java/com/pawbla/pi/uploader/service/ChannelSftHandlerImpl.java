package com.pawbla.pi.uploader.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Vector;

import static com.pawbla.pi.uploader.utils.Commons.*;

@Service
public class ChannelSftHandlerImpl implements ChannelSftpHandler {
    private static final Logger LOG = LogManager.getLogger(ChannelSftHandlerImpl.class);
    private ChannelSftp channelSftp = null;
    private String homeDir;

    @Override
    public boolean createFolderInHomeDir(Session session, String folderName) {
        boolean isFolderCreated = false;
        if (openChannel(session)) {
            homeDir = getRemoteHomeDir();
            remoteCd(homeDir);
            printRemoteWorkingDir();
            isFolderCreated = checkRemoteFolderExists(homeDir, folderName);
            if (!isFolderCreated) {
                makeRemoteDir(folderName);
                isFolderCreated = checkRemoteFolderExists(homeDir, folderName);
            }
            if (isFolderCreated) {
                removeRemoteFiles(getTmpDir());
            }
        }
        closeChannel();
        return isFolderCreated;
    }

    @Override
    public boolean copyLocalToRemoteTmp(Session session, String source, String destination) {
        boolean retVal = false;
        if (openChannel(session)) {
            //remoteListDir(destination);
            retVal = copyToRemote(source, destination);
            //remoteListDir(destination);
        }
        closeChannel();
        return retVal;
    }

    private boolean openChannel(Session session) {
        LOG.info("Open sftp channel");
        try {
            channelSftp= (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
        } catch (JSchException e) {
            LOG.warn("Cannot create open channel and connect: {}", e.getMessage());
            return false;
        }
        return true;
    }

    private void closeChannel() {
        channelSftp.exit();
        channelSftp.disconnect();
    }

    private String getRemoteHomeDir() {
        String homeDir = EMPTY_STRING;
        try {
            homeDir = channelSftp.getHome();
            LOG.info("Home dir: {}", homeDir);
        } catch (SftpException e) {
            LOG.warn("Cannot get home directory: {}", e.getMessage());
        }
        return homeDir;
    }

    private void remoteCd(String path) {
        LOG.info("cd {}", path);
        try {
            channelSftp.cd(path);
        } catch (SftpException e) {
            LOG.warn("Cannot move to {} directory: {}", path, e.getMessage());
        }
    }

    private String printRemoteWorkingDir() {
        LOG.info("pwd");
        String pwd = EMPTY_STRING;
        try {
            pwd = channelSftp.pwd();
            LOG.info("Working directory {}", pwd);
        } catch (SftpException e) {
            LOG.warn("Cannot get working directory: {}", e.getMessage());
        }
        return pwd;
    }

    private boolean checkRemoteFolderExists(String path, String folderName) {
        LOG.info("Check if folder {} exists under path {}", folderName, path);
        try {
            channelSftp.lstat(path.concat(REMOTE_SEPARATOR).concat(folderName));
        } catch (SftpException e) {
            LOG.info("Expected exception, folder doesn't exists: {}", e.getMessage());
            return false;
        }
        return true;
    }

    private boolean makeRemoteDir(String folderName) {
        LOG.info("mkdir {}", folderName);
        try {
            channelSftp.mkdir(folderName);
        } catch (SftpException e) {
            LOG.info("Folder cannot be created: {}", e.getMessage());
            return false;
        }
        return true;
    }

    private void remoteListDir(String path) {
        LOG.info("ls {}", path);
        try {
            Vector ls = channelSftp.ls(path);
            ls.forEach(LOG::info);
        } catch (SftpException e) {
            LOG.info("Cannot list folders: {}", e.getMessage());
        }
    }

    private void removeRemoteFiles(String path) {
        LOG.info("rm {}", path);
        try {
            channelSftp.rm(path);
        } catch (SftpException e) {
            LOG.info("Cannot remove files: {}", e.getMessage());
        }
    }

    private boolean copyToRemote(String source, String destination) {
        LOG.info("Copy from {} to remote {}", source, destination);
        try {
            channelSftp.put(source, destination);
        } catch (SftpException e) {
            LOG.info("Cannot copy to remote: {}", e.getMessage());
            return false;
        }
        return true;
    }

    private String getTmpDir() {
        return homeDir.concat(REMOTE_SEPARATOR).concat(TMP_FOLDER_NAME);
    }
}
