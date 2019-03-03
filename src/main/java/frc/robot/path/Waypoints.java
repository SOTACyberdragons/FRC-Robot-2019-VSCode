package frc.robot.path;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public interface Waypoints {

	public Waypoint[] points();


	public final class Vision implements Waypoints {

		private final double distanceX;
		private final double distanceY;
		private final double angleDeg;

		public Vision(double distanceX, double distanceY, double angleDeg) {
			this.distanceX = distanceX;
			this.distanceY = distanceY;
			this.angleDeg = angleDeg;
		}

		public Waypoint[] points() {
			return  new Waypoint[] {
					new Waypoint(0, 0, 0),
					new Waypoint(distanceX, distanceY, Math.toRadians(angleDeg)),

			};
		}
	}
	
	public final class LeftCenterHatch implements Waypoints {
		public Waypoint[] points() {
			return  new Waypoint[] {
					new Waypoint(0, 0, 0),
					//drive straight a little 
					new Waypoint(48.024,0, 0),
					//move to the right 
					new Waypoint(64.046, 8.770, Pathfinder.d2r(0)),//add angle
					new Waypoint(69.349, 25.192, Pathfinder.d2r(0)),//add angle
					new Waypoint(88.54, 35, Pathfinder.d2r(0)),
					//drive straight to cargo
					new Waypoint(121.25, 35, Pathfinder.d2r(0))
			};
		}
	}

	public final class RightCenterHatch implements Waypoints {
		public Waypoint[] points() {
			return  new Waypoint[] {
					new Waypoint(0, 0, 0),
					//drive straight a little 
					new Waypoint(48.024,0, 0),
					//move to the right 
					new Waypoint(64.046, -8.770, Pathfinder.d2r(0)),//add angle
					new Waypoint(69.349, -25.192, Pathfinder.d2r(0)),//add angle
					new Waypoint(88.54, -35, Pathfinder.d2r(0)),
					//drive straight to cargo
					new Waypoint(121.25, -35, Pathfinder.d2r(0))
			};
		}
	}

	// public final class LeftFarHatch implements Waypoints {
	// 	public Waypoint [] points() {
	// 		return  new Waypoint[] {
	// 				new Waypoint(0, 0, 0),
	// 				new Waypoint(73, 0, 0),
	// 				new Waypoint(89, -5,4 Pathfinder.d2r(0)), //add angle
	// 				new Waypoint()
	// 		};
	// 	}
	// }
	
	
}
