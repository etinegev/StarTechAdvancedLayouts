package gett.com.advancedlayouts1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import gett.com.library.Item;

class ImageRVAdapter extends RecyclerView.Adapter<ImageRVAdapter.AppViewHolder> {

  private Item[] mThumbIds;
  private RVClickListener mClickListener;

  ImageRVAdapter(Item[] thumbIds, RVClickListener clickListener) {
    mThumbIds = thumbIds;
    mClickListener = clickListener;
  }

  @Override public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_view, parent, false);
    return new ImageRVAdapter.AppViewHolder(v);
  }

  @Override public void onBindViewHolder(ImageRVAdapter.AppViewHolder holder, int position) {
    holder.bindViewHolder(mThumbIds[position]);
  }

  @Override public int getItemCount() {
    return mThumbIds.length;
  }

  class AppViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;
    private TextView mTvName;

    AppViewHolder(View v) {
      super(v);
      mImageView = v.findViewById(R.id.img_view);
      mTvName = v.findViewById(R.id.tv_name);
      v.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          mClickListener.clickedOnItem(mThumbIds[getAdapterPosition()]);
        }
      });
    }

    void bindViewHolder(Item item) {
      mImageView.setImageResource(item.getResId());
      mTvName.setText(item.getName());
    }
  }

  interface RVClickListener {
    void clickedOnItem(Item item);
  }
}
