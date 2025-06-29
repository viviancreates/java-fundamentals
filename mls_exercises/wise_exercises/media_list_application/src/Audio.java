public class Audio extends Media {
    private int duration;
    private String artist;

    public Audio(int duration, String artist, String name) {
        this.duration = duration;
        this.artist = artist;
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }

    private void setArtist(String artist) {
        this.artist = artist;
    }

    //add abstract methodds from media with override

    @Override
    //Audio: "Playing audio '[name]' using audio player software"
    public void play(){
        System.out.println("Playing audio " + name + " using audio player software.");
    };

    @Override
    public String getDescription(){
        return "Audio: "+ name + "\nDuration: " + duration +"\nArtist: " + artist;
    };
}
