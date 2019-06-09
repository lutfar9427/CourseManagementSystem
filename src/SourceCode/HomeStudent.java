/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.GeminiSkin;

/**
 *
 * @author Hak Srun Lao
 */
public class HomeStudent extends javax.swing.JFrame {
    Connection connect;
    PreparedStatement prepare;
    ResultSet result;
    String realName;

    /**
     * Creates new form HomeStudent
     */
    public HomeStudent() {
        initComponents();
        connect = Functions.connectToDatabase();
        setInitial();
    }
    
    public final void setInitial() {
        Functions.initialFrame(this);
        setWelcome();
        addCourse();
        showCourse();
        setTableModel();
        callAdd();
        Functions.logOut(this, jButtonLogOut);
    }
    
    public void setWelcome() {
        String getRealNameSQL  = "select RealName from Account where UserName = ?";
        String userName = Login.jTextFieldUserName.getText();
        try {
            prepare = connect.prepareStatement(getRealNameSQL);
            prepare.setString(1, userName);
            result = prepare.executeQuery();
            if(result.next()) {
                realName = result.getString("RealName");
                jLabelWelcome.setText("****** Welcome " + realName + " ******");
            }
        } catch(SQLException ex) {
            Logger.getLogger(HomeStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTableModel() {
        jTableResult.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 14));
        TableColumn column;
        column = jTableResult.getColumnModel().getColumn(0);
        column.setPreferredWidth(300);
        column = jTableResult.getColumnModel().getColumn(1);
        column.setPreferredWidth(100);
    }
    
    public void addCourse() {
        String addSQL = "select CourseName from Course";
        try {
            prepare = connect.prepareStatement(addSQL);
            result = prepare.executeQuery();
            while(result.next()) {
                String courseName = result.getString("CourseName");
                jComboBoxSelect.addItem(courseName);
            }
        } catch(SQLException ex) {
            Logger.getLogger(HomeTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showCourse() {
        String showSQL = "select CourseName, Mark from Result where StudentName = ?";
        try {
            prepare = connect.prepareStatement(showSQL);
            prepare.setString(1, realName);
            result = prepare.executeQuery();
            jTableResult.setModel(DbUtils.resultSetToTableModel(result));
        } catch(SQLException ex) {
            Logger.getLogger(HomeStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addFunction() {
        String studentSQL = "select UserName from Account where RealName = ?";
        String courseSQL = "select CourseID from Course where CourseName = ?";
        String addSQL = "insert into Result(ResultCode, StudentID, StudentName, CourseID, CourseName, Mark) "
                + "values(?, ?, ?, ?, ?, ?)";
        String coureName = (String)jComboBoxSelect.getSelectedItem();
        String studentID = null;
        String courseID = null;
        String resultCode;
        try {
            prepare = connect.prepareStatement(studentSQL);
            prepare.setString(1, realName);
            result = prepare.executeQuery();
            if(result.next()) {
                studentID = result.getString("UserName");
            }
            prepare = connect.prepareStatement(courseSQL);
            prepare.setString(1, coureName);
            result = prepare.executeQuery();
            if(result.next()) {
                courseID = result.getString("CourseID");
            }
            resultCode = studentID + "-" + courseID;
            prepare = connect.prepareStatement(addSQL);
            prepare.setString(1, resultCode);
            prepare.setString(2, studentID);
            prepare.setString(3, realName);
            prepare.setString(4, courseID);
            prepare.setString(5, coureName);
            prepare.setString(6, "");
            prepare.execute();
            JOptionPane.showMessageDialog(null, "Course added!");
        } catch(SQLException ex) {
            Logger.getLogger(HomeStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void callAdd() {
        jButtonAdd.addActionListener((ActionEvent e) -> {
            addFunction();
            showCourse();
            setTableModel();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelWelcome = new javax.swing.JLabel();
        jSeparatorWelcome = new javax.swing.JSeparator();
        jLabelSelect = new javax.swing.JLabel();
        jComboBoxSelect = new javax.swing.JComboBox<>();
        jButtonAdd = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jTableResult = new javax.swing.JTable();
        jButtonLogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CMS - Student");

        jLabelWelcome.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabelWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWelcome.setText("Welcome");
        jLabelWelcome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelSelect.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelSelect.setText("Please a course to add into your time table:");

        jComboBoxSelect.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBoxSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        jButtonAdd.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonAdd.setText("Add");

        jTableResult.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTableResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Name", "Mark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableResult.setRowHeight(25);
        jScrollPane.setViewportView(jTableResult);

        jButtonLogOut.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonLogOut.setText("Log Out");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addComponent(jSeparatorWelcome))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparatorWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabelSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButtonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HomeStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            SubstanceLookAndFeel.setSkin(new GeminiSkin());
            new HomeStudent().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonLogOut;
    private javax.swing.JComboBox<String> jComboBoxSelect;
    private javax.swing.JLabel jLabelSelect;
    private javax.swing.JLabel jLabelWelcome;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JSeparator jSeparatorWelcome;
    private javax.swing.JTable jTableResult;
    // End of variables declaration//GEN-END:variables
}
