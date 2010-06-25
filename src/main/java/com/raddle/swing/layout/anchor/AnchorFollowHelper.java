/**
 *
 */
package com.raddle.swing.layout.anchor;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JScrollPane;

/**
 * 跟随其他元素
 *
 * @author xurong
 */
public class AnchorFollowHelper {

    private Component followTo;
    private Component self;
    private int       leftPad      = -1;
    private int       topPad       = -1;
    private int       rightPad     = -1;
    private int       bottomPad    = -1;
    private boolean   anchorLeft   = false;
    private boolean   anchorTop    = false;
    private boolean   anchorRight  = false;
    private boolean   anchorBottom = false;
    private boolean   listening    = false;

    public AnchorFollowHelper(Component followTo, Component self){
        this.followTo = followTo;
        this.self = self;
    }

    /**
     * 构造helper
     *
     * @param outer
     * @param self
     * @param followTo
     * @param leftPad 大于-1跟随左边元素，左边距保持leftPad
     * @param topPad 大于-1跟随上元素，上边距保持topPad
     * @param rightPad 大于-1跟随右元素，右边距保持rightPad
     * @param bottomPad 大于-1跟随下元素，下边距保持bottomPad
     */
    public AnchorFollowHelper(Component followTo, Component self, int leftPad, int topPad, int rightPad, int bottomPad){
        this.followTo = followTo;
        this.self = self;
        this.leftPad = leftPad;
        this.topPad = topPad;
        this.rightPad = rightPad;
        this.bottomPad = bottomPad;
        this.anchorLeft = leftPad > -1;
        this.anchorTop = topPad > -1;
        this.anchorRight = rightPad > -1;
        this.anchorBottom = bottomPad > -1;
    }

    /**
     * 构造helper
     *
     * @param outer
     * @param followTo
     * @param self
     * @param anchorLeft 跟随左元素，左边距保持不变
     * @param anchorTop 跟随上元素，上边距保持不变
     * @param anchorRight 跟随右元素，右边距保持不变
     * @param anchorBottom 跟随下元素，下边距保持不变
     */
    public AnchorFollowHelper(Component followTo, Component self, boolean anchorLeft, boolean anchorTop, boolean anchorRight, boolean anchorBottom){
        this.followTo = followTo;
        this.self = self;
        this.anchorLeft = anchorLeft;
        this.anchorTop = anchorTop;
        this.anchorRight = anchorRight;
        this.anchorBottom = anchorBottom;
    }

    /**
     * 开始跟随元素
     */
    public void anchor() {
        if (!listening) {
            listening = true;
            followTo.addComponentListener(new ComponentAdapter() {

                public void componentResized(ComponentEvent evt) {
                    follow();
                }

                @Override
                public void componentMoved(ComponentEvent e) {
                    follow();
                }

                private void follow() {
                    if (anchorLeft) {
                        if (leftPad == -1) {
                            leftPad = self.getX() - followTo.getX() - followTo.getWidth();
                        }
                        anchorLeft(self, leftPad);
                    }
                    if (anchorTop) {
                        if (topPad == -1) {
                            topPad = self.getY() - followTo.getY() - self.getHeight();
                        }
                        anchorTop(self, topPad);
                    }
                    if (anchorRight) {
                        if (rightPad == -1) {
                            rightPad = followTo.getX() - self.getX() - self.getWidth();
                        }
                        anchorRight(self, rightPad);
                    }
                    if (anchorBottom) {
                        if (bottomPad == -1) {
                            bottomPad = followTo.getY() - self.getY() - followTo.getHeight();
                        }
                        anchorBottom(self, bottomPad);
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
    private void anchorLeft(Component self, int spacing) {
        self.setBounds(followTo.getX() + followTo.getWidth() + spacing, self.getY(), self.getWidth() + (self.getX() - spacing - followTo.getX() - followTo.getWidth()), self.getHeight());
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorLeft(container.getViewport().getView(), 0);
        }
    }

    /**
     * 跟随右边
     *
     * @param outer 外部要跟随的组件
     * @param self 需要调整的组件
     * @param spacing 右边距
     */
    private void anchorRight(Component self, int spacing) {
        self.setSize(followTo.getX() - self.getX() - spacing, self.getHeight());
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorRight(container.getViewport().getView(), 5);
        }
    }

    /**
     * 跟随上边
     *
     * @param self 需要调整的组件
     * @param spacing 上边距
     */
    private void anchorTop(Component self, int spacing) {
        self.setBounds(self.getX(), followTo.getY() + followTo.getHeight() + spacing, self.getWidth(), self.getHeight() + (self.getY() - spacing - followTo.getY() - followTo.getHeight()));
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorTop(container.getViewport().getView(), 0);
        }
    }

    /**
     * 跟随下边
     *
     * @param outer 外部要跟随的组件
     * @param self 需要调整的组件
     * @param spacing 下边距
     */
    private void anchorBottom(Component self, int spacing) {
        self.setSize(self.getWidth(), followTo.getY() - self.getY() - spacing);
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorBottom(container.getViewport().getView(), 5);
        }
    }

    public int getLeftPad() {
        return leftPad;
    }

    public void setLeftPad(int leftPad) {
        this.leftPad = leftPad;
    }

    public int getTopPad() {
        return topPad;
    }

    public void setTopPad(int topPad) {
        this.topPad = topPad;
    }

    public int getRightPad() {
        return rightPad;
    }

    public void setRightPad(int rightPad) {
        this.rightPad = rightPad;
    }

    public int getBottomPad() {
        return bottomPad;
    }

    public void setBottomPad(int bottomPad) {
        this.bottomPad = bottomPad;
    }

    public boolean isAnchorLeft() {
        return anchorLeft;
    }

    public void setAnchorLeft(boolean anchorLeft) {
        this.anchorLeft = anchorLeft;
    }

    public boolean isAnchorTop() {
        return anchorTop;
    }

    public void setAnchorTop(boolean anchorTop) {
        this.anchorTop = anchorTop;
    }

    public boolean isAnchorRight() {
        return anchorRight;
    }

    public void setAnchorRight(boolean anchorRight) {
        this.anchorRight = anchorRight;
    }

    public boolean isAnchorBottom() {
        return anchorBottom;
    }

    public void setAnchorBottom(boolean anchorBottom) {
        this.anchorBottom = anchorBottom;
    }

    public Component getFollowTo() {
        return followTo;
    }

    public void setFollowTo(Component followTo) {
        this.followTo = followTo;
    }
}
