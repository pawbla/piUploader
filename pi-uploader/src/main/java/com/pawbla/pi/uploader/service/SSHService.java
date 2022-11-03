package com.pawbla.pi.uploader.service;

public interface SSHService {
    boolean openSSHSession();
    void closeSSHSession();
    boolean copyFileViaSSH(String source, String destination);
    boolean copyFolderViaSSH(String source, String destination);
    boolean createFolderRpi(String folderName);
}
