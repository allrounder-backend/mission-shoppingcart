package mission.application.domain.enums;

import java.util.Map;
import mission.application.domain.Lecture;
import mission.application.domain.lecture.CSLecture;
import mission.application.domain.lecture.DBMSLecture;
import mission.application.domain.lecture.DevOpsLecture;
import mission.application.domain.lecture.FWLecture;
import mission.application.domain.lecture.LangLecture;

public enum LectureType {
    DevOps("DevOps", new DevOpsLecture()),
    DBMS("DBMS", new DBMSLecture()),
    Lang("Lang", new LangLecture()),
    FW("F/W", new FWLecture()),
    CS("CS", new CSLecture());

    private static final Map<String, LectureType> namingMap =
            Map.of(DevOps.name, DevOps, DBMS.name, DBMS, Lang.name, Lang, FW.name, FW, CS.name, CS);
    private final String name;
    private final Lecture lecture;

    LectureType(String name, Lecture lecture) {
        this.name = name;
        this.lecture = lecture;
    }

    public static LectureType getLectureType(String lectureType) {
        return namingMap.get(lectureType.trim());
    }

    public String getName() {
        return name;
    }

    public Lecture getLecture() {
        return lecture;
    }
}
