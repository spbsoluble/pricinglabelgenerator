
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Sean
 */
public class PreviewPanel extends JFrame{
    private BufferedImage image;
    private Image img;
    private String filePath = "";
    private static final Dimension MIN_SIZE = new Dimension(100,100);
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static final Dimension MAX_SIZE = tk.getScreenSize();
    private static final int MAINTAIN_ASPECT = -1;
    private static final int SCALE_FAST = 2;
    
    public PreviewPanel(String windowTitle){
        this.setTitle(windowTitle);
        filePath = windowTitle;
        createFrame();
    }

    private void createFrame(){
        try {
            image = ImageIO.read(new File(filePath));
            int width = image.getWidth();
            int height = image.getHeight();
            Dimension preferredSize = new Dimension(width,height);
            
            JLabel picLabel;
            
            //scale logic
            if((width > MAX_SIZE.width) && !(height > MAX_SIZE.height)){
                img = image.getScaledInstance(MAX_SIZE.width, MAINTAIN_ASPECT, SCALE_FAST);
                picLabel = new JLabel(new ImageIcon(img));
            } else if(!(width > MAX_SIZE.width) && (height > MAX_SIZE.height)){
                img = image.getScaledInstance(MAINTAIN_ASPECT, MAX_SIZE.width, SCALE_FAST);
                picLabel = new JLabel(new ImageIcon(img));
            } else if((width > MAX_SIZE.width) && (height > MAX_SIZE.height)){
                img = image.getScaledInstance(MAX_SIZE.height, MAX_SIZE.width, SCALE_FAST);
                picLabel = new JLabel(new ImageIcon(img));
            } else {
                picLabel = new JLabel(new ImageIcon(image));
            }
            
            picLabel.setPreferredSize(preferredSize);
            this.add(picLabel);
            
            this.setPreferredSize(preferredSize);
            this.setMaximumSize(MAX_SIZE);
            this.setMinimumSize(MIN_SIZE);
            this.setDefaultCloseOperation(HIDE_ON_CLOSE);
            
            this.pack();
            this.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PreviewPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
