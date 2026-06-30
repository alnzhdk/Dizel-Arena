package com.dieselarena.sportapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.data.entity.User;
import com.dieselarena.sportapp.ui.sportsman.SportsmanActivity;
import com.dieselarena.sportapp.ui.trainer.TrainerActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.login_button);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = usernameEditText.getText().toString().trim();
                final String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final User user = viewModel.login(username, password);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (user != null) {
                                    Intent intent;
                                    if ("sportsman".equals(user.getRole())) {
                                        intent = new Intent(LoginActivity.this, SportsmanActivity.class);
                                    } else {
                                        intent = new Intent(LoginActivity.this, TrainerActivity.class);
                                    }
                                    intent.putExtra("user_id", user.getId());
                                    intent.putExtra("user_name", user.getName());
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
}