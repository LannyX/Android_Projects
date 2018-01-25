package edu.depaul.csc472.lanny.xul_remote_control_ii;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lanny on 10/25/17.
 */

public class Favorites implements Parcelable {

    private String position;
    private String label;
    private int chan;

    public Favorites(String position, String label, int chan){
        this.position = position;
        this.label = label;
        this.chan = chan;
    }

    public Favorites(){
        super();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Favorites(Parcel parcel){
        this.position = parcel.readString();
        this.label = parcel.readString();
        this.chan = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.position);
        parcel.writeString(this.label);
        parcel.writeInt(this.chan);
    }

    public static final Creator<Favorites> CREATOR = new Creator<Favorites>() {
        @Override
        public Favorites createFromParcel(Parcel parcel) {
            return new Favorites(parcel);
        }

        @Override
        public Favorites[] newArray(int i) {
            return new Favorites[i];
        }
    };

    public void setPosition(String position) {this.position = position; }
    public void setLabel(String label) {this.label = label; }
    public void setChan(int chan) {this.chan = chan; }

    public String getPosition(){return position; }
    public String getLabel() {return label;}
    public int getChan() {return chan; }
}
