package com.woniuxy.chess;

import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * TODO 棋子类
 *
 * @author 95741
 * @version 1.0
 * @date 2020/10/17 17:09
 */
public class Chess {
  private int x;
  private int y;
  private Color color;

  public Chess() {}

  public Chess(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Chess)) return false;
    Chess chess = (Chess) o;
    return getX() == chess.getX() && getY() == chess.getY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY(), getColor());
  }

  @Override
  public String toString() {
    return "Chess{" + "x=" + x + ", y=" + y + ", color=" + color + '}';
  }
}
