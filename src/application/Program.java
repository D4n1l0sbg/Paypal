package application;

import models.entities.Contract;
import models.entities.Installment;
import models.services.ContractService;
import models.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre com os dados do contrato: ");
        System.out.print("Número: ");
        Integer number = sc.nextInt();
        System.out.print("Data (dd/mm/yyyy): ");
        LocalDate localDate = LocalDate.parse(sc.next(), fmt);
        System.out.print("Valor do Contrato: ");
        Double totalValue = sc.nextDouble();


        Contract contract = new Contract(number, localDate, totalValue);

        System.out.print("Entre com o numero de parcela: ");
        Integer n = sc.nextInt();

        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract, n);
        System.out.println("Parcelas: ");
        for (Installment installment: contract.getInstallments()
             ) {
            System.out.println(installment);
        }


        sc.close();
    }
}