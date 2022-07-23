package cinema.services;

import cinema.entities.Cinema;
import cinema.entities.Seat;
import cinema.entities.Ticket;
import cinema.repositories.CinemaRepository;
import cinema.request.CinemaRequest;
import cinema.request.CinemaRequestReturn;
import cinema.response.CinemaResponse;
import cinema.response.CinemaResponseReturn;
import cinema.response.CinemaResponseStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    public Cinema getCinemaInfo() {
        return new Cinema(cinemaRepository.getTotalRows(), cinemaRepository.getTotalColumns(), cinemaRepository.getAllSeats());
    }

    public CinemaResponse addPurchase(CinemaRequest cinemaRequest) throws Exception {
        if (cinemaRequest.getColumn() < 1 ||
            cinemaRequest.getRow() < 1 ||
            cinemaRequest.getRow() > cinemaRepository.getTotalRows() ||
            cinemaRequest.getColumn() > cinemaRepository.getTotalColumns()) {
            throw new Exception("wrong number");
        }

        for (int i = 0; i < cinemaRepository.getAllSeats().size(); i++) {
            Seat s = cinemaRepository.getAllSeats().get(i);
            if (s.getColumn() == cinemaRequest.getColumn() && s.getRow() == cinemaRequest.getRow()) {
                Ticket ticket = new Ticket(UUID.randomUUID(), s);
                cinemaRepository.getAllSeats().remove(i);
                cinemaRepository.getAllTickets().add(ticket);
                cinemaRepository.calculateIncome(s.getPrice(), true);
                return new CinemaResponse(ticket.getToken(), ticket.getTicket());
            }
        }

        throw new Exception("exist");
    }

    public CinemaResponseReturn returnTicket(CinemaRequestReturn cinemaRequestReturn) throws Exception {
        for (Ticket i : cinemaRepository.getAllTickets()) {
            if (i.getToken().equals(cinemaRequestReturn.getToken())) {
                cinemaRepository.calculateIncome(i.getTicket().getPrice(), false);
                cinemaRepository.getAllTickets().remove(i);
                cinemaRepository.getAllSeats().add(i.getTicket());
                return new CinemaResponseReturn(i.getTicket());
            }
        }

        throw new Exception("error");
    }

    public CinemaResponseStats check(String password) throws Exception {
        if (password != null && password.equals("super_secret")) {
           return new CinemaResponseStats(cinemaRepository.currentIncome(),
                   cinemaRepository.countAvailableSeats(),
                   cinemaRepository.countPurchasedTickets());
        }

        throw new Exception("wrong");
    }
}