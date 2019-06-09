/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.GeminiSkin;

/**
 *
 * @author HSL
 */
public class EditCourse extends javax.swing.JFrame {
    Connection connect;
    PreparedStatement prepare;
    ResultSet result;
    String courseID;

    /**
     * Creates new form EditCourse
     */
    public EditCourse() {
        initComponents();
        connect = Functions.connectToDatabase();
        setInitial();
    }
    
    public final void setInitial() {
        Functions.initialFrame(this);
        setTeacher();
        callSearch();
        callEdit();
        callDelete();
        callBack();
    }
    
    public void setTeacher() {
        String getTeacherSQL = "select RealName from Account where UserType = 'Teacher'";
        try {
            prepare = connect.prepareStatement(getTeacherSQL);
            result = prepare.executeQuery();
            while(result.next()) {
                String teacherName = result.getString("RealName");
                jComboBoxCourseTeacher.addItem(teacherName);
            }
        } catch(SQLException ex) {
            Logger.getLogger(AddCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void searchFunction() {
        String searchSQL = "select * from Course where CourseID = ?";
        courseID = jTextFieldCourseID.getText();
        try {
            prepare = connect.prepareStatement(searchSQL);
            prepare.setString(1, courseID);
            result = prepare.executeQuery();
            if(result.next()) {
                String courseName = result.getString("CourseName");
                String sections = result.getString("Sections");
                String courseTeacher = result.getString("CourseTeacher");
                jTextFieldCourseName.setText(courseName);
                jComboBoxSections.setSelectedItem(sections);
                jComboBoxCourseTeacher.setSelectedItem(courseTeacher);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Course ID!");
                jTextFieldCourseName.setText("");
                jComboBoxSections.setSelectedIndex(0);
                jComboBoxCourseTeacher.setSelectedIndex(0);
            }
        } catch(SQLException ex) {
            Logger.getLogger(EditCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void callSearch() {
        jButtonSearch.addActionListener((ActionEvent e) -> {
            searchFunction();
        });
    }
    
    public void editFunction() {
        String newCourseID = jTextFieldCourseID.getText();
        String courseName = jTextFieldCourseName.getText();
        String sections = (String)jComboBoxSections.getSelectedItem();
        String courseTeacher = (String)jComboBoxCourseTeacher.getSelectedItem();
        String editSQL = "update Course set CourseID = '"+newCourseID+"', CourseName = '"+courseName+"', Sections = '"+sections+"', "
                + "CourseTeacher = '"+courseTeacher+"' where CourseID = '"+courseID+"'";
        try {
            prepare = connect.prepareStatement(editSQL);
            prepare.execute();
            JOptionPane.showMessageDialog(null, "The Course updated!");
        } catch(SQLException ex) {
            Logger.getLogger(EditCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void callEdit() {
        jButtonEdit.addActionListener((ActionEvent e) -> {
            editFunction();
        });
    }
    
    public void deleteFunction() {
        String deleteSQL = "delete from Course where CourseName = ?";
        String courseName = jTextFieldCourseName.getText();
        try {
            prepare = connect.prepareStatement(deleteSQL);
            prepare.setString(1, courseName);
            prepare.execute();
            JOptionPane.showMessageDialog(null, "The Course deleted!");
            Functions.clearTextField(jPanelEditCourse);
            jComboBoxSections.setSelectedIndex(0);
            jComboBoxCourseTeacher.setSelectedIndex(0);
        } catch(SQLException ex) {
            Logger.getLogger(EditCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void callDelete() {
        jButtonDelete.addActionListener((ActionEvent e) -> {
            deleteFunction();
        });
    }
    
    public void callBack() {
        jButtonBack.addActionListener((ActionEvent e) -> {
            Functions.gotoNewFrame(this, new HomeAdmin());
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

        jPanelEditCourse = new javax.swing.JPanel();
        jLabelCourseID = new javax.swing.JLabel();
        jLabelCourseName = new javax.swing.JLabel();
        jLabelSections = new javax.swing.JLabel();
        jLabelCourseTeacher = new javax.swing.JLabel();
        jTextFieldCourseID = new javax.swing.JTextField();
        jTextFieldCourseName = new javax.swing.JTextField();
        jComboBoxSections = new javax.swing.JComboBox<>();
        jButtonEdit = new javax.swing.JButton();
        jComboBoxCourseTeacher = new javax.swing.JComboBox<>();
        jButtonDelete = new javax.swing.JButton();
        jButtonSearch = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CMS - Edit Course");

        jPanelEditCourse.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edit Course", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 24))); // NOI18N

        jLabelCourseID.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelCourseID.setText("Course ID");

        jLabelCourseName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelCourseName.setText("Course Name");

        jLabelSections.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelSections.setText("Sections");

        jLabelCourseTeacher.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelCourseTeacher.setText("Course Teacher");

        jTextFieldCourseID.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jTextFieldCourseName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jComboBoxSections.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBoxSections.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "2", "3" }));

        jButtonEdit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonEdit.setText("Edit");

        jComboBoxCourseTeacher.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jComboBoxCourseTeacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        jButtonDelete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonDelete.setText("Delete");

        jButtonSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonSearch.setText("Search");

        javax.swing.GroupLayout jPanelEditCourseLayout = new javax.swing.GroupLayout(jPanelEditCourse);
        jPanelEditCourse.setLayout(jPanelEditCourseLayout);
        jPanelEditCourseLayout.setHorizontalGroup(
            jPanelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditCourseLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelEditCourseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEditCourseLayout.createSequentialGroup()
                        .addComponent(jLabelCourseTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCourseTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEditCourseLayout.createSequentialGroup()
                        .addComponent(jLabelSections, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSections, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEditCourseLayout.createSequentialGroup()
                        .addComponent(jLabelCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEditCourseLayout.createSequentialGroup()
                        .addComponent(jLabelCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelEditCourseLayout.setVerticalGroup(
            jPanelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditCourseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCourseID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSections, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSections, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCourseTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCourseTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEditCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButtonBack.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonBack.setText("Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonBack)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jPanelEditCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jPanelEditCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButtonBack)
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
//            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            SubstanceLookAndFeel.setSkin(new GeminiSkin());
            new EditCourse().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox<String> jComboBoxCourseTeacher;
    private javax.swing.JComboBox<String> jComboBoxSections;
    private javax.swing.JLabel jLabelCourseID;
    private javax.swing.JLabel jLabelCourseName;
    private javax.swing.JLabel jLabelCourseTeacher;
    private javax.swing.JLabel jLabelSections;
    private javax.swing.JPanel jPanelEditCourse;
    private javax.swing.JTextField jTextFieldCourseID;
    private javax.swing.JTextField jTextFieldCourseName;
    // End of variables declaration//GEN-END:variables
}
