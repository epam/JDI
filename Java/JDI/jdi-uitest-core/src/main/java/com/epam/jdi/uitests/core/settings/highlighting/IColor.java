package com.epam.jdi.uitests.core.settings.highlighting;

/** Created by oksana_cherniavskaia on 13.10.2018. */
public interface IColor {

  String getName();

  default long getRed() {
    return getRgb() & 0xFF0000;
  }

  long getRgb();

  default String formatToRgbHexString() {
    return formatToRgbHexString("");
  }

  default String formatToRgbHexString(String suffix) {
    return formatToHex(getRgb(), 6, suffix);
  }

  static String formatToHex(long color, int precision, String suffix) {
    return String.format("%s%0" + precision + "X", suffix, color);
  }

  default long getGreen() {
    return getRgb() & 0x00FF00;
  }

  default long getBlue() {
    return getRgb() & 0x0000FF;
  }
}
