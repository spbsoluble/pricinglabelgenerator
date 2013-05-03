
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GUI.java
 *
 * Created on May 29, 2012, 11:53:40 AM
 */
/**
 *
 * @author Sean
 */
public class GUI extends javax.swing.JFrame {

    private static final int [] colWidths = {300,150,300,250,235};
    private static final int numberOfCols = 7;
    private static boolean productFieldClicked = false;
    private Product currentProduct;
    private PricingList pricingList;
    Connection dbConnection;
    
    /** Creates new form GUI */
    public GUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_fileChooser = new javax.swing.JFileChooser();
        product_lbl = new javax.swing.JLabel();
        productID_tf = new javax.swing.JTextField();
        newLabel_btn = new javax.swing.JButton();
        openLabel_btn = new javax.swing.JButton();
        saveLabel_btn = new javax.swing.JButton();
        printLabel_btn = new javax.swing.JButton();
        priceType_cb = new javax.swing.JComboBox();
        pricingType_lbl = new javax.swing.JLabel();
        file_menuBar = new javax.swing.JMenuBar();
        file_menu = new javax.swing.JMenu();
        newLabel_menuItem = new javax.swing.JMenuItem();
        openLabel_menuItem = new javax.swing.JMenuItem();
        saveLabel_menuItem = new javax.swing.JMenuItem();
        printLabel_menuItem = new javax.swing.JMenuItem();
        Profiles = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pricing Label Creator");
        setMinimumSize(new java.awt.Dimension(690, 100));
        setName("gui_frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(690, 50));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        product_lbl.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        product_lbl.setText("Product:");

        productID_tf.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        productID_tf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productID_tfMouseClicked(evt);
            }
        });
        productID_tf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                productID_tfFocusLost(evt);
            }
        });
        productID_tf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                productID_tfKeyTyped(evt);
            }
        });

        newLabel_btn.setFont(new java.awt.Font("DialogInput", 0, 14)); // NOI18N
        newLabel_btn.setText("New");
        newLabel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLabel_btnActionPerformed(evt);
            }
        });

        openLabel_btn.setFont(new java.awt.Font("DialogInput", 0, 14)); // NOI18N
        openLabel_btn.setText("Open");
        openLabel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openLabel_btnActionPerformed(evt);
            }
        });

        saveLabel_btn.setFont(new java.awt.Font("DialogInput", 0, 14)); // NOI18N
        saveLabel_btn.setText("Save");
        saveLabel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveLabel_btnActionPerformed(evt);
            }
        });

        printLabel_btn.setFont(new java.awt.Font("DialogInput", 0, 14)); // NOI18N
        printLabel_btn.setText("Print");
        printLabel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printLabel_btnActionPerformed(evt);
            }
        });

        priceType_cb.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        priceType_cb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MSRP", "Wholesale", "Distributor" }));
        priceType_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceType_cbActionPerformed(evt);
            }
        });
        priceType_cb.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                priceType_cbPropertyChange(evt);
            }
        });

        pricingType_lbl.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        pricingType_lbl.setText(" Pricing:");

        file_menu.setText("File");

        newLabel_menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newLabel_menuItem.setText("New Label");
        newLabel_menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLabel_menuItemActionPerformed(evt);
            }
        });
        file_menu.add(newLabel_menuItem);

        openLabel_menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openLabel_menuItem.setText("Open Label");
        openLabel_menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openLabel_menuItemActionPerformed(evt);
            }
        });
        file_menu.add(openLabel_menuItem);

        saveLabel_menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveLabel_menuItem.setText("Save");
        saveLabel_menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveLabel_menuItemActionPerformed(evt);
            }
        });
        file_menu.add(saveLabel_menuItem);

        printLabel_menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        printLabel_menuItem.setText("Print");
        printLabel_menuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printLabel_menuItemActionPerformed(evt);
            }
        });
        file_menu.add(printLabel_menuItem);

        file_menuBar.add(file_menu);

        Profiles.setText("Profiles");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("New Profile");
        Profiles.add(jMenuItem1);

        file_menuBar.add(Profiles);

        setJMenuBar(file_menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(product_lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productID_tf))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addComponent(newLabel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openLabel_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveLabel_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printLabel_btn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pricingType_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceType_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {newLabel_btn, openLabel_btn, printLabel_btn, saveLabel_btn});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(product_lbl)
                            .addComponent(productID_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newLabel_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(openLabel_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(saveLabel_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(printLabel_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                    .addComponent(pricingType_lbl)
                    .addComponent(priceType_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {newLabel_btn, openLabel_btn, printLabel_btn, productID_tf, saveLabel_btn});

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-702)/2, (screenSize.height-151)/2, 702, 151);
    }// </editor-fold>//GEN-END:initComponents

private void newLabel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLabel_btnActionPerformed
    this.productID_tf.setText("");
    this.productID_tf.requestFocus();
}//GEN-LAST:event_newLabel_btnActionPerformed

