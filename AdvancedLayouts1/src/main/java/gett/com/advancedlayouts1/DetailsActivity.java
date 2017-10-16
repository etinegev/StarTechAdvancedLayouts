package gett.com.advancedlayouts1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import gett.com.library.Item;

public class DetailsActivity extends AppCompatActivity {
  private final static String ITEM_KEY = "item_key";

  public static Intent createIntent(Context context, Item item) {
    Intent intent = new Intent(context, DetailsActivity.class);
    intent.putExtra(ITEM_KEY, item);
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    ImageView detailedImg = (ImageView) findViewById(R.id.img_details);
    TextView detailsTv = (TextView) findViewById(R.id.tv_details);
    TextView nameTv = (TextView) findViewById(R.id.tv_name);

    Item item = getIntent().getParcelableExtra(ITEM_KEY);
    nameTv.setText(item.getName());
    detailsTv.setText(item.getDescription());
    detailedImg.setImageResource(item.getResId());
  }
}