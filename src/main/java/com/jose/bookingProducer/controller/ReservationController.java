package com.jose.bookingProducer.controller;

import com.jose.bookingProducer.dto.ReservationDTO;
import com.jose.bookingProducer.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reserve")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> reserveCampsite(@RequestBody ReservationDTO reservationDTO){
       return ResponseEntity.ok(reservationService.reserveCampsite(reservationDTO));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<ReservationDTO> modifyReserve(@RequestBody ReservationDTO reservationDTO){
        return ResponseEntity.ok(reservationService.modifyReserve(reservationDTO));
    }

}
