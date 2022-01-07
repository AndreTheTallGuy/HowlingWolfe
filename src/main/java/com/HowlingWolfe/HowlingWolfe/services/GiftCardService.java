package com.HowlingWolfe.HowlingWolfe.services;

import com.HowlingWolfe.HowlingWolfe.email.SendEmail;
import com.HowlingWolfe.HowlingWolfe.models.GiftCard;
import com.HowlingWolfe.HowlingWolfe.models.GiftObj;
import com.HowlingWolfe.HowlingWolfe.repositories.GiftCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftCardService {

    private GiftCardRepo giftCardRepo;

    @Autowired
    public GiftCardService(GiftCardRepo giftCardRepo){
        this.giftCardRepo = giftCardRepo;
    }

    public List<GiftCard> getGiftCards(){return giftCardRepo.findAll();}

    public GiftCard getGiftCardByCardNumber(int cardNumber){
        return giftCardRepo.findByCardNumber(cardNumber);
    }

    public List<GiftCard> findAll(){return giftCardRepo.findAll();}

    public String postGiftCard(GiftObj giftObj){
        SendEmail.sendGiftCard("recipient", giftObj);
        SendEmail.sendGiftCard("sender", giftObj);
        SendEmail.sendGiftCard("Jake", giftObj);

        GiftCard giftCard = giftObj.getGiftCard();
        try {
            giftCardRepo.save(giftCard);
            return "Success";
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String deleteById(int id){
        try {
            giftCardRepo.deleteById(id);
            return "Success";
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "Failure";
        }
    }

    public String updateBalance(GiftCard giftCard){

//        send email with new balance!!!!!!!!!!!!


        try {
            int cardNumber = giftCard.getCardNumber();
            int newBalance = giftCard.getBalance();
            GiftCard gc = giftCardRepo.findByCardNumber(cardNumber);
            gc.setBalance(newBalance);
            giftCardRepo.save(gc);
            return "Success";
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

}
