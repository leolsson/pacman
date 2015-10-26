package my.tdl.generator;

import java.awt.Graphics2D;
import java.util.ArrayList;

import my.project.gop.main.Vector2F;

public class TileManager {

	public ArrayList<Block> blocks = new ArrayList<Block>();

	public TileManager() {

	}

	public void tick(double deltaTime) {
		for (Block block : blocks) {
			block.tick(deltaTime);

			block.pos.add(new Vector2F(1, 0));
			
		}
	}

	public void render(Graphics2D g) {
		for (Block block : blocks) {
			block.render(g);

		}

	}
}
