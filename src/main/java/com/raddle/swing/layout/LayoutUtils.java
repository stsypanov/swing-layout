package com.raddle.swing.layout;

import java.awt.Component;
import java.awt.Container;

import com.raddle.swing.layout.anchor.BorderAnchorHelper;
import com.raddle.swing.layout.anchor.BorderFollowAnchorHelper;
import com.raddle.swing.layout.anchor.DriftAnchorHelper;
import com.raddle.swing.layout.anchor.DriftFollowAnchorHelper;
import com.raddle.swing.layout.anchor.border.FixedBorderAnchor;
import com.raddle.swing.layout.anchor.border.RelativeBorderAnchor;
import com.raddle.swing.layout.anchor.drift.FixedDriftAnchor;
import com.raddle.swing.layout.anchor.drift.RelativeDriftAnchor;
import com.raddle.swing.layout.anchor.follow.BorderFollowAnchor;
import com.raddle.swing.layout.anchor.follow.DriftFollowAnchor;

/**
 * 类AnchorUtils.java的实现描述：跟随父框架或周围组件移动或改变大小
 *
 * @author xurong 2009-3-26 上午10:34:38
 */
public class LayoutUtils {

    /**
     * 固定间距边框跟随外边框，改变大小自身
     *
     * @param outer
     * @param self
     * @return
     */
    public static FixedBorderAnchor anchorFixedBorder(Container outer, Component self) {
        BorderAnchorHelper helper = new BorderAnchorHelper(outer, self);
        helper.anchor();
        return helper;
    }

    /**
     * 相对比例间距边框跟随外边框，改变大小自身
     *
     * @param outer
     * @param self
     * @return
     */
    public static RelativeBorderAnchor anchorRelativeBorder(Container outer, Component self) {
        BorderAnchorHelper helper = new BorderAnchorHelper(outer, self);
        helper.anchor();
        return helper;
    }

    /**
     * 固定间距整体跟随外边框，移动自身位置
     *
     * @param outer
     * @param self
     * @return
     */
    public static FixedDriftAnchor anchorFixedDrift(Container outer, Component self) {
        DriftAnchorHelper helper = new DriftAnchorHelper(outer, self);
        helper.drifting();
        return helper;
    }

    /**
     * 相对比例间距整体跟随外边框，移动自身位置
     *
     * @param outer
     * @param self
     * @return
     */
    public static RelativeDriftAnchor anchorRelativeDrift(Container outer, Component self) {
        DriftAnchorHelper helper = new DriftAnchorHelper(outer, self);
        helper.drifting();
        return helper;
    }

    /**
     * 固定间距整体跟随周围组件，改变自身大小
     *
     * @param self
     * @param followTo
     * @return
     */
    public static BorderFollowAnchor anchorBorderFollow(Container self, Component followTo) {
        BorderFollowAnchorHelper helper = new BorderFollowAnchorHelper(self, followTo);
        helper.following();
        return helper;
    }

    /**
     * 固定间距整体跟随周围组件，移动自身位置
     *
     * @param self
     * @param followTo
     * @return
     */
    public static DriftFollowAnchor anchorDriftFollow(Container self, Component followTo) {
        DriftFollowAnchorHelper helper = new DriftFollowAnchorHelper(self, followTo);
        helper.following();
        return helper;
    }
}
