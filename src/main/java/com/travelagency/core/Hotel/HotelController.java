package com.ditraacademy.travelagency.core.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class HotelController {
    @Autowired
    HotelServices hotelServices;

    @PostMapping("/hotel")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel){
        return hotelServices.createHotel(hotel);
    }

    @GetMapping("/hotel")
     public List<Hotel> getHotel(){
         return hotelServices.getHotel();
     }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<?> getOneHotel(@PathVariable int id){
        return hotelServices.getOneHotel(id);
    }

    @PutMapping("/hotel/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable int id,@RequestBody Hotel updateHotel) {
        return hotelServices.updateHotel(id,updateHotel);
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable int id ){
        return hotelServices.deleteHotel(id);
    }


}
