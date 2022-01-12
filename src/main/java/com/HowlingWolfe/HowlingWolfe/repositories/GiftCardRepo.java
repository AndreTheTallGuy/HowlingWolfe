package com.HowlingWolfe.HowlingWolfe.repositories;

import com.HowlingWolfe.HowlingWolfe.models.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GiftCardRepo extends JpaRepository<GiftCard, Integer> {

    GiftCard findByCardNumber(int cardNumber);

    @Transactional
    void deleteByCardNumber(int cardNumber);

}
