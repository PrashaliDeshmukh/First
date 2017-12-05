
import java.util.*;
import java.io.*;

public class KMeans {
    String table[][], centroids[][],line="";
    ArrayList<Float> distance;
    String oldClusterList="", newClusterList="", random="";
    StringTokenizer st;
    int rows=0, columns=0, clusters;
    Scanner sc;
    
    public KMeans(){
        distance = new ArrayList<>();
        sc = new Scanner(System.in);
    }
    
    void dataInput(){
    	try{
			FileReader file = new FileReader("input.csv");
			BufferedReader br = new BufferedReader(file);
			line = br.readLine();
			columns = (line.split(",")).length;
		    do{
		    	rows++;
		    }while((line = br.readLine())!=null);
		  	System.out.println("\nEnter Cluster Count: ");
		  	clusters = sc.nextInt();
		  	if(clusters>(rows-1)){
		  		System.out.println("\nCluster count is greater than points count!\n");
		  		System.exit(0);
		  	}
			centroids = new String[clusters][columns];
		  
		    file = new FileReader("input.csv");
			br = new BufferedReader(file);
		    table = new String[rows][columns];
		    for(int i=0;i<rows;i++){
		    	st = new StringTokenizer(br.readLine(),",");
		    	for(int j=0;j<columns;j++)
		    		table[i][j]=st.nextToken();
		    }
		    
		    Random ran = new Random();
		    for(int i=1;i<=clusters;i++){
		    	Random rn = new Random();
				int range = rows - 1;
				int x = rn.nextInt(range) + 1;
				while(random.contains(""+x))
					x = rn.nextInt(range) + 1;
				random=random+x;
		    	table[x][2] = ""+i;
		    	centroids[i-1][0] = table[x][0];
		    	centroids[i-1][1] = table[x][1];
		    	centroids[i-1][2] = ""+i;
		    }
        }catch(Exception e){
        	e.printStackTrace();
       	}
    }
    
    boolean pass(){
    	oldClusterList=newClusterList;
    	newClusterList="";
    	float x, y;
    	for(int i=1;i<rows;i++){
    		distance.clear();
		    for(int k=0;k<clusters;k++){
		    	x = Float.parseFloat(table[i][0])-Float.parseFloat(centroids[k][0]);
		    	y = Float.parseFloat(table[i][1])-Float.parseFloat(centroids[k][1]);
		    	distance.add(k,(float)Math.sqrt((x*x)+(y*y)));
		    }
			System.out.println(distance);
		    updateCentroids(i);
		}
		for(int i=0;i<rows;i++)
    		newClusterList=newClusterList+table[i][2];
    	return (oldClusterList.equals(newClusterList))?true:false;
    }
    
    void updateCentroids(int i){
    	int cnt=0;
    	float x=0.0f,y=0.0f;
    	int clstr = distance.indexOf(Collections.min(distance))+1;
    	if(table[i][2].equals(""+clstr))
    		return;
    	String oc = table[i][2];
    	table[i][2] = ""+(clstr);
		for(int p=1;p<rows;p++){
			if(table[p][2].equals(""+clstr)){
				x+=Float.parseFloat(table[p][0]);
				y+=Float.parseFloat(table[p][1]);
				cnt++;
			}
		}
		centroids[clstr-1][0] = ""+(float)x/cnt;
		centroids[clstr-1][1] = ""+(float)y/cnt;
		
		if(oc.equals("-"))
			return;
		int oldClstr = Integer.parseInt(table[i][2]);
		x=0.0f;
		y=0.0f;
		for(int p=1;p<rows;p++){
			if(table[p][2].equals(""+clstr)){
				x+=Float.parseFloat(table[p][0]);
				y+=Float.parseFloat(table[p][1]);
				cnt++;
			}
		}
		centroids[oldClstr-1][0] = ""+(float)x/cnt;
		centroids[oldClstr-1][1] = ""+(float)y/cnt;
    }
    
    void output(){
    	System.out.println("Centroids:");
    	for(int i=0;i<clusters;i++){
            for(int j=0;j<columns;j++){
            	if(j==2)
                	System.out.print(centroids[i][j]+"\t|");
                else
                	System.out.print(String.format("%.1f",Float.parseFloat(centroids[i][j]))+"\t|");
            }
            System.out.println();
        }
        System.out.println("\nPoints:");
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
            	if(i==0 || j==2 || table[i][j].equals("-"))
                	System.out.print(table[i][j]+"\t|");
                else
                	System.out.print(String.format("%.1f",Float.parseFloat(table[i][j]))+"\t|");
            }
            System.out.println();
        }         
    }
    
    public static void main(String args[]){
        KMeans obj = new KMeans();
        System.out.println("\nData is taken from 'input.csv'.");
        obj.dataInput();
       
        obj.output();
        int i=1;
        boolean res;
        do{
        	System.out.println("\nPass "+i+":");
        	res=obj.pass();
        	obj.output();
        	i++;
        }while(!res);
        System.out.println();
    }
}
