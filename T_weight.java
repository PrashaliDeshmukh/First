import java.io.*;
import java.util.Scanner;
public class T_weight
{
	public static void main(String[]args)
	{
		int r,i;
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter number of regions:");
		r=scan.nextInt();
		System.out.println("Enter number of items:");
		i=scan.nextInt();
		int [][]arr=new int[r][i];
		for(int j=0;j<r;j++)
		{
			System.out.println("Enter values for "+j+"region");
			for(int k=0;k<i;k++)
			{
			arr[j][k]=scan.nextInt();
			}
		}
		int sum1[]=new int[i];
		int  sum2[]=new int[r];
		
		for(int j=0;j<r;j++)
		{
			for(int k=0;k<i;k++)
			
			sum1[j]+=arr[k][j];
			
		}
		
		for(int j=0;j<r;j++)
		{
			for(int k=0;k<i;k++)
			
			sum2[j]+=arr[j][k];
			
		}
		
		double [][]t_weight=new double[i][r];
		double [][]d_weight=new double[i][r];
		for(int j=0;j<i;j++)
		{
			for(int k=0;k<r;k++)
			{
				t_weight[k][j]=((double)arr[k][j]*100)/(double)sum2[k];;	
			}
		}
		for(int j=0;j<i;j++)
		{
			for(int k=0;k<r;k++)
			{
				d_weight[k][j]=((double)arr[k][j]*100)/(double)sum1[j];
			}
		}
		System.out.println("t_weight");
		for(int j=0;j<i;j++)
		{
			
			for(int k=0;k<r;k++)
			{
			System.out.print(" "+t_weight[j][k]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("d_weight");
		for(int j=0;j<i;j++)
		{
			
			for(int k=0;k<r;k++)
			{
			System.out.print("  "+d_weight[j][k]);
			}
			System.out.println();
			
		}
				
		
	}
}
