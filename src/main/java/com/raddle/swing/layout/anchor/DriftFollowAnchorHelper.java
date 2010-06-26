/**
 *
 */
package com.raddle.swing.layout.anchor;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
                        self.setLocation(followTo.getX() + followTo.getWidth() + leftPad, self.getY());
                    }
                    if (topPad >= 0) {
                        self.setLocation(self.getX(), followTo.getY() + followTo.getHeight() + topPad);
                    }
                    if (rightPad >= 0) {
                        self.setLocation(followTo.getX() - self.getWidth() - rightPad, self.getY());
                    }
                    if (bottomPad >= 0) {
                        self.setLocation(self.getX(), followTo.getY() - self.getHeight() - bottomPad);
                    }
                }
            });
        }
    }

    @Override
    public DriftFollowAnchor followBottom(int fixedPadding) {
        bottomPad = fixedPadding;
        return this;
    }

    @Override
    public DriftFollowAnchor followBottom() {
        bottomPad = followTo.getY() - self.getY() - followTo.getHeight();
        if (bottomPad < 0) {
            bottomPad = 5;
        }
        return this;
    }

    @Override
    public DriftFollowAnchor followLeft(int fixedPadding) {
        leftPad = fixedPadding;
        return this;
    }

    @Override
    public DriftFollowAnchor followLeft() {
        leftPad = self.getX() - followTo.getX() - followTo.getWidth();
        if (leftPad < 0) {
            leftPad = 5;
        }
        return this;
    }

    @Override
    public DriftFollowAnchor followRight(int fixedPadding) {
        rightPad = fixedPadding;
        return this;
    }

    @Override
    public DriftFollowAnchor followRight() {
        rightPad = followTo.getX() - self.getX() - self.getWidth();
        if (rightPad < 0) {
            rightPad = 5;
        }
        return this;
    }

    @Override
    public DriftFollowAnchor followTop(int fixedPadding) {
        topPad = fixedPadding;
        return this;
    }

    @Override
    public DriftFollowAnchor followTop() {
        topPad = self.getY() - followTo.getY() - self.getHeight();
        if (topPad < 0) {
            topPad = 5;
        }
        return this;
    }

}
