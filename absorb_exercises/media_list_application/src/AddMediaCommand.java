public class AddMediaCommand {
    private TerminalUtils io;

    public AddMediaCommand(TerminalUtils io) {
        this.io = io;
    }

    public static void execute(MediaService service){
        io.displayMessage("Select media type:");
        io.displayMessage("1. Video\n2. Audio\n3. Image\n4. Book");

        int mediaType = io.getIntInput("Choose type: ");
        String name = io.getStringInput("Enter name: ");

        Media newMedia = null;

        switch (mediaType) {
            case 1:
                int duration = io.getIntInput("Enter duration: ");
                String resolution = io.getStringInput("Enter resolution: ");
                newMedia = new Video(name, duration, resolution);
                break;
            case 2:
                int audioDuration = io.getIntInput("Enter duration: ");
                String artist = io.getStringInput("Enter artist: ");
                newMedia = new Audio(audioDuration, artist, name);
                break;
            case 3:
                String dimensions = io.getStringInput("Enter dimensions: ");
                String format = io.getStringInput("Enter file format: ");
                newMedia = new Image(dimensions, format, name);
                break;
            case 4:
                String author = io.getStringInput("Enter author: ");
                int pages = io.getIntInput("Enter page count: ");
                newMedia = new Book(author, pages, name);
                break;
            default:
                io.displayMessage("Invalid media type.");
                return;
        }

        mediaService.addMedia(newMedia);
        io.displayMessage("Media added successfully.");

    }
}
