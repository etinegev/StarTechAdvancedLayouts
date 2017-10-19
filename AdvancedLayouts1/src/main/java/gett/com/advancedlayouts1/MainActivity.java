package gett.com.advancedlayouts1;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import gett.com.library.Helpers;
import gett.com.library.Item;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, ImageRVAdapter.RVClickListener {
  private ImageView mDetailedImg;
  private TextView mDetailsNameTv, mDetailsTv;
  private ImageButton mGridBtn, mListBtn;
  private RecyclerView mRecyclerView;
  private ImageRVAdapter mAdapter;
  private Item[] mThumbIds = Helpers.getItemsForDisplay();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mListBtn = (ImageButton) findViewById(R.id.button_list);
    mListBtn.setOnClickListener(this);
    mGridBtn = (ImageButton) findViewById(R.id.button_grid);
    mGridBtn.setOnClickListener(this);
    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    mDetailedImg = (ImageView) findViewById(R.id.img_details);
    mDetailsTv = (TextView) findViewById(R.id.tv_details);
    mDetailsNameTv = (TextView) findViewById(R.id.tv_name);
    mAdapter = new ImageRVAdapter(mThumbIds, this, ImageRVAdapter.GRID_TYPE);

    setLinearList(ImageRVAdapter.LIST_TYPE, new LinearLayoutManager(this), false);
    mRecyclerView.setAdapter(mAdapter);

    if (mDetailedImg != null) {
      clickedOnItem(0);
    }
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.button_list:
      case R.id.button_grid:
        toggleLayouts();
        break;
    }
  }

  private void toggleLayouts() {
    if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
      setLinearList(ImageRVAdapter.LIST_TYPE, new LinearLayoutManager(this), false);
    } else {
      setLinearList(ImageRVAdapter.GRID_TYPE, new GridLayoutManager(this, 2), true);
    }
    mRecyclerView.setAdapter(mAdapter);
  }

  private void setLinearList(int listType, LinearLayoutManager layout, boolean isListEnabled) {
    mAdapter.updateType(listType);
    mRecyclerView.setLayoutManager(layout);
    enableListButton(isListEnabled);
  }

  private void enableListButton(boolean isListEnabled) {
    mListBtn.setEnabled(isListEnabled);
    mGridBtn.setEnabled(!isListEnabled);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_manu, menu);
    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    searchView.setIconifiedByDefault(false);
    return true;
  }

  private void goToDetailsActivity(Item item) {
    startActivity(DetailsActivity.createIntent(this, item));
  }

  @Override public void clickedOnItem(int adapterPosition) {
    if (mDetailedImg != null) {
      mDetailedImg.setImageResource(mThumbIds[adapterPosition].getResId());
      mDetailsNameTv.setText(mThumbIds[adapterPosition].getName());
      mDetailsTv.setText(mThumbIds[adapterPosition].getDescription());
    } else {
      goToDetailsActivity(mThumbIds[adapterPosition]);
    }
  }
}
