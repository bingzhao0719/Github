

package com.bing.github.kotlin.utils;

import android.app.Activity;
import android.support.annotation.StyleRes;

import com.bing.github.kotlin.BaseActivity;
import com.bing.github.kotlin.R;

public class ThemeHelper {

    public static void apply(BaseActivity activity){
        String theme = PrefUtils.getTheme();
        int accentColor = PrefUtils.getAccentColor();
        activity.setTheme(getTheme(theme, accentColor));
    }

    public static void applyForAboutActivity(Activity activity) {
        String theme = PrefUtils.getTheme();
        activity.setTheme(getAboutTheme(theme));
    }

    @StyleRes
    public static int getAboutTheme(String theme) {
        switch (theme) {
            case PrefUtils.LIGHT_TEAL:
                return R.style.ThemeLightTeal_AboutActivity;
            case PrefUtils.LIGHT_INDIGO:
                return R.style.ThemeLight_AboutActivity;
            case PrefUtils.DARK:
                return R.style.ThemeDark_AboutActivity;
            default:
                return R.style.ThemeLightTeal_AboutActivity;
        }
    }

    @StyleRes
    public static int getTheme(String theme, int accentColor) {
        switch (theme) {
            case PrefUtils.LIGHT_TEAL:
                switch (accentColor) {
                    case PrefUtils.LIGHT_BLUE:
                        return R.style.ThemeLightTeal_LightBlue;
                    case PrefUtils.BLUE:
                        return R.style.ThemeLightTeal_Blue;
                    case PrefUtils.INDIGO:
                        return R.style.ThemeLightTeal_Indigo;
                    case PrefUtils.ORANGE:
                        return R.style.ThemeLightTeal_Orange;

                    case PrefUtils.YELLOW:
                        return R.style.ThemeLightTeal_Yellow;
                    case PrefUtils.AMBER:
                        return R.style.ThemeLightTeal_Amber;
                    case PrefUtils.GREY:
                        return R.style.ThemeLightTeal_Grey;
                    case PrefUtils.BROWN:
                        return R.style.ThemeLightTeal_Brown;

                    case PrefUtils.CYAN:
                        return R.style.ThemeLightTeal_Cyan;
                    case PrefUtils.TEAL:
                        return R.style.ThemeLightTeal_Teal;
                    case PrefUtils.LIME:
                        return R.style.ThemeLightTeal_Lime;
                    case PrefUtils.GREEN:
                        return R.style.ThemeLightTeal_Green;

                    case PrefUtils.PINK:
                        return R.style.ThemeLightTeal_Pink;
                    case PrefUtils.RED:
                        return R.style.ThemeLightTeal_Red;
                    case PrefUtils.PURPLE:
                        return R.style.ThemeLightTeal_Purple;
                    case PrefUtils.DEEP_PURPLE:
                        return R.style.ThemeLightTeal_DeepPurple;
                }
            case PrefUtils.LIGHT_INDIGO:
                switch (accentColor) {
                    case PrefUtils.LIGHT_BLUE:
                        return R.style.ThemeLight_LightBlue;
                    case PrefUtils.BLUE:
                        return R.style.ThemeLight_Blue;
                    case PrefUtils.INDIGO:
                        return R.style.ThemeLight_Indigo;
                    case PrefUtils.ORANGE:
                        return R.style.ThemeLight_Orange;

                    case PrefUtils.YELLOW:
                        return R.style.ThemeLight_Yellow;
                    case PrefUtils.AMBER:
                        return R.style.ThemeLight_Amber;
                    case PrefUtils.GREY:
                        return R.style.ThemeLight_Grey;
                    case PrefUtils.BROWN:
                        return R.style.ThemeLight_Brown;

                    case PrefUtils.CYAN:
                        return R.style.ThemeLight_Cyan;
                    case PrefUtils.TEAL:
                        return R.style.ThemeLight_Teal;
                    case PrefUtils.LIME:
                        return R.style.ThemeLight_Lime;
                    case PrefUtils.GREEN:
                        return R.style.ThemeLight_Green;

                    case PrefUtils.PINK:
                        return R.style.ThemeLight_Pink;
                    case PrefUtils.RED:
                        return R.style.ThemeLight_Red;
                    case PrefUtils.PURPLE:
                        return R.style.ThemeLight_Purple;
                    case PrefUtils.DEEP_PURPLE:
                        return R.style.ThemeLight_DeepPurple;
                }
            case PrefUtils.DARK:
                switch (accentColor) {
                    case PrefUtils.LIGHT_BLUE:
                        return R.style.ThemeDark_LightBlue;
                    case PrefUtils.BLUE:
                        return R.style.ThemeDark_Blue;
                    case PrefUtils.INDIGO:
                        return R.style.ThemeDark_Indigo;
                    case PrefUtils.ORANGE:
                        return R.style.ThemeDark_Orange;

                    case PrefUtils.YELLOW:
                        return R.style.ThemeDark_Yellow;
                    case PrefUtils.AMBER:
                        return R.style.ThemeDark_Amber;
                    case PrefUtils.GREY:
                        return R.style.ThemeDark_Grey;
                    case PrefUtils.BROWN:
                        return R.style.ThemeDark_Brown;

                    case PrefUtils.CYAN:
                        return R.style.ThemeDark_Cyan;
                    case PrefUtils.TEAL:
                        return R.style.ThemeDark_Teal;
                    case PrefUtils.LIME:
                        return R.style.ThemeDark_Lime;
                    case PrefUtils.GREEN:
                        return R.style.ThemeDark_Green;

                    case PrefUtils.PINK:
                        return R.style.ThemeDark_Pink;
                    case PrefUtils.RED:
                        return R.style.ThemeDark_Red;
                    case PrefUtils.PURPLE:
                        return R.style.ThemeDark_Purple;
                    case PrefUtils.DEEP_PURPLE:
                        return R.style.ThemeDark_DeepPurple;
                }
        }
        return R.style.ThemeLight_Indigo;
    }
}