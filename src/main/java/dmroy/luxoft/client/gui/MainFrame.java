package dmroy.luxoft.client.gui;

import java.awt.*;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

public class MainFrame extends JFrame{

    // размещаем окно на весь монитор
    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;
    private final int LOCATION_X;
    private final int LOCATION_Y;
    ////////////////////////////
    public static Container mainContainer = null;
//    public static JMenuBar menu = null;
    public static JPanel startPanel = null;

    public MainFrame() throws BadLocationException, SQLException{
        // устанавливаем русскую раскладку в приложении
        Locale loc = new Locale("ru","RU");
        setLocale(loc);
        getInputContext().selectInputMethod(loc);
        // размещаем окно на полэкрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        FRAME_WIDTH = screenSize.width/2 - 100 ;
        FRAME_HEIGHT = (int)(rect.getBounds().getHeight() - getSize().getHeight())/2 - 200 ;
        LOCATION_X = FRAME_WIDTH / 2;
        LOCATION_Y = FRAME_HEIGHT / 2;
        ////////////////////////////

       // НАЧАЛО - меню программы
//        menu = new MainMenu().getMenu();
        startPanel = new StartPanel().getPanel();

        mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());
        setTitle("Сбор статистики файлов.");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocation(LOCATION_X, LOCATION_Y);
        setMinimumSize(new Dimension(800, 600));
        setResizable(false);
        setVisible(true);
//        mainContainer.add(menu, BorderLayout.NORTH);
        mainContainer.add(startPanel, BorderLayout.CENTER, 0);
        // КОНЕЦ - центральный фрейм программы

    }



}
