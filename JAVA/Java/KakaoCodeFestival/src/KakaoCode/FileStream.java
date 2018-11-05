import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStream {
    private static final Scanner scan = new Scanner(System.in);
    
    public static void main(String args[]) throws Exception {
        // read the string filename
        String filename;
        ArrayList<String> list = new ArrayList<String>();
        filename = scan.nextLine();
        String data="";
        BufferedReader br = null;
        File inFile = new File(filename);
        try{
            br= new BufferedReader(new FileReader(inFile));
            String line;
            while((line=br.readLine())!=null){
                data = line+"\n"+data;
            }
        }catch(FileNotFoundException e){
            
        }catch(IOException e){
            
        }finally{
            if(br != null) try {br.close();} catch(IOException e){}
        }
        String[] resource=data.split("\n");
        for(int i=0;i<resource.length;i++){
            if (resource[i].split("\"")[2].contains("200 ")&&resource[i].split("\"")[1].contains(".gif")){
                list.add(resource[i].split("\"")[1]);
            }
        }
        for(int i=0;i<list.size();i++)
        System.out.println(list.get(i));
    }
}