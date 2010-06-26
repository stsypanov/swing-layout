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

    public BorderFollowAnchor followLeft();

    public BorderFollowAnchor followLeft(int fixedPadding);

    public BorderFollowAnchor followTop();

    public BorderFollowAnchor followTop(int fixedPadding);

    public BorderFollowAnchor followRight();

    public BorderFollowAnchor followRight(int fixedPadding);

    public BorderFollowAnchor followBottom();

    public BorderFollowAnchor followBottom(int fixedPadding);
}