private void openLabel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openLabel_btnActionPerformed
    
}//GEN-LAST:event_openLabel_btnActionPerformed

private void saveLabel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveLabel_btnActionPerformed

}//GEN-LAST:event_saveLabel_btnActionPerformed

private void printLabel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printLabel_btnActionPerformed
        try {
            //loadProduct(this.productID_tf.getText().trim(), this.priceType_cb.getSelectedItem().toString(), this.discount_tf.getText(),this.discountType_cb.getSelectedItem().toString().charAt(0));
            currentProduct = loadCurrentProduct(this.productID_tf.getText());
            generatePDF(System.getProperty("user.home")+"\\Documents\\Price Labels\\pdfs\\"+currentProduct.getSKU()+"-"+this.priceType_cb.getSelectedItem().toString()+".pdf");
            launchLabel(currentProduct.getSKU()+"-"+this.priceType_cb.getSelectedItem().toString()+".pdf");
        } catch (DocumentException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (MalformedURLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_printLabel_btnActionPerformed

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    createFileDirectory();
    pricingList = new PricingList();
}//GEN-LAST:event_formWindowOpened

private void productID_tfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productID_tfKeyTyped
   int keyCode = evt.getKeyCode();
   System.out.println(keyCode);
   if(keyCode == 0){
       
       
   }
}//GEN-LAST:event_productID_tfKeyTyped

private void newLabel_menuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLabel_menuItemActionPerformed
    newLabel_btnActionPerformed(evt);
}//GEN-LAST:event_newLabel_menuItemActionPerformed

private void openLabel_menuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openLabel_menuItemActionPerformed
     openLabel_btnActionPerformed(evt);
}//GEN-LAST:event_openLabel_menuItemActionPerformed

private void saveLabel_menuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveLabel_menuItemActionPerformed
    saveLabel_btnActionPerformed(evt);
}//GEN-LAST:event_saveLabel_menuItemActionPerformed

private void printLabel_menuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printLabel_menuItemActionPerformed
    printLabel_btnActionPerformed(evt);
}//GEN-LAST:event_printLabel_menuItemActionPerformed

private void productID_tfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productID_tfMouseClicked
    if(!productFieldClicked){
        this.productID_tf.selectAll();
        productFieldClicked = true;
    }
}//GEN-LAST:event_productID_tfMouseClicked

private void productID_tfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_productID_tfFocusLost
    productFieldClicked = false;
}//GEN-LAST:event_productID_tfFocusLost

private void priceType_cbPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_priceType_cbPropertyChange
    
}//GEN-LAST:event_priceType_cbPropertyChange

    private void priceType_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceType_cbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceType_cbActionPerformed

private Product loadCurrentProduct(String sku_upc){
    return pricingList.findProduct(sku_upc);
}

private boolean createFileDirectory(){
    new File(System.getProperty("user.home")+"\\Documents\\Price Labels\\temp").mkdirs();
    return (new File(System.getProperty("user.home")+"\\Documents\\Price Labels\\pdfs")).mkdirs();
}



/*
 * Don't touch this works so well
 */
