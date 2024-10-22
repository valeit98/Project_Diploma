package ru.netology.diploma.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditDataModel {
    private String id;
    private String bank_id;
    private String created;
    private String status;
}
