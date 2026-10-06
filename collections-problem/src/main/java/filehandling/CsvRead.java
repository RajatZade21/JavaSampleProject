package filehandling;


import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Tunnel{
    public String hostname;
    public String  interfaceDescription;
    public String   targetHostname;
    public String targetInterfaceDescription;
    public String encryptionType;

    public Tunnel(String hostname,String interfaceDescription,String targetHostname,String targetInterfaceDescription,String encryptionType ){
        this.hostname = hostname;
        this.interfaceDescription = interfaceDescription;
        this.targetHostname = targetHostname;
        this.targetInterfaceDescription = targetInterfaceDescription;
        this.encryptionType = encryptionType;
    }
}

public class CsvRead {

    public static void main(String[] args){
        List<Tunnel> list = new ArrayList<>();

        try(BufferedReader reader  = Files.newBufferedReader(Paths.get("C:\\Users\\Rajat\\Desktop\\testFile1.csv"))){

            reader.readLine();

            String line;

            while((line = reader.readLine()) != null){

                String[] columns = line.split(",");

                if(columns.length != 5){
                    continue;
                }

                Tunnel tunnel = new Tunnel(
                        columns[0],
                        columns[1],
                        columns[2],
                        columns[3],
                        columns[4]
                );
                list.add(tunnel);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getCause());
        }

        HashMap<String, Integer> resultMap = getResultMap(list);

        System.out.println(resultMap);
    }


    private static HashMap<String, Integer> getResultMap(List<Tunnel> list) {
        HashMap<String,Integer> resultMap = new HashMap<>();

        for(int i = 0; i < list.size() - 1; i++){
            Tunnel element =  list.get(i);
            Tunnel element2 =  list.get(i + 1);
            if(element.hostname.equals(element2.targetHostname) && element.interfaceDescription.equals(element2.targetInterfaceDescription) && element.encryptionType.equals(element2.encryptionType)){
                resultMap.put(element.encryptionType, resultMap.getOrDefault(element.encryptionType,0) + 1);
            }
        }
        return resultMap;
    }
}
