import java.io.*;

public class Str {

	public static boolean isNumber(String val) {
	    try {
	        Integer.parseInt(val);
	        return true;
	    } catch (NumberFormatException nfex) {
	        return false;
	    }
	}
	
	public static boolean isFloat(String val) {
	    try {
	        Float.parseFloat(val);
	        return true;
	    } catch (NumberFormatException nfex) {
	        return false;
	    }
	}
	
	public static void main(String[] args) {
		
		try {
            FileReader in = new FileReader(args[0]);
            //String[] strFile;
            //strFile = args[0].split("\\.");
            FileWriter out = new FileWriter("EL.txt"); //中間値を削ったもの
            FileWriter out2 = new FileWriter("AVE.txt"); //平均を出したもの
            //動かない（exeでは生成されない）
            //FileWriter out = new FileWriter("EL_" + strFile[0] + ".txt");
            //FileWriter out2 = new FileWriter("AVE_" + strFile[0] + ".txt");
            BufferedReader br = new BufferedReader(in);
            BufferedWriter bw = new BufferedWriter(out);
            BufferedWriter bw2 = new BufferedWriter(out2);
            String line;
            String[] strAry;
            String tmp = null;
            float tmpf = 0.0f;
            int tmpi = 0, count = 0;
            int i=0,j=0,flag=0;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                strAry = line.split(" ");
                for(String s : strAry){
                	//System.out.println(s+isNumber(s));
                	if(i<2){
            			bw.write(s + " ");
            			//あて先と途中の結果
            			//項目に関してiは++されない
                		if(isNumber(s))
                			i++;
                		else if(isFloat(s))
                			i++;
                		
                		if(i==1){
                			j++;                			
                			
                			//項目による分類
                			if(j==2){
                				//一番最初の項目
                				if(tmp==null)
                					tmp = new String(s);
                				//2番目以降
                				else{
                					//違う項目が来たらファイル書き込み
                					if(!tmp.equals(s)){
                						//平均(int)スループット
                						if(flag==1)
                							bw2.write(tmp + " " + (float)tmpi/count);
                						//平均(float)遅延
                						else
                							bw2.write(tmp + " " + tmpf/count);
                		                bw2.newLine();
                		                bw2.newLine();
                						tmp = new String(s);
                						count = 0;
                						tmpi=0;
                						tmpf=0.0f;
                						//flag=0;
                					}
                					flag = 0;
                				}
                			}
                		}
                		
                		//if(!tmp.equals(s))に引っかからない，つまり同じ項目の間は足し続ける
                		else{
                			//sumスループット
                			if(isNumber(s)){
                				tmpi+=Integer.parseInt(s);
                				System.out.println(tmpi);
                				count++;
                				flag=1;
                			}      
                			//sum遅延
                			else if(isFloat(s)){
                				tmpf+=Float.parseFloat(s);
                				System.out.println(tmpf);
                				count++;
                				flag=2;
                			} 
                		}
                			
                		
            		}
                }
                i=0;
                j=0;
                //for(int i = 0; i < 6; i++)
                //	bw.write(strAry[i] + " ");
                bw.newLine();

                //break;
            }
            
            //最後のを出力
            if(flag==1)
				bw2.write(tmp + " " + (float)tmpi/count);
			else
				bw2.write(tmp + " " + tmpf/count);
            bw2.newLine();
            
            bw.flush();
            bw2.flush();
            br.close();
            in.close();
            out.close();
            out2.close();
        } catch (IOException e) {
            System.out.println(e);
        }

	}

}
