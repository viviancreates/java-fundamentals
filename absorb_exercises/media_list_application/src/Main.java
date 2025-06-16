public class Main {
    public static void main(String[] args) {

        Video video = new Video("Example", 100, "4K");
        System.out.println(video.getDescription());
        video.play();
    }
}