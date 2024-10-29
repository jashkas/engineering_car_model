package objectOrientedProgramming.Inheritance;

import objectOrientedProgramming.encapsulation.Movie;

import java.util.Date;
//
// Наследование
//
public class Series extends Movie {
    private int season;
    private int episode;

    public Series(String name, int releaseYear, Date watchDate, int season, int episode) {
        super(name, releaseYear, watchDate);
        this.season = season;
        this.episode = episode;
    }

    public Series() {
        this.season = 0;
        this.episode = 0;
    }

    public int getSeason() {
        return season;
    }
    public int getEpisode() {
        return episode;
    }

    public void setSeason(int season) {
        this.season = season;
    }
    public void setEpisode(int episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        return "Название: " + getName() +
                "\nГод выпуска: " + getReleaseYear() +
                "\nДата просмотра: " + getWatchDate() +
                "\nСезон: " + getSeason() +
                "\nЭпизод: " + getEpisode();
    }

    public static void main(String[] args) {
        Series series = new Series("Игра престолов", 2011, new Date(), 1, 1);
        System.out.println(series.toString());
    }
}
