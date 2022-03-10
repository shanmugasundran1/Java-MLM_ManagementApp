package sst;
import sst.execute.Action;
import sst.exception.UnsupportedRootUserDeletion;
import sst.exception.OperationHaltException;
import sst.exception.NoUserExistsException;
import sst.util.Methods;
import java.util.Locale;
import java.util.stream.Stream;

public class SSTCORP {
    
    public static void main(String[] args) {
        
        System.out.println("\t\t\t   ******  ******  ******");
        System.out.println("\t\t\t   *       *          *");
        System.out.println("\t\t\t   *       *          *");
        System.out.println("\t\t\t   ******  ******     *");
        System.out.println("\t\t\t        *       *     *");
        System.out.println("\t\t\t        *       *     *");
        System.out.println("\t\t\t   ******  ******     *");
        System.out.println("\t\t      WELCOME TO DREAMCORP MANAGED BY SST");
        try {
            Operations operation;
            do {

                System.out.println("\n\nPlease input the choice for your operation listed below. ");
                System.out.println("MENU: ");
                Stream.of(Operations.values()).forEach(operations -> {
                    System.out.println(String.join(")", String.valueOf(operations.ordinal() + 1), operations.getName()));
                });
                operation = Methods.operationseek();
                try {
                    Action.execute(operation);
                } catch (NoUserExistsException | UnsupportedRootUserDeletion e) {
                    System.out.println(e.getMessage());
                }
            } while (operation != Operations.QUIT);
        } catch (OperationHaltException e) {
            try {
                System.out.println("Logging out. See you again!");
            } catch (OperationHaltException ignored) {
            }
        }
    }

}
