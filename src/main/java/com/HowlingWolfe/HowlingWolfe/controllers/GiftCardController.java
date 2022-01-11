package com.HowlingWolfe.HowlingWolfe.controllers;

import com.HowlingWolfe.HowlingWolfe.models.GiftCard;
import com.HowlingWolfe.HowlingWolfe.models.GiftObj;
import com.HowlingWolfe.HowlingWolfe.services.GiftCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/giftcard")
public class GiftCardController {

    GiftCardService giftCardService;

    @Autowired
    public GiftCardController (GiftCardService giftCardService){
        this.giftCardService = giftCardService;
    }

    @GetMapping(path = "{cardNumber}")
    public ResponseEntity<GiftCard> getGiftCardByCardNumber(@PathVariable int cardNumber){
        GiftCard response = this.giftCardService.getGiftCardByCardNumber(cardNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<GiftCard>> getAllGiftCards(){
        List<GiftCard> response = giftCardService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/post")
    public ResponseEntity<String> postGiftCard(@RequestBody GiftObj giftObj){
        String response = giftCardService.postGiftCard(giftObj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateGiftCard(@RequestBody GiftCard giftCard){
        String response = giftCardService.updateBalance(giftCard);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{cardNumber}")
    public ResponseEntity<String> deleteById(@PathVariable int cardNumber){
        String response = giftCardService.deleteByCardNumber(cardNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
