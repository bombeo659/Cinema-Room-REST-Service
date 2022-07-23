package cinema.repositories;

import cinema.entities.Seat;
import cinema.entities.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CinemaRepository {
    private static final int TOTAL_ROWS = 9;
    private static final int TOTAL_COLUMNS = 9;
    private static final List<Seat> SEATS;

    @JsonIgnore
    private static final List<Ticket> TICKETS;

    @JsonIgnore
    private int income;

    public CinemaRepository() {
        this.income = 0;
    }

    static {
        SEATS = new ArrayList<>();
        for (int row = 1; row <= TOTAL_ROWS; row++) {
            for (int column = 1; column <= TOTAL_COLUMNS; column++) {
                SEATS.add(new Seat(row, column));
            }
        }
        TICKETS = new ArrayList<>();
    }
    public int getTotalRows() {
        return TOTAL_ROWS;
    }

    public int getTotalColumns() {
        return TOTAL_COLUMNS;
    }

    public List<Seat> getAllSeats() {
        return SEATS;
    }

    public List<Ticket> getAllTickets() {
        return TICKETS;
    }

    public int countAvailableSeats() {
        return SEATS.size();
    }

    public int countPurchasedTickets() {
        return TICKETS.size();
    }

    public int currentIncome(){
        return this.income;
    }

    public void calculateIncome(int price, boolean isPurchase) {
        if (isPurchase) {
            this.income += price;
        } else {
            this.income -= price;
        }
    }
}
