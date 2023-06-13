package com.example.sf5tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner type_spinner = findViewById(R.id.type_spinner);
        Spinner time_spinner = findViewById(R.id.time_spinner);
        Spinner goal_spinner = findViewById(R.id.goal_spinner);
        Spinner xp_spinner = findViewById(R.id.xp_spinner);
        Spinner points_spinner = findViewById(R.id.points_spinner);
        Button add_button = findViewById(R.id.add_button);
        EditText editText = findViewById(R.id.text_et);

        DataBaseHelper db = new DataBaseHelper();
        db.connect();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // получаем данные со спиннеров
                String text = editText.getText().toString();
                String type = type_spinner.getSelectedItem().toString();
                String time = time_spinner.getSelectedItem().toString();
                String goal = goal_spinner.getSelectedItem().toString();
                String xp = xp_spinner.getSelectedItem().toString();
                String points = points_spinner.getSelectedItem().toString();

                // проверяем заполнены ли они
                if (text.equals("Введите текст задания") || type.equals("Тип") ||
                        time.equals("Кол-во дней") || goal.equals("Кол-во для выполнения") ||
                        xp.equals("XP") || points.equals("Баллы")) {
                    Toast.makeText(getApplicationContext(), "Какое-то из полей не заполнено!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String[] data = new String[] {text, type, goal, xp, points, time};
                    db.updateTasks(data); // отправляем в нужный метод

                    Toast.makeText(getApplicationContext(), "Задание добавлено!", // выводим сообщение
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}