package com.pawbla.pi.uploader;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.awt.*;

@SpringBootApplication
public class PiUploaderMain {
    public static void main( String[] args ) {
        ApplicationContext context =  new SpringApplicationBuilder(PiUploaderMain.class).headless(false).run(args);
        EventQueue.invokeLater(() -> {

            Frame ex = context.getBean(Frame.class)  ;
            ex.setVisible(true);
        });
    }
}
