package com.example.instagram;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class StoryModel extends ArrayList<Parcelable> implements Parcelable {
    private  String name;
    private  String ImgStory;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgStory() {
        return ImgStory;
    }

    public void setImgStory(String imgStory) {
        ImgStory = imgStory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.ImgStory);
    }

    public StoryModel() {
    }

    protected StoryModel(Parcel in) {
        this.name = in.readString();
        this.ImgStory = in.readString();
    }

    public static final Parcelable.Creator<StoryModel> CREATOR = new Parcelable.Creator<StoryModel>() {
        @Override
        public StoryModel createFromParcel(Parcel source) {
            return new StoryModel(source);
        }

        @Override
        public StoryModel[] newArray(int size) {
            return new StoryModel[size];
        }
    };
}
