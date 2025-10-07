package swingy.model.game;

import java.util.Random;
import java.util.function.Consumer;

public class BSPTree {

	private static final Random random = new Random();

	private BSPTree left;
	private BSPTree right;
	private final Region region;
	private Region room;

	public BSPTree(Region region) {
		this.region = region;
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

	public boolean isLeaf(){
		return left == null && right == null;
	}

	//return true if succeed
	public boolean split(int minSize){
		if (!isLeaf()) return false;

		boolean splitVertical = random.nextBoolean();
		int max = (splitVertical ? region.width() : region.height()) - minSize ;
		if (max <= minSize) return false;

		int split = minSize + random.nextInt(max - minSize);

		if (splitVertical){
			left = new BSPTree(new Region(region.topLeft(), split, region.height()));
			Coordinate rightCorner = new Coordinate(region.x() + split, region.y());
			right = new BSPTree(new Region(rightCorner, region.width() - split, region.height()));
			System.out.println("Split Vertical with split = "+ split +" minsSize = " + minSize + " and max = " + max + " :\nleft is:" + left.getRegion() + "\nright is:" + right.getRegion());
		} else {
			left = new BSPTree(new Region(region.topLeft(), region.width(), split));
			Coordinate bottomCorner = new Coordinate(region.x(),  region.y() + split);
			right = new BSPTree(new Region(bottomCorner, region.width(), region.height() - split));
			System.out.println("Split Horizontal with split = "+ split +" minsSize = " + minSize + " and max = " + max + " :\nleft is:" + left.getRegion() + "\nright is:" + right.getRegion());

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

	public void recursiveCreateRoom(){
		if (!isLeaf()){
			if (left != null) left.recursiveCreateRoom();
			if (right != null) right.recursiveCreateRoom();
			return;
		}

		int margin = 2;
		int roomMinSize = Math.max(2, Math.min(region.width(), region.height()) / 4);
		int roomMaxWidth = Math.max(roomMinSize, region.width() - margin);
		int roomMaxHeight = Math.max(roomMinSize, region.height() - margin);

		if (roomMaxWidth < roomMinSize || roomMaxHeight < roomMinSize){
			System.out.println("too small region : minSize = " + roomMinSize + ", maxSizeWidth = " + roomMaxWidth + ", roomMaxHeight = " + roomMaxHeight);
			return; //too small region
		}

		int w = roomMinSize + random.nextInt(roomMaxWidth - roomMinSize + 1);
		int h = roomMinSize + random.nextInt(roomMaxHeight - roomMinSize + 1);

		int x = region.x() + random.nextInt(Math.max(1, region.width() - w));
		int y = region.y() + random.nextInt(Math.max(1, region.height() - h));

		room = new Region(new Coordinate(x, y), w, h);
		System.out.println("room recursive create\nRegion: " + region+"\nRoom: " + room);
	}

	public void traverse(Consumer<BSPTree> action){
		action.accept(this);
		if (left != null) left.traverse(action);
		if (right != null) right.traverse(action);
	}
}
