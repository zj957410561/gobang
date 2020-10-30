package com.woniuxy.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Music extends Thread {
  private File f;
  private URI uri;
  private URL url;

  public static void main(String[] args) {
    //
    Music music = new Music();
    music.run();
  }

  @Override
  public void run() { // 注意，java只能播放无损音质，如.wav这种格式
    try {

      f =
          new File(
              "C:\\softwareInstall\\intellDEA2020\\programCode\\gobang\\music\\Uu-那女孩对我说.wav"); // 绝对路径
      uri = f.toURI();
      url = uri.toURL(); // 解析路径
      AudioClip aau;
      aau = Applet.newAudioClip(url);
      aau.play(); // 单曲循环
      System.out.println("1");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
