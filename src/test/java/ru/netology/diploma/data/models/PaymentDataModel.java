package ru.netology.diploma.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDataModel {
    private String id;
    private String amount;
    private String created;
    private String status;
    private String transaction_id;
}
