package com.epam.jdi.uitests.core.settings;

import com.epam.commons.StringUtils;

/** Created by oksana_cherniavskaia on 11.10.2018. */
public enum CssColorNames {
  DODGER_BLUE("DodgerBlue", "#1E90FF"),
  MEDIUM_SLATE_BLUE("MediumSlateBlue", "#7B68EE"),
  MEDIUM_ORCHID("MediumOrchid", "#BA55D3"),
  MEDIUM_PURPLE("MediumPurple", "#9370DB"),

  DARK_SLATE_GREY("DarkSlateGrey", "#2F4F4F"),
  DARK_TURQUOISE("DarkTurquoise ", "#00CED1"),
  DARK_VIOLET("DarkViolet ", "#9400D3"),
  DEEP_SKY_BLUE("DeepSkyBlue ", "#00BFFF"),
  DIM_GRAY("DimGray ", "#696969"),
  MEDIUM_SPRING_GREEN;
  private String name;
  private String hex;

  CssColorNames(String hex) {
    this();
    this.hex = hex;
  }

  CssColorNames() {
    String name =
            StringUtils.toUpperCamelCase(name(), '_');
    this.name = name;
  }

public static void main(String[] args) {
    System.out.println(MEDIUM_SPRING_GREEN);
    //
  }

  CssColorNames(String name, String hex) {
    this.name = name;
    this.hex = hex;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getHex() {
    return hex;
  }

  public void setHex(String hex) {
    this.hex = hex;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("CssColorNames{");
    sb.append("name='").append(name).append('\'');
    sb.append(", hex='").append(hex).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
