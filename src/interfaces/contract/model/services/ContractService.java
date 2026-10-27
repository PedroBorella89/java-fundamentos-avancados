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
            double amountWithInterest = basicValue + interest;
            double paymentFee = onlinePaymentService.paymentFee(amountWithInterest);
            double finalAmount = amountWithInterest + paymentFee;
            Installment installment = new Installment(dueDate, finalAmount);
            contract.getInstallments().add(installment);

        }

    }

}
