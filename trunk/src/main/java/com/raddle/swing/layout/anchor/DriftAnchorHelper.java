package com.raddle.swing.layout.anchor;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.raddle.swing.layout.anchor.drift.FixedDriftAnchor;
import com.raddle.swing.layout.anchor.drift.RelativeDriftAnchor;

/**
 * 类DriftAnchorHelper的实现描述：浮动跟随边框，跟随的边保持边距，其他边也相应的移动，保持图形大小不变
 *
 * @author xurong 2009-6-14 下午02:00:33
 */
public class DriftAnchorHelper implements FixedDriftAnchor, RelativeDriftAnchor {

    public static enum DRIFT_TYPE {
        /**
         * 绝对定位，和边框保持绝对数值的大小
         */
        ABSOLUTE,
        /**
         * 相对定位，和边框保持外框大小百分之多少的距离
         */
        RELATIVE
    };

    private Container  outer;
    private Component  self;
    private int        leftPad         = -1;
    private int        topPad          = -1;
    private int        rightPad        = -1;
    private int        bottomPad       = -1;
    private double     alignmentL      = -1;
    private double     alignmentT      = -1;
    private double     alignmentR      = -1;
    private double     alignmentB      = -1;
    private int        tuningLeftPad   = 0;
    private int        tuningTopPad    = 0;
    private int        tuningRightPad  = 0;
    private int        tuningBottomPad = 0;
    private boolean    listening       = false;
    private DRIFT_TYPE driftType       = DRIFT_TYPE.ABSOLUTE;

    public DriftAnchorHelper(Container outer, Component self){
        this.outer = outer;
        this.self = self;
    }

    /**
     * 开始跟随边框
     */
    public void drifting() {
        if (!listening) {
            listening = true;
            outer.addComponentListener(new ComponentAdapter() {

                public void componentResized(ComponentEvent evt) {
                    if (driftType == DRIFT_TYPE.ABSOLUTE) {
                        if (leftPad >= 0) {
                            self.setLocation(leftPad, self.getY());
                        }
                        if (topPad >= 0) {
                            self.setLocation(self.getX(), topPad);
                        }
                        if (rightPad >= 0) {
                            self.setLocation(outer.getWidth() - (rightPad) - self.getWidth(), self.getY());
                        }
                        if (bottomPad >= 0) {
                            self.setLocation(self.getX(), outer.getHeight() - (bottomPad) - self.getHeight());
                        }
                    } else if (driftType == DRIFT_TYPE.RELATIVE) {
                        if (alignmentL >= 0) {
                            self.setLocation((int) (outer.getWidth() * alignmentL) + tuningLeftPad, self.getY());
                        }
                        if (alignmentT >= 0) {
                            self.setLocation(self.getX(), (int) (outer.getHeight() * alignmentT) + tuningTopPad);
                        }
                        if (alignmentR >= 0) {
                            self.setLocation(outer.getWidth() - ((int) (outer.getWidth() * alignmentR)) - self.getWidth() + tuningRightPad, self.getY());
                        }
                        if (alignmentB >= 0) {
                            self.setLocation(self.getX(), outer.getHeight() - ((int) (outer.getHeight() * alignmentB)) - self.getHeight() + tuningBottomPad);
                        }
                    }

                }
            });
        }
    }

    @Override
    public RelativeDriftAnchor anchorBottom(double relativePadding) {
        driftType = DRIFT_TYPE.RELATIVE;
        alignmentB = relativePadding;
        return this;
    }

    @Override
    public RelativeDriftAnchor anchorLeft(double relativePadding) {
        driftType = DRIFT_TYPE.RELATIVE;
        alignmentL = relativePadding;
        return this;
    }

    @Override
    public RelativeDriftAnchor anchorRight(double relativePadding) {
        driftType = DRIFT_TYPE.RELATIVE;
        alignmentR = relativePadding;
        return this;
    }

    @Override
    public RelativeDriftAnchor anchorTop(double relativePadding) {
        driftType = DRIFT_TYPE.RELATIVE;
        alignmentT = relativePadding;
        return this;
    }

    @Override
    public FixedDriftAnchor anchorBottom() {
        driftType = DRIFT_TYPE.ABSOLUTE;
        bottomPad = outer.getHeight() - self.getY() - self.getHeight();
        if (bottomPad < 0) {
            bottomPad = 5;
        }
        return this;
    }

    @Override
    public FixedDriftAnchor anchorBottom(int fixedPadding) {
        driftType = DRIFT_TYPE.ABSOLUTE;
        bottomPad = fixedPadding;
        return this;
    }

    @Override
    public FixedDriftAnchor anchorLeft() {
        driftType = DRIFT_TYPE.ABSOLUTE;
        leftPad = self.getX();
        return this;
    }

    @Override
    public FixedDriftAnchor anchorLeft(int fixedPadding) {
        driftType = DRIFT_TYPE.ABSOLUTE;
        leftPad = fixedPadding;
        return this;
    }

    @Override
    public FixedDriftAnchor anchorRight() {
        driftType = DRIFT_TYPE.ABSOLUTE;
        rightPad = outer.getWidth() - self.getX() - self.getWidth();
        if (rightPad < 0) {
            rightPad = 5;
        }
        return this;
    }

    @Override
    public FixedDriftAnchor anchorRight(int fixedPadding) {
        driftType = DRIFT_TYPE.ABSOLUTE;
        rightPad = fixedPadding;
        return this;
    }

    @Override
    public FixedDriftAnchor anchorTop() {
        driftType = DRIFT_TYPE.ABSOLUTE;
        topPad = self.getY();
        return this;
    }

    @Override
    public FixedDriftAnchor anchorTop(int fixedPadding) {
        driftType = DRIFT_TYPE.ABSOLUTE;
        topPad = fixedPadding;
        return this;
    }

    @Override
    public RelativeDriftAnchor anchorBottom(double relativePadding, int tuningPadding) {
        anchorBottom(relativePadding);
        tuningBottomPad = tuningPadding;
        return this;
    }

    @Override
    public RelativeDriftAnchor anchorLeft(double relativePadding, int tuningPadding) {
        anchorLeft(relativePadding);
        tuningLeftPad = tuningPadding;
        return this;
    }

    @Override
    public RelativeDriftAnchor anchorRight(double relativePadding, int tuningPadding) {
        anchorRight(relativePadding);
        tuningRightPad = tuningPadding;
        return this;
    }

    @Override
    public RelativeDriftAnchor anchorTop(double relativePadding, int tuningPadding) {
        anchorTop(relativePadding);
        tuningTopPad = tuningPadding;
        return this;
    }
}
