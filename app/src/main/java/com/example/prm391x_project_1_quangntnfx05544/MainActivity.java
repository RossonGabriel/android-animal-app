package com.example.prm391x_project_1_quangntnfx05544;

import static com.example.prm391x_project_1_quangntnfx05544.Constant.KEY_BACKGROUND_IMAGE;
import static com.example.prm391x_project_1_quangntnfx05544.Constant.KEY_DESCRIPTION;
import static com.example.prm391x_project_1_quangntnfx05544.Constant.KEY_FAVORITE;
import static com.example.prm391x_project_1_quangntnfx05544.Constant.KEY_ID;
import static com.example.prm391x_project_1_quangntnfx05544.Constant.KEY_IS_FAVORITE;
import static com.example.prm391x_project_1_quangntnfx05544.Constant.KEY_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = DatabaseHandler.getInstance(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null)
            db.updateAnimalFavorite(bundle.getInt(KEY_ID), bundle.getBoolean(KEY_IS_FAVORITE));

        initData();
        initView();
    }

    private void initView() {
        ArrayList<Animal> animals = db.getAllAnimals();
        for (Animal animal : animals) {
            int animalId = animal.getId();
            findViewById(animalId).setOnClickListener(v -> {
                v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
                Intent intent = new Intent(this, DetailActivity.class);
                Bundle bundle = animalToBundle(db.getAnimal(animalId));

                intent.putExtras(bundle);
                startActivity(intent);
            });
            if (animal.isFavorite()) {
                ImageView favorite = findViewById(animal.getFavorite());
                favorite.setVisibility(View.VISIBLE);
                favorite.setOnClickListener(v -> {
                    v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_out));
                    favorite.setVisibility(View.GONE);
                    db.updateAnimalFavorite(animalId, false);
                });

            }
        }
    }

    private void initData() {
        /* Init data in the first time run */
        if (db.isEmpty()) {
            db.addAnimal(new Animal(R.string.elephant, R.id.iv_icon_elephant, R.drawable.bg_elephant, R.string.elephant_des, R.id.iv_elephant_favorite, false));
            db.addAnimal(new Animal(R.string.dragonfly, R.id.iv_icon_dragonfly, R.drawable.bg_dragonfly, R.string.dragonfly_des, R.id.iv_dragonfly_favorite, false));
            db.addAnimal(new Animal(R.string.dolphin, R.id.iv_icon_dolphin, R.drawable.bg_dolphin, R.string.dolphin_des, R.id.iv_dolphin_favorite, false));
            db.addAnimal(new Animal(R.string.dog, R.id.iv_icon_dog, R.drawable.bg_dog, R.string.dog_des, R.id.iv_dog_favorite, false));
            db.addAnimal(new Animal(R.string.pig, R.id.iv_icon_pig, R.drawable.bg_pig, R.string.pig_des, R.id.iv_pig_favorite, false));
            db.addAnimal(new Animal(R.string.goose, R.id.iv_icon_goose, R.drawable.bg_goose, R.string.goose_des, R.id.iv_goose_favorite, false));
            db.addAnimal(new Animal(R.string.ladybug, R.id.iv_icon_ladybug, R.drawable.bg_ladybug, R.string.ladybug_des, R.id.iv_ladybug_favorite, false));
            db.addAnimal(new Animal(R.string.turtle, R.id.iv_icon_turtle, R.drawable.bg_turtle, R.string.turtle_des, R.id.iv_turtle_favorite, false));
            db.addAnimal(new Animal(R.string.penguin, R.id.iv_icon_penguin, R.drawable.bg_penguin, R.string.penguin_des, R.id.iv_penguin_favorite, false));
        }
    }

    private Bundle animalToBundle(Animal animal) {
        Bundle bundle = new Bundle();

        bundle.putInt(KEY_ID, animal.getId());
        bundle.putInt(KEY_NAME, animal.getName());
        bundle.putInt(KEY_BACKGROUND_IMAGE, animal.getBackgroundImage());
        bundle.putInt(KEY_DESCRIPTION, animal.getDescription());
        bundle.putInt(KEY_FAVORITE, animal.getFavorite());
        bundle.putBoolean(KEY_IS_FAVORITE, animal.isFavorite());

        return bundle;
    }
}