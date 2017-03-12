package bayesian;

import java.io.File;
import java.io.IOException;

import org.tc33.jheatchart.HeatChart;

public class Calculate {
	public int[][] table;
	public double[][] prob;
	public double[][] prob_tmp;
	public int len;
	public int wid;

	public Calculate() {
		len = 5;
		wid = 5;
		table = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				table[i][j] = 4;
			}
		}
		table[1][1] = 2;
		table[1][2] = 2;
		table[1][3] = 3;
		table[2][1] = 1;
		table[2][2] = 1;
		table[2][3] = 1;
		table[3][1] = 1;
		table[3][2] = 4;
		table[3][3] = 2;
		prob = new double[5][5];
		prob_tmp = new double[5][5];
		prob[1][1] = 0.125;
		prob[1][2] = 0.125;
		prob[1][3] = 0.125;
		prob[2][1] = 0.125;
		prob[2][2] = 0.125;
		prob[2][3] = 0.125;
		prob[3][1] = 0.125;
		prob[3][2] = 0;
		prob[3][3] = 0.125;
	}

	public Calculate(int wid, int len) {
		this.len = len + 2;
		this.wid = wid + 2;

		table = new int[wid + 2][len + 2];
		for (int i = 0; i < wid + 1; i++) {
			for (int j = 0; j < len + 1; j++) {
				table[i][j] = 4;
			}
		}

		prob = new double[wid + 2][len + 2];
		prob_tmp = new double[wid + 2][len + 2];

	}

	public void normolize() {
		double tmp = 0;
		for (int i = 1; i < wid - 1; i++) {
			for (int j = 1; j < len - 1; j++) {
				tmp += prob[i][j];
			}
		}
		for (int i = 1; i < wid - 1; i++) {
			for (int j = 1; j < len - 1; j++) {
				prob[i][j] = prob[i][j] / tmp;
			}
		}

	}

	public void print() {
		double tmp = 0;
		for (int i = 1; i < wid - 1; i++) {
			for (int j = 1; j < len - 1; j++) {
				tmp += prob[i][j];
			}
		}
		System.out.println(tmp);
	}

	public void display_prob() {
		for (int i = 1; i < wid - 1; i++) {
			for (int j = 1; j < len - 1; j++) {
				System.out.print(prob[i][j] + "     ");
			}
			System.out.println();
		}
	}

	public void display_table() {
		for (int i = 1; i < wid - 1; i++) {
			for (int j = 1; j < len - 1; j++) {
				System.out.print(table[i][j] + "     ");
			}
			System.out.println();
		}
	}

	/*
	 * 1 left 2 right 3 up 4 down
	 */
	public void action(int act, int type) throws IOException {
		for (int i = 0; i < wid; i++) {
			for (int j = 0; j < len; j++) {
				prob_tmp[i][j] = prob[i][j];
			}
		}
		if (act == 1) {
			for (int i = 1; i < wid - 1; i++) {
				for (int j = 1; j < len - 1; j++) {
					if (table[i][j] == 4)
						continue;
					if (table[i][j + 1] == 4 && table[i][j - 1] != 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.05;

						}

					} else if (table[i][j + 1] != 4 && table[i][j - 1] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] + prob_tmp[i][j + 1] * 0.9) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] + prob_tmp[i][j + 1] * 0.9) * 0.05;

						}
					} else if (table[i][j + 1] == 4 && table[i][j - 1] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j]) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j]) * 0.05;

						}
					}

					else {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1 + prob_tmp[i][j + 1] * 0.9) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1 + prob_tmp[i][j + 1] * 0.9) * 0.05;
						}
					}
				}
			}

		} else if (act == 2) {
			for (int i = 1; i < wid - 1; i++) {
				for (int j = 1; j < len - 1; j++) {
					if (table[i][j] == 4)
						continue;
					if (table[i][j + 1] != 4 && table[i][j - 1] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.05;

						}

					} else if (table[i][j + 1] == 4 && table[i][j - 1] != 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] + prob_tmp[i][j - 1] * 0.9) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] + prob_tmp[i][j - 1] * 0.9) * 0.05;

						}
					} else if (table[i][j + 1] == 4 && table[i][j - 1] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j]) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j]) * 0.05;

						}
					}

					else {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1 + prob_tmp[i][j - 1] * 0.9) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1 + prob_tmp[i][j - 1] * 0.9) * 0.05;
						}
					}
				}
			}

		} else if (act == 3) {
			for (int i = 1; i < wid - 1; i++) {
				for (int j = 1; j < len - 1; j++) {
					if (table[i][j] == 4)
						continue;
					if (table[i + 1][j] == 4 && table[i - 1][j] != 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.05;

						}

					} else if (table[i + 1][j] != 4 && table[i - 1][j] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] + prob_tmp[i + 1][j] * 0.9) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] + prob_tmp[i + 1][j] * 0.9) * 0.05;

						}
					} else if (table[i + 1][j] == 4 && table[i - 1][j] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j]) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j]) * 0.05;

						}
					}

					else {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1 + prob_tmp[i + 1][j] * 0.9) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1 + prob_tmp[i + 1][j] * 0.9) * 0.05;
						}
					}
				}
			}
		}

		else {

			for (int i = 1; i < wid - 1; i++) {
				for (int j = 1; j < len - 1; j++) {
					if (table[i][j] == 4)
						continue;
					if (table[i - 1][j] == 4 && table[i + 1][j] != 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.05;

						}

					} else if (table[i - 1][j] != 4 && table[i + 1][j] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] + prob_tmp[i - 1][j] * 0.9) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] + prob_tmp[i - 1][j] * 0.9) * 0.05;

						}
					} else if (table[i - 1][j] == 4 && table[i + 1][j] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j]) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j]) * 0.05;

						}
					}

					else {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1 + prob_tmp[i - 1][j] * 0.9) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1 + prob_tmp[i - 1][j] * 0.9) * 0.05;
						}
					}
				}
			}
		}
		this.normolize();
