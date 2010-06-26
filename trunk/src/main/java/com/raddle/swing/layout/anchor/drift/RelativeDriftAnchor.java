/**
 *
 */
package com.raddle.swing.layout.anchor.drift;

/**
 * 相对边框锚，自己的边框和外边框保持相对距离(外容器宽度*relativePadding)，当外边框变化时，调整自身位置已保持边距不变
 *
 * @author xurong
 */
public interface RelativeDriftAnchor {

    public RelativeDriftAnchor anchorLeft(double relativePadding);

    public RelativeDriftAnchor anchorTop(double relativePadding);

    public RelativeDriftAnchor anchorRight(double relativePadding);

    public RelativeDriftAnchor anchorBottom(double relativePadding);
}
