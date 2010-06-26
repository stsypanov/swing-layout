/**
 *
 */
package com.raddle.swing.layout.anchor.follow;

import java.awt.Component;

/**
 * 跟随周围组件保持固定距离(fixedPadding)，当周围组件变化时，调整自身位置已保持边距不变
 *
 * @author xurong
 */
public interface DriftFollowAnchor {

    public void followLeft(Component followTo, int fixedPadding);

    public void followTop(Component followTo, int fixedPadding);

    public void followRight(Component followTo, int fixedPadding);

    public void followBottom(Component followTo, int fixedPadding);
}
