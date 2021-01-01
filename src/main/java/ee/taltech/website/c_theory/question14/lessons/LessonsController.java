package ee.taltech.website.c_theory.question14.lessons;

import ee.taltech.website.model.Booking;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("lessons")
@RestController
public class LessonsController {

    // Mimics service
    //@Autowired
    private List<Lesson> lessonsService = new ArrayList<>();

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for lessons. Think page where you are looking at lessons like echo360.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class

    //todo B create a method to query lessons (plural)
    @GetMapping
    public List<Lesson> getLessons(@RequestParam(value = "true", required = false) boolean byCourse,
                                   @RequestParam(value = "year", defaultValue = "2020") String year,
                                   @RequestParam(value = "order", defaultValue = "original") String order) {
        //code
        return lessonsService;
    }

    //todo C create a method to query single lesson
    @GetMapping("{id}")
    public Lesson getLesson(@PathVariable Long id) {
        return findLesson(id);
    }

    //todo D create a method to save a lesson
    @PostMapping
    public boolean saveLesson(@RequestBody Lesson lesson) {
        return lessonsService.add(lesson);
    }

    //todo E create a method to update a lesson
    @PutMapping("{id}")
    public Lesson updateLesson(@RequestBody Lesson lessonDto, @PathVariable Long id) {
        Lesson dbLesson = findLesson(id);
        if (dbLesson != null) {
            lessonsService.remove(dbLesson);
            // should replace all/neccesary variables
            dbLesson = lessonDto;
            lessonsService.add(dbLesson);
            return dbLesson;
        }
        return null;
    }

    //todo F create a method to delete a lesson
    @DeleteMapping("{id}")
    public void deleteLesson(@PathVariable Long id) {
        Lesson dbLesson = findLesson(id);
        if (dbLesson != null) {
            lessonsService.remove(dbLesson);
        }
    }

    //todo G assuming each Lesson has students (one-to-many relation) create a method to query lesson's students
    @GetMapping("students/{id}")
    public List<Students> getStudents(@PathVariable Long id) {
        Lesson dbLesson = findLesson(id);
        if (dbLesson != null) {
            return dbLesson.getStudents();
        }
        return new ArrayList<>();
    }

    //todo H create a method to update lesson's name (and nothing else)
    @PatchMapping("{id}")
    public Lesson updateLessonsName(@RequestBody String newName, @PathVariable Long id) {
        Lesson dbLesson = findLesson(id);
        if (dbLesson != null) {
            lessonsService.remove(dbLesson);
            // should replace all/neccesary variables
            dbLesson.setName(newName);
            lessonsService.add(dbLesson);
            return dbLesson;
        }
        return null;
    }

    //todo G modify correct method to support searching lessons by course id while keeping original functionality

    //todo H modify correct method to support searching by year with default being current year (2020)
    // (you can ignore semesters or use year-semester string)

    //todo K modify correct method to order lessons
    // * by most visitors first
    // * by least visitors first
    // (you can assume that by default it searches by predefined lecturer's order)

    //helper method
    private Lesson findLesson(Long id) {
        for (Lesson lesson : lessonsService) {
            if (lesson.getId().equals(id)) {
                return lesson;
            }
        }
        return null;
    }
}
