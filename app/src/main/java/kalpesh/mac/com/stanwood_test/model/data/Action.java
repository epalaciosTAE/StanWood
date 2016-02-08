package kalpesh.mac.com.stanwood_test.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Action {

    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("layout")
    @Expose
    private String layout;

    /**
     *
     * @return
     * The data
     */
    public String getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The layout
     */
    public String getLayout() {
        return layout;
    }

    /**
     *
     * @param layout
     * The layout
     */
    public void setLayout(String layout) {
        this.layout = layout;
    }

}