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

    public FixedBorderAnchor anchorLeft();

    public FixedBorderAnchor anchorLeft(int fixedPadding);

    public FixedBorderAnchor anchorTop();

    public FixedBorderAnchor anchorTop(int fixedPadding);

    public FixedBorderAnchor anchorRight();

    public FixedBorderAnchor anchorRight(int fixedPadding);

    public FixedBorderAnchor anchorBottom();

    public FixedBorderAnchor anchorBottom(int fixedPadding);
}
