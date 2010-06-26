/**
 *
 */
package com.raddle.swing.layout.anchor.follow;


/**
 * 跟随周围组件保持固定距离(fixedPadding)，当周围组件变化时，调整自身位置已保持边距不变
 *
 * @author xurong
 */
public interface DriftFollowAnchor {

    public DriftFollowAnchor followLeft();

    public DriftFollowAnchor followLeft(int fixedPadding);

    public DriftFollowAnchor followTop();

    public DriftFollowAnchor followTop(int fixedPadding);

    public DriftFollowAnchor followRight();

    public DriftFollowAnchor followRight(int fixedPadding);

    public DriftFollowAnchor followBottom();

    public DriftFollowAnchor followBottom(int fixedPadding);
}
