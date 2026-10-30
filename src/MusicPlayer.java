import java.util.*;
public class MusicPlayer {
    HashMap<String, Node> library = new HashMap<>();
    Deque<Node> upNext = new ArrayDeque<>();
    Deque<Node> history = new ArrayDeque<>();
    HashMap<String, Integer> playCount = new HashMap<>();

    Node head = null;
    Node tail = null;
    Node current = null;

    public boolean addSong(String id, String title, String artist){
        if(library.get(id) != null){
            return false;
        }
        Song song = new Song(title,artist);
        Node node = new Node(song);
        library.put(id,node);
        if (head==null){
            head = node;
            tail = node;
            current = node;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        return true;
    }

    public ArrayList<String> showPlaylist(){
        ArrayList<String> songs = new ArrayList<>();
        Node temp = head;
        if (temp == null){
            return songs;
        }
        while(temp != null) {
            songs.add(temp.song.getTitle().toString());
            temp = temp.next;
        }
        return songs;
    }
    public Song findSong(String id){
        if(library.get(id) != null){
            Node node = library.get(id);
            return node.song;
        }
        return null;
    }
    public Song play(String id){
        if(library.get(id) != null){
            Node node = library.get(id);
            if (current != null && current != node) {
                history.push(current);
            }
            current = node;

            if(playCount.get(id) != null){
                int count = playCount.get(id);
                playCount.replace(id, count + 1);
            }else{
                playCount.put(id, 1);
            }
            return current.song;
        }
        return null;
    }
    public Song nowPlaying(){
        if(current != null){
            return current.song;
        }
        return null;
    }
    public int playCount(String id){
        if(library.get(id) == null){
            return 0;
        }
        if(playCount.get(id) != null){
            return playCount.get(id);
        }
        return 0;
    }
    public boolean queueNext(String id){
        Node node = library.get(id);
        if(library.containsValue(node)){
            upNext.addFirst(node);
            return true;
        }
        return false;
    }
    public boolean queueLater(String id){
        Node node = library.get(id);
        if(library.containsValue(node)){
            upNext.addLast(node);
            return true;
        }
        return false;
    }
    public ArrayList<String> showUpNext(){
        ArrayList<String> queuedSongs = new ArrayList<>();
        for (Node node : upNext){
            queuedSongs.add(node.song.getTitle().toString());
        }
        return queuedSongs;
    }
    public Song next(){
        Node nextNode = null;

        while (!upNext.isEmpty()) {
            Node queuedNode = upNext.removeFirst();
            if (library.containsValue(queuedNode)) {
                nextNode = queuedNode;
                break;
            }
        }
        if (nextNode == null && current != null) {
            nextNode = current.next;
            while (nextNode != null && !library.containsValue(nextNode)) {
                nextNode = nextNode.next;
            }
        }
        if(nextNode == null) {
            return null;
        }
        if (current != null && current != nextNode) {
            history.push(current);
        }
        current = nextNode;
        for(String id : library.keySet()){
            if (library.get(id) == current){
                if(playCount.containsKey(id)){

                    int count = playCount.get(id);
                    playCount.replace(id, count + 1);

                }else{
                    playCount.put(id, 1);
                }
            }
        }
        return current.song;
    }
    public Song back(){
        if(!history.isEmpty()) {
            if (current != null) {
                upNext.addFirst(current);
            }
            current = history.pop();

            for(String id : library.keySet()){
                if (library.get(id) == current){
                    if(playCount.containsKey(id)){

                        int count = playCount.get(id);
                        playCount.replace(id, count + 1);

                    }else{
                        playCount.put(id, 1);
                    }
                }
            }

            return current.song;
        }
        return null;
    }
    public LinkedList<String> showHistory(){
        LinkedList<String> historyList = new LinkedList<>();
        for (Node node : history){
            historyList.offerFirst(node.song.getTitle().toString());
        }
        return historyList;
    }
    public boolean removeSong(String id){
        if(library.get(id) == null){
            return false;
        }
        Node remove = library.get(id);
        if (current == remove) {
            return false;
        }
        library.remove(id);
        upNext.remove(remove);
        history.remove(remove);
        if(remove.prev != null){
            remove.prev.next = remove.next;
        }else{
            head = remove.next;
        }
        if(remove.next != null){
            remove.next.prev = remove.prev;
        }else{
            tail = remove.prev;
        }
        return true;
    }
}
