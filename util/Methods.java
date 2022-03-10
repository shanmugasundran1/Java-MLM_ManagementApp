package sst.util;

import sst.Operations;
import sst.exception.OperationHaltException;
import sst.model.Customer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Methods {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readNotEmptyString() {
        String s = "";
        try {
            s = reader.readLine();
            while (s.isEmpty()) {
                System.out.println("Invalid input. Try again");
                s = readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String readLine() {
        String s = "";
        try {
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static Operations operationseek() throws OperationHaltException {
        System.out.println("\nChoose operation:");
        String operation = readLine();

        while (!operation.matches("\\d+") || Integer.parseInt(operation) < 1
                || Integer.parseInt(operation) > Operations.values().length + 1) {
            System.out.println("Invalid input. Try again");
            operation = readLine();
        }

        return Operations.returnOperation(Integer.parseInt(operation));
    }

    public static String askNumber(int from, int to) throws OperationHaltException {
        String number = readLine();

        while (!number.matches("\\d")
                || Integer.parseInt(number) < from || Integer.parseInt(number) > to) {
            System.out.println("Invalid input. Try again");
            number = readLine();
        }

        return number;
    }

    public static String askNumber() throws OperationHaltException {
        String number = readLine();

        while (!number.matches("\\d")) {
            System.out.println("Invalid input. Try again");
            number = readLine();
        }

        return number;
    }

    public static void recursivePrint(Customer user, int generation) {
        for (int i = 0; i < generation; i++) {
            System.out.print("\t");
        }
        System.out.println("user[id:" + user.getId() + "]");
        if (user.getDirectDownlines().isEmpty()) {
            return;
        }
        int downGeneration = generation + 1;
        user.getDirectDownlines().forEach(u -> recursivePrint(u, downGeneration));
    }

    public static String askDouble() throws OperationHaltException {
        String number = readLine();

        while (!number.matches("^(?!00)\\d\\d?(\\.\\d\\d?)?$")) {
            System.out.println("Invalid input. Try again");
            number = readLine();
        }

        return number;
    }

}
