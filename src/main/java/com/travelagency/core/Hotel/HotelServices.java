package com.ditraacademy.travelagency.core.Hotel;


import com.ditraacademy.travelagency.core.Chambre.Chambre.Chambre;
import com.ditraacademy.travelagency.core.Chambre.Chambre.ChambreRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServices {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ChambreRepository chambreRepository;


    public ResponseEntity<?> createHotel(Hotel hotel) {
        for(Chambre chambre : hotel.getChambres()){
            Optional<Chambre> chambreOptional = chambreRepository.findById((chambre.getId()));
            if(!chambreOptional.isPresent()){
                return new ResponseEntity<>(new ErrorResponseModel("chambre not found "+ chambre.getId()),HttpStatus.OK);
            }

        }
        hotel = hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }


    public ResponseEntity<?> getOneHotel(int id){

        Optional<Hotel> hotelOptional = hotelRepository.findById(id);

        if (!hotelOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("hotel not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        Hotel hotel = hotelOptional.get();
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    public List<Hotel> getHotel(){

        return hotelRepository.findAll();
    }


    public ResponseEntity<?> deleteHotel( int id ){

        Optional<Hotel> hotelOptional = hotelRepository.findById(id);

        if (!hotelOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("hotel not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        hotelRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updateHotel(int id,Hotel updateHotel) {

        Optional<Hotel> hotelOptional = hotelRepository.findById(id);


        if (!hotelOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("hotel not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        hotelRepository.save(updateHotel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
