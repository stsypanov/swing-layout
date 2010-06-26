/**
 *
 */
package com.raddle.swing.layout.anchor.drift;

/**
 * 固定边框锚，自己的边框和外边框保持固定距离(fixedPadding)，当外边框变化时，调整自身位置已保持边距不变
 *
 * @author xurong
 */
public interface FixedDriftAnchor {

    public FixedDriftAnchor anchorLeft();

    public FixedDriftAnchor anchorLeft(int fixedPadding);

    public FixedDriftAnchor anchorTop();

    public FixedDriftAnchor anchorTop(int fixedPadding);

    public FixedDriftAnchor anchorRight();

    public FixedDriftAnchor anchorRight(int fixedPadding);

    public FixedDriftAnchor anchorBottom();

    public FixedDriftAnchor anchorBottom(int fixedPadding);
}
