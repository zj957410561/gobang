package com.woniuxy.view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * TODO
 *
 * @author 95741
 * @version 1.0
 * @date 2020/10/26 16:56
 */
public class GameStart extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // LoginUI.getINSTANCE().show();
    RegisterUI.getINSTANCE().show();
  }
}
