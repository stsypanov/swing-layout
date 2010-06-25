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
import com.raddle.swing.layout.anchor.dynamic.DefaultDynamicPadding;


public class AnchorHelperTest extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private JDesktopPane jDesktopPane1;
    private JButton      jButton1;
    private JButton      jButton3;
    private JTextPane    jTextPane1;
    private JScrollPane  jScrollPane1;
    private JButton      jButton2;
    private JButton      jButton5;
    private JButton      jButton4;

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
        // 放在初始化之后
        LayoutUtils.bindAnchorRB(jDesktopPane1, jButton1, true, false);
        LayoutUtils.bindAnchorRB(jDesktopPane1, jButton2, false, true);
        LayoutUtils.bindAnchorRB(jDesktopPane1, jScrollPane1, true, true);

        // 浮动
        LayoutUtils.bindFloatAnchorRB(jDesktopPane1, jButton4, true, false);
        LayoutUtils.bindAnchorLT(jDesktopPane1, jButton4, 0, -1).setDynamicPadding(new DefaultDynamicPadding(jDesktopPane1) {
            @Override
            public int getLeftPad() {
                return outer.getWidth()/2;
            }
        });
        LayoutUtils.bindCenterAnchor(jDesktopPane1, jButton3, false, true);
        LayoutUtils.bindCenterAnchor(jDesktopPane1, jButton5,true,false);
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
                    jButton1.setText("右边框跟随");
                    jButton1.setBounds(273, 12, 100, 23);
                }
                {
                    jButton2 = new JButton();
                    jDesktopPane1.add(jButton2);
                    jButton2.setText("下边框跟随");
                    jButton2.setBounds(12, 230, 116, 23);
                }
                {
                    jScrollPane1 = new JScrollPane();
                    jDesktopPane1.add(jScrollPane1);
                    jScrollPane1.setBounds(178, 130, 194, 122);
                    {
                        jTextPane1 = new JTextPane();
                        jScrollPane1.setViewportView(jTextPane1);
                        jTextPane1.setText("右下角跟随右下角跟随右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n右下角跟随\n");
                    }
                }
                {
                    jButton3 = new JButton();
                    jDesktopPane1.add(jButton3);
                    jButton3.setText("纵向中间浮动");
                    jButton3.setBounds(12, 12, 116, 23);
                }
                {
                    jButton4 = new JButton();
                    jDesktopPane1.add(jButton4);
                    jButton4.setText("跟随中间和右边");
                    jButton4.setBounds(287, 52, 86, 23);
                    jButton4.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                        }
                    });
                }
                {
                    jButton5 = new JButton();
                    jDesktopPane1.add(jButton5);
                    jButton5.setText("横向浮动中间");
                    jButton5.setBounds(108, 101, 118, 23);
                }
            }
            pack();
            setSize(400, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
