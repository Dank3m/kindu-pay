package com.kinduberre.kindupay.mappers;

import com.kinduberre.kindupay.models.auth.User;
import com.kinduberre.kindupay.models.core.Transactions;
import com.kinduberre.kindupay.models.dtos.familybank.RegistrationResponseDTO;
import com.kinduberre.kindupay.models.familybank.ConfirmationRequestPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "tranAmount", source = "payload.txnAmount")
    @Mapping(target = "bankAcct", source = "payload.collectionAccount")
    @Mapping(target = "narration",source = "payload.txnNarration")
    @Mapping(target = "bankTranRef",source = "payload.txnReference")
    @Mapping(target = "tranId", ignore = true)
    @Mapping(target = "tranType", ignore = true)
    @Mapping(target = "tranDate", ignore = true)
    @Mapping(target = "shortCode", ignore = true)
    Transactions confirmationRequestToTransaction(ConfirmationRequestPayload payload);
}
