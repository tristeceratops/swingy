package swingy.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class BSPTree {

	private static final Random random = new Random();

	private BSPTree left;
	private BSPTree right;
	private final Region region;
	private Region room;
	List<Coordinate> pathPoints;

	public BSPTree(Region region) {
		this.region = region;
		pathPoints = new ArrayList<>();
	}

	public BSPTree getLeft() {
		return left;
	}

	public BSPTree getRight() {
		return right;
	}

	public Region getRegion() {
		return region;
	}

	public Region getRoom() {
		return room;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}

	// Collect all rooms (leaf node rooms) under `node` and add to `rooms`
	private void collectLeafRooms(BSPTree node, List<Region> rooms) {
		if (node == null) return;
		if (node.isLeaf()) {
			if (node.getRoom() != null) rooms.add(node.getRoom());
			return;
		}
		if (node.left != null) collectLeafRooms(node.left, rooms);
		if (node.right != null) collectLeafRooms(node.right, rooms);
	}

	// Create a corridor (pathPoints) between two rooms (a and b).
	private void createCorridorBetweenRooms(Region a, Region b) {
		if (a == null || b == null) return;

		// aligned horizontally (overlap on Y)
		if ((a.y() > b.y() && a.y() < b.bottom()) || (a.bottom() > b.y() && a.bottom() < b.bottom())) {
			int minY = Math.min(a.y(), b.y());
			int maxY = Math.max(a.bottom(), b.bottom());
			int randomY = minY + random.nextInt(maxY - minY + 1);

			int leftX = (a.right() < b.x()) ? a.right() : a.x();
			int rightX = (leftX == a.right()) ? b.x() : b.right();

			Coordinate leftPoint = new Coordinate(leftX, randomY);
			Coordinate rightPoint = new Coordinate(rightX, randomY);
			pathPoints.add(leftPoint);
			pathPoints.add(rightPoint);
			return;
		}

		// aligned vertically (overlap on X)
		if ((a.x() > b.x() && a.x() < b.right()) || (a.right() > b.x() && a.right() < b.right())) {
			int minX = Math.min(a.x(), b.x());
			int maxX = Math.max(a.right(), b.right());
			int randomX = minX + random.nextInt(maxX - minX + 1);

			int topY = (a.bottom() < b.y()) ? a.bottom() : a.y();
			int bottomY = (topY == a.bottom()) ? b.y() : b.bottom();

			Coordinate topPoint = new Coordinate(randomX, topY);
			Coordinate bottomPoint = new Coordinate(randomX, bottomY);
			pathPoints.add(topPoint);
			pathPoints.add(bottomPoint);
			return;
		}

		// Not aligned: make an L/Z shaped corridor. Pick random point inside each room, then join with one turn.
		int ax = a.x() + random.nextInt(Math.max(1, a.width()));
		int ay = a.y() + random.nextInt(Math.max(1, a.height()));
		int bx = b.x() + random.nextInt(Math.max(1, b.width()));
		int by = b.y() + random.nextInt(Math.max(1, b.height()));

		Coordinate start = new Coordinate(ax, ay);
		Coordinate end = new Coordinate(bx, by);

		if (random.nextBoolean()) {
			// horizontal then vertical: (ax,ay) -> (bx,ay) -> (bx,by)
			Coordinate mid = new Coordinate(bx, ay);
			pathPoints.add(start);
			pathPoints.add(mid);
			pathPoints.add(end);
		} else {
			// vertical then horizontal: (ax,ay) -> (ax,by) -> (bx,by)
			Coordinate mid = new Coordinate(ax, by);
			pathPoints.add(start);
			pathPoints.add(mid);
			pathPoints.add(end);
		}
	}

	// Updated createPath() to use collection and corridor helpers
	public void createPath() {
		if (isLeaf()) return;

		// If both immediate children are leaves, keep original direct-room connection
		if (this.left != null && this.left.isLeaf() && this.right != null && this.right.isLeaf()) {
			createCorridorBetweenRooms(this.left.getRoom(), this.right.getRoom());
			return;
		}

		// Otherwise, collect all leaf rooms under left and right subtrees,
		// pick one room from each side and connect them.
		List<Region> leftRooms = new ArrayList<>();
		List<Region> rightRooms = new ArrayList<>();

		if (this.left != null) collectLeafRooms(this.left, leftRooms);
		if (this.right != null) collectLeafRooms(this.right, rightRooms);

		if (!leftRooms.isEmpty() && !rightRooms.isEmpty()) {
			Region from = leftRooms.get(random.nextInt(leftRooms.size()));
			Region to = rightRooms.get(random.nextInt(rightRooms.size()));
			createCorridorBetweenRooms(from, to);
		}
	}


	//return true if succeed
	public boolean split(int minSize) {
		if (!isLeaf()) return false;

		boolean splitVertical = random.nextBoolean();
		int max = (splitVertical ? region.width() : region.height()) - minSize;
		if (max <= minSize) return false;

		int split = minSize + random.nextInt(max - minSize);

		if (splitVertical) {
			left = new BSPTree(new Region(region.topLeft(), split, region.height()));
			Coordinate rightCorner = new Coordinate(region.x() + split, region.y());
			right = new BSPTree(new Region(rightCorner, region.width() - split, region.height()));
			System.out.println("Split Vertical with split = " + split + " minsSize = " + minSize + " and max = " + max + " :\nleft is:" + left.getRegion() + "\nright is:" + right.getRegion());
		} else {
			left = new BSPTree(new Region(region.topLeft(), region.width(), split));
			Coordinate bottomCorner = new Coordinate(region.x(), region.y() + split);
			right = new BSPTree(new Region(bottomCorner, region.width(), region.height() - split));
			System.out.println("Split Horizontal with split = " + split + " minsSize = " + minSize + " and max = " + max + " :\nleft is:" + left.getRegion() + "\nright is:" + right.getRegion());

		}

		return true;
	}

	public void recursiveSplit(int depth, int minSize) {
		if (depth <= 0) return;

		if (split(minSize)) {
			left.recursiveSplit(depth - 1, minSize);
			right.recursiveSplit(depth - 1, minSize);
		}
	}

	public void recursiveCreateRoom() {
		if (!isLeaf()) {
			if (left != null) left.recursiveCreateRoom();
			if (right != null) right.recursiveCreateRoom();
			createPath();
			System.out.println("PathPoints: " + pathPoints);
			return;
		}

		int margin = 2;
		int roomMinSize = Math.max(2, Math.min(region.width(), region.height()) / 4);
		int roomMaxWidth = Math.max(roomMinSize, region.width() - margin);
		int roomMaxHeight = Math.max(roomMinSize, region.height() - margin);

		if (roomMaxWidth < roomMinSize || roomMaxHeight < roomMinSize) {
			System.out.println("too small region : minSize = " + roomMinSize + ", maxSizeWidth = " + roomMaxWidth + ", roomMaxHeight = " + roomMaxHeight);
			return; //too small region
		}

		int w = roomMinSize + random.nextInt(roomMaxWidth - roomMinSize + 1);
		int h = roomMinSize + random.nextInt(roomMaxHeight - roomMinSize + 1);

		int x = region.x() + random.nextInt(Math.max(1, region.width() - w));
		int y = region.y() + random.nextInt(Math.max(1, region.height() - h));

		room = new Region(new Coordinate(x, y), w, h);
		System.out.println("room recursive create\nRegion: " + region + "\nRoom: " + room);
	}

	public void traverse(Consumer<BSPTree> action) {
		action.accept(this);
		if (left != null) left.traverse(action);
		if (right != null) right.traverse(action);
	}

	public void setLeft(BSPTree left) {
		this.left = left;
	}

	public void setRight(BSPTree right) {
		this.right = right;
	}

	public void setRoom(Region room) {
		this.room = room;
	}

	public List<Coordinate> getPathPoints() {
		return pathPoints;
	}

	public void setPathPoints(List<Coordinate> pathPoints) {
		this.pathPoints = pathPoints;
	}
}
