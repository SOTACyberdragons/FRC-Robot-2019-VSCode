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
	
	//start in the center and put hatch panel on the front left hatch of the cargo ship
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

	//start in the center and put hatch panel on front right hatch on cargo ship
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


	// start on the left and put hatch panel on the farthest hatch on the left side of the cargo ship
	public final class LeftFartchCargo implements Waypoints {
		public Waypoint [] points() {
			return  new Waypoint[] {
					new Waypoint(0, 0, 0),
					//drive straight
					new Waypoint(73, 0, 0),
					//move to the left 
					new Waypoint(89, -5.4, Pathfinder.d2r(0)), //add angle
					new Waypoint(98, -33, Pathfinder.d2r(0)), //add angle
					new Waypoint(112, -41, Pathfinder.d2r(0)),
					//drive straight again
					new Waypoint(195, -41, Pathfinder.d2r(0)), 
					//turn to cargo ship
					new Waypoint(216.466, -19.678, Pathfinder.d2r(0)), //add angle 
					new Waypoint(225.125, 4.974, Pathfinder.d2r(90))
			};
		}
	}


	// start on the right and put hatch panel on the farthest hatch on the right side of the cargo ship
	public final class RightFartchCargo implements Waypoints {
		public Waypoint [] points() {
			return  new Waypoint[] {
					new Waypoint(0, 0, 0),
					//drive straight
					new Waypoint(73, 0, 0),
					//move to the left 
					new Waypoint(89, 5.4, Pathfinder.d2r(0)), //add angle
					new Waypoint(98, 33, Pathfinder.d2r(0)), //add angle
					new Waypoint(112, 41, Pathfinder.d2r(0)),
					//drive straight again
					new Waypoint(195, 41, Pathfinder.d2r(0)), 
					//turn to cargo ship
					new Waypoint(216.466, 19.678, Pathfinder.d2r(0)), //add angle 
					new Waypoint(225.125, -4.974, Pathfinder.d2r(-90))
			};
		}
	}

	//go back to the human player station to get another hatch panel after RightFartchCargo
	public final class RightFartchCargoRetrieval implements Waypoints {
		public Waypoint [] points() {
			return  new Waypoint[] {
					new Waypoint(0, 0, 0),
					new Waypoint(-5.768, 18.933, Pathfinder.d2r(0)), //add angle
					new Waypoint(-26.88, 36.859, Pathfinder.d2r(0)), //add angle 
					new Waypoint(-243.18, 99, Pathfinder.d2r(90)), 
					new Waypoint(-286.125, 99, Pathfinder.d2r(90))
			};
		}
	}

	//go back to the human player station to get another hatch panel after LeftFartchCargo
	public final class LeftFartchCargoRetrieval implements Waypoints {
		public Waypoint [] points() {
			return  new Waypoint[] {
					new Waypoint(0, 0, 0),
					new Waypoint(-5.768, -18.933, Pathfinder.d2r(0)), //add angle
					new Waypoint(-26.88, -36.859, Pathfinder.d2r(0)), //add angle 
					new Waypoint(-243.18, -99, Pathfinder.d2r(-90)), 
					new Waypoint(-286.125, -99, Pathfinder.d2r(-90))
			};
		}
	}

	//go back to second to furthest hatch on right side of cargo ship after getting hatch panel from human player station
	public final class RightFartchCargo2 implements Waypoints {
		public Waypoint [] points() {
			return  new Waypoint[] {
					new Waypoint(37.865, 0, Pathfinder.d2r(0)), //add angle
					new Waypoint(231.446, -53.371, Pathfinder.d2r(0)), //add angle 
					new Waypoint(256.591, -72.348, Pathfinder.d2r(0)), //add angle
					new Waypoint(264.375, -99, Pathfinder.d2r(-90))
			};
		}
	}

	//go back to second to furthest hatch on left side of cargo ship after getting hatch panel from human player station
	public final class LeftFartchCargo2 implements Waypoints {
		public Waypoint [] points() {
			return  new Waypoint[] {
					new Waypoint(37.865, 0, Pathfinder.d2r(0)), //add angle
					new Waypoint(231.446, 53.371, Pathfinder.d2r(0)), //add angle 
					new Waypoint(256.591, 72.348, Pathfinder.d2r(0)), //add angle
					new Waypoint(264.375, 99, Pathfinder.d2r(90))
			};
		}
	}
}
