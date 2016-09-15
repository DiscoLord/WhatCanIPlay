package boriszeus.whatcaniplay;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Size;

/**
 * Created by Bo on 07.06.2016.
 */
public class GamesInfo implements Parcelable{

    private String name;
    private String description;
    private String imageLink;
    private Integer videocard;
    private Integer cpu;
    private Integer ram;
    private Integer releaseYear;
    private Integer metascore;
    private String steamLink;
    private String gogLink;

    public GamesInfo(String name, String description, String imageLink, Integer videocard, Integer cpu,
                     Integer ram, Integer releaseYear, Integer metascore, String steamLink, String gogLink){

        this.name = name;
        this.description=description;
        this.imageLink=imageLink;
        this.videocard=videocard;
        this.cpu=cpu;
        this.ram=ram;
        this.releaseYear=releaseYear;
        this.metascore=metascore;
        this.steamLink = steamLink;
        this.gogLink=gogLink;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Integer getVideocard() {
        return videocard;
    }

    public Integer getCpu() {
        return cpu;
    }

    public Integer getRam() {
        return ram;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Integer getMetascore() {
        return metascore;
    }

    public String getSteamLink() {
        return steamLink;
    }

    public String getGogLink() {
        return gogLink;
    }

    @Override
    public String toString(){
        return this.getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageLink);
        dest.writeInt(videocard);
        dest.writeInt(cpu);
        dest.writeInt(ram);
        dest.writeInt(releaseYear);
        dest.writeInt(metascore);
        dest.writeString(steamLink);
        dest.writeString(gogLink);

    }

    public static final Parcelable.Creator<GamesInfo> CREATOR = new Parcelable.Creator<GamesInfo>() {
        public GamesInfo createFromParcel(Parcel in) {
            return new GamesInfo(in);
        }

        public GamesInfo[] newArray(int size) {
            return new GamesInfo[size];
        }
    };
    private GamesInfo(Parcel in) {
        name = in.readString();
        description=in.readString();
        imageLink=in.readString();
        videocard=in.readInt();
        cpu=in.readInt();
        ram=in.readInt();
        releaseYear=in.readInt();
        metascore=in.readInt();
        steamLink=in.readString();
        gogLink=in.readString();

    }

}
