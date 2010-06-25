package com.raddle.swing.layout.anchor;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JScrollPane;

import com.raddle.swing.layout.anchor.dynamic.DefaultDynamicPadding;
import com.raddle.swing.layout.anchor.dynamic.DynamicPadding;

/**
 * 类AnchorHelper.java的实现描述：固定跟随边框，保持和边距，不跟随的边位置保持不便
 *
 * @author xurong 2009-6-14 下午12:57:21
 */
public class AnchorHelper {

    public static enum ANCHOR_TYPE {
        /**
         * 绝对定位，和边框保持绝对数值的大小
         */
        ABSOLUTE,
        /**
         * 相对定位，和边框保持外框大小百分之多少的距离
         */
        RELATIVE
    };

    private Container      outer;
    private Component      self;
    private int            leftPad      = -1;
    private int            topPad       = -1;
    private int            rightPad     = -1;
    private int            bottomPad    = -1;
    private boolean        anchorLeft   = false;
    private boolean        anchorTop    = false;
    private boolean        anchorRight  = false;
    private boolean        anchorBottom = false;
    private boolean        listening    = false;
    private ANCHOR_TYPE    anchorType   = ANCHOR_TYPE.ABSOLUTE;
    private double         alignmentL   = -1;
    private double         alignmentT   = -1;
    private double         alignmentR   = -1;
    private double         alignmentB   = -1;
    private DynamicPadding dynamicPadding;

    public AnchorHelper(Container outer, Component self){
        this.outer = outer;
        this.self = self;
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        dynamicPadding = new DefaultDynamicPadding(outer);
    }

    /**
     * 构造helper
     *
     * @param outer
     * @param self
     * @param leftPad 大于-1跟随左边框，左边距保持leftPad
     * @param topPad 大于-1跟随上边框，上边距保持topPad
     * @param rightPad 大于-1跟随右边框，右边距保持rightPad
     * @param bottomPad 大于-1跟随下边框，下边距保持bottomPad
     */
    public AnchorHelper(Container outer, Component self, int leftPad, int topPad, int rightPad, int bottomPad){
        this.outer = outer;
        this.self = self;
        this.leftPad = leftPad;
        this.topPad = topPad;
        this.rightPad = rightPad;
        this.bottomPad = bottomPad;
        this.anchorLeft = leftPad >= 0;
        this.anchorTop = topPad >= 0;
        this.anchorRight = rightPad >= 0;
        this.anchorBottom = bottomPad >= 0;
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        dynamicPadding = new DefaultDynamicPadding(outer);
    }

    /**
     * 构造helper
     *
     * @param outer
     * @param self
     * @param anchorLeft 跟随左边框，左边距保持不变
     * @param anchorTop 跟随上边框，上边距保持不变
     * @param anchorRight 跟随右边框，右边距保持不变
     * @param anchorBottom 跟随下边框，下边距保持不变
     */
    public AnchorHelper(Container outer, Component self, boolean anchorLeft, boolean anchorTop, boolean anchorRight, boolean anchorBottom){
        this.outer = outer;
        this.self = self;
        this.anchorLeft = anchorLeft;
        this.anchorTop = anchorTop;
        this.anchorRight = anchorRight;
        this.anchorBottom = anchorBottom;
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        dynamicPadding = new DefaultDynamicPadding(outer);
    }

    public AnchorHelper(Container outer, Component self, double alignmentX, double alignmentY, boolean isLT){
        this.outer = outer;
        this.self = self;
        if (isLT) {
            this.alignmentL = alignmentX;
            this.alignmentT = alignmentY;
        } else {
            this.alignmentR = alignmentX;
            this.alignmentB = alignmentY;
        }
        anchorType = ANCHOR_TYPE.RELATIVE;
        dynamicPadding = new DefaultDynamicPadding(outer);
    }

