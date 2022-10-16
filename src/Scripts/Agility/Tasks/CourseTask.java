package Scripts.Agility.Tasks;

import Scripts.Agility.Course;
import framework.Player.Player;
import framework.World.Areas;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.coords.WorldPoint;
import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class CourseTask extends Task {

    @Getter@Setter
    private Course course;

    public CourseTask(ClientContext a, Course course) {
        super(a);
        this.course = course;
    }

    @Override
    public void run() {
        if (!course.inCourse(Player.getLocation())) {
            WorldPoint tile = course.getObstacles().get(0).getValue().randomTile();
            if (tile != null && ClientContext.instance().pathing.reachable(tile))
                ClientContext.instance().pathing.step(tile);
        }

        course.handleNextObstacle();
    }

    @Override
    public String status() {
        return "Handling course";
    }

    @Override
    public boolean condition() {
        return Areas.containsIgnoreZ(this.course.getArea(), Player.getLocation()) && !Player.isAnimating();
    }
}
