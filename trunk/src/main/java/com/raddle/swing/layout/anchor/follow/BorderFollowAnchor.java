/**
 *
 */
package com.raddle.swing.layout.anchor.follow;


/**
 * 跟随周围组件保持固定距离(fixedPadding)，当周围组件变化时，调整自身大小已保持边距不变
 *
 * @author xurong
 */
public interface BorderFollowAnchor {

    public void followLeft();

    public void followLeft(int fixedPadding);

    public void followTop();

    public void followTop(int fixedPadding);

    public void followRight();

    public void followRight(int fixedPadding);

    public void followBottom();

    public void followBottom(int fixedPadding);
}
