package test_empreinte_carbone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class readEC {
	
	public String readEC() throws IOException {
		//System.out.println("hello1");
        InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("test_empreinte_carbone/TestInsert.json"));
        //System.out.println("hello2");
        BufferedReader br = new BufferedReader(ipsr);
        String outjsonString = "";
        String chaine = "";

        while ((outjsonString = br.readLine()) != null) {
            chaine = chaine + outjsonString;
        }
        return chaine;
    }
	
	
}
