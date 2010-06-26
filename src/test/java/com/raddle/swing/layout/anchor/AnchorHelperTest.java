package com.raddle.swing.layout.anchor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.raddle.swing.layout.LayoutUtils;

public class AnchorHelperTest extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private JDesktopPane      jDesktopPane1;
    private JButton           jButton1;
    private JButton           jButton3;
    private JTextPane         jTextPane1;
    private JScrollPane       jScrollPane1;
    private JButton           jButton2;
    private JButton           jButton5;
    private JButton           jButton4;

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                AnchorHelperTest inst = new AnchorHelperTest();
                inst.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public AnchorHelperTest(){
        super();
        initGUI();
        // 固定边框
        LayoutUtils.anchorFixedBorder(jDesktopPane1, jScrollPane1).anchorRight().anchorBottom();
        // 相对边框
        LayoutUtils.anchorRelativeBorder(jDesktopPane1, jButton4).anchorLeft(0.5);
        LayoutUtils.anchorFixedBorder(jDesktopPane1, jButton4).anchorRight();

        // 固定浮动
        LayoutUtils.anchorFixedDrift(jDesktopPane1, jButton1).anchorRight();
        // 相对浮动
        LayoutUtils.anchorRelativeDrift(jDesktopPane1, jButton3).anchorBottom(0.5);

        // 边框跟随组件
        LayoutUtils.anchorBorderFollow(jButton2, jButton3).followTop(5);
        // 组件跟随组件
        LayoutUtils.anchorDriftFollow(jButton5, jButton1).followRight(5);
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                jDesktopPane1 = new JDesktopPane();
                getContentPane().add(jDesktopPane1, BorderLayout.CENTER);
                {
                    jButton1 = new JButton();
                    jDesktopPane1.add(jButton1);
                    jButton1.setText("1右边框FixedDrift");
                    jButton1.setBounds(273, 12, 150, 23);
                }
                {
                    jButton2 = new JButton();
                    jDesktopPane1.add(jButton2);
                    jButton2.setText("2上边框BorderFollow");
                    jButton2.setBounds(12, 230, 150, 23);
                }
                {
                    jScrollPane1 = new JScrollPane();
                    jDesktopPane1.add(jScrollPane1);
                    jScrollPane1.setBounds(178, 130, 194, 122);
                    {
                        jTextPane1 = new JTextPane();
                        jScrollPane1.setViewportView(jTextPane1);
                        jTextPane1.setText("FixedBorder右下角跟随右下角跟随右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n");
                    }
                }
                {
                    jButton3 = new JButton();
                    jDesktopPane1.add(jButton3);
                    jButton3.setText("3纵向RelativeDrift");
                    jButton3.setBounds(12, 12, 150, 23);
                }
                {
                    jButton4 = new JButton();
                    jDesktopPane1.add(jButton4);
                    jButton4.setText("4跟随RB+FB");
                    jButton4.setBounds(287, 52, 150, 23);
                    jButton4.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                        }
                    });
                }
                {
                    jButton5 = new JButton();
                    jDesktopPane1.add(jButton5);
                    jButton5.setText("5DriftFollow1");
                    jButton5.setBounds(108, 101, 150, 23);
                }
            }
            pack();
            setSize(450, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
