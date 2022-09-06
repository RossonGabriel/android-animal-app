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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();

        /* Set the UI resource depend on which animal user chose */
        ((ImageView) findViewById(R.id.iv_animal_background)).setImageResource(bundle.getInt(KEY_BACKGROUND_IMAGE));
        ((TextView) findViewById(R.id.tv_animal_name)).setText(bundle.getInt(KEY_NAME));
        ((TextView) findViewById(R.id.tv_animal_description)).setText(bundle.getInt(KEY_DESCRIPTION));

        ImageView favorite = findViewById(R.id.iv_favorite);
        ImageView notFavorite = findViewById(R.id.iv_not_favorite);
        /* For red heart appear when user liked animal */
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

        /* Return to the main activity when user click on back icon */
        findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
        findViewById(R.id.iv_back).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
