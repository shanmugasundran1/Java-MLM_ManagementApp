
package sst.execute;

import sst.exception.OperationHaltException;
import sst.util.Methods;

class Quit implements Command {
    @Override
    public void execute() throws OperationHaltException {
        System.out.println("logging out.See you again.");
    }
}