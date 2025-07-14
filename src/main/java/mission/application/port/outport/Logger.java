package mission.application.port.outport;

import mission.application.domain.enums.MessageConstant;

public interface Logger {
    void print(MessageConstant message, Object... args);
}
