public class Main {
    public static void main(String[] args) {

        Video video = new Video("Example", 100, "4K");
        System.out.println(video.getDescription());
        video.play();

        Audio audio = new Audio(60, "TS", "Red");
        System.out.println(audio.getDescription());
        audio.play();

        Book book = new Book("Dostoevsky", 300, "Crime and Punishment");
        System.out.println(book.getDescription());
        book.play();

        Image image = new Image("5x5", "jpeg", "Selfie");
        System.out.println(image.getDescription());
        image.play();
    }
}