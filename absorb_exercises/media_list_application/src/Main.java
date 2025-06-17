public class Main {
    public static void main(String[] args) {
        MediaService mediaService = new MediaService();
        TerminalUtils io = new TerminalUtils();
        boolean running = true;

        while (running) {
            TerminalUtils.displayMenu();
            int choice = io.getIntInput("");

            switch (choice) {
                case 1:
                    io.displayMessage("Select media type:");
                    io.displayMessage("1. Video");
                    io.displayMessage("2. Audio");
                    io.displayMessage("3. Image");
                    io.displayMessage("4. Book");

                    int mediaType = io.getIntInput("Choose type: ");
                    String name = io.getStringInput("Enter name: ");

                    Media newMedia = null;

                    if (mediaType == 1) {
                        newMedia = createVideo(name, io);
                    } else if (mediaType == 2) {
                        newMedia = createAudio(name, io);
                    } else if (mediaType == 3) {
                        newMedia = createImage(name, io);
                    } else if (mediaType == 4) {
                        newMedia = createBook(name, io);
                    }

                    if (newMedia != null) {
                        mediaService.addMedia(newMedia);
                        io.displayMessage("Media added successfully.");
                    } else {
                        io.displayMessage("Invalid media type.");
                    }
                    break;

                case 2:
                    String nameToRemove = io.getStringInput("Enter the name of the media to remove: ");
                    boolean removed = mediaService.removeMedia(nameToRemove);
                    if (removed) {
                        io.displayMessage("Media removed.");
                    } else {
                        io.displayMessage("Media not found.");
                    }
                    break;

                case 3:
                    String nameToPlay = io.getStringInput("Enter the name of the media to play: ");
                    Media mediaToPlay = mediaService.findMediaByName(nameToPlay);
                    if (mediaToPlay != null) {
                        mediaToPlay.play();
                    } else {
                        io.displayMessage("Media not found.");
                    }
                    break;

                case 4:
                    if (mediaService.isEmpty()) {
                        io.displayMessage("Media list is empty");
                    } else {
                        io.displayMessage("All Media:");
                        for (Media media : mediaService.getList()) {
                            System.out.println(media.getDescription());
                            System.out.println();
                        }
                    }
                    break;

                case 5:
                    io.displayMessage("Thank you! Goodbye");
                    running = false;
                    break;

                default:
                    io.displayMessage("Invalid option. Try again.");
            }
        }


    }

    //add private helper methods, cut out rep
    private static Video createVideo(String name, TerminalUtils io) {
        int duration = io.getIntInput("Enter duration: ");
        String resolution = io.getStringInput("Enter resolution: ");
        return new Video(name, duration, resolution);
    }

    private static Audio createAudio(String name, TerminalUtils io) {
        int duration = io.getIntInput("Enter duration: ");
        String artist = io.getStringInput("Enter artist name: ");
        return new Audio(duration, artist, name);
    }

    private static Image createImage(String name, TerminalUtils io) {
        String dimensions = io.getStringInput("Enter dimensions: ");
        String format = io.getStringInput("Enter file format: ");
        return new Image(dimensions, format, name);
    }

    private static Book createBook(String name, TerminalUtils io) {
        String author = io.getStringInput("Enter author name: ");
        int pageCount = io.getIntInput("Enter number of pages: ");
        return new Book(author, pageCount, name);
    }
}