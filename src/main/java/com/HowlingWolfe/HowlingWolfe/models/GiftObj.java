package com.HowlingWolfe.HowlingWolfe.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftObj {

    private GiftCard giftCard;
    private String fromName;
    private String fromEmail;
    private String message;

}
