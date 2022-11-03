package com.pawbla.pi.uploader.service;

import com.pawbla.pi.uploader.model.Item;
import com.pawbla.pi.uploader.view.elements.AppListCheckboxes;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UpdateAppService {
    private static final Logger LOG = LogManager.getLogger(UpdateAppService.class);

    private final ItemsHandler itemsHandler;
    private final AppListCheckboxes appListCheckboxes;
    private final SSHService sshService;

    public UpdateAppService(ItemsHandler itemsHandler, AppListCheckboxes appListCheckboxes, SSHService sshService) {
        this.itemsHandler = itemsHandler;
        this.appListCheckboxes = appListCheckboxes;
        this.sshService = sshService;
    }

    public void uploadApps() {
        LOG.info("START APPLICATION UPLOAD");
        List<Item> uploadApps = getAppToUpload();
        sshService.openSSHSession();
        uploadApps.forEach(this::upploadApp);
        sshService.closeSSHSession();
        LOG.info("APPLICATION UPLOAD FINISHED");
    }

    private List<Item> getAppToUpload() {
        Map<String, Item> items = itemsHandler.getConfiguration().getItems();

        return appListCheckboxes.getCheckboxList().stream()
                .filter(AbstractButton::isSelected)
                .map(jCheckBox -> items.get(jCheckBox.getText()))
                .collect(Collectors.toList());
    }

    private void upploadApp(Item app) {
        if (StringUtils.isBlank(app.getFile())) {
            sshService.copyFolderViaSSH(app.getSource(), app.getDestination());
        } else {
            String pathToFile = app.getSource().concat(app.getFile());
            sshService.copyFileViaSSH(pathToFile, app.getDestination());
        }
    }
}