private void generatePDF(String fileName) throws DocumentException, FileNotFoundException, BadElementException, MalformedURLException, IOException{
    Rectangle pageSize = new Rectangle(252,72); //3.5" x 1"
    Document document = new Document(pageSize);
    document.setMargins(4, 1, 0, 0);
    
        /*
         * step 2
         * Create writer object and create the actual PDF file.
         */
        PdfWriter writer;
        
        System.out.println(fileName);
        try{
            writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        } catch (FileNotFoundException fnf) {
           boolean success = createFileDirectory();
            if (!success) {
              writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            } else {
                System.err.println("Unable to make directory");
                return;
            }
        }
      
        /*
         * step 3
         * Open the document by opening that actual PDF file
         */
        document.open();
        //layout table
        PdfPTable table = new PdfPTable(new float[] {2,10,3});
        table.setWidthPercentage(100);
        
        PdfPCell blankCell = new PdfPCell();
        blankCell.addElement(new Paragraph(""));
        
        blankCell.setBorder(0);
        
        //logo cell
        PdfPCell logoCell = new PdfPCell();
        logoCell.setPadding(0);
        logoCell.setBorder(0);
        //logoCell.setFixedHeight(70);
         Image pdfLogo;
        /*if(this.profileSelector.getSelectedItem().toString().equals("EZEL")){
            //File logoFile = new File
            pdfLogo =Image.getInstance("ezellogovert-fat.png");
        } else {
            pdfLogo =Image.getInstance("s:/logos/seattleBookslogo.png");
        }
        */
        pdfLogo =Image.getInstance("ezellogovert-fat.png");
        //cell.addElement(pdfLogo);
        logoCell.setImage(pdfLogo);
        
        table.addCell(logoCell);
        
        //barcode cell
        
        //barcode cell nested table
        PdfPTable barcodeTable = new PdfPTable(new float[] {10});
        
        //nested table cells
        PdfPCell productNameCell = new PdfPCell();
        productNameCell.setBorder(0);
        productNameCell.setFixedHeight(68);
        productNameCell.setPadding(0);
        productNameCell.setIndent(0);
        productNameCell.setLeading(0, 0);
        productNameCell.setUseBorderPadding(false);
        System.out.println(productNameCell.getPaddingTop());
        productNameCell.setBorder(0);
        
        PdfPCell barcodeCell = new PdfPCell();
        
        //Barcode object
        BarcodeEAN code = new BarcodeEAN();
        code.setCodeType(Barcode.UPCA);
        //Barcode128 code = new Barcode128();
        code.setCode(currentProduct.getUPC());
        code.setX(1);
        code.setBarHeight(10);
        //code.setFont(null);
        PdfContentByte cb = writer.getDirectContent();
        Image barcode = code.createImageWithBarcode(cb, null, null);
        
        //Product Name
        String productName = currentProduct.getProductName();
        Font productNameFont = FontFactory.getFont("Arial", 13, Font.BOLD);
        Paragraph productNameParagraph = new Paragraph(productName, productNameFont);
        //productNameParagraph.setAlignment(Element.ALIGN_BOTTOM);
        productNameParagraph.setExtraParagraphSpace(0);
        productNameParagraph.setFirstLineIndent(0);
        productNameParagraph.setSpacingBefore(0);
        productNameParagraph.setSpacingAfter(0);
        
        //add to cells
        productNameCell.addElement(productNameParagraph);
        productNameCell.addElement(barcode);
        barcodeCell.addElement(barcode);
        
        //add cells to nested table
        barcodeTable.addCell(productNameCell);
        //barcodeTable.addCell(barcodeCell);
        
        //add nested table to the table
        barcodeTable.setWidthPercentage(100);
        PdfPCell barcodeTableCell = new PdfPCell();
        barcodeTableCell.setBorder(0);
        barcodeTable.setSpacingAfter(0);
        barcodeTable.setSpacingBefore(0);
        
        barcodeTableCell.addElement(barcodeTable);
        table.addCell(barcodeTableCell);
        
        //price cell
        PdfPTable priceTable = new PdfPTable(new float [] {5});
        DecimalFormat format = new DecimalFormat("0.00");
        
        PdfPCell calcPriceCell = new PdfPCell();
        PdfPCell msrpCell = new PdfPCell();
        
        
        float msrp = currentProduct.getMSRP();
        float price = 0; 
        
        if(this.priceType_cb.getSelectedItem().toString().equals("Wholesale")){
            price = currentProduct.getWholesale();
        } else if(this.priceType_cb.getSelectedItem().toString().equals("Distributor")) {
            price = currentProduct.getDistributor();
        } else {
            price = msrp;
        }
        String sku = currentProduct.getSKU();
        
        //fonts
        Font msrpFont = FontFactory.getFont("Arial", 6 );
        Font priceFont = FontFactory.getFont("Arial", 14,Font.BOLD);
        Font skuFont = FontFactory.getFont("Arial", 4, Font.BOLD);
        
        Paragraph priceParagraph = new Paragraph("$"+format.format(price), priceFont);
        Paragraph msrpParagraph;
        if(! (price == msrp)){
            msrpParagraph = new Paragraph("MSRP: $"+format.format(msrp),msrpFont);
        } else {
            msrpParagraph = new Paragraph("",msrpFont);
        }
        Paragraph skuParagraph = new Paragraph(sku, skuFont);
        
        priceParagraph.setAlignment(Element.ALIGN_CENTER);
        msrpParagraph.setAlignment(Element.ALIGN_CENTER);
        skuParagraph.setAlignment(Element.ALIGN_CENTER);
        
        calcPriceCell.addElement(priceParagraph);
        calcPriceCell.addElement(msrpParagraph);
        calcPriceCell.addElement(skuParagraph);
        calcPriceCell.setBorder(0);
        calcPriceCell.setPaddingTop(15);
        
        msrpCell.addElement(msrpParagraph);
        msrpCell.setBorder(0);
        
        priceTable.addCell(blankCell);
        priceTable.addCell(blankCell);
        priceTable.addCell(blankCell);
        priceTable.addCell(calcPriceCell);
        priceTable.addCell(msrpCell);
        table.addCell(calcPriceCell);
        
        //add table to document
        document.add(table);
        
        writer.setOpenAction(new PdfAction(PdfAction.PRINTDIALOG)); //make it so print dialog opens when pdf opens
        document.close();
}

