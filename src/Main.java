public class Main {
    public static void main(String[] args) {

        MusicPlayer mp = new MusicPlayer();

        System.out.println("--- 1. Build the playlist ---");
        System.out.println(mp.addSong("s1","Night Drive","Paul Jackson)"));
        System.out.println(mp.addSong("s2","Slow Tide","John Doe)"));
        System.out.println(mp.addSong("s3","Copper Sky","Chris Paul)"));
        System.out.println(mp.addSong("s4","Static Bloom","Jane Doe)"));
        System.out.println(mp.addSong("s5","Low Orbit","Jean Grey)"));
        System.out.println(mp.addSong("s6","Glass Rivers","Joe"));
        System.out.println("duplicate s3: " + mp.addSong("s3","Copper Sky","Chris Paul)"));
        System.out.println("playlist: " + mp.showPlaylist());

        System.out.println("--- 2. Look up songs ---");
        System.out.println("find s4: " + mp.findSong("s4").getTitle());
        System.out.println("find s9: " + mp.findSong("s9"));

        System.out.println("--- 3. Play and move forward ---");
        System.out.println("play s1: " + mp.play("s1").getTitle());
        System.out.println("play s9: " + mp.play("s9"));
        System.out.println("next: " + mp.next().getTitle());
        System.out.println("next: " + mp.next().getTitle());
        System.out.println("history: " + mp.showHistory());

        System.out.println("--- 4. Up next ---");
        System.out.println(mp.queueLater("s1"));
        System.out.println(mp.queueLater("s5"));
        System.out.println(mp.queueLater("s6"));
        System.out.println("queue s9: " + mp.queueLater("s9"));
        System.out.println("up next: " + mp.showUpNext());
        System.out.println("next: " + mp.next().getTitle());
        System.out.println("now playing: " + mp.nowPlaying().getTitle());
        System.out.println("history: " + mp.showHistory());

        System.out.println("--- 5. Back button ---");
        System.out.println("back: " + mp.back().getTitle());
        System.out.println("up next: " + mp.showUpNext());
        System.out.println("history: " + mp.showHistory());

        System.out.println("--- 6. Removed songs ---");
        System.out.println("remove: " + mp.removeSong("s5"));
        System.out.println("remove current(s3): " + mp.removeSong("s3"));
        System.out.println("remove: " + mp.removeSong("s9"));
        System.out.println("playlist: " + mp.showPlaylist());
        System.out.println("up next: " + mp.showUpNext());

        System.out.println("--- 7. Removed songs are skipped ---");
        System.out.println("next: " + mp.next().getTitle());
        System.out.println("next: " + mp.next().getTitle());
        System.out.println("next at end: " + mp.next());
        System.out.println("now playing: " + mp.nowPlaying().getTitle());
        System.out.println("back: " + mp.back().getTitle());
        System.out.println("up next: " + mp.showUpNext());

        System.out.println("--- 8. Play counts ---");
        System.out.println("s1: " + mp.playCount("s1"));
        System.out.println("s3: " + mp.playCount("s3"));
        System.out.println("s6: " + mp.playCount("s6"));
        System.out.println("s5 (removed): " + mp.playCount("s5"));

        System.out.println("--- 9. Empty player ---");
        MusicPlayer empty = new MusicPlayer();
        System.out.println("next: " + empty.next());
        System.out.println("back: " + empty.back());
        System.out.println("now playing: " + empty.nowPlaying());
        System.out.println("playlist: " + empty.showPlaylist());
    }
}