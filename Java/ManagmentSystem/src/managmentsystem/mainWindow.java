package managmentsystem;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author gerosantacruz
 */
public class mainWindow extends javax.swing.JFrame {

    /**
     * Creates new form mainWindow
     */
    public mainWindow() {
        initComponents();
        getConnection();
        ShowTheListIJpanel();
    }
    
    String imgPath = null;
    int pos = 0;
    
    public Connection getConnection(){
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/productos_db","monty","password");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(mainWindow.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Disconnected");
            return null;

        }
    }
    
    
    public class Product{
        private int id;
        private String name;
        private float price;
        private String addDate;
        private byte[] picture;
        
        public Product(int pid,String pname, float pprice, String pdate, byte[] pimg){
            this.id = pid;
            this.name=pname;
            this.price = pprice;
            this.addDate= pdate;
            this.picture =pimg;
            
        }

        /**
         * @return the id
         */
        public int getId() {
            return id;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @return the price
         */
        public float getPrice() {
            return price;
        }

        /**
         * @return the addDate
         */
        public String getAddDate() {
            return addDate;
        }

        /**
         * @return the picture
         */
        public byte[] getPicture() {
            return picture;
        }
    }
    // check the input fields
    
    public boolean checkInputs(){
        if(txt_name.getText()  == null
            || txt_price.getText() == null
            || txt_date.getText() == null){
            return false;
        }else{
            try{
                Float.parseFloat(txt_price.getText());
                return true;
            }catch(Exception ex){
                return false;
                
            }
        }
    }
    
    // reize image
    
    public ImageIcon ResizeImage(String imagePath, byte[] picture){
        ImageIcon myImage = null;
        
        if(imagePath != null){
            myImage = new ImageIcon(imagePath);
        }else{
            myImage = new ImageIcon(picture);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lab_img.getWidth(), 
                lab_img.getHeight(), Image.SCALE_SMOOTH);
        
        ImageIcon image = new ImageIcon(img2);
        
        return image;
        
    }
    
    
    //display data in Jtable
    
    //fill the array with data
    
    public ArrayList<Product> getProductList(){
        ArrayList<Product> productList = new ArrayList<Product>();
            Connection con = getConnection();
            
            String query = "SELECT * FROM products";
            
            Statement st;
            ResultSet rs;
            
            
        try {                        
            st = con.createStatement();
            rs = st.executeQuery(query);
            Product product;
            
            while(rs.next()){
                product = new Product(rs.getInt("id"), rs.getString("name"),
                Float.parseFloat(rs.getString("price")), rs.getString("add_date"), rs.getBytes("img"));
                
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }
    
    //pupulate the list
    
    public void ShowTheListIJpanel(){
        
        ArrayList<Product> list = getProductList();
        DefaultTableModel model = (DefaultTableModel) table_pro.getModel();
        
        //clear jtable
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++){
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddDate();
            
            model.addRow(row);
        }
        
    }
    
    public void ShowItem(int index){
        txt_id.setText(Integer.toString(getProductList().get(index).getId()));
        txt_name.setText((getProductList().get(index).getName()));
        txt_price.setText(Float.toString(getProductList().get(index).getPrice()));
        txt_date.setText(getProductList().get(index).getAddDate());
        lab_img.setIcon(ResizeImage(null, getProductList().get(index).getPicture()));
            
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_date = new javax.swing.JFormattedTextField();
        lab_img = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_pro = new javax.swing.JTable();
        btn_choose_img = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Ubuntu Mono", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Ubuntu Mono", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("NAME:");

        jLabel3.setFont(new java.awt.Font("Ubuntu Mono", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("PRICE:");

        jLabel4.setFont(new java.awt.Font("Ubuntu Mono", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("DATE:");

        jLabel5.setFont(new java.awt.Font("Ubuntu Mono", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("IMG:");

        txt_id.setBackground(new java.awt.Color(204, 204, 204));
        txt_id.setForeground(new java.awt.Color(0, 0, 0));
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        txt_name.setBackground(new java.awt.Color(204, 204, 204));
        txt_name.setForeground(new java.awt.Color(0, 0, 0));
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        txt_price.setBackground(new java.awt.Color(204, 204, 204));
        txt_price.setForeground(new java.awt.Color(0, 0, 0));
        txt_price.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txt_date.setBackground(new java.awt.Color(204, 204, 204));
        txt_date.setForeground(new java.awt.Color(0, 0, 0));
        txt_date.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lab_img.setOpaque(true);

        table_pro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Date"
            }
        ));
        table_pro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_proMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_pro);

        btn_choose_img.setText("Browser");
        btn_choose_img.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_choose_imgActionPerformed(evt);
            }
        });

        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/list-add.png"))); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/process-stop.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_first.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/go-first.png"))); // NOI18N
        btn_first.setText("First");
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/view-refresh.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/go-previous.png"))); // NOI18N
        btn_prev.setText("Previous ");
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });

        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/go-next.png"))); // NOI18N
        btn_next.setText("Next");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/go-last.png"))); // NOI18N
        btn_last.setText("Last");
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_insert)
                        .addGap(20, 20, 20)
                        .addComponent(btn_update)
                        .addGap(18, 18, 18)
                        .addComponent(btn_delete))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_date))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_id))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_price))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_choose_img)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lab_img, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btn_first)
                        .addGap(18, 18, 18)
                        .addComponent(btn_prev)
                        .addGap(18, 18, 18)
                        .addComponent(btn_next)
                        .addGap(18, 18, 18)
                        .addComponent(btn_last)))
                .addContainerGap(426, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lab_img, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_choose_img)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_insert)
                    .addComponent(btn_delete)
                    .addComponent(btn_update)
                    .addComponent(btn_first)
                    .addComponent(btn_prev)
                    .addComponent(btn_next)
                    .addComponent(btn_last))
                .addContainerGap(334, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        
        if( checkInputs() && imgPath != null){
            Connection con = getConnection();
            try {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO products(name, price, add_date, img) values(?,?,?,?)");
                ps.setString(1, txt_name.getText());
                ps.setString(2, txt_price.getText());
                ps.setString(3, txt_date.getText());
                
                InputStream img = new FileInputStream(new File(imgPath));
                ps.setBlob(4, img);
                
                ps.executeUpdate();
                ShowTheListIJpanel();
                JOptionPane.showMessageDialog(null, "Data Inserted");
                
                
                
                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                //String addDate = dateFormat.format(txt_date.getDate());
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } 
        } else{
            JOptionPane.showMessageDialog(null, "Error: "
                    + "One or more fields are empty.");
        }
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        
        if(!txt_id.getText().equals("")){
            
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE "
                        + "FROM products WHERE id= ?");
                int id = Integer.parseInt(txt_id.getText());
                
                ps.setInt(1, id);
                
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Data Deleted");
            } catch (SQLException ex) {
                Logger.getLogger(mainWindow.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Prdouct not deleted");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Prdouct not deleted: not id");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos =0;
            ShowItem(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        //check if the inputs are completed
        
        if(checkInputs() && txt_id.getText() != null){
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
            //update without image
            
            if(imgPath == null){
                try {
                    UpdateQuery = "UPDATE products SET name = ?, price = ?"
                            + ", add_date = ? WHERE id = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, txt_name.getText());
                    ps.setString(2, txt_price.getText());
                    ps.setString(3, txt_date.getText());
                    
                    ps.setInt(4, Integer.parseInt(txt_id.getText()));
                    
                    ps.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Data Updated");
                    
                    ShowTheListIJpanel();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(mainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                try {
                    //update with image
                    InputStream img = new FileInputStream(new File(imgPath));
                    UpdateQuery = "UPDATE products SET name = ?, price = ?"
                            + ", add_date = ? , image=? WHERE id = '";
                    
                        ps.setString(1, txt_name.getText());
                        ps.setString(2, txt_price.getText());
                        ps.setString(3, txt_date.getText());
                        ps.setBlob(4, img);
                        ps.setInt(5, Integer.parseInt(txt_id.getText()));
                        
                        ps.executeUpdate();
                        ShowTheListIJpanel();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                
            } 
                    
        }else{
            JOptionPane.showMessageDialog(null, "One or more fields "
                    + "are emptyor wrong");
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        // TODO add your handling code here:
        pos--;
        if(pos <0){
            pos = 0;
        }
        
        ShowItem(pos);
    }//GEN-LAST:event_btn_prevActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        
        pos++;
        if(pos >= getProductList().size()){
            
            pos = getProductList().size()-1;
        }
        
        ShowItem(pos);
        
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        // TODO add your handling code here:
        pos = getProductList().size()-1;
            ShowItem(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_choose_imgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_choose_imgActionPerformed
        
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "png", "jpg");
        file.addChoosableFileFilter(filter);
        
        int result = file.showSaveDialog(null);
        
        if( result == JFileChooser.APPROVE_OPTION ){
            
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lab_img.setIcon(ResizeImage(path, null));
            imgPath = path;
            
        }else{
            System.out.println("No File Selelected");
        }
    }//GEN-LAST:event_btn_choose_imgActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void table_proMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_proMouseClicked
        // TODO add your handling code here:
        
        int index = table_pro.getSelectedRow();
        
        ShowItem(index);
    }//GEN-LAST:event_table_proMouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_choose_img;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lab_img;
    private javax.swing.JTable table_pro;
    private javax.swing.JFormattedTextField txt_date;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
