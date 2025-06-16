public class Image {
    private String dimensions;
    private String fileFormat;

    public Image(String dimensions, String fileFormat, String name) {
        this.dimensions = dimensions;
        this.fileFormat = fileFormat;
        this.name = name;
    }

    public int getDimensions() {
        return dimensions;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    private void String setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    private void String setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }
}
