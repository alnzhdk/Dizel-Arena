package com.dieselarena.sportapp.ui.sportsman.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.dieselarena.sportapp.R;
import com.dieselarena.sportapp.data.entity.User;

public class ProfileFragment extends Fragment {
    private static final int PICK_IMAGE = 1;
    private ImageView photoView;
    private TextView nameText;
    private TextView roleText;
    private TextView phoneText;
    private Button changePhotoButton;
    private ProfileViewModel viewModel;
    private int userId;

    public static ProfileFragment newInstance(int userId) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt("user_id", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt("user_id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        photoView = view.findViewById(R.id.photo_view);
        nameText = view.findViewById(R.id.name_text);
        roleText = view.findViewById(R.id.role_text);
        phoneText = view.findViewById(R.id.phone_text);
        changePhotoButton = view.findViewById(R.id.change_photo_button);

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        viewModel.getUser(userId).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                nameText.setText(user.getName());
                roleText.setText(user.getRole());
                phoneText.setText(user.getPhone());

                if (user.getPhotoUri() != null && !user.getPhotoUri().isEmpty()) {
                    Glide.with(this).load(user.getPhotoUri()).into(photoView);
                }
            }
        });

        changePhotoButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            viewModel.updatePhoto(userId, imageUri.toString());
            Glide.with(this).load(imageUri).into(photoView);
        }
    }
}