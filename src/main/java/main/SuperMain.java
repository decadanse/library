package main;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;

public class SuperMain { //jar needs to know the actual Main class that does not extend Application
    
//    public static void main(String[] args) {
//        StartFxApp.main(args);
//    }
    
    public static void main(String[] args) throws Exception {
//        assertNoOtherInstanceRunning();
//        StartFxApp.main(args);
        String userHome = System.getProperty("user.home");
        File file = new File(userHome, "my.lock");
        try {
            FileChannel fc = FileChannel.open(file.toPath(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);
            FileLock lock = fc.tryLock();
            if (lock == null) {
                System.out.println("another instance is running");
                System.exit(1);
            }
            StartFxApp.main(args);
        } catch (IOException e) {
            throw new Error(e);
        }
//        JustOneLock ua = new JustOneLock("JustOneId");
//        if (ua.isAppActive()) {
//            System.out.println("Already active.");
//            System.exit(1);
//        } else {
//            System.out.println("NOT already active.");
//            StartFxApp.main(args);
//        }
    }
//    
//    public static void assertNoOtherInstanceRunning() {       
//    new Thread(() -> {
//        try {
//            new ServerSocket(9000).accept();
//            
//        } catch (IOException e) {
//          throw new RuntimeException("the application is probably already started", e);
//        }
//    }).start();       
//    }
    
}