package dmroy.luxoft.client.util;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author dmitriyroy
 */
public class FileUtils {

    public static File getFile(String dialogName){

        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setDialogTitle(dialogName);
//        fileChooser.setCurrentDirectory(new File("."));  // установка директории старта по умолчанию
        fileChooser.setCurrentDirectory(new File("D:/"));  // установка директории старта по умолчанию
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);                             
        fileChooser.showOpenDialog(null);
        
        File chooseCDirectory = fileChooser.getSelectedFile();

        return chooseCDirectory;
    }
}
