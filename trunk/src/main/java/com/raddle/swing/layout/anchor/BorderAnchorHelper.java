package com.raddle.swing.layout.anchor;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JScrollPane;

import com.raddle.swing.layout.anchor.border.FixedBorderAnchor;
import com.raddle.swing.layout.anchor.border.RelativeBorderAnchor;

/**
 * 类BorderAnchorHelper的实现描述：固定跟随边框，保持和边距，不跟随的边位置保持不便
 *
 * @author xurong 2009-6-14 下午12:57:21
 */
public class BorderAnchorHelper implements FixedBorderAnchor, RelativeBorderAnchor {

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

    private Container   outer;
    private Component   self;
    private int         leftPad    = -1;
    private int         topPad     = -1;
    private int         rightPad   = -1;
    private int         bottomPad  = -1;
    private double      alignmentL = -1;
    private double      alignmentT = -1;
    private double      alignmentR = -1;
    private double      alignmentB = -1;
    private boolean     listening  = false;
    private ANCHOR_TYPE anchorType = ANCHOR_TYPE.ABSOLUTE;

    public BorderAnchorHelper(Container outer, Component self){
        this.outer = outer;
        this.self = self;
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
                        if (leftPad >= 0) {
                            anchorLeft(self, leftPad);
                        }
                        if (topPad >= 0) {
                            anchorTop(self, topPad);
                        }
                        if (rightPad >= 0) {
                            anchorRight(outer, self, rightPad);
                        }
                        if (bottomPad >= 0) {
                            anchorBottom(outer, self, bottomPad);
                        }
                    } else if (anchorType == ANCHOR_TYPE.RELATIVE) {
                        if (alignmentL >= 0) {
                            anchorLeft(self, (int) (outer.getWidth() * alignmentL));
                        }
                        if (alignmentT >= 0) {
                            anchorTop(self, (int) (outer.getHeight() * alignmentT));
                        }
                        if (alignmentR >= 0) {
                            anchorRight(outer, self, (int) (outer.getWidth() * alignmentR));
                        }
                        if (alignmentB >= 0) {
                            anchorBottom(outer, self, (int) (outer.getHeight() * alignmentB));
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

    @Override
    public RelativeBorderAnchor anchorBottom(double relativePadding) {
        anchorType = ANCHOR_TYPE.RELATIVE;
        alignmentB = relativePadding;
        return this;
    }

    @Override
    public RelativeBorderAnchor anchorLeft(double relativePadding) {
        anchorType = ANCHOR_TYPE.RELATIVE;
        alignmentL = relativePadding;
        return this;
    }

    @Override
    public RelativeBorderAnchor anchorRight(double relativePadding) {
        anchorType = ANCHOR_TYPE.RELATIVE;
        alignmentR = relativePadding;
        return this;
    }

    @Override
    public RelativeBorderAnchor anchorTop(double relativePadding) {
        anchorType = ANCHOR_TYPE.RELATIVE;
        alignmentT = relativePadding;
        return this;
    }

    @Override
    public FixedBorderAnchor anchorBottom() {
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        bottomPad = outer.getHeight() - self.getY() - self.getHeight();
        if(bottomPad < 0){
            bottomPad = 5;
        }
        return this;
    }

    @Override
    public FixedBorderAnchor anchorBottom(int fixedPadding) {
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        bottomPad = fixedPadding;
        return this;

    }

    @Override
    public FixedBorderAnchor anchorLeft() {
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        leftPad = self.getX();
        return this;
    }

    @Override
    public FixedBorderAnchor anchorLeft(int fixedPadding) {
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        leftPad = fixedPadding;
        return this;
    }

    @Override
    public FixedBorderAnchor anchorRight() {
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        rightPad = outer.getWidth() - self.getX() - self.getWidth();
        if(rightPad < 0){
            rightPad = 5;
        }
        return this;
    }

    @Override
    public FixedBorderAnchor anchorRight(int fixedPadding) {
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        rightPad = fixedPadding;
        return this;
    }

    @Override
    public FixedBorderAnchor anchorTop() {
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        topPad = self.getY();
        return this;
    }

    @Override
    public FixedBorderAnchor anchorTop(int fixedPadding) {
        anchorType = ANCHOR_TYPE.ABSOLUTE;
        topPad = fixedPadding;
        return this;
    }

}
