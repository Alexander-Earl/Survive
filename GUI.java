import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  
  public abstract class GUI extends Frame{
      
      
      public GUI() {
          setLayout(null);
          setTitle("Survival");
          setSize(500,500);
          setLocationRelativeTo(null);
          setVisible(true);
          addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent ev) {
             System.exit(0);
            }
          });
      }
 
}