//		HeatChart map_1 = new HeatChart(prob);
//		map_1.saveToFile(new File("java-heat-chart.png"));
		

	}

	public void action_2(int act, int type) throws IOException {

		for (int i = 0; i < wid; i++) {
			for (int j = 0; j < len; j++) {
				prob_tmp[i][j] = prob[i][j];
			}
		}
		if (act == 1) {
			for (int i = 1; i < wid - 1; i++) {
				for (int j = 1; j < len - 1; j++) {
					if (table[i][j] == 4)
						continue;
					if (table[i][j + 1] == 4 && table[i][j - 1] != 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.05;

						}

					} else if (table[i][j + 1] != 4 && table[i][j - 1] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (Math.max(prob_tmp[i][j],prob_tmp[i][j + 1] * 0.9)  ) * 0.9;
						} else {
							prob[i][j] = (Math.max(prob_tmp[i][j],prob_tmp[i][j + 1] * 0.9)) * 0.05;

						}
					} else if (table[i][j + 1] == 4 && table[i][j - 1] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j]) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j]) * 0.05;

						}
					}

					else {
						if (table[i][j] == type) {
							prob[i][j] = (Math.max(prob_tmp[i][j] * 0.1, prob_tmp[i][j + 1] * 0.9)  ) * 0.9;
						} else {
							prob[i][j] = (Math.max(prob_tmp[i][j] * 0.1, prob_tmp[i][j + 1] * 0.9) ) * 0.05;
						}
					}
				}
			}

		} else if (act == 2) {
			for (int i = 1; i < wid - 1; i++) {
				for (int j = 1; j < len - 1; j++) {
					if (table[i][j] == 4)
						continue;
					if (table[i][j + 1] != 4 && table[i][j - 1] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.05;

						}

					} else if (table[i][j + 1] == 4 && table[i][j - 1] != 4) {
						if (table[i][j] == type) {
							prob[i][j] = (Math.max(prob_tmp[i][j], prob_tmp[i][j - 1] * 0.9)  ) * 0.9;
						} else {
							prob[i][j] = (Math.max(prob_tmp[i][j], prob_tmp[i][j - 1] * 0.9) ) * 0.05;

						}
					} else if (table[i][j + 1] == 4 && table[i][j - 1] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j]) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j]) * 0.05;

						}
					}

					else {
						if (table[i][j] == type) {
							prob[i][j] = (Math.max(prob_tmp[i][j] * 0.1 , prob_tmp[i][j - 1] * 0.9) ) * 0.9;
						} else {
							prob[i][j] = (Math.max(prob_tmp[i][j] * 0.1 , prob_tmp[i][j - 1] * 0.9) ) * 0.05;
						}
					}
				}
			}

		} else if (act == 3) {
			for (int i = 1; i < wid - 1; i++) {
				for (int j = 1; j < len - 1; j++) {
					if (table[i][j] == 4)
						continue;
					if (table[i + 1][j] == 4 && table[i - 1][j] != 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.05;

						}

					} else if (table[i + 1][j] != 4 && table[i - 1][j] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (Math.max(prob_tmp[i][j], prob_tmp[i + 1][j] * 0.9) ) * 0.9;
						} else {
							prob[i][j] = (Math.max(prob_tmp[i][j], prob_tmp[i + 1][j] * 0.9) ) * 0.05;

						}
					} else if (table[i + 1][j] == 4 && table[i - 1][j] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j]) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j]) * 0.05;

						}
					}

					else {
						if (table[i][j] == type) {
							prob[i][j] = (Math.max(prob_tmp[i][j] * 0.1, prob_tmp[i + 1][j] * 0.9)  ) * 0.9;
						} else {
							prob[i][j] = (Math.max(prob_tmp[i][j] * 0.1, prob_tmp[i + 1][j] * 0.9) ) * 0.05;
						}
					}
				}
			}
		}

		else {

			for (int i = 1; i < wid - 1; i++) {
				for (int j = 1; j < len - 1; j++) {
					if (table[i][j] == 4)
						continue;
					if (table[i - 1][j] == 4 && table[i + 1][j] != 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j] * 0.1) * 0.05;

						}

					} else if (table[i - 1][j] != 4 && table[i + 1][j] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (Math.max(prob_tmp[i][j], prob_tmp[i - 1][j] * 0.9)  ) * 0.9;
						} else {
							prob[i][j] = (Math.max(prob_tmp[i][j], prob_tmp[i - 1][j] * 0.9) ) * 0.05;

						}
					} else if (table[i - 1][j] == 4 && table[i + 1][j] == 4) {
						if (table[i][j] == type) {
							prob[i][j] = (prob_tmp[i][j]) * 0.9;
						} else {
							prob[i][j] = (prob_tmp[i][j]) * 0.05;

						}
					}

					else {
						if (table[i][j] == type) {
							prob[i][j] = (Math.max(prob_tmp[i][j] * 0.1, prob_tmp[i - 1][j] * 0.9) ) * 0.9;
						} else {
							prob[i][j] = (Math.max(prob_tmp[i][j] * 0.1, prob_tmp[i - 1][j] * 0.9) ) * 0.05;
						}
					}
				}
			}
		}		
		this.normolize();
//		HeatChart map_1 = new HeatChart(prob);
//		map_1.saveToFile(new File("java-heat-chart.png"));		
	}

}
