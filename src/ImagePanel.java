import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ActionListener{

    private BufferedImage image;
    private Image img;
    private String imageFilePath = "";
    public int order = 0;
    
    private static final int MAX_WIDTH = 125;
    private static final int MAX_HEIGHT = 125;
    private static final int MAX_SCALED_HEIGHT = 125;
    private static final int MAX_SCALED_WIDTH = 125;
    private static final int BUTTON_VERTICAL_OFFSET = 20;
    private static final int BUTTON_HORIZONTAL_OFFSET = 125;
    private static final int MAINTAIN_ASPECT = -1;
    private static final int SCALE_FAST = 2;
    
    private static final String PLACEHOLDER_IMAGE = "images/placeholder.jpg";
    
    public ImagePanel(int order){
        createPanel(PLACEHOLDER_IMAGE);
        this.order = order;
    }
    
    public ImagePanel(int order, String filePath) {
       this.order = order;
       this.imageFilePath = filePath;
        createPanel(filePath);
    }
    
    private void createPanel(String filePath){
       try {        
            this.setLayout(null);
            image = ImageIO.read(new File(filePath));
            int width = image.getWidth();
            int height = image.getHeight();
            
            JLabel picLabel;
            JButton enlarge_btn;
            JButton remove_btn;
            //scaling logic
            if((height > MAX_HEIGHT) && !(width > MAX_WIDTH)){
                img = image.getScaledInstance(MAX_SCALED_HEIGHT,MAINTAIN_ASPECT,SCALE_FAST);
            } else if (!(height > MAX_HEIGHT) && (width > MAX_WIDTH)){
                img = image.getScaledInstance(MAINTAIN_ASPECT,MAX_SCALED_WIDTH,SCALE_FAST);
            } else if((height > MAX_HEIGHT) && (width > MAX_WIDTH)){
                img = image.getScaledInstance(MAX_SCALED_HEIGHT,MAINTAIN_ASPECT,SCALE_FAST);
            } else {
                picLabel = new JLabel(new ImageIcon(img));
            }
            
            //create panel components
            JLabel order_lbl = new JLabel(""+(order+1));
            order_lbl.setFont(new Font("Verdana",Font.BOLD, 18));
            
            //order_lbl.setOpaque(true);
            picLabel = new JLabel(new ImageIcon(img));
            enlarge_btn = new JButton("Enlarge"); 
            remove_btn = new JButton("Remove");
            
            //add panel components
            this.add(picLabel);
            this.add(enlarge_btn);
            this.add(remove_btn);
            //this.add(order_lbl);
            
            //get relative coordinate system in the panel
            Insets insets = this.getInsets();
            
            //System.err.println("width" + img.getWidth(this) + " " + "height" + img.getWidth(this));
            
            //set position of components in the panel
            picLabel.setBounds(insets.left, insets.top, img.getHeight(this), img.getWidth(this));
            enlarge_btn.setBounds(insets.left, insets.top + MAX_SCALED_HEIGHT, BUTTON_HORIZONTAL_OFFSET, BUTTON_VERTICAL_OFFSET);
            enlarge_btn.addActionListener(this);
            remove_btn.setBounds(insets.left, insets.top + MAX_SCALED_HEIGHT + BUTTON_VERTICAL_OFFSET, BUTTON_HORIZONTAL_OFFSET, BUTTON_VERTICAL_OFFSET);
            remove_btn.addActionListener(this);
            
       } catch (IOException ex) {
            System.err.println("Failed to create image panel" + ex.toString());
       } 
    }
    
    public String getFilePath(){
        return this.imageFilePath;
    }
    
    private void enlarge_btnActionPerformed(java.awt.event.ActionEvent evt){
        System.out.println("Enlarge btn fired");
        if(!(imageFilePath == null) && !(imageFilePath.trim().equalsIgnoreCase(""))){
            PreviewPanel pp = new PreviewPanel(imageFilePath);
        }
        System.out.println(order);
    }
    
    private void remove_btnActionPerformed(java.awt.event.ActionEvent evt){
        System.out.println("Remove btn fired");
        System.out.println(order);
        
        //Destroy the old panel and recreate it
        this.removeAll();
        resetVars();
        createPanel(PLACEHOLDER_IMAGE);
    }

    private void resetVars(){
        imageFilePath = "";
        image = null;
        img = null;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.err.println(this.getWidth());
        //g.drawImage(img, 0, 0, null); // see javadoc for more info on the parameters            
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.paramString());
        String command = ae.getActionCommand();
        if(command.equalsIgnoreCase("enlarge")){
            enlarge_btnActionPerformed(ae);
        } else if(command.equalsIgnoreCase("remove")){
            remove_btnActionPerformed(ae);
        }
    }

}