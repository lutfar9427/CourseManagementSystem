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
 * @author Hak Srun Lao
 */
public class ForgotPassword extends javax.swing.JFrame {
    Connection connect;
    PreparedStatement prepare;
    ResultSet result;

    /**
     * Creates new form ForgotPassword
     */
    public ForgotPassword() {
        initComponents();
        connect = Functions.connectToDatabase();
        setInitial();
    }
    
    public final void setInitial() {
        Functions.initialFrame(this);
        searchAction();
        submitAction();
        loginAction();
        Functions.exit(jButtonExit);
    }
    
    public void searchFunction() {
        String searchSQL = "select * from Account where RealName = ?";
        String realName = jTextFieldRealName.getText();
        try {
            prepare = connect.prepareStatement(searchSQL);
            prepare.setString(1, realName);
            result = prepare.executeQuery();
            if(result.next()) {
                String userName = result.getString("UserName");
                String question = result.getString("SecurityQuestion");
                jTextFieldUserName.setText(userName);
                jTextFieldQuestion.setText(question);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Real Name!");
                Functions.clearTextField(jPanelForgotPassword);
            }
        } catch(SQLException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchAction() {
        jButtonSearch.addActionListener((ActionEvent e) -> {
            searchFunction();
        });
        jTextFieldRealName.addActionListener((ActionEvent e) -> {
            searchFunction();
        });
    }
    
    public void submitFunction() {
        String submitSQL = "select Password from Account where UserName = ? and SecurityAnswer = ?";
        String userName = jTextFieldUserName.getText();
        String answer = jTextFieldAnswer.getText();
        try {
            prepare = connect.prepareStatement(submitSQL);
            prepare.setString(1, userName);
            prepare.setString(2, answer);
            result = prepare.executeQuery();
            if(result.next()) {
                String password = result.getString("Password");
                jTextFieldPassword.setText(password);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Security Answer!");
                Functions.clearTextField(jPanelForgotPassword);
            }
        } catch(SQLException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void submitAction() {
        jButtonSubmit.addActionListener((ActionEvent e) -> {
            submitFunction();
        });
        jTextFieldAnswer.addActionListener((ActionEvent e) -> {
            submitFunction();
        });
    }
    
    public void loginAction() {
        jButtonBack.addActionListener((ActionEvent e) -> {
            Functions.gotoNewFrame(this, new Login());
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

        jPanelForgotPassword = new javax.swing.JPanel();
        jLabelRealName = new javax.swing.JLabel();
        jLabelUserName = new javax.swing.JLabel();
        jLabelQuestion = new javax.swing.JLabel();
        jLabelAnswer = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldRealName = new javax.swing.JTextField();
        jTextFieldUserName = new javax.swing.JTextField();
        jTextFieldQuestion = new javax.swing.JTextField();
        jTextFieldAnswer = new javax.swing.JTextField();
        jTextFieldPassword = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jButtonSubmit = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CMS - Forgot Password");

        jPanelForgotPassword.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forgot Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 3, 24))); // NOI18N

        jLabelRealName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelRealName.setText("Real Name");

        jLabelUserName.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelUserName.setText("User Name");

        jLabelQuestion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelQuestion.setText("Security Question");

        jLabelAnswer.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAnswer.setText("Security Answer");

        jLabelPassword.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelPassword.setText("Password");

        jTextFieldRealName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jTextFieldUserName.setEditable(false);
        jTextFieldUserName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jTextFieldQuestion.setEditable(false);
        jTextFieldQuestion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jTextFieldAnswer.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jTextFieldPassword.setEditable(false);
        jTextFieldPassword.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jButtonSearch.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonSearch.setText("Search");

        jButtonSubmit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonSubmit.setText("Submit");

        javax.swing.GroupLayout jPanelForgotPasswordLayout = new javax.swing.GroupLayout(jPanelForgotPassword);
        jPanelForgotPassword.setLayout(jPanelForgotPasswordLayout);
        jPanelForgotPasswordLayout.setHorizontalGroup(
            jPanelForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelForgotPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelForgotPasswordLayout.createSequentialGroup()
                        .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelForgotPasswordLayout.createSequentialGroup()
                        .addComponent(jLabelAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelForgotPasswordLayout.createSequentialGroup()
                        .addComponent(jLabelQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelForgotPasswordLayout.createSequentialGroup()
                        .addComponent(jLabelUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelForgotPasswordLayout.createSequentialGroup()
                        .addComponent(jLabelRealName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRealName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelForgotPasswordLayout.setVerticalGroup(
            jPanelForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelForgotPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRealName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRealName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelForgotPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButtonBack.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonBack.setText("Back");

        jButtonExit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButtonExit.setText("Exit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jPanelForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jPanelForgotPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
//            java.util.logging.Logger.getLogger(ForgotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            SubstanceLookAndFeel.setSkin(new GeminiSkin());
            new ForgotPassword().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JLabel jLabelAnswer;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelQuestion;
    private javax.swing.JLabel jLabelRealName;
    private javax.swing.JLabel jLabelUserName;
    private javax.swing.JPanel jPanelForgotPassword;
    private javax.swing.JTextField jTextFieldAnswer;
    private javax.swing.JTextField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldQuestion;
    private javax.swing.JTextField jTextFieldRealName;
    private javax.swing.JTextField jTextFieldUserName;
    // End of variables declaration//GEN-END:variables
}