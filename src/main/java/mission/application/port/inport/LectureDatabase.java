package mission.application.port.inport;

import mission.application.domain.Lecture;

public interface LectureDatabase {
    Lecture findLecture(int id);
}
