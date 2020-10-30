package com.woniuxy.login;

import com.woniuxy.chess.GameUI;
import com.woniuxy.util.JavaFXUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * TODO
 *
 * @author 95741
 * @version 1.0
 * @date 2020/10/21 21:38
 */
public class Car extends Application {
  Pane pane;
  Text text;
  Button btn = JavaFXUtil.getButton("进去app", 50, 690, 90, 30, 16);
  private String name;
  private int width = 700; // 宽度
  private int height = 740; // 高度

  public Car(String name) {
    this.name = name;
  }

  public Car() {
    super();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    BorderPane root = new BorderPane();

    Image image = new Image("file:C:\\123.gif");
    ImageView imageView = new ImageView();
    imageView.setImage(image);
    root.setCenter(imageView);
    text = new Text(200, 50, name + "你好点击按钮进去下棋");
    text.setFont(new Font(50));
    root.setTop(text);
    root.setBottom(btn);
    Scene scene = new Scene(root, width, height);
    primaryStage.setScene(scene);
    primaryStage.show();
    clicked(primaryStage);
  }

  public void clicked(Stage stage) {
    btn.setOnAction(
        event -> {
          GameUI gameUI = new GameUI(name);
          try {
            gameUI.start(new Stage());
            stage.hide();
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
  }
}
