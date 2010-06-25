package com.raddle.swing.layout;

import java.awt.Component;
import java.awt.Container;

import com.raddle.swing.layout.anchor.AnchorHelper;
import com.raddle.swing.layout.anchor.FloatAnchorHelper;
import com.raddle.swing.layout.anchor.FloatAnchorHelper.LOCATION_TYPE;

/**
 * 类AnchorUtils.java的实现描述：跟随父框架变大小
 *
 * @author xurong 2009-3-26 上午10:34:38
 */
public class LayoutUtils {
    /**
     * 左上绝对定位
     * @param outer
     * @param self
     * @param lefPad 大于-1，跟随右边框，保持边距rightPad
     * @param topPad 大于-1，跟随下边框，保持边距bottomPad
     * @return
     */
    public static AnchorHelper bindAnchorLT(Container outer, Component self, int lefPad, int topPad) {
        AnchorHelper helper = new AnchorHelper(outer, self, lefPad, topPad, -1, -1);
        helper.anchor();
        return helper;
    }

    /**
     * 右下绝对定位
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

    /**anchorOutRectangle
     * 右下绝对定位
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
     * 左上浮动，绝对距离
     * @param outer
     * @param self
     * @param lefPad 大于-1，跟随右边框浮动，保持边距rightPad
     * @param topPad 大于-1，跟随下边框浮动，保持边距bottomPad
     * @return
     */
    public static FloatAnchorHelper bindFloatAnchorLT(Container outer, Component self, int lefPad, int topPad) {
        FloatAnchorHelper helper = new FloatAnchorHelper(outer, self, lefPad, topPad, -1, -1);
        helper.floating();
        return helper;
    }

    /**
     * 右下浮动，绝对距离
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
     * @param outer
     * @param self
     * @param anchorRight 跟随右边框浮动，保持边距不变
     * @param anchorBottom 跟随下边框浮动，保持边距不变
     * @return
     */
    public static FloatAnchorHelper bindFloatAnchorRB(Container outer, Component self, boolean anchorRight, boolean anchorBottom) {
        FloatAnchorHelper helper =  new FloatAnchorHelper(outer, self, false, false, anchorRight, anchorBottom);
        helper.floating();
        return helper;
    }

    /**
     * 左上或中心浮动
     * @param outer
     * @param self
     * @param locationType 左上或中心
     * @param alignmentX 左边距百分比
     * @param alignmentY 上边距百分比
     * @return
     */
    public static FloatAnchorHelper bindFloatAnchor(Container outer, Component self, LOCATION_TYPE locationType, float alignmentX, float alignmentY) {
        FloatAnchorHelper helper =  new FloatAnchorHelper(outer, self, locationType, alignmentX, alignmentY);
        helper.floating();
        return helper;
    }

    /**
     * 中心浮动
     * @param outer
     * @param self
     * @param horizontal 横向
     * @param vertical 纵向
     * @return
     */
    public static FloatAnchorHelper bindCenterAnchor(Container outer, Component self, boolean horizontal, boolean vertical) {
        FloatAnchorHelper helper = new FloatAnchorHelper(outer, self, LOCATION_TYPE.CENTER, horizontal ? 0.5f : -1, vertical ? 0.5f : -1);
        helper.floating();
        return helper;
    }
}
