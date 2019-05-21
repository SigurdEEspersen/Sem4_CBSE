package org.netbeans.modules.autoupdate.silentupdate;

/**
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 *
 * @author Gruppe 11
 */
public class UpdateActivator extends ModuleInstall {

    private final ScheduledExecutorService exector = Executors.newScheduledThreadPool(1);

    @Override
    public void restored() {
        String partDir[] = System.getProperty("user.dir").split("Sem4_CBSE");
        String rootDir = partDir[0] + "Sem4_CBSE";
        
        String pathToUpdateCenter = rootDir + "/application/target/netbeans_site/updates.xml";
        String pathToBundleProperties = rootDir + "/SilentUpdate/src/main/resources/org/netbeans/modules/autoupdate/silentupdate/resources/Bundle.properties";
        
        File file = new File(pathToBundleProperties);
        System.out.println(file.canRead());
        
        StringBuilder newContent = new StringBuilder();
        
        String pathLine = "";
        
        try (Scanner scanner = new Scanner(file);) {
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                
                if (line.contains("org_netbeans_modules_autoupdate_silentupdate_update_center")) {
                    pathLine = line;
                    String partLine[] = pathLine.split("=");
        
                    pathLine = partLine[0] + "=file://" + pathToUpdateCenter;

                    System.out.println(pathLine);
                    
                    newContent.append(pathLine);
                    newContent.append("\n");

                } else {
                    newContent.append(line);
                    newContent.append("\n");
                }
            }
            
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        try (FileWriter writer = new FileWriter(file);) {
            writer.write(newContent.toString());
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(newContent.toString());
        
        exector.scheduleAtFixedRate(doCheck, 5000, 5000, TimeUnit.MILLISECONDS);
    }

    private static final Runnable doCheck = new Runnable() {
        @Override
        public void run() {
            if (UpdateHandler.timeToCheck()) {
                UpdateHandler.checkAndHandleUpdates();
            }
        }

    };

    @Override
    public void uninstalled() {
        super.uninstalled(); //To change body of generated methods, choose Tools | Templates.
    }

}
