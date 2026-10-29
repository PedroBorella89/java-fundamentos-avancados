package interfaces.contract.application;

import interfaces.contract.model.entities.Contract;
import interfaces.contract.model.entities.Installment;
import interfaces.contract.model.services.ContractService;
import interfaces.contract.model.services.PayPalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter the contract details");
        System.out.print("Contract Number: ");
        int number = sc.nextInt();
        sc.nextLine();
        System.out.print("Contract Date (DD/MM/YYYY): ");
        LocalDate date = LocalDate.parse(sc.nextLine(), dtf);
        System.out.print("Contract Amount: ");
        double amount = sc.nextDouble();
        System.out.print("Number of Installments: ");
        int numberOfInstallments = sc.nextInt();
        Contract contract01 = new Contract(number, date, amount);

        ContractService contractService01 = new ContractService(new PayPalService());

        contractService01.processContract(contract01, numberOfInstallments);

        System.out.println();
        System.out.println("===== INVOICES =====");
        for (Installment installment : contract01.getInstallments()) {
            System.out.println(installment);
        }

        sc.close();

    }
}
