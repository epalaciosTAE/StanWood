package kalpesh.mac.com.stanwood_test.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Image implements Parcelable {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("aspectRatio")
    @Expose
    private Double aspectRatio;

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The aspectRatio
     */
    public Double getAspectRatio() {
        return aspectRatio;
    }

    /**
     *
     * @param aspectRatio
     * The aspectRatio
     */
    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }




    protected Image(Parcel in) {
        url = in.readString();
        aspectRatio = in.readDouble();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeDouble(aspectRatio);
    }
}