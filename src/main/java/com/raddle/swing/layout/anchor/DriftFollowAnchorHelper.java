/**
 *
 */
package com.raddle.swing.layout.anchor;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JScrollPane;

import com.raddle.swing.layout.anchor.follow.DriftFollowAnchor;

/**
 * 跟随其他元素改变大小或移动
 *
 * @author xurong
 */
public class DriftFollowAnchorHelper implements DriftFollowAnchor {

    private Component self;
    private Component followTo;
    private int       leftPad   = -1;
    private int       topPad    = -1;
    private int       rightPad  = -1;
    private int       bottomPad = -1;
    private boolean   listening = false;

    public DriftFollowAnchorHelper(Component self, Component followTo){
        this.self = self;
        this.followTo = followTo;
    }

    /**
     * 开始跟随元素
     */
    public void following() {
        if (!listening) {
            listening = true;
            followTo.addComponentListener(new ComponentAdapter() {

                public void componentResized(ComponentEvent evt) {
                    followTo();
                }

                @Override
                public void componentMoved(ComponentEvent e) {
                    followTo();
                }

                private void followTo() {
                    if (leftPad >= 0) {
                        anchorLeft(self, followTo, leftPad);
                    }
                    if (topPad >= 0) {
                        anchorTop(self, followTo, topPad);
                    }
                    if (rightPad >= 0) {
                        anchorRight(self, followTo, rightPad);
                    }
                    if (bottomPad >= 0) {
                        anchorBottom(self, followTo, bottomPad);
                    }
                }
            });
        }
    }

    /**
     * 跟随左边
     *
     * @param self 需要调整的组件
     * @param spacing 左边距
     */
    private void anchorLeft(Component self, Component followTo, int spacing) {
        self.setBounds(followTo.getX() + followTo.getWidth() + spacing, self.getY(), self.getWidth() + (self.getX() - spacing - followTo.getX() - followTo.getWidth()), self.getHeight());
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorLeft(container.getViewport().getView(), followTo, 0);
        }
    }

    /**
     * 跟随右边
     *
     * @param outer 外部要跟随的组件
     * @param self 需要调整的组件
     * @param spacing 右边距
     */
    private void anchorRight(Component self, Component followTo, int spacing) {
        self.setSize(followTo.getX() - self.getX() - spacing, self.getHeight());
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorRight(container.getViewport().getView(), followTo, 5);
        }
    }

    /**
     * 跟随上边
     *
     * @param self 需要调整的组件
     * @param spacing 上边距
     */
    private void anchorTop(Component self, Component followTo, int spacing) {
        self.setBounds(self.getX(), followTo.getY() + followTo.getHeight() + spacing, self.getWidth(), self.getHeight() + (self.getY() - spacing - followTo.getY() - followTo.getHeight()));
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorTop(container.getViewport().getView(), followTo, 0);
        }
    }

    /**
     * 跟随下边
     *
     * @param outer 外部要跟随的组件
     * @param self 需要调整的组件
     * @param spacing 下边距
     */
    private void anchorBottom(Component self, Component followTo, int spacing) {
        self.setSize(self.getWidth(), followTo.getY() - self.getY() - spacing);
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorBottom(container.getViewport().getView(), followTo, 5);
        }
    }

    @Override
    public void followBottom(int fixedPadding) {
        bottomPad = fixedPadding;
    }

    @Override
    public void followBottom() {
        bottomPad = followTo.getY() - self.getY() - followTo.getHeight();
        if (bottomPad < 0) {
            bottomPad = 5;
        }
    }

    @Override
    public void followLeft(int fixedPadding) {
        leftPad = fixedPadding;

    }

    @Override
    public void followLeft() {
        leftPad = self.getX() - followTo.getX() - followTo.getWidth();
        if (leftPad < 0) {
            leftPad = 5;
        }
    }

    @Override
    public void followRight(int fixedPadding) {
        rightPad = fixedPadding;
    }

    @Override
    public void followRight() {
        rightPad = followTo.getX() - self.getX() - self.getWidth();
        if (rightPad < 0) {
            rightPad = 5;
        }
    }

    @Override
    public void followTop(int fixedPadding) {
        topPad = fixedPadding;
    }

    @Override
    public void followTop() {
        topPad = self.getY() - followTo.getY() - self.getHeight();
        if (topPad < 0) {
            topPad = 5;
        }
    }

}
