package gett.com.advancedlayouts1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import gett.com.library.Item;

class ImageGVAdapter extends BaseAdapter {
  private Context mContext;
  private Item[] mThumbIds;

  ImageGVAdapter(Context context, Item[] thumbIds) {
    mContext = context;
    mThumbIds = thumbIds;
  }

  @Override public int getCount() {
    return mThumbIds.length;
  }

  @Override public Object getItem(int i) {
    return null;
  }

  @Override public long getItemId(int i) {
    return 0;
  }

  @Override public View getView(int position, View view, ViewGroup viewGroup) {
    View itemView;
    if (view == null) {
      itemView =
          LayoutInflater.from(mContext).inflate(R.layout.rv_grid_item_view, viewGroup, false);
    } else {
      itemView = view;
    }

    ((ImageView) itemView.findViewById(R.id.img_view)).setImageResource(
        mThumbIds[position].getResId());
    ((TextView) itemView.findViewById(R.id.tv_name)).setText(mThumbIds[position].getName());
    ((TextView) itemView.findViewById(R.id.tv_details)).setText(
        mThumbIds[position].getDescription());
    return itemView;
  }
}
