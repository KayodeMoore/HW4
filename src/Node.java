class Node {
    Song song;
    Node next;
    Node prev;

    Node(Song song){
        this.song = song;
        this.next = null;
        this.prev = null;
    }
}