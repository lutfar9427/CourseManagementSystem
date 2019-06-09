/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Hak Srun Lao
 */
public class Functions {
    public static Connection connectToDatabase() {
        String databasePath = "jdbc:ucanaccess://CMS Database.accdb;jackcessOpener=SourceCode.OpenDatabase";
        String userName = "";
        String password = "123456";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connect = DriverManager.getConnection(databasePath, userName, password);
            return connect;
        } catch(ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void initialFrame(JFrame frame) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setIconImage(new ImageIcon("Logo.png").getImage());
    }
    
    public static void gotoNewFrame(JFrame oldFrame, JFrame newFrame) {
        newFrame.setVisible(true);
        oldFrame.setVisible(false);
    }
    
    public static void home(JFrame frame, JButton button, String userType) {
        button.addActionListener((ActionEvent e) -> {
            switch(userType) {
                case "SuperAdmin":
                    gotoNewFrame(frame, new HomeAdmin()); break;
                case "Admin":
                    gotoNewFrame(frame, new HomeAdmin()); break;
                case "Teacher":
                    gotoNewFrame(frame, new HomeTeacher()); break;
                case "Student":
                    gotoNewFrame(frame, new HomeStudent()); break;
            }
        });
    }
    
    public static void addTeacher(JFrame frame, JButton button) {
        button.addActionListener((ActionEvent e) -> {
            gotoNewFrame(frame, new AddTeacher());
        });
    }
    
    public static void addStudent(JFrame frame, JButton button) {
        button.addActionListener((ActionEvent e) -> {
            gotoNewFrame(frame, new AddStudent());
        });
    }
    
    public static void addCourse(JFrame frame, JButton button) {
        button.addActionListener((ActionEvent e) -> {
            gotoNewFrame(frame, new AddCourse());
        });
    }
    
    public static void editTeacher(JFrame frame, JButton button) {
        button.addActionListener((ActionEvent e) -> {
            gotoNewFrame(frame, new EditTeacher());
        });
    }
    
    public static void editStudent(JFrame frame, JButton button) {
        button.addActionListener((ActionEvent e) -> {
            gotoNewFrame(frame, new EditStudent());
        });
    }
    
    public static void editCourse(JFrame frame, JButton button) {
        button.addActionListener((ActionEvent e) -> {
            gotoNewFrame(frame, new EditCourse());
        });
    }
    
    public static void logOut(JFrame frame, JButton button) {
        button.addActionListener((ActionEvent e) -> {
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure want to log out?", "CMS - Log Out", JOptionPane.YES_NO_OPTION);
            if(yesOrNo == 0) {
                gotoNewFrame(frame, new Login());
            }
        });
    }
    
    public static void exit(JButton button) {
        button.addActionListener((ActionEvent e) -> {
            int yesOrNo = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?", "CMS - Exit System", JOptionPane.YES_NO_OPTION);
            if(yesOrNo == 0) {
                System.exit(0);
            }
        });
    }
    
    public static void clearTextField(JPanel panel) {
        for(Component getComponent : panel.getComponents()) {
            if(getComponent instanceof JTextField) {
                JTextField textField = (JTextField)getComponent;
                textField.setText("");
            }
        }
    }
}
