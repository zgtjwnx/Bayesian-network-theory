package bayesian;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.tc33.jheatchart.HeatChart;

public class Main {
	public static void main(String[] args) throws IOException {
		Calculate test = new Calculate();
		
		test.action_2(2, 1);
		test.display_prob();

		test.action_2(2, 1);
		test.display_prob();

		test.action_2(4, 2);
		test.display_prob();

		test.action_2(4, 2);
		test.display_prob();

		test = new Calculate(120, 160);
		Map map = new Map(160, 120);
		map.generate_file();
		map.print_file();
		map.generate_startpoint();
		map.action_run(100);
		map.display();
		int count = 120 * 160;
		for (int i = 1; i < 122 - 1; i++) {
			for (int j = 1; j < 162 - 1; j++) {
				if (map.node[i - 1][j - 1] == '0') {
					test.table[i][j] = 1;
				} else if (map.node[i - 1][j - 1] == '1') {
					test.table[i][j] = 3;
				} else if (map.node[i - 1][j - 1] == '2') {
					count--;
				} else {
					test.table[i][j] = 2;
				}
			}
		}

		for (int i = 1; i < 122 - 1; i++) {
			for (int j = 1; j < 162 - 1; j++) {
				if (test.table[i][j] == 4) {
					test.prob[i][j] = 0;
				} else
					test.prob[i][j] = (double) 1 / count;
			}
		}
//		double distance = 0;
		// test.display_table();
		// test.display_prob();
		for (int i = 0; i < 100; i++) {
			int act, typ;

			if (map.action[i] == 'U')
				act = 3;
			else if (map.action[i] == 'L')
				act = 1;
			else if (map.action[i] == 'D')
				act = 4;
			else
				act = 2;

			if (map.reading[i] == 'N')
				typ = 1;
			else if (map.reading[i] == 'H')
				typ = 2;
			else
				typ = 3;

			test.action(act, typ);

//			int test_x = 0;
//			int test_y = 0;
//			double goal = 0;
//			for (int p = 0; p < 121; p++) {
//				for (int q = 0; q < 161; q++) {
//					if (goal < test.prob[p][q]) {
//						goal = (double) test.prob[p][q];
//						test_x = p;
//						test_y = q;
//					}
//				}
//			}
//			if (i >= 20)
//				distance = distance + Math.sqrt((test_x - 1 - map.x_or[i + 1]) * (test_x - 1 - map.x_or[i + 1])
//						+ (test_y - 1 - map.y_or[i + 1]) * (test_y - 1 - map.y_or[i + 1]));

			// System.out.println(goal+" "+test_x+" "+test_y);

			// test.display_prob();
		}
//		distance /= 80;
//		System.out.println(distance);
		HeatChart map_1 = new HeatChart(test.prob);
		map_1.setBackgroundColour(Color.LIGHT_GRAY);
		map_1.setColourScale(0.1);
		map_1.setHighValueColour(Color.RED);
		map_1.setLowValueColour(Color.YELLOW);
		map_1.saveToFile(new File("java-heat-chart.png"));
	}
}
