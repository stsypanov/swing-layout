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

    public DefaultDynamicPadding(Container outer){
        this.outer = outer;
    }

    @Override
    public int getBottomPad() {
        return 0;
    }

    @Override
    public int getTopPad() {
        return 0;
    }

    @Override
    public int getLeftPad() {
        return 0;
    }

    @Override
    public int getRightPad() {
        return 0;
    }

    public Container getOuter() {
        return outer;
    }

}
