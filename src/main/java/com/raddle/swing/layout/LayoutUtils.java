package com.raddle.swing.layout;

import java.awt.Component;
import java.awt.Container;

import com.raddle.swing.layout.anchor.AnchorFollowHelper;
import com.raddle.swing.layout.anchor.AnchorHelper;
import com.raddle.swing.layout.anchor.FloatAnchorHelper;

/**
 * 类AnchorUtils.java的实现描述：跟随父框架变大小
 *
 * @author xurong 2009-3-26 上午10:34:38
 */
public class LayoutUtils {
    /**
     * 左上角相对定位
     * @param outer
     * @param self
     * @param alignmentX 宽度的百分比
     * @param alignmentY 高度百分比
     * @return
     */
    public static AnchorHelper bindAnchor(Container outer, Component self, double alignmentX, double alignmentY) {
        AnchorHelper helper = new AnchorHelper(outer, self, alignmentX, alignmentY);
        helper.anchor();
        return helper;
    }
    /**
     * 右下绝对定位
     *
     * @param outer
     * @param self
     * @param rightPad 大于-1，跟随右边框，保持边距rightPad
     * @param bottomPad 大于-1，跟随下边框，保持边距bottomPad
     * @return
     */
    public static AnchorHelper bindAnchorRB(Container outer, Component self, int rightPad, int bottomPad) {
        AnchorHelper helper = new AnchorHelper(outer, self, -1, -1, rightPad, bottomPad);
        helper.anchor();
        return helper;
    }

    /**
     * 右下绝对定位
     *
     * @param outer
     * @param self
     * @param anchorRight 跟随右边框，保持边距不变
     * @param anchorBottom 跟随下边框，保持边距不变
     * @return
     */
    public static AnchorHelper bindAnchorRB(Container outer, Component self, boolean anchorRight, boolean anchorBottom) {
        AnchorHelper helper = new AnchorHelper(outer, self, false, false, anchorRight, anchorBottom);
        helper.anchor();
        return helper;
    }


    /**
     * 右下浮动，绝对距离
     *
     * @param outer
     * @param self
     * @param rightPad 大于-1，跟随右边框浮动，保持边距rightPad
     * @param bottomPad 大于-1，跟随下边框浮动，保持边距bottomPad
     * @return
     */
    public static FloatAnchorHelper bindFloatAnchorRB(Container outer, Component self, int rightPad, int bottomPad) {
        FloatAnchorHelper helper = new FloatAnchorHelper(outer, self, -1, -1, rightPad, bottomPad);
        helper.floating();
        return helper;
    }

    /**
     * 右下浮动，绝对距离
     *
     * @param outer
     * @param self
     * @param anchorRight 跟随右边框浮动，保持边距不变
     * @param anchorBottom 跟随下边框浮动，保持边距不变
     * @return
     */
    public static FloatAnchorHelper bindFloatAnchorRB(Container outer, Component self, boolean anchorRight, boolean anchorBottom) {
        FloatAnchorHelper helper = new FloatAnchorHelper(outer, self, false, false, anchorRight, anchorBottom);
        helper.floating();
        return helper;
    }

    /**
     * 相对浮动
     *
     * @param outer
     * @param self
     * @param alignmentX 宽度的百分比
     * @param alignmentY 高度百分比
     * @return
     */
    public static FloatAnchorHelper bindFloatAnchor(Container outer, Component self, double alignmentX, double alignmentY) {
        FloatAnchorHelper helper = new FloatAnchorHelper(outer, self, alignmentX, alignmentY);
        helper.floating();
        return helper;
    }

    /**
     * 跟随旁边的元素
     *
     * @param followTo
     * @param self
     * @param leftPad 大于-1跟随左边元素，左边距保持leftPad
     * @param topPad 大于-1跟随上元素，上边距保持topPad
     * @param rightPad 大于-1跟随右元素，右边距保持rightPad
     * @param bottomPad 大于-1跟随下元素，下边距保持bottomPad
     * @return
     */
    public static AnchorFollowHelper bindAnchorFollow(Component followTo, Component self, int leftPad, int topPad, int rightPad, int bottomPad) {
        AnchorFollowHelper helper = new AnchorFollowHelper(followTo, self, leftPad, topPad, rightPad, bottomPad);
        helper.anchor();
        return helper;
    }

    /**
     * @param followTo
     * @param self
     * @param anchorLeft 跟随左元素，左边距保持不变
     * @param anchorTop 跟随上元素，上边距保持不变
     * @param anchorRight 跟随右元素，右边距保持不变
     * @param anchorBottom 跟随下元素，下边距保持不变
     * @return
     */
    public static AnchorFollowHelper bindAnchorFollow(Component followTo, Component self, boolean anchorLeft, boolean anchorTop, boolean anchorRight, boolean anchorBottom) {
        AnchorFollowHelper helper = new AnchorFollowHelper(followTo, self, anchorLeft, anchorTop, anchorRight, anchorBottom);
        helper.anchor();
        return helper;
    }
}
