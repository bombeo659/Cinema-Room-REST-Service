package cinema.response;

import cinema.entities.Seat;

public class CinemaResponseReturn {
    private Seat returned_ticket;

    public CinemaResponseReturn(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public Seat getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
