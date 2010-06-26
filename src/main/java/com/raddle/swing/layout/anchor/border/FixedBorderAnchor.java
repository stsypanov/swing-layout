/**
 *
 */
package com.raddle.swing.layout.anchor.border;

/**
 * 固定边框锚，自己的边框和外边框保持固定距离(fixedPadding)，当外边框变化时，调整自身大小已保持边距不变
 *
 * @author xurong
 */
public interface FixedBorderAnchor {

    public void anchorLeft();

    public void anchorLeft(int fixedPadding);

    public void anchorTop();

    public void anchorTop(int fixedPadding);

    public void anchorRight();

    public void anchorRight(int fixedPadding);

    public void anchorBottom();

    public void anchorBottom(int fixedPadding);
}
