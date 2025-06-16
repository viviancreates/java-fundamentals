public abstract class Media {
    protected String name;

    // Regular method with implementation
    public String getName() {
        return name;
    }

    private void String setName(String name){
        this.name = name;
    }

    // Abstract methods - no implementation, must be overridden
    public abstract void play();
    public abstract String getDescription();
}
