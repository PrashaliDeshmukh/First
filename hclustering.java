import java.io.*;


class hclustering
{
public static void main(String args[])throws IOException
{
int n,temp;
String temp1;
DataInputStream in=new DataInputStream(System.in);
System.out.println("Enter no of elements");
n=Integer.parseInt(in.readLine());
String clust1[]=new String[n];
int clust2[]=new int[n];
System.out.println("Enter elements");
for(int i=0;i<n;i++)
{
clust1[i]=in.readLine();
clust2[i]=Integer.parseInt(in.readLine());
}
for(int j=0;j<n;j++)
{
for(int k=0;k<n;k++)
{
if(clust2[j]<clust2[k])
{
temp=clust2[j];
clust2[j]=clust2[k];
clust2[k]=temp;
temp1=clust1[j];
clust1[j]=clust1[k];
clust1[k]=temp1;
}
}
}
operation op=new operation();
op.display(clust1,clust2,n);
for(int i=0;i<n-1;i++)
{
clust1[0]=clust1[0]+clust1[i+1];
clust2[0]=clust2[0]+clust2[i+1];
clust1[i+1]="0";
op.display(clust1,clust2,n);
}
}
}

class operation
{
void display(String clust1[],int clust2[],int n)
{
System.out.println("variable\tvalue");
for(int i=0;i<n;i++)
{
if(clust1[i]!="0")
System.out.println(clust1[i]+"\t\t"+clust2[i]);
}
System.out.println("");
}
}
