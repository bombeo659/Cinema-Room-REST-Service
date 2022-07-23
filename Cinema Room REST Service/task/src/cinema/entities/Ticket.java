package cinema.entities;

import java.util.UUID;

public class Ticket {
    private UUID token;
    private Seat ticket;

    public Ticket(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
