package bayesian;
import java.util.Random;

public class Map extends MazeMap {
	public static void main(String[] args) {
		Map test = new Map(160,120);
		test.generate_startpoint();
		test.action_run(100);
		test.display();
		
	}
	public char[] action;
	public char[] reading;
	int start_x;
	int start_y;
	int[] x_or;
	int[] y_or;
	public Map(int width, int height){          
		super(width,height);
	}
	public void display(){
		for(int i=0;i<101;i++){
			System.out.print("("+x_or[i]+","+y_or[i]+")");
		}
		System.out.println();
		for(int i=0;i<100;i++){
			System.out.print(" "+action[i]+" ");
		}
		System.out.println();
		for(int i=0;i<100;i++){
			System.out.print(" "+reading[i]+" ");
		}
		System.out.println();
	}
	public void generate_startpoint(){
		Random random = new Random();
		start_x= random.nextInt(120)%(120-0+1) + 0;
		start_y= random.nextInt(160)%(160-0+1) + 0;
		while(node[start_x][start_y]==2){
			start_x= random.nextInt(120)%(120-0+1) + 0;
			start_y= random.nextInt(160)%(160-0+1) + 0;
		}
		System.out.println(" "+start_x+" "+start_y+" "+node[start_x][start_y]);
	}
	public void action_run(int num){
		x_or=new int[num+1];
		y_or=new int[num+1];
		x_or[0]=start_x;
		y_or[0]=start_y;
		double x,y,z;
		action=new char[num];
		reading=new char[num];
		Random random = new Random();
		for(int i=1;i<(num+1);i++){
			x=random.nextDouble();
			y=random.nextDouble();
			z=random.nextDouble();
			if(x<0.25){
				action[i-1]='U';
				
				if(y<0.9){
					if(x_or[i-1]==0||node[x_or[i-1]-1][y_or[i-1]]=='2'){
						x_or[i]=x_or[i-1];
						y_or[i]=y_or[i-1];
					}
					else{
						x_or[i]=x_or[i-1]-1;
						y_or[i]=y_or[i-1];
					}
				}
				else{
					x_or[i]=x_or[i-1];
					y_or[i]=y_or[i-1];
				}
				if(z<0.9){
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='H';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='N';
						}
						else{
							reading[i-1]='T';
						}
					
				}
				else if(z<0.95){
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='N';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='T';
						}
						else{
							reading[i-1]='H';
						}
				}
				else{
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='T';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='H';
						}
						else{
							reading[i-1]='N';
						}
				}
				
			}
			else if(x<0.5){
				action[i-1]='L';
				if(y<0.9){
					if(y_or[i-1]==0||node[x_or[i-1]][y_or[i-1]-1]==2){
						x_or[i]=x_or[i-1];
						y_or[i]=y_or[i-1];
					}
					else{
						x_or[i]=x_or[i-1];
						y_or[i]=y_or[i-1]-1;
					}
				}
				else{
					x_or[i]=x_or[i-1];
					y_or[i]=y_or[i-1];
				}
				
				if(z<0.9){
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='H';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='N';
						}
						else{
							reading[i-1]='T';
						}
					
				}
				else if(z<0.95){
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='N';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='T';
						}
						else{
							reading[i-1]='H';
						}
				}
				else{
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='T';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='H';
						}
						else{
							reading[i-1]='N';
						}
				}
				
			}
			else if(x<0.75){
				action[i-1]='D';

				
				if(y<0.9){
					if(x_or[i-1]==119||node[x_or[i-1]+1][y_or[i-1]]==2){
						x_or[i]=x_or[i-1];
						y_or[i]=y_or[i-1];
					}
					else{
						x_or[i]=x_or[i-1]+1;
						y_or[i]=y_or[i-1];
					}
				}
				else{
					x_or[i]=x_or[i-1];
					y_or[i]=y_or[i-1];
				}
				if(z<0.9){
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='H';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='N';
						}
						else{
							reading[i-1]='T';
						}
					
				}
				else if(z<0.95){
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='N';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='T';
						}
						else{
							reading[i-1]='H';
						}
				}
				else{
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='T';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='H';
						}
						else{
							reading[i-1]='N';
						}
				}
				
			}
			else{
				action[i-1]='R';


				
				if(y<0.9){
					if(y_or[i-1]==159||node[x_or[i-1]][y_or[i-1]+1]==2){
						x_or[i]=x_or[i-1];
						y_or[i]=y_or[i-1];
					}
					else{
						x_or[i]=x_or[i-1];
						y_or[i]=y_or[i-1]+1;
					}
				}
				else{
					x_or[i]=x_or[i-1];
					y_or[i]=y_or[i-1];
				}
				if(z<0.9){
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='H';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='N';
						}
						else{
							reading[i-1]='T';
						}
					
				}
				else if(z<0.95){
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='N';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='T';
						}
						else{
							reading[i-1]='H';
						}
				}
				else{
					if(node[x_or[i]][y_or[i]]=='3'||node[x_or[i]][y_or[i]]=='4'){
						reading[i-1]='T';
						}
						else if(node[x_or[i]][y_or[i]]=='0'){
							reading[i-1]='H';
						}
						else{
							reading[i-1]='N';
						}
				}
				
			}
			
			
		}
	}
	
}
