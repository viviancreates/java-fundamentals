import java.util.ArrayList;

public class MediaService {
    private ArrayList<Media> mediaList;

    public MediaService(){
        mediaList = new ArrayList<Media>();
    }

    //addMedia(Media media) - adds media to the list
    public void addMedia(Media media){
        mediaList.add(media);
    }

    //removeMedia(String name) - removes media by name, returns boolean success
    public boolean removeMedia(String name) {
        //loop through each media item in the list using the index
        for (int index = 0; index<mediaList.size(); index++){
           //get the media item at index i and stroe it variable "media"
           Media media = mediaList.get(index);
           //comparison
           if (media.getName().equalsIgnoreCase(name)){
               mediaList.remove(index);
               return true;
           }

        }
        return false;
    }

    //findMediaByName(String name) - finds and returns media by name
    public Media findMediaByName(String name){
        for (int index = 0; index<mediaList.size(); index++){
            //get the media item at index i and stroe it variable "media"
            Media media = mediaList.get(index);
            //comparison
            if (media.getName().equalsIgnoreCase(name)){
                return media;
            }

        }
        return null;
    }

    //getAllMedia() - returns copy of media list
    public ArrayList<Media> getList(){
        return new ArrayList<>(mediaList);
    }

    //getMediaCount() - returns number of media items

    public int getMediaCount(){
        return mediaList.size();
    }

    //isEmpty() - checks if list is empty

    public boolean isEmpty(){
        return mediaList.isEmpty();
    }

}
