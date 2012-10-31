package pkginterface;

import javax.swing.UIManager;

/**
 *
 * @author Pyciak
 */
public class Interface
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
      try
      {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch(Exception e){}  
      mainWindow window = new mainWindow();
      window.setVisible(true);
    }
}
