package Rozdzial_22.Zad22_39;

import java.util.*;
public class SongService {
    private List<Song> songs = new ArrayList<Song>();

    public SongService() {
        fillTestData();
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    private void fillTestData() {
        addSong(new Song("Pipe", "Metal",
                "Clang", "Random-11.jpg",
                "Pipe.mp3"));
        addSong(new Song("Fail", "Spongebob",
                "Spongebob the Squarepants", "Random-16.jpg",
                "Spongebob.mp3"));
        addSong(new Song("Random", "WhoKnows",
                "Randomized",
                "Random-21.jpg", "Random.mp3"));
    }
}