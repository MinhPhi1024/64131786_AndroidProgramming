package ntu.leminhphi.example.giuakymauluyentap;

public class Landscape {
    String landImageFileName;
    String landName;

    public Landscape(String landImageFileName, String landName) {
        this.landImageFileName = landImageFileName;
        this.landName = landName;
    }

    public String getLandImageFileName() {
        return landImageFileName;
    }

    public void setLandImageFileName(String landImageFileName) {
        this.landImageFileName = landImageFileName;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }
}
