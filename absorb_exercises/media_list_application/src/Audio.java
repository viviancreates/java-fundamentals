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

    public getArtist() {
        return artist;
    }

    private void int setDuration(int duration) {
        this.duration = duration;
    }

    private void String setArtist(String artist) {
        this.artist = artist;
    }
}
