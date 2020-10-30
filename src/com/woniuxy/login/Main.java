package com.woniuxy.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {
  Button btn = JavaFXUtil.getButton("登录", 300, 100, 120, 30, 20);
  Button btn1 = JavaFXUtil.getButton("注册", 300, 200, 120, 30, 20);
  Button btn2 = JavaFXUtil.getButton("结束", 300, 300, 120, 30, 20);
  Pane pane;
  private int width = 700; // 宽度
  private int height = 740; // 高度

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    pane = new Pane();
    // 2.创建一个场景
    Scene scene = new Scene(pane, width, height);
    pane.setBackground(new Background(new BackgroundFill(Color.rgb(1, 1, 23, 0.3), null, null)));
    // 给舞台设置一个场景
    primaryStage.setScene(scene);
    // 关闭自适应 禁止窗口拉伸
    primaryStage.setResizable(false);
    primaryStage.setTitle("五子棋");
    primaryStage.show();
    pane.getChildren().add(btn);
    pane.getChildren().add(btn1);
    pane.getChildren().add(btn2);
    login(primaryStage);
    register(primaryStage);
    exit();
  }

  public void login(Stage stage) {
    btn.setOnAction(
        e -> {
          Login open = new Login();
          try {
            open.start(new Stage());
            stage.hide();
          } catch (Exception exception) {
            exception.printStackTrace();
          }
        });
  }

  public void register(Stage stage) {
    btn1.setOnAction(
        e -> {
          Register open = new Register();
          try {
            open.start(new Stage());
            stage.hide();
          } catch (Exception exception) {
            exception.printStackTrace();
          }
        });
  }

  public void exit() {
    btn2.setOnAction(
        event -> {
          Alert.AlertType alertAlertType;
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("退出");
          alert.setHeaderText("你是否要退出?");
          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK) {

            System.exit(0);

          } else {
            event.consume();
          }
        });
  }
}
