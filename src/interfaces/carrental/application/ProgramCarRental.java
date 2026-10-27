package interfaces.carrental.application;

import interfaces.carrental.model.entities.CarRental;
import interfaces.carrental.model.entities.Vehicle;
import interfaces.carrental.model.services.BrazilTaxService;
import interfaces.carrental.model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class ProgramCarRental {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Enter the rent data");
        System.out.print("Car model: ");
        String carModel = sc.nextLine();
        System.out.print("Start (dd/MM/yyyy HH:mm): ");
        LocalDateTime start =  LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("Finish (dd/MM/yyyy HH:mm): ");
        LocalDateTime finish =  LocalDateTime.parse(sc.nextLine(), dtf);
        CarRental cr01 = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Enter the price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Enter the price per day: ");
        double pricePerDay = sc.nextDouble();
        RentalService rentalService01 = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

        rentalService01.processInvoice(cr01);

        System.out.println();
        System.out.println("===== INVOICE =====");
        System.out.println("Basic payment: " + String.format("$ %.2f", cr01.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("$ %.2f", cr01.getInvoice().getTax()));
        System.out.println("Total payment: " + String.format("$ %.2f", cr01.getInvoice().getTotalPayment()));

        sc.close();
    }
}
