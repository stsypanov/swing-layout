package com.raddle.swing.layout.anchor;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.raddle.swing.layout.anchor.dynamic.DynamicPadding;
import com.raddle.swing.layout.anchor.dynamic.DefaultDynamicPadding;

/**
 * 类FloatAnchorHelper.java的实现描述：浮动跟随边框，跟随的边保持边距，其他边也相应的移动，保持图形大小不变
 *
 * @author xurong 2009-6-14 下午02:00:33
 */
public class FloatAnchorHelper {

    public static enum FLOAT_TYPE {
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
    private FLOAT_TYPE     floatType    = FLOAT_TYPE.ABSOLUTE;
    private double         alignmentL   = -1;
    private double         alignmentT   = -1;
    private double         alignmentR   = -1;
    private double         alignmentB   = -1;
    private DynamicPadding dynamicPadding;

    public FloatAnchorHelper(Container outer, Component self){
        this.outer = outer;
        this.self = self;
        this.floatType = FLOAT_TYPE.ABSOLUTE;
        dynamicPadding = new DefaultDynamicPadding(outer);
    }

    /**
     * 构建helper，左上角绝对定位浮动
     *
     * @param outer
     * @param self
     * @param leftPad 大于-1跟随左边框，左边距保持leftPad
     * @param topPad 大于-1跟随上边框，上边距保持topPad
     * @param rightPad 大于-1跟随右边框，右边距保持rightPad
     * @param bottomPad 大于-1跟随下边框，下边距保持bottomPad
     */
    public FloatAnchorHelper(Container outer, Component self, int leftPad, int topPad, int rightPad, int bottomPad){
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
        this.floatType = FLOAT_TYPE.ABSOLUTE;
        dynamicPadding = new DefaultDynamicPadding(outer);
    }

    /**
     * 构建helper，左上角绝对定位浮动
     *
     * @param outer
     * @param self
     * @param anchorLeft 跟随左边框，左边距保持不变
     * @param anchorTop 跟随上边框，上边距保持不变
     * @param anchorRight 跟随右边框，右边距保持不变
     * @param anchorBottom 跟随下边框，下边距保持不变
     */
    public FloatAnchorHelper(Container outer, Component self, boolean anchorLeft, boolean anchorTop, boolean anchorRight, boolean anchorBottom){
        this.outer = outer;
        this.self = self;
        this.anchorLeft = anchorLeft;
        this.anchorTop = anchorTop;
        this.anchorRight = anchorRight;
        this.anchorBottom = anchorBottom;
        this.floatType = FLOAT_TYPE.ABSOLUTE;
        dynamicPadding = new DefaultDynamicPadding(outer);
    }

    /**
     * 构建helper，相对定位浮动
     *
     * @param outer
     * @param self
     * @param locationType 最上角还是中心定位
     * @param alignmentX 左边距的百分比
     * @param alignmentY 上边距的百分比
     */
    public FloatAnchorHelper(Container outer, Component self, double alignmentX, double alignmentY, int xPad, int yPad, boolean isLT){
        this.floatType = FLOAT_TYPE.RELATIVE;
        dynamicPadding = new DefaultDynamicPadding(outer);
        this.outer = outer;
        this.self = self;
        if (isLT) {
            this.alignmentL = alignmentX;
            this.alignmentT = alignmentY;
            if (xPad > 0) {
                ((DefaultDynamicPadding) dynamicPadding).setLeftPad(xPad);
            }
            if (yPad > 0) {
                ((DefaultDynamicPadding) dynamicPadding).setTopPad(yPad);
            }
        } else {
            this.alignmentR = alignmentX;
            this.alignmentB = alignmentY;
            if (xPad > 0) {
                ((DefaultDynamicPadding) dynamicPadding).setRightPad(xPad);
            }
            if (yPad > 0) {
                ((DefaultDynamicPadding) dynamicPadding).setBottomPad(yPad);
            }
        }
    }

    /**
     * 开始跟随边框
     */
    public void floating() {
        if (!listening) {
            listening = true;
            outer.addComponentListener(new ComponentAdapter() {

                public void componentResized(ComponentEvent evt) {
                    if (floatType == FLOAT_TYPE.ABSOLUTE) {
                        if (anchorLeft) {
                            if (leftPad < 0) {
                                leftPad = self.getX();
                            }
                            self.setLocation(leftPad + dynamicPadding.getLeftPad(), self.getY());
                        }
                        if (anchorTop) {
                            if (topPad < 0) {
                                topPad = self.getY();
                            }
                            self.setLocation(self.getX(), topPad + dynamicPadding.getTopPad());
                        }
                        if (anchorRight) {
                            if (rightPad < 0) {
                                rightPad = outer.getWidth() - self.getX() - self.getWidth();
                            }
                            self.setLocation(outer.getWidth() - (rightPad + dynamicPadding.getRightPad()) - self.getWidth(), self.getY());
                        }
                        if (anchorBottom) {
                            if (bottomPad < 0) {
                                bottomPad = outer.getHeight() - self.getY() - self.getHeight();
                            }
                            self.setLocation(self.getX(), outer.getHeight() - (bottomPad + dynamicPadding.getBottomPad()) - self.getHeight());
                        }
                    } else if (floatType == FLOAT_TYPE.RELATIVE) {
                        if (alignmentL >= 0) {
                            self.setLocation((int) (outer.getWidth() * alignmentL) + dynamicPadding.getLeftPad(), self.getY());
                        }
                        if (alignmentT >= 0) {
                            self.setLocation(self.getX(), (int) (outer.getHeight() * alignmentT) + dynamicPadding.getTopPad());
                        }
                        if (alignmentR >= 0) {
                            self.setLocation(outer.getWidth() - ((int) (outer.getWidth() * alignmentR) + dynamicPadding.getRightPad()) - self.getWidth(), self.getY());
                        }
                        if (alignmentB >= 0) {
                            self.setLocation(self.getX(), outer.getHeight() - ((int) (outer.getHeight() * alignmentR) + dynamicPadding.getBottomPad()) - self.getHeight());
                        }
                    }

                }
            });
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

    public FLOAT_TYPE getFloatType() {
        return floatType;
    }

    public void setFloatType(FLOAT_TYPE floatType) {
        this.floatType = floatType;
    }

    public DynamicPadding getDynamicPadding() {
        return dynamicPadding;
    }

    public void setDynamicPadding(DynamicPadding anchorOutRectangle) {
        this.dynamicPadding = anchorOutRectangle;
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
