package mission.adapter.inAdapter;

import api.Console;
import mission.application.port.inport.Input;

public class InputTerminal implements Input {

    @Override
    public int getInt() {
        return Integer.parseInt(Console.readLine().trim());
    }

    @Override
    public String getString() {
        return Console.readLine();
    }
}
