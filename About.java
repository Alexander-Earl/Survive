import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.JLabel; 

    public class About extends GUI{
    
      public About() {
            BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            setLayout(boxlayout);
            setSize(500,500);
            
            Button back_button = new Button("BACK");
            add(back_button);
            
            JLabel game_rules = new JLabel(FileReader.readFile("About.txt"));
            add(game_rules);
            
            JScrollPane scroll_bar = new JScrollPane();
            scroll_bar.setViewportView(game_rules);
            add(scroll_bar);
            
            back_button.addActionListener((new ActionListener(){ 
                public void actionPerformed(ActionEvent evt) {
                    WorldGUIFrame.main();
                    dispose();
                    } 
                }
                ));
      }
}