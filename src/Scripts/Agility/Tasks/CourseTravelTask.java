package Scripts.Agility.Tasks;

import Scripts.Agility.Course;
import framework.Tasks.TeleportTask;
import lombok.Getter;
import simple.robot.api.ClientContext;

public class CourseTravelTask extends TeleportTask {

    @Getter
    private Course course;

    public CourseTravelTask(ClientContext ctx, Course course) {
        super(ctx, course.getLoc(), course.getArea());
        this.course = course;
    }

    public void setCourse(Course course)
    {
        super.setLoc(course.getLoc());
        super.setTarget(course.getArea());
        this.course = course;
    }
}
