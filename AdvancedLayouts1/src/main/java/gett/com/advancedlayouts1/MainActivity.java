package gett.com.advancedlayouts1;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import gett.com.library.Helpers;
import gett.com.library.Item;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, ImageRVAdapter.RVClickListener {
  private RecyclerView mRecyclerView;
  private GridView mGridView;
  private ImageButton mGridBtn, mListBtn;
  private Item[] mThumbIds = Helpers.getItemsForDisplay();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mListBtn = (ImageButton) findViewById(R.id.button_list);
    mListBtn.setOnClickListener(this);
    mGridBtn = (ImageButton) findViewById(R.id.button_grid);
    mGridBtn.setOnClickListener(this);
    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    mGridView = (GridView) findViewById(R.id.grid_view);

    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);

    ImageRVAdapter mAdapter = new ImageRVAdapter(mThumbIds, this);
    mRecyclerView.setAdapter(mAdapter);

    mGridView.setAdapter(new ImageGVAdapter(this, mThumbIds));

    mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        clickedOnItem(position);
      }
    });

    showList();
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.button_list:
        showList();
        break;
      case R.id.button_grid:
        showGrid();
        break;
    }
  }

  private void showList() {
    toggleLayouts(true);
  }

  private void showGrid() {
    toggleLayouts(false);
  }

  private void toggleLayouts(boolean showList) {
    mRecyclerView.setVisibility(showList ? View.VISIBLE : View.GONE);
    mGridView.setVisibility(!showList ? View.VISIBLE : View.GONE);
    mListBtn.setEnabled(!showList);
    mGridBtn.setEnabled(showList);
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

  private void clickedOnItem(int adapterPosition) {
    clickedOnItem(mThumbIds[adapterPosition]);
  }

  @Override public void clickedOnItem(Item item) {
    startActivity(DetailsActivity.createIntent(this, item));
  }
}
