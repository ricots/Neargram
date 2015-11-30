package com.nearsoft.neargram.view.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.nearsoft.neargram.BR;
import com.nearsoft.neargram.instagram.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Photo view model.
 * Created by epool on 11/24/15.
 */
public class PhotoVM extends BaseObservable implements Parcelable {
    public static final Creator<PhotoVM> CREATOR = new Creator<PhotoVM>() {
        public PhotoVM createFromParcel(Parcel source) {
            return new PhotoVM(source);
        }

        public PhotoVM[] newArray(int size) {
            return new PhotoVM[size];
        }
    };
    private String thumbnailUrl;
    private String lowResolutionUrl;
    private String standardResolutionUrl;
    private String username;
    private String caption;
    private List<CommentVM> commentVMs = new ArrayList<>();

    public PhotoVM() {
    }

    public PhotoVM(Photo photo) {
        this.thumbnailUrl = photo.getImages().getThumbnail().getUrl();
        this.lowResolutionUrl = photo.getImages().getLowResolution().getUrl();
        this.standardResolutionUrl = photo.getImages().getStandardResolution().getUrl();
        if (photo.getUser() != null) {
            this.username = photo.getUser().getUsername();
        }
        if (photo.getCaption() != null) {
            this.caption = photo.getCaption().getText();
        }
        if (photo.getComments().getCount() > 0) {
            for (com.nearsoft.neargram.instagram.Comment comment : photo.getComments().getData()) {
                commentVMs.add(new CommentVM(comment));
            }
        }
    }

    protected PhotoVM(Parcel in) {
        this.thumbnailUrl = in.readString();
        this.lowResolutionUrl = in.readString();
        this.standardResolutionUrl = in.readString();
        this.username = in.readString();
        this.caption = in.readString();
        this.commentVMs = in.createTypedArrayList(CommentVM.CREATOR);
    }

    @Bindable
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        notifyPropertyChanged(BR.thumbnailUrl);
    }

    @Bindable
    public String getLowResolutionUrl() {
        return lowResolutionUrl;
    }

    public void setLowResolutionUrl(String lowResolutionUrl) {
        this.lowResolutionUrl = lowResolutionUrl;
        notifyPropertyChanged(BR.lowResolutionUrl);
    }

    @Bindable
    public String getStandardResolutionUrl() {
        return standardResolutionUrl;
    }

    public void setStandardResolutionUrl(String standardResolutionUrl) {
        this.standardResolutionUrl = standardResolutionUrl;
        notifyPropertyChanged(BR.standardResolutionUrl);
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
        notifyPropertyChanged(BR.caption);
    }

    @Bindable
    public List<CommentVM> getCommentVMs() {
        return commentVMs;
    }

    public void setCommentVMs(List<CommentVM> commentVMs) {
        this.commentVMs = commentVMs;
        notifyPropertyChanged(BR.commentVMs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.thumbnailUrl);
        dest.writeString(this.lowResolutionUrl);
        dest.writeString(this.standardResolutionUrl);
        dest.writeString(this.username);
        dest.writeString(this.caption);
        dest.writeTypedList(commentVMs);
    }
}
