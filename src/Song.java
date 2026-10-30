public class Song {
    Object title;
    Object artist;

    Song(Object title, Object artist){
        this.title = title;
        this.artist = artist;
    }
    public Object getTitle(){
        return title;
    }
}