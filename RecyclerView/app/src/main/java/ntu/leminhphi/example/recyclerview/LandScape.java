package ntu.leminhphi.example.recyclerview;

public class LandScape {
    String landImageFileName;
    String landCation;

    public LandScape(String landCation, String landImageFileName) {
        this.landCation = landCation;
        this.landImageFileName = landImageFileName;
    }

    public String getLandImageFileName() {
        return landImageFileName;
    }

    public void setLandImageFileName(String landImageFileName) {
        this.landImageFileName = landImageFileName;
    }

    public String getLandCation() {
        return landCation;
    }

    public void setLandCation(String landCation) {
        this.landCation = landCation;
    }
}
