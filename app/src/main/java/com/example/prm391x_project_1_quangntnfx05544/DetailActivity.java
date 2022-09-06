package com.example.prm391x_project_1_quangntnfx05544;
import static com.example.prm391x_project_1_quangntnfx05544.Constant.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private ImageView back, background, favorite, notFavorite;
    private TextView animalName, animalDes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        initView(bundle);
    }

    private void initView(Bundle bundle) {
        back = findViewById(R.id.iv_back);
        background = findViewById(R.id.iv_animal_background);
        favorite = findViewById(R.id.iv_favorite);
        notFavorite = findViewById(R.id.iv_not_favorite);
        animalName = findViewById(R.id.tv_animal_name);
        animalDes = findViewById(R.id.tv_animal_description);

        back.setVisibility(View.VISIBLE);
        background.setImageResource(bundle.getInt(KEY_BACKGROUND_IMAGE));
        animalName.setText(bundle.getInt(KEY_NAME));
        animalDes.setText(bundle.getInt(KEY_DESCRIPTION));

        boolean isFavorite = bundle.getBoolean(KEY_IS_FAVORITE, false);
        if (isFavorite) favorite.setVisibility(View.VISIBLE);

        favorite.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_out));
            favorite.setVisibility(View.GONE);
            bundle.putBoolean(KEY_IS_FAVORITE, false);
        });
        notFavorite.setOnClickListener(v -> {
            favorite.setVisibility(View.VISIBLE);
            v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
            bundle.putBoolean(KEY_IS_FAVORITE, true);
        });
        back.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
