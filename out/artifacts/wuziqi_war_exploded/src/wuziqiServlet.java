import java.io.IOException;
import java.util.Comparator;
import java.util.Vector;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

//0表示白，1表示黑，-1表示没有
public class wuziqiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    int col=0;
    public class P{
        int x,y,pri;
        public P(int _x,int _y) {
            x=_x;y=_y;pri=0;
        }
    }
    int fx[][]={{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
    public int elevaluate(int a[][],int x,int y,int f) {
        int res=0,pcol=col^1,cnt=0;
        int i=x,j=y;
        while (x>=0&&x<=9&&y>=0&&y<=9&&a[x][y]==col) {
            cnt++;
            x=x+fx[f][0];y=y+fx[f][1];
        }
        if (cnt==5)	res=1000000000;
        if (x>=0&&x<=9&&y>=0&&y<=9) {
            if (a[x][y]==-1) {
                if (cnt==4)	res=10000000;
                if (cnt==3)	res=10000;
                if (cnt==2)	res=10;
            }else {
                if (cnt==4)	res=1000000;
                if (cnt==3)	res=100;
                if (cnt==2)	res=1;
            }
        }else {
            if (cnt==4)	res=1000000;
            if (cnt==3)	res=100;
            if (cnt==2)	res=1;
        }
        x=i;y=j;
        cnt=0;
        while (x>=0&&x<=9&&y>=0&&y<=9&&a[x][y]==pcol) {
            cnt++;
            x=x+fx[f][0];y=y+fx[f][1];
        }
        if (cnt==5) {
            //人赢了
            res=-1;
        }
        if (cnt==4) res=100000000;
        if (x>=0&&x<=9&&y>=0&&y<=9) {
            if (a[x][y]==-1) {
                if (cnt==3)	res=100000;
                if (cnt==2)	res=10000;
            }else if (cnt==3)	res=1000;
        }
        return res;
    }
    public void sortthepos(int a[][],int bow){
        col=bow;
        Vector<P> vt=new Vector<P>();
        for (int i=0;i<10;i++)
            for (int j=0;j<10;j++) {
                if (a[i][j]!=-1)	continue;
                P cur=new P(i,j);
                for (int k=0;k<8;k++) {
                    int ii=i+fx[k][0],jj=j+fx[k][1];
                    if (ii<0||ii>9||jj<0||jj>9)	continue;
                    else {
                        int c=elevaluate(a,ii,jj,k);
                        if (c==1000000000) {
                            //下在ii jj
                            //电脑赢了
                            return ;
                        }
                        if (c<0) {
                            //人赢了
                            return ;
                        }else cur.pri+=c;
                    }
                }
                vt.add(cur);
            }
        vt.sort(new Comparator<P>(){
            @Override
            public int compare(P o1, P o2) {
                // TODO Auto-generated method stub
                return o1.pri>o2.pri?1:0;
            }

        });
        int xx=vt.elementAt(0).x;int yy=vt.elementAt(0).y;
    }

}

