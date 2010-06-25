/**
 *
 */
package com.raddle.swing.layout.anchor.dynamic;

/**
 * 跟随或浮动的方形区域，不一定就是外容器大小，可以设置x，y值跟随中间或三分之一处
 *
 * @author xurong
 */
public interface DynamicPadding {

    public int getTopPad();

    public int getBottomPad();

    public int getLeftPad();

    public int getRightPad();

}
