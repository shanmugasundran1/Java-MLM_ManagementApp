
package sst.execute;

import sst.exception.OperationHaltException;

interface Command {
    void execute() throws OperationHaltException;
}