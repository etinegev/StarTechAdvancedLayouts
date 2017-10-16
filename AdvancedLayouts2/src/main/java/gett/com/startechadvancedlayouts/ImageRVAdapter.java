package gett.com.startechadvancedlayouts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import gett.com.library.Item;

class ImageRVAdapter extends RecyclerView.Adapter<ImageRVAdapter.AppViewHolder> {
  final static int LIST_TYPE = 0;
  final static int GRID_TYPE = 1;

  private Item[] mThumbIds;
  private RVClickListener mClickListener;
  private int mType;

  ImageRVAdapter(Item[] thumbIds, RVClickListener clickListener, int type) {
    mThumbIds = thumbIds;
    mClickListener = clickListener;
    mType = type;
  }

  void updateType(int type) {
    mType = type;
  }

  @Override public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    return new ImageRVAdapter.AppViewHolder(v);
  }

  @Override public int getItemViewType(int position) {
    switch (mType) {
      case LIST_TYPE:
        return R.layout.rv_item_view;
      case GRID_TYPE:
        return R.layout.rv_grid_item_view;
    }
    return super.getItemViewType(position);
  }

  @Override public void onBindViewHolder(ImageRVAdapter.AppViewHolder holder, int position) {
    holder.bindViewHolder(mThumbIds[position]);
  }

  @Override public int getItemCount() {
    return mThumbIds.length;
  }

  class AppViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;
    private TextView mTvName, mTvDetails;

    AppViewHolder(View v) {
      super(v);
      mImageView = v.findViewById(R.id.img_view);
      mTvName = v.findViewById(R.id.tv_name);
      mTvDetails = v.findViewById(R.id.tv_details);
      v.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          mClickListener.clickedOnItem(getAdapterPosition());
        }
      });
    }

    void bindViewHolder(Item item) {
      mImageView.setImageResource(item.getResId());
      mTvName.setText(item.getName());
      mTvDetails.setText(item.getDescription());
    }
  }

  interface RVClickListener {
    void clickedOnItem(int adapterPosition);
  }
}
