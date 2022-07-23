package cinema.controllers;

import cinema.entities.Cinema;
import cinema.request.CinemaRequest;
import cinema.request.CinemaRequestReturn;
import cinema.response.CinemaResponse;
import cinema.response.CinemaResponseReturn;
import cinema.response.CinemaResponseStats;
import cinema.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/seats")
    public Cinema getCinemaInfo() {
        return cinemaService.getCinemaInfo();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> addPurchase(@RequestBody CinemaRequest cinemaRequest){
        CinemaResponse cinemaResponse = null;
        try {
             cinemaResponse =  cinemaService.addPurchase(cinemaRequest);
        } catch (Exception e) {
            if (e.getMessage().equals("exist")) {
                return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
            } else if (e.getMessage().equals("wrong number")) {
                return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
            }
        }
        if (cinemaResponse == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cinemaResponse, HttpStatus.OK);
    }

    @PostMapping("/return")
    public  ResponseEntity<?> returnTicket(@RequestBody CinemaRequestReturn cinemaRequestReturn) {
        CinemaResponseReturn cinemaResponseReturn = null;
        try {
            cinemaResponseReturn = cinemaService.returnTicket(cinemaRequestReturn);
        } catch (Exception e) {
            if (e.getMessage().equals("error")) {
                return new ResponseEntity<>(Map.of("error",  "Wrong token!"), HttpStatus.BAD_REQUEST);
            }
        }
        if (cinemaResponseReturn == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cinemaResponseReturn, HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> check(@RequestParam (required = false) String password) {
        CinemaResponseStats cinemaResponseStats = null;
        try {
            cinemaResponseStats = cinemaService.check(password);
        } catch (Exception e) {
            if (e.getMessage().equals("wrong")) {
                return new ResponseEntity<>(Map.of("error",  "The password is wrong!"), HttpStatus.UNAUTHORIZED);
            }
        }
        if (cinemaResponseStats == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cinemaResponseStats, HttpStatus.OK);
    }
}
