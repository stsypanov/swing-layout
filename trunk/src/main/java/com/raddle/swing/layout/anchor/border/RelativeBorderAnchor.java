/**
 *
 */
package com.raddle.swing.layout.anchor.border;

/**
 * 相对边框锚，自己的边框和外边框保持相对距离(外容器宽度*relativePadding)，当外边框变化时，调整自身大小已保持边距不变
 *
 * @author xurong
 */
public interface RelativeBorderAnchor {

    public RelativeBorderAnchor anchorLeft(double relativePadding);

    public RelativeBorderAnchor anchorTop(double relativePadding);

    public RelativeBorderAnchor anchorRight(double relativePadding);

    public RelativeBorderAnchor anchorBottom(double relativePadding);
}
