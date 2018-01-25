package edu.depaul.csc472.lanny.xul_things_to_do_in_beijing;



public class Things{

    enum Type{Sites, Museums, Parks, Food}

    String name;
    Type type;
    String sDes;
    String lDes;
    float rating = 4.0f;

    public Things (String name, Type type, String sDes, String lDes, float rating){
        this.name = name;
        this.type = type;
        this.sDes = sDes;
        this.lDes = lDes;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public String getSDes() {
        return sDes;
    }
    public void setSDes(String sDes) {
        this.sDes = sDes;
    }
    public String getLDes() {
        return lDes;
    }
    public void setLDes(String lDes) {
        this.lDes = lDes;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String toString() {
        return name;
    }

    public static int getIconResource(Type type) {
        switch (type) {
            case Sites:
                return  R.drawable.sites;
            case Museums:
                return R.drawable.museums;
            case Parks:
                return R.drawable.park;
            case Food:
                return R.drawable.food;
        }
        return -1;
    }
}
