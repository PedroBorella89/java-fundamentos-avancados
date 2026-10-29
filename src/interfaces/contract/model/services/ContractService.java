package interfaces.contract.model.services;

import interfaces.contract.model.entities.Contract;
import interfaces.contract.model.entities.Installment;

import java.time.LocalDate;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, int months) {

        double basicValue = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {

            LocalDate dueDate = contract.getDate().plusMonths(i);
            double interest = onlinePaymentService.interest(basicValue, i);
            double paymentFee = onlinePaymentService.paymentFee(basicValue +  interest);
            double quota = basicValue + interest + paymentFee;

            contract.getInstallments().add(new Installment(dueDate, quota));

        }

    }

}
