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

    public void anchorLeft(double relativePadding);

    public void anchorTop(double relativePadding);

    public void anchorRight(double relativePadding);

    public void anchorBottom(double relativePadding);
}
