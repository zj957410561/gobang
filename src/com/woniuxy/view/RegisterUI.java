package com.woniuxy.view;

import com.woniuxy.util.JavaFXUtil;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
public class RegisterUI extends Stage {
  private static final RegisterUI INSTANCE = new RegisterUI();
  TextField loginfield = JavaFXUtil.getTextField(150, 50, 200, 50);
  Label pwdlabel = JavaFXUtil.getTextLabel("密          码:", 25, 120, 20);
  PasswordField pwdfield = JavaFXUtil.getPasswordField(150, 120, 200, 50);
  PasswordField pwdfields = JavaFXUtil.getPasswordField(150, 190, 200, 50);
  Label pwdlabels = JavaFXUtil.getTextLabel("再次输入密码:", 25, 190, 20);
  Label userlabel = JavaFXUtil.getTextLabel("用    户    名:", 25, 50, 20);
  Button registerbutton = JavaFXUtil.getButton("注册", 100, 260, 90, 40, 20);
  Button backbutton = JavaFXUtil.getButton("返回", 210, 260, 90, 40, 20);

  private RegisterUI() {
    super();
    init();
  }

  public static RegisterUI getINSTANCE() {
    return INSTANCE;
  }

  private void init() {
    // 1.创建面板
    Pane pane = new Pane();
    // 1.1
    draw(pane);
    // 注册
    register();
    // 返回
    back();

    backMain();
  }

  /**
   * 绘制页面
   *
   * @param pane
   */
  private void draw(Pane pane) {
    pane.getChildren().add(userlabel);
    pane.getChildren().add(loginfield);
    pane.getChildren().add(pwdlabel);
    pane.getChildren().add(pwdfield);
    pane.getChildren().add(pwdlabels);
    pane.getChildren().add(pwdfields);
    pane.getChildren().add(registerbutton);
    pane.getChildren().add(backbutton);
    // 2.创建一个场景
    Scene scene = new Scene(pane, 400, 300);
    scene.getStylesheets().add(RegisterUI.class.getResource("css/Register.css").toExternalForm());
    // 在舞台中设置场景
    this.setScene(scene);
    /* this.setResizable(false);*/
    this.setTitle("五子棋游戏");
  }

  /** 注册 */
  public void register() {
    registerbutton.setOnMouseClicked(
        event -> {
          BufferedWriter bufferedWriter = null;
          BufferedReader bufferedReader = null;
          try {
            FileWriter fileWriter = new FileWriter(new File("127.csv"), true);
            bufferedWriter = new BufferedWriter(fileWriter);
            FileReader fileReader = new FileReader(new File("127.csv"));
            bufferedReader = new BufferedReader(fileReader);
          } catch (IOException e) {
            e.printStackTrace();
          }
          if (check()) {
            int id = 1;
            boolean flag = true;
            while (true) {
              try {
                String a = bufferedReader.readLine();
                if (a != null) {
                  String[] b = a.split(",");
                  id = Integer.parseInt(b[0]);
                  if (loginfield.getText().equals(b[1])) {
                    JavaFXUtil.alert("用户名重复", "用户名重复", "请重新输入");
                    flag = false;
                    break;
                  }
                } else {
                  break;
                }
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
            if (flag) {
              String contents = ++id + "," + loginfield.getText() + "," + pwdfield.getText();
              try {
                bufferedWriter.write(contents);
                bufferedWriter.newLine();
                bufferedReader.close();
                bufferedWriter.close();
              } catch (IOException e) {
                e.printStackTrace();
              }
              JavaFXUtil.alert("注册成功", "注册成功", "前往登录界面");
              LoginUI.getINSTANCE().show();
              this.hide();
            }

          } else {
            JavaFXUtil.alert("格式错误", "输入有误", "请重新输入");
            loginfield.setText("");
            pwdfield.setText("");
            pwdfields.setText("");
          }
        });
  }
  /** 返回 */
  private void back() {
    backbutton.setOnMouseClicked(
        event -> {
          LoginUI.getINSTANCE().show();
          this.hide();
        });
  }
  /**
   * 正则校验
   *
   * @return
   */
  private boolean check() {
    String name = loginfield.getText();
    String password = pwdfield.getText();
    String password2 = pwdfields.getText();
    String namePatern = "^[a-zA-Z]{4,16}$";
    String pwdPatern = "^[A-Za-z0-9]{6,8}$";
    if (name != null
        && password != null
        && password.equals(password2)
        && name.matches(namePatern)
        && password.matches(pwdPatern)) {
      return true;
    } else {
      return false;
    }
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
            LoginUI.getINSTANCE().show();
            this.hide();

          } else {
            event.consume();
          }
        });
  }

  private void setBackGround() {}
}
