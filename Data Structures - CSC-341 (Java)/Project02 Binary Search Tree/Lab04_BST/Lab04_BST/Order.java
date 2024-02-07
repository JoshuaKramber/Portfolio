import java.util.*;

public class Order {

    public static class byArtist implements Comparator<Track> {

        @Override
        public int compare(Track track1, Track track2) {
           return track1.artist().compareTo(track2.artist());
        }
    }

    public static class byTitle implements Comparator<Track> {

        @Override
        public int compare(Track track1, Track track2) {
            return track1.title().compareTo(track2.title());
        }
    }

    public static class byPopularity implements Comparator<Track> {

        @Override
        public int compare(Track track1, Track track2) {
            if(track1.popularity().compareTo(track2.popularity())==0){
                return track1.title().compareTo(track2.title());
            }
            return track1.popularity().compareTo(track2.popularity());
        }
    }
}