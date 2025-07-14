package mission.model;

public class Lecture {
    private final int lectureID;
    private final String lectureName;
    private final String lectureType;
    private int lecturePrice;

    public Lecture(int lectureID, String lectureName, String lectureType, int lecturePrice) {
        this.lectureID = lectureID;
        this.lectureName = lectureName;
        this.lectureType = lectureType;
        this.lecturePrice = lecturePrice;
    }

    public int getLectureID() {
        return lectureID;
    }

    public int getLecturePrice() {
        return lecturePrice;
    }
}

