package dmroy.luxoft.client;

import dmroy.luxoft.client.gui.MainFrame;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import static org.springframework.context.i18n.LocaleContextHolder.setLocale;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author dmitriyroy
 */
public class Main {

    public static String SERVER_URL;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SERVER_URL = "http://localhost:8080/luxoft-web";

        new ClassPathXmlApplicationContext("beans.xml");

        try {
            // устанавливаем русскую раскладку в приложении
            Locale loc = new Locale("ru","RU");
            setLocale(loc);

            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            new InitMainFrame().run();

        }
            catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        }

    }
    public static class InitMainFrame implements Runnable{

        public InitMainFrame(){
        }
            @Override
            public void run(){
            try {
                new MainFrame();
            } catch (BadLocationException | SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
