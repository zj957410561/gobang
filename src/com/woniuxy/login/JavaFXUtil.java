package com.woniuxy.login;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;

/**
 * 自定义JavaFX工具
 */
public class JavaFXUtil {

    /**
     * 弹框工具类
     *
     * @param title
     * @param header
     * @param content
     */
    public static void alert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION); // 弹框
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * @param text
     * @param x
     * @param y
     * @param width
     * @param height
     * @param fontSize
     * @return
     */
    public static Button getButton(String text, int x, int y, int width, int height, int fontSize) {
        Button btn = new Button();
        btn.setLayoutX(x);
        btn.setLayoutY(y);
        btn.setText(text);
        btn.setPrefSize(width, height);
        btn.setFont(new Font(fontSize));
        return btn;
    }

    public static TextField getTextField(TextField textField, int x, int y, int width, int height) {
        textField = new TextField();
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setMaxWidth(width);
        textField.setMaxHeight(height);
        return textField;
    }

    public static PasswordField getPasswordField(PasswordField textField, int x, int y, int width, int height) {
        textField = new PasswordField();
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setMaxWidth(width);
        textField.setMaxHeight(height);
        return textField;
    }

    public static Label getTextLabel(Label textField, int x, int y, int width, int height, String s) {
        textField = new Label();
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setMaxWidth(width);
        textField.setMaxHeight(height);
        textField.setText(s);
        return textField;
    }
}
