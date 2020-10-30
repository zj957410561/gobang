package com.woniuxy.login;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Main;

import java.io.*;


/**
 * TODO
 *
 * @author 95741
 * @version 1.0
 * @date 2020/10/21 19:06
 */
public class Register extends Application {
    boolean flag = true;
    Pane pane;
    Label label;
    Label label1;
    Label label2;
    Label label3;
    Label label4;
    Label label5;
    Button button;
    Button button1;
    TextField textField;
    TextField textField1;
    TextField textField2;
    TextField textField3;
    TextField textField4;
    TextField textField5;
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
        primaryStage.setTitle("注册界面");
        primaryStage.show();
        draw();
        login(primaryStage);
        back(primaryStage);

    }

    public void login(Stage stage) {
        button.setOnAction(e -> {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(new File("C:\\127.csv"), true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            FileReader fileReader = null;
            try {
                fileReader = new FileReader("C:\\127.csv");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String name = textField1.getText();
            String password = textField2.getText();
            String repeadPwd = textField3.getText();
            String phone = textField4.getText();
            String birth = textField5.getText();
            String namePatern = "^[a-zA-Z]{4,16}$";
            String phonePatern = "^(13|14|15|16|17|18|19)[0-9]{9}$";
            String datePatern = "^\\d{4}(\\-)\\d{1,2}\\1\\d{1,2}$";
            // phone.matches(phonePatern)&&namePatern.matches(namePatern)&&
            if (phone.matches(phonePatern)
                    && name.matches(namePatern)
                    && birth.matches(datePatern)
                    && password.equals(repeadPwd)
                    && phone != null && name != null && birth != null && password != null && repeadPwd != null) {
                String a;
                while (true) {
                    try {
                        if (!((a = bufferedReader.readLine()) != null)) {
                            flag = true;
                            break;
                        } else {
                            String[] b = a.split(",");
                            if (b[0].equals(name)) {
                                flag = false;
                                break;
                            }
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                if (flag) {
                    String c = name + "," + password + "," + phone + "," + birth;
                    try {
                        bufferedWriter.write(c);
                        bufferedWriter.newLine();
                        bufferedWriter.close();
                        bufferedReader.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    sample.Main open = new sample.Main();
                    try {
                        JavaFXUtil.alert("注册成功", "进入主页面", "111");
                        open.start(new Stage());
                        stage.hide();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else {
                    JavaFXUtil.alert("数据错误", "用户名重复", "请重新输入");
                }
            } else {
                JavaFXUtil.alert("数据错误", "输入有误", "请重新输入");
            }
        });

    }

    public void back(Stage stage) {
        button1.setOnAction(event -> {
            sample.Main main = new Main();
            try {
                main.start(new Stage());
                stage.hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void draw() {
        label1 = JavaFXUtil.getTextLabel(label, 220, 200, 80, 40, "用户名");
        textField1 = JavaFXUtil.getTextField(textField, 300, 200, 200, 40);
        pane.getChildren().add(textField1);
        pane.getChildren().add(label1);
        label2 = JavaFXUtil.getTextLabel(label, 220, 300, 80, 40, "密码");
        textField2 = JavaFXUtil.getTextField(textField, 300, 300, 200, 40);
        pane.getChildren().add(textField2);
        pane.getChildren().add(label2);
        label3 = JavaFXUtil.getTextLabel(label, 220, 400, 80, 40, "确认密码");
        textField3 = JavaFXUtil.getTextField(textField, 300, 400, 200, 40);
        pane.getChildren().add(textField3);
        pane.getChildren().add(label3);
        label4 = JavaFXUtil.getTextLabel(label, 220, 500, 80, 40, "手机号码");
        textField4 = JavaFXUtil.getTextField(textField, 300, 500, 200, 40);
        pane.getChildren().add(textField4);
        pane.getChildren().add(label4);
        label5 = JavaFXUtil.getTextLabel(label, 220, 600, 80, 40, "身份证号码");
        textField5 = JavaFXUtil.getTextField(textField, 300, 600, 200, 40);
        pane.getChildren().add(textField5);
        pane.getChildren().add(label5);
        button = JavaFXUtil.getButton("注册", 350, 700, 100, 50, 16);
        pane.getChildren().add(button);
        button1 = JavaFXUtil.getButton("返回主页面", 500, 700, 100, 50, 16);
        pane.getChildren().add(button1);
    }
}
