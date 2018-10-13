package com.epam.jdi.uitests.core.settings.highlighting;

import com.epam.commons.StringUtils;
import com.epam.jdi.uitests.core.settings.JDISettings;

import java.util.Arrays;
import java.util.Optional;

/** Created by oksana_cherniavskaia on 13.10.2018. */
public enum CssColors implements IColor {
  ALICE_BLUE(0xF0F8FF),
  ANTIQUE_WHITE(0xFAEBD7),
  AQUA(0x00FFFF),
  AQUAMARINE(0x7FFFD4),
  AZURE(0xF0FFFF),
  BEIGE(0xF5F5DC),
  BISQUE(0xFFE4C4),
  BLACK(0x000000),
  BLANCHED_ALMOND(0xFFEBCD),
  BLUE(0x0000FF),
  BLUE_VIOLET(0x8A2BE2),
  BROWN(0xA52A2A),
  BURLY_WOOD(0xDEB887),
  CADET_BLUE(0x5F9EA0),
  CHARTREUSE(0x7FFF00),
  CHOCOLATE(0xD2691E),
  CORAL(0xFF7F50),
  CORNFLOWER_BLUE(0x6495ED),
  CORNSILK(0xFFF8DC),
  CRIMSON(0xDC143C),
  CYAN(0x00FFFF),
  DARK_BLUE(0x00008B),
  DARK_CYAN(0x008B8B),
  DARK_GOLDEN_ROD(0xB8860B),
  DARK_GRAY(0xA9A9A9),
  DARK_GREY(0xA9A9A9),
  DARK_GREEN(0x006400),
  DARK_KHAKI(0xBDB76B),
  DARK_MAGENTA(0x8B008B),
  DARK_OLIVE_GREEN(0x556B2F),
  DARK_ORANGE(0xFF8C00),
  DARK_ORCHID(0x9932CC),
  DARK_RED(0x8B0000),
  DARK_SALMON(0xE9967A),
  DARK_SEA_GREEN(0x8FBC8F),
  DARK_SLATE_BLUE(0x483D8B),
  DARK_SLATE_GRAY(0x2F4F4F),
  DARK_SLATE_GREY(0x2F4F4F),
  DARK_TURQUOISE(0x00CED1),
  DARK_VIOLET(0x9400D3),
  DEEP_PINK(0xFF1493),
  DEEP_SKY_BLUE(0x00BFFF),
  DIM_GRAY(0x696969),
  DIM_GREY(0x696969),
  DODGER_BLUE(0x1E90FF),
  FIRE_BRICK(0xB22222),
  FLORAL_WHITE(0xFFFAF0),
  FOREST_GREEN(0x228B22),
  FUCHSIA(0xFF00FF),
  GAINSBORO(0xDCDCDC),
  GHOST_WHITE(0xF8F8FF),
  GOLD(0xFFD700),
  GOLDEN_ROD(0xDAA520),
  GRAY(0x808080),
  GREY(0x808080),
  GREEN(0x008000),
  GREEN_YELLOW(0xADFF2F),
  HONEY_DEW(0xF0FFF0),
  HOT_PINK(0xFF69B4),
  INDIAN_RED(0xCD5C5C),
  INDIGO(0x4B0082),
  IVORY(0xFFFFF0),
  KHAKI(0xF0E68C),
  LAVENDER(0xE6E6FA),
  LAVENDER_BLUSH(0xFFF0F5),
  LAWN_GREEN(0x7CFC00),
  LEMON_CHIFFON(0xFFFACD),
  LIGHT_BLUE(0xADD8E6),
  LIGHT_CORAL(0xF08080),
  LIGHT_CYAN(0xE0FFFF),
  LIGHT_GOLDEN_ROD_YELLOW(0xFAFAD2),
  LIGHT_GRAY(0xD3D3D3),
  LIGHT_GREY(0xD3D3D3),
  LIGHT_GREEN(0x90EE90),
  LIGHT_PINK(0xFFB6C1),
  LIGHT_SALMON(0xFFA07A),
  LIGHT_SEA_GREEN(0x20B2AA),
  LIGHT_SKY_BLUE(0x87CEFA),
  LIGHT_SLATE_GRAY(0x778899),
  LIGHT_SLATE_GREY(0x778899),
  LIGHT_STEEL_BLUE(0xB0C4DE),
  LIGHT_YELLOW(0xFFFFE0),
  LIME(0x00FF00),
  LIME_GREEN(0x32CD32),
  LINEN(0xFAF0E6),
  MAGENTA(0xFF00FF),
  MAROON(0x800000),
  MEDIUM_AQUA_MARINE(0x66CDAA),
  MEDIUM_BLUE(0x0000CD),
  MEDIUM_ORCHID(0xBA55D3),
  MEDIUM_PURPLE(0x9370DB),
  MEDIUM_SEA_GREEN(0x3CB371),
  MEDIUM_SLATE_BLUE(0x7B68EE),
  MEDIUM_SPRING_GREEN(0x00FA9A),
  MEDIUM_TURQUOISE(0x48D1CC),
  MEDIUM_VIOLET_RED(0xC71585),
  MIDNIGHT_BLUE(0x191970),
  MINT_CREAM(0xF5FFFA),
  MISTY_ROSE(0xFFE4E1),
  MOCCASIN(0xFFE4B5),
  NAVAJO_WHITE(0xFFDEAD),
  NAVY(0x000080),
  OLD_LACE(0xFDF5E6),
  OLIVE(0x808000),
  OLIVE_DRAB(0x6B8E23),
  ORANGE(0xFFA500),
  ORANGE_RED(0xFF4500),
  ORCHID(0xDA70D6),
  PALE_GOLDEN_ROD(0xEEE8AA),
  PALE_GREEN(0x98FB98),
  PALE_TURQUOISE(0xAFEEEE),
  PALE_VIOLET_RED(0xDB7093),
  PAPAYA_WHIP(0xFFEFD5),
  PEACH_PUFF(0xFFDAB9),
  PERU(0xCD853F),
  PINK(0xFFC0CB),
  PLUM(0xDDA0DD),
  POWDER_BLUE(0xB0E0E6),
  PURPLE(0x800080),
  REBECCA_PURPLE(0x663399),
  RED(0xFF0000),
  ROSY_BROWN(0xBC8F8F),
  ROYAL_BLUE(0x4169E1),
  SADDLE_BROWN(0x8B4513),
  SALMON(0xFA8072),
  SANDY_BROWN(0xF4A460),
  SEA_GREEN(0x2E8B57),
  SEA_SHELL(0xFFF5EE),
  SIENNA(0xA0522D),
  SILVER(0xC0C0C0),
  SKY_BLUE(0x87CEEB),
  SLATE_BLUE(0x6A5ACD),
  SLATE_GRAY(0x708090),
  SLATE_GREY(0x708090),
  SNOW(0xFFFAFA),
  SPRING_GREEN(0x00FF7F),
  STEEL_BLUE(0x4682B4),
  TAN(0xD2B48C),
  TEAL(0x008080),
  THISTLE(0xD8BFD8),
  TOMATO(0xFF6347),
  TURQUOISE(0x40E0D0),
  VIOLET(0xEE82EE),
  WHEAT(0xF5DEB3),
  WHITE(0xFFFFFF),
  WHITE_SMOKE(0xF5F5F5),
  YELLOW(0xFFFF00),
  YELLOW_GREEN(0x9ACD32);

  private long rgb;

  CssColors(long rgb) {
    this.rgb = rgb;
  }

  public static Optional<IColor> tryConstructEnumColor(String color) {
    return Optional.ofNullable(color)
        .map(
            (String c) -> {
              CssColors cssColors = null;
              try {
                cssColors = cssColors.from(c);
              } catch (IllegalArgumentException e) {
                JDISettings.logger.debug(
                    "Unable to construct CssColors from input string: {}.", color);
                // it is ok, not enum, some shade
              }
              return cssColors;
            });
  }

  public static CssColors from(String v) {
    CssColors colors =
        Arrays.stream(CssColors.values())
            .filter(i -> i.getName().equalsIgnoreCase(StringUtils.toUpperCamelCase(v, '_')))
            .findFirst()
            .orElse(null);
    return colors;
  }

  @Override
  public String getName() {
    return StringUtils.toUpperCamelCase(name(), '_');
  }

  @Override
  public long getRgb() {
    return rgb;
  }

  public String getHexRGB() {
    return IColor.formatToHex(rgb, 2, "");
  }

  public String getHexRGB(String suf) {
    return String.format("%s%06X", suf, this.rgb);
  }
}
