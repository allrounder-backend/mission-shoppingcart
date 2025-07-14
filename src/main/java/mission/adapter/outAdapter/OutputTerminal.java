package mission.adapter.outAdapter;

import mission.application.domain.enums.MessageConstant;
import mission.application.port.outport.Logger;

public class OutputTerminal implements Logger {
    @Override
    public void print(MessageConstant message, Object... args) {
        System.out.printf(message.getMessage() + "%n", args);
    }
}
