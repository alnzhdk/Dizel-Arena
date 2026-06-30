package com.dieselarena.sportapp.ui.login;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.dieselarena.sportapp.data.entity.User;
import com.dieselarena.sportapp.repository.AuthRepository;

public class LoginViewModel extends AndroidViewModel {
    private AuthRepository repository;

    public LoginViewModel(Application application) {
        super(application);
        repository = new AuthRepository(application);
    }

    public User login(String username, String password) {
        return repository.login(username, password);
    }
}