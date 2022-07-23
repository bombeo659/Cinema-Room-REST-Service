package cinema.response;

import cinema.entities.Seat;

import java.util.UUID;

public class CinemaResponse {
    private UUID token;
    private Seat ticket;

    public CinemaResponse() {
    }

    public CinemaResponse(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
