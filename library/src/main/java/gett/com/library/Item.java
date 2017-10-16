package gett.com.library;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
  private int resId;
  private String name;
  private String description;

  public Item(int resId, String name, String description) {
    this.resId = resId;
    this.name = name;
    this.description = description;
  }

  public int getResId() {
    return resId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.resId);
    dest.writeString(this.name);
    dest.writeString(this.description);
  }

  protected Item(Parcel in) {
    this.resId = in.readInt();
    this.name = in.readString();
    this.description = in.readString();
  }

  public static final Creator<Item> CREATOR = new Creator<Item>() {
    @Override public Item createFromParcel(Parcel source) {
      return new Item(source);
    }

    @Override public Item[] newArray(int size) {
      return new Item[size];
    }
  };
}
