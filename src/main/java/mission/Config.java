package mission;

import mission.adapter.inAdapter.InputTerminal;
import mission.adapter.inAdapter.LectureLoader;
import mission.adapter.outAdapter.OutputTerminal;
import mission.application.port.inport.Input;
import mission.application.port.inport.LectureDatabase;
import mission.application.port.outport.Logger;

public class Config {
    public Logger getLogger() {
        return new OutputTerminal();
    }

    public Input getInput() {
        return new InputTerminal();
    }

    public LectureDatabase getLectureDatabase() {
        return LectureLoader.getInstance();
    }
}
