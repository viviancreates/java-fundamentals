public class Video extends Media {
    private int duration;
    private String resolution;

    //add parameters to constructor
    public Video(String name, int duration, String resolution) {
        this.duration = duration;
        this.resolution = resolution;
        //inherited from media
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public String getResolution() {
        return resolution;
    }

    //add coid to setters - a setter method is meant to change a field inside an object, not return anything
    private void setDuration(int duration) {
        this.duration = duration;
    }

    private void setResolution(String resolution) {
        this.resolution = resolution;
    }

    //add abstract methodds from media with override

    //Video: "Playing video '[name]' using video player software"
    @Override
    public void play(){
        System.out.println("Playing video" + name + "using video player software.");

    }

    @Override
    public String getDescription(){
        return "Video: "+ name + "/nDuration: " + duration +"/nResolution: " + resolution;

    }
}
