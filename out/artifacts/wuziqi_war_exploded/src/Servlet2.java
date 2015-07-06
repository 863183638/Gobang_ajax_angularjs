

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import java.util.Comparator;
import java.util.Vector;



public class Servlet2 extends HttpServlet {
    int col=0;
    public class P{
        int x,y,pri;
        public P(int _x,int _y) {
            x=_x;y=_y;pri=0;
        }
    }
    int fx[][]={{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};

    public int elevaluate(int a[][],int x,int y,int f) {
        int res=0,pcol=col^1,cnt=0,nf,c;
        int i=x,j=y;
        while (x>=0&&x<=9&&y>=0&&y<=9&&a[x][y]==col) {
            cnt++;
            x=x+fx[f][0];y=y+fx[f][1];
        }
        if (cnt==4)	res=1000000000;
        else{
            c=1;
            x=i;y=j;
            x=x-fx[f][0];y=y-fx[f][1];
            x=x-fx[f][0];y=y-fx[f][1];
            while (x>=0&&x<=9&&y>=0&&y<=9&&a[x][y]==col) {
                c++;
                x=x-fx[f][0];y=y-fx[f][1];
            }
            if (c+cnt==5)	res=1000000000;
            if (c+cnt==4)   res=1000000;
        }
        if (x>=0&&x<=9&&y>=0&&y<=9) {
            if (a[x][y]==-1) {
                c=0;
                while (x>=0&&x<=9&&y>=0&&y<=9&&a[x][y]==-1) {
                    c++;
                    x=x+fx[f][0];y=y+fx[f][1];
                }
                x=i;y=j;
                nf=7-f;
                x=x+fx[nf][0];y=y+fx[nf][1];
                while (x>=0&&x<=9&&y>=0&&y<=9&&a[x][y]==-1) {
                    c++;
                    x=x+fx[nf][0];y=y+fx[nf][1];
                }
                if (cnt==3&&c>1)	res=10000000;
                if (cnt==2&&c>2)	res=10000;
                if (cnt==1&&c>3)	res=10;
            }else {
                x=i;y=j;
                nf=7-f;
                x=x+fx[nf][0];y=y+fx[nf][1];
                c=0;
                while (x>=0&&x<=9&&y>=0&&y<=9&&a[x][y]==-1) {
                    c++;
                    x=x+fx[nf][0];y=y+fx[nf][1];
                }
                if (cnt==3&&c>1)	res=1000000;
                if (cnt==2&&c>2)	res=100;
                if (cnt==1&&c>3)	res=1;
            }
        }else {
            x=i;y=j;
            nf=7-f;
            x=x+fx[nf][0];y=y+fx[nf][1];
            c=0;
            while (x>=0&&x<=9&&y>=0&&y<=9&&a[x][y]==-1) {
                c++;
                x=x+fx[nf][0];y=y+fx[nf][1];
            }
            if (cnt==3&&c>1)	res=1000000;
            if (cnt==2&&c>2)	res=100;
            if (cnt==1&&c>3)	res=1;
        }

        x=i;y=j;
        int _x=x,_y=y;
        c=1;
        _x=_x-fx[f][0];_y=_y-fx[f][1];
        _x=_x-fx[f][0];_y=_y-fx[f][1];
        while (_x>=0&&_x<=9&&_y>=0&&_y<=9&&a[_x][_y]==pcol) {
            c++;
            _x=_x-fx[f][0];_y=_y-fx[f][1];
        }
        cnt=0;
        while (x>=0&&x<=9&&y>=0&&y<=9&&a[x][y]==pcol) {
            cnt++;
            x=x+fx[f][0];y=y+fx[f][1];
        }
        if (cnt==4) res=100000000;
        if (c>1) {
            if (c+cnt==5)	res=100000000;
            if (c+cnt==4)	res=100000;
            if (c+cnt==3)	res=1000;
        }else
        if (x>=0&&x<=9&&y>=0&&y<=9) {
            if (a[x][y]==-1) {
                if (cnt==3)	res=100000;
                if (cnt==2)	res=10000;
                if (cnt==1)	res=2;
            }else {
                if (cnt==3)	res=1000;
                if (cnt==2)	res=10;
                if (cnt==1)	res=2;
            }
        }else {
            if (cnt==3)	res=1000;
            if (cnt==2)	res=10;
            if (cnt==1)	res=2;
        }
        return res;
    }


    public boolean checkpw(int a[][],int x,int y) {
        boolean flag=false;
        int z=a[x][y];
        for (int i=0;i<8;i++) {
            int xx=x,yy=y,cnt=0;
            while (xx>=0&&xx<=9&&yy>=0&&yy<=9&&a[xx][yy]==z) {
                cnt++;
                xx=xx+fx[i][0];yy=yy+fx[i][1];
            }
            if (cnt==5) {
                flag=true;
                break;
            }
        }
        return flag;
    }


    public String sortthepos(int a[][],int bow){
        col=bow;
        Vector<P> vt=new Vector<P>();
        for (int i=0;i<10;i++)
            for (int j=0;j<10;j++)
                if (a[i][j]==(bow^1)&&checkpw(a,i,j)) {
                    System.out.println("pw"+i+" "+j);
                    return i+","+j+","+2;
                }

        System.out.println(a[5][5]);
        for (int i=0;i<10;i++)
            for (int j=0;j<10;j++) {
                if (a[i][j]!=-1)	continue;
                P cur=new P(i,j);
                if(i==5&&j==5)	cur.pri=1;
                for (int k=0;k<8;k++) {
                    int ii=i+fx[k][0],jj=j+fx[k][1];
                    if (ii<0||ii>9||jj<0||jj>9)	continue;
                    else {
                        int c=elevaluate(a,ii,jj,k);
                        if (c==1000000000) {
                            //下在ii jj
                            //电脑赢了
                            System.out.println("cw"+i+" "+j);
                            return i+","+j+","+1;
                        }
                        cur.pri+=c;
                    }
                }
                vt.add(cur);
            }
        int maxi=-0x3f3f3f3f,pos=0;
        for (int i=0;i<vt.size();i++) if (vt.elementAt(i).pri>maxi) {
            maxi=vt.elementAt(i).pri;
            pos=i;
        }

        int xx=vt.elementAt(pos).x;int yy=vt.elementAt(pos).y; //"ii="+xx+"&jj="+yy+"&reState="+0
        System.out.println("co"+xx+" "+yy);
        return xx+","+yy+","+0;
    }



    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();//返回值

        String str = req.getParameter("test");
        String state = req.getParameter("state");

        System.out.println(str);
        int[][] a = new int[10][10];
        int[] b = new int[100];
        String[] sourceStrArray=str.split(",");
        for(int i =0;i<100;i++){
            b[i] = Integer.valueOf(sourceStrArray[i]).intValue();

        }
        for(int i=0 ;i<10;i++)
            for(int j=0;j<10;j++)
                a[i][j]=b[10*i+j];
        //调用函数部分
        int xx = 0;
        int yy = 0;
        int reState = 0;

        String responString=null;
        responString = sortthepos(a,Integer.valueOf(state).intValue());

        System.out.println(responString);
        String[] rString=responString.split(",");
        xx = Integer.valueOf(rString[0]).intValue();
        yy = Integer.valueOf(rString[1]).intValue();
        reState = Integer.valueOf(rString[2]).intValue();
        String responseText = xx+","+yy+","+reState;
        out.print(responseText);

        JSONObject obj = new JSONObject();
        obj.put("xx",xx);
        obj.put("yy",yy);
        obj.put("reState",reState);

    }


}