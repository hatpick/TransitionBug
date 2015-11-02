package com.transitionbug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private ImageView detailItemBackground;
    private TextView detailItemName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Item seletedItem = (Item) getIntent().getSerializableExtra("item");

        detailItemBackground = (ImageView) findViewById(R.id.detail_item_bg);
        detailItemName = (TextView) findViewById(R.id.detail_item_name);

        detailItemBackground.setBackground(getDrawable(seletedItem.getImageId()));
        detailItemName.setText(seletedItem.getName());
    }


    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            supportFinishAfterTransition();
            return true;
        }
        return true;
    }
}
