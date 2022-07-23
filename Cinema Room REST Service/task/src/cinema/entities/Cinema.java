package cinema.entities;

import java.util.List;

public class Cinema {
    private int total_rows;
    private int total_columns;
    private List<Seat> available_seats;

    public Cinema() {}
    public Cinema(int total_rows, int total_columns, List<Seat> availableSeats) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = availableSeats;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }
}
