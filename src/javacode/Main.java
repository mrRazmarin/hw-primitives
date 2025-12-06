package javacode;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int earning = 0;    // доходы
        int spending = 0;   // расходы

        while (true) {
            System.out.println("Выберите операцию и введите её номер:\n1. Добавить новый доход\n2. Добавить новый расход\n3. Выбрать систему налогообложения ");
            System.out.print("Ввод: ");
            String value = scanner.nextLine();
            if (value.equals("end"))
                break;
            int valueInt = Integer.parseInt(value);

            switch (valueInt) {
                case 1:
                    System.out.println("Введите сумму дохода:");
                    int moneyEarning = Integer.parseInt(scanner.nextLine());
                    earning += moneyEarning;
                    break;
                case 2:
                    System.out.println("Введите сумму расходов:");
                    int moneySpending = Integer.parseInt(scanner.nextLine());
                    spending += moneySpending;
                    break;
                case 3:
                    String choiceTax = hintTax(earning, spending);
                    System.out.println(choiceTax);
                    System.out.println();
                    break;
                default:
                    System.out.println("Не знаком с данной операцией. Попробуйте еще раз");
                    break;
            }
        }
        System.out.println("Программа завершена!");
    }

    private static String hintTax(int earning, int spending) {
        String valueTaxOne = "Мы советуем вам УСН доходы";
        String valueTaxTwo = "Мы советуем вам УСН доходы минус расходы";
        String valueEqualsBoth = "Можете выбрать любую систему налогообложения:\n- УСН доходы\n- УСН доходы минус расходы";

        int taxEarningResult = taxEarnings(earning);
        int taxEarningMinusSpendingResult = taxEarningsMinusSpending(earning, spending);

        int economy = Math.abs(taxEarningResult - taxEarningMinusSpendingResult);

        if (taxEarningResult < taxEarningMinusSpendingResult){
            return String.format("%s\nВаш налог составит: %d\nНалог на другой системе: %d\nЭкономия: %d",
                    valueTaxOne, taxEarningResult, taxEarningMinusSpendingResult, economy);
        } else if (taxEarningResult == taxEarningMinusSpendingResult) {
            return String.format("%s\nНалог по обоим системам составит: %d\n", valueEqualsBoth, taxEarningResult);
        } else {
            return String.format("%s\nВаш налог составит: %d\nНалог на другой системе: %d\nЭкономия: %d",
                    valueTaxTwo, taxEarningMinusSpendingResult, taxEarningResult, economy);
        }
    }

    public static int taxEarnings(int earning) {
        if(earning == 0){
            return 0;
        } else return earning * 6 / 100;
    }

    public static int taxEarningsMinusSpending(int earning, int spending) {
        int tax = (earning - spending) * 15 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }

}