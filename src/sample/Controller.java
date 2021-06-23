package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    private boolean start = true;
    private String operate = "";
    private float num1 = 0;


    @FXML
    private Label res;

    @FXML
    public void numbers(ActionEvent event) {
        if (start) {
            res.setText("");
            start = false;
        }
        res.setText(res.getText() + ((Button) event.getSource()).getText());
    }

    @FXML
    public void operators(ActionEvent event) {
        float num2 = 0;
        String value = ((Button) event.getSource()).getText();

        if (value.equals("AC")) {
            start = true;
            operate = "";
            res.setText("");
            return;
        }

        if (!value.equals("=")) {
            if (!operate.isEmpty())
                return;
            operate = value;
            num1 = Float.parseFloat(res.getText());
            res.setText("");
        } else {
            if (operate.isEmpty())
                return;
            if (operate.equals("sin") || operate.equals("cos")) {
                float answer2 = calculate2(num1,operate);
                res.setText(String.valueOf(answer2));
                operate = "";
                start = true;
                num1 = answer2;
            }else {
                num2 = Float.parseFloat(res.getText());
                float answer = calculate1(num1, num2, operate);
                res.setText(String.valueOf(answer));
                operate = "";
                start = true;
                num1 = answer;
            }
            }
    }


    public float calculate1(float num1, float num2, String opr) {
        switch (opr) {
            case "+":
                return num1 + num2;
            case "_":
                return num1 - num2;
            case "x":
                return num1 * num2;
            case "/":
                if (num2 == 0)
                    return 0;
                return num1 / num2;
            default:
                return 0;
        }

    }

    public float calculate2(float num, String opr) {
        switch (opr) {
            case "sin":
                return (float) Math.sin((num*3.14)/180);
            case "cos":
                return (float) Math.cos((num/180)*3.14);
            default:
                return 0;
        }
    }
}
