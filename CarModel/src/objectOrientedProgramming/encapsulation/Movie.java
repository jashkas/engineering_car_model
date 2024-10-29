package objectOrientedProgramming.encapsulation;

import java.util.Date;
//
// Инкапсуляция
//
public class Movie {
    private String name;
    private int releaseYear;
    private Date watchDate;

    public Movie() {
        this.name = "";
        this.releaseYear = 0;
        this.watchDate = null;
    }

    public Movie(String name, int releaseYear, Date watchDate) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.watchDate = watchDate;
    }

    public String getName() {
        return name;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public Date getWatchDate() {
        return watchDate;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
    }

    @Override
    public String toString() {
        String result = "Название:" + this.name +
                "\nГод выпуска:" + this.releaseYear +
                "\nДата просмотра:" + this.watchDate + "\n";
        return result;
    }

    public static void main(String[] args) {
        Movie movie = new Movie("Плохие парни навсегда", 2020, new Date());
        System.out.println(movie.toString());

        Movie movie2 = new Movie();
        movie2.setName("Босс-молокосос");
        movie2.setReleaseYear(2017);
        movie2.setWatchDate(new Date());
        System.out.println(movie2.toString());

        System.out.println(movie2.getName());

    }
}