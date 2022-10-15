package Scripts.Agility.Tasks;

import Scripts.Agility.Course;
import framework.Tasks.TeleportTask;
import lombok.Getter;
import lombok.Setter;
import simple.robot.api.ClientContext;

public class CourseTravelTask extends TeleportTask {

    @Getter
    @Setter
    private Course course;

    public CourseTravelTask(ClientContext ctx, Course course) {
        super(ctx, course.getLoc(), course.getArea());
        this.course = course;
    }
}
