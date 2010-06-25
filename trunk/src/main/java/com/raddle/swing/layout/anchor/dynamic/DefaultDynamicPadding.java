/**
 *
 */
package com.raddle.swing.layout.anchor.dynamic;

import java.awt.Container;

/**
 * 默认的跟随区域，就和外部容器一样的大小
 *
 * @author xurong
 */
public class DefaultDynamicPadding implements DynamicPadding {

    protected Container outer;
    private int         leftPad   = 0;
    private int         topPad    = 0;
    private int         rightPad  = 0;
    private int         bottomPad = 0;

    public DefaultDynamicPadding(Container outer){
        this.outer = outer;
    }

    @Override
    public int getBottomPad() {
        return bottomPad;
    }

    @Override
    public int getTopPad() {
        return topPad;
    }

    @Override
    public int getLeftPad() {
        return leftPad;
    }

    @Override
    public int getRightPad() {
        return rightPad;
    }

    public Container getOuter() {
        return outer;
    }

    public void setLeftPad(int leftPad) {
        this.leftPad = leftPad;
    }

    public void setTopPad(int topPad) {
        this.topPad = topPad;
    }

    public void setRightPad(int rightPad) {
        this.rightPad = rightPad;
    }

    public void setBottomPad(int bottomPad) {
        this.bottomPad = bottomPad;
    }

}
