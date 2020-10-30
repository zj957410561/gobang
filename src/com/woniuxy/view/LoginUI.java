package com.woniuxy.view;

import com.woniuxy.util.JavaFXUtil;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

/**
 * TODO 主页面是单单例的 Stage 舞台
 *
 * @author 95741
 * @version 1.0
 * @date 2020/10/26 16:52
 */
public class LoginUI extends Stage {
  private static final LoginUI INSTANCE = new LoginUI();
  Label userlabel = JavaFXUtil.getTextLabel("用  户  名:", 50, 50, 20);
  TextField loginfield = JavaFXUtil.getTextField(150, 50, 200, 50);
  Label pwdlabel = JavaFXUtil.getTextLabel("密        码:", 50, 120, 20);
  PasswordField pwdfield = JavaFXUtil.getPasswordField(150, 120, 200, 50);
  Button loginbutton = JavaFXUtil.getButton("登录", 130, 200, 90, 40, 20);
  Hyperlink hyperlink = JavaFXUtil.getHyperlink("注册", 300, 250, 20);

  private LoginUI() {
    super();
    init();
  }

  public static LoginUI getINSTANCE() {
    return INSTANCE;
  }

  private void init() {
    // 1.创建面板
    Pane pane = new Pane();
    draw(pane);
    login();
    backMain();
    goRegister();
  }

  private void draw(Pane pane) {
    // 1.创建面板
    pane.getChildren().add(userlabel);
    pane.getChildren().add(loginfield);
    pane.getChildren().add(pwdlabel);
    pane.getChildren().add(pwdfield);
    pane.getChildren().add(loginbutton);
    pane.getChildren().add(hyperlink);
    // 2.创建一个场景
    Scene scene = new Scene(pane, 400, 300);
    // 在舞台中设置场景
    this.setScene(scene);
    this.setResizable(false);
    this.setTitle("五子棋游戏");
  }

  private void login() {
    loginbutton.setOnMouseClicked(
        event -> {
          String name = loginfield.getText();
          String password = pwdfield.getText();
          BufferedReader bufferedReader = null;
          try {
            bufferedReader = new BufferedReader(new FileReader(new File("127.csv")));
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          if (name == null && password == null) {
            JavaFXUtil.alert("用户名和密码不能为空", "用户名和密码不能为空", "用户名和密码不能为空");
          } else {
            while (true) {
              try {
                String a = bufferedReader.readLine();
                if (a != null) {
                  String[] contents = a.split(",");
                  if (name.equals(contents[1])) {
                    if (password.equals(contents[2])) {
                      JavaFXUtil.alert("登录成功", "登录成功", "登录成功");
                      break;
                    } else {
                      JavaFXUtil.alert("密码不对", "密码不对", "登录失败");
                      break;
                    }
                  }
                } else {
                  JavaFXUtil.alert("用户不存在", "用户不存在", "请先去注册");
                  this.hide();
                  RegisterUI.getINSTANCE().show();
                  break;
                }
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
          }
        });
  }

  private void backMain() {
    this.setOnCloseRequest(
        event -> {
          Alert.AlertType alertAlertType;
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("退出");
          alert.setHeaderText("你是否要退出?");
          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK) {
            MainUI.getINSTANCE().show();
            this.hide();

          } else {
            event.consume();
          }
        });
  }

  private void goRegister() {
    hyperlink.setOnMouseClicked(
        event -> {
          this.hide();
          RegisterUI.getINSTANCE().show();
        });
  }
}