/*
 * Open the label after it has been created in adobe reader
 */
private void launchLabel(String fileName){
      if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(System.getProperty("user.home")+"\\Documents\\Price Labels\\pdfs\\"+fileName);
                    Desktop.getDesktop().open(myFile);
                } catch (FileNotFoundException fnf){
                    JOptionPane.showMessageDialog(null,
                        "Please close the PDF file before printing a new one",
                        "File access error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IOException ioe) {
                      JOptionPane.showMessageDialog(null,
                        "Error generating PDF file, please restart program and try again.",
                        "IO Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                } 
    }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                GUI gui = new GUI();
                BufferedImage image = null; //image object to hold favicon image
                
               try{                         //set the icon in the upper left corner
                   image = ImageIO.read(gui.getClass().getResource("unixfavicon.png")); 
                }
                catch(IOException e){
                    System.err.println("Error file not found");
                    e.printStackTrace();
                }
               gui.setIconImage(image);
                gui.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Profiles;
    private javax.swing.JMenu file_menu;
    private javax.swing.JMenuBar file_menuBar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JFileChooser label_fileChooser;
    private javax.swing.JButton newLabel_btn;
    private javax.swing.JMenuItem newLabel_menuItem;
    private javax.swing.JButton openLabel_btn;
    private javax.swing.JMenuItem openLabel_menuItem;
    private javax.swing.JComboBox priceType_cb;
    private javax.swing.JLabel pricingType_lbl;
    private javax.swing.JButton printLabel_btn;
    private javax.swing.JMenuItem printLabel_menuItem;
    private javax.swing.JTextField productID_tf;
    private javax.swing.JLabel product_lbl;
    private javax.swing.JButton saveLabel_btn;
    private javax.swing.JMenuItem saveLabel_menuItem;
    // End of variables declaration//GEN-END:variables
}
