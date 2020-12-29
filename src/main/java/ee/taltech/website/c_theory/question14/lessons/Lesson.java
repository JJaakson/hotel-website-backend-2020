package ee.taltech.website.c_theory.question14.lessons;

import java.time.Duration;
import java.util.List;

public class Lesson {

    private String name;
    private Long id;
    private Long course;
    private Duration duration;
    private String description;
    private List<String> materials;
    //    ... many more
    private List<Students> students;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Students> getStudents() {
        return students;
    }

    public Long getCourse() {
        return course;
    }

    public Duration getDuration() {
        return duration;
    }
}
