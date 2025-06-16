public class Image extends Media {
    private String dimensions;
    private String fileFormat;

    public Image(String dimensions, String fileFormat, String name) {
        this.dimensions = dimensions;
        this.fileFormat = fileFormat;
        this.name = name;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    private void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    private void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    //add abstract methodds from media with override

    //Image: "Displaying image '[name]' using image viewer software"
    @Override
    public void play(){
        System.out.println("Displaying image " + name + " using image viewer software.");
    };


    @Override
    public String getDescription(){
        return "Image: "+ name + "\nDimensions: " + dimensions +"\nFile Format: " + fileFormat;
    };
}