    /**
     * 开始跟随边框
     */
    public void anchor() {
        if (!listening) {
            listening = true;
            outer.addComponentListener(new ComponentAdapter() {

                public void componentResized(ComponentEvent evt) {
                    if (anchorType == ANCHOR_TYPE.ABSOLUTE) {
                        if (anchorLeft) {
                            if (leftPad < 0) {
                                leftPad = self.getX();
                            }
                            anchorLeft(self, leftPad + dynamicPadding.getLeftPad());
                        }
                        if (anchorTop) {
                            if (topPad < 0) {
                                topPad = self.getY();
                            }
                            anchorTop(self, topPad + dynamicPadding.getTopPad());
                        }
                        if (anchorRight) {
                            if (rightPad < 0) {
                                rightPad = outer.getWidth() - self.getX() - self.getWidth();
                            }
                            anchorRight(outer, self, rightPad + dynamicPadding.getRightPad());
                        }
                        if (anchorBottom) {
                            if (bottomPad < 0) {
                                bottomPad = outer.getHeight() - self.getY() - self.getHeight();
                            }
                            anchorBottom(outer, self, bottomPad + dynamicPadding.getBottomPad());
                        }
                    } else if (anchorType == ANCHOR_TYPE.RELATIVE) {
                        if (alignmentL >= 0) {
                            anchorLeft(self, (int) (outer.getWidth() * alignmentL) + dynamicPadding.getLeftPad());
                        }
                        if (alignmentT >= 0) {
                            anchorTop(self, (int) (outer.getHeight() * alignmentT) + dynamicPadding.getTopPad());
                        }
                        if (alignmentR >= 0) {
                            anchorRight(outer, self, (int) (outer.getWidth() * alignmentR) + dynamicPadding.getRightPad());
                        }
                        if (alignmentB >= 0) {
                            anchorBottom(outer, self, (int) (outer.getHeight() * alignmentB) + dynamicPadding.getBottomPad());
                        }
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
        self.setBounds(spacing, self.getY(), self.getWidth() + (self.getX() - spacing), self.getHeight());
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
    private void anchorRight(Component outer, Component self, int spacing) {
        self.setSize(outer.getWidth() - self.getX() - spacing, self.getHeight());
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorRight(container, container.getViewport().getView(), 5);
        }
    }

    /**
     * 跟随上边
     *
     * @param self 需要调整的组件
     * @param spacing 上边距
     */
    private void anchorTop(Component self, int spacing) {
        self.setBounds(self.getX(), spacing, self.getWidth(), self.getHeight() + (self.getY() - spacing));
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
    private void anchorBottom(Component outer, Component self, int spacing) {
        self.setSize(self.getWidth(), outer.getHeight() - self.getY() - spacing);
        if (self instanceof JScrollPane) {
            JScrollPane container = (JScrollPane) self;
            anchorBottom(container, container.getViewport().getView(), 5);
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

    public DynamicPadding getDynamicPadding() {
        return dynamicPadding;
    }

    public void setDynamicPadding(DynamicPadding anchorOutRectangle) {
        this.dynamicPadding = anchorOutRectangle;
    }

    public ANCHOR_TYPE getAnchorType() {
        return anchorType;
    }

    public void setAnchorType(ANCHOR_TYPE anchorType) {
        this.anchorType = anchorType;
    }

    public double getAlignmentL() {
        return alignmentL;
    }

    public void setAlignmentL(double alignmentL) {
        this.alignmentL = alignmentL;
    }

    public double getAlignmentT() {
        return alignmentT;
    }

    public void setAlignmentT(double alignmentT) {
        this.alignmentT = alignmentT;
    }

    public double getAlignmentR() {
        return alignmentR;
    }

    public void setAlignmentR(double alignmentR) {
        this.alignmentR = alignmentR;
    }

    public double getAlignmentB() {
        return alignmentB;
    }

    public void setAlignmentB(double alignmentB) {
        this.alignmentB = alignmentB;
    }
}
