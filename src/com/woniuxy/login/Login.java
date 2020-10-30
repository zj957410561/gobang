package com.woniuxy.login;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * TODO
 *
 * @author 95741
 * @version 1.0
 * @date 2020/10/21 16:39
 */
public class Login extends Application {
    int n = 0;
    boolean flags = false;
    Pane pane;
    Label label;
    Label label1;
    Label label2;
    PasswordField textField2;
    TextField textField1;
    Button button = new Button();
    TextField textField;
    private int width = 700; // 宽度
    private int height = 740; // 高度


    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = new Pane();
        // 2.创建一个场景
        Scene scene = new Scene(pane, width, height);
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(1, 1, 23, 0.3), null, null)));
        // 给舞台设置一个场景
        primaryStage.setScene(scene);
        // 关闭自适应 禁止窗口拉伸
        primaryStage.setResizable(false);
        primaryStage.setTitle("登录界面");
        primaryStage.show();
        label1 = JavaFXUtil.getTextLabel(label, 250, 200, 50, 40, "用户名");
        textField1 = JavaFXUtil.getTextField(textField, 300, 200, 200, 40);
        pane.getChildren().add(textField1);
        pane.getChildren().add(label1);
        label2 = JavaFXUtil.getTextLabel(label, 250, 300, 50, 40, "密码");
        textField2 = JavaFXUtil.getPasswordField(textField2, 300, 300, 200, 40);
        pane.getChildren().add(textField2);
        pane.getChildren().add(label2);
        button = JavaFXUtil.getButton("登录", 350, 380, 100, 50, 16);
        pane.getChildren().add(button);
        login(primaryStage);
    }

    public void login(Stage stage) {
        button.setOnAction(e -> {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader("C:\\127.csv");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String name = textField1.getText();
            String password = textField2.getText();
            while (true) {
                try {
                    String reader = bufferedReader.readLine();
                    if (reader != null) {
                        String[] b = reader.split(",");
                        if (b[0].equals(name) && b[1].equals(password)) {
                            flags = true;
                            bufferedReader.close();
                            break;
                        }
                    } else {
                        flags = false;
                        n++;
                        bufferedReader.close();
                        break;
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (flags) {
                JavaFXUtil.alert("登录成功", "1111", "1111");
                Car car = new Car(textField1.getText());
                try {
                    stage.hide();
                    car.start(new Stage());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (n <= 3) {
                JavaFXUtil.alert("登录失败", "用户名密码不正确", "失败" + n + "次数");
            } else {
                JavaFXUtil.alert("登录失败四次", "请先去注册", "失败" + n + "次数");
                n = 0;
                Register register = new Register();
                try {
                    register.start(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

    }

}
