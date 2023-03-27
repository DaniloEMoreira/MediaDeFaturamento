
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Danilo
 */


public class MediaFaturamento {
   public static void main(String[] args) throws FileNotFoundException, IOException, ParseException{
       
        String arquivo = "C:\\Users\\Danilo\\Documents\\NetBeansProjects\\MediaFaturamento\\src\\mediafaturamento\\dados.json";
        // cria um objeto JSONParser
        JSONParser jsonParser = new JSONParser();

        List<Map<Integer, Float>> faturamentoJson = (List) jsonParser.parse(new FileReader(arquivo));
        
        int contador = 0;
        int contadorAcimaMedia = 0;
        int diaMenor = 0;
        int diaMaior = 0;
        float valorMenor = 0;
        float valorMaior = 0;
        float somaTotal = 0;
        
        List<DiaValor> faturamentoObj = new ArrayList<DiaValor>();
        
        for(Map diaF : faturamentoJson){
            int diaObj = Integer.parseInt(diaF.get("dia").toString());
            float valorObj = Float.parseFloat(diaF.get("valor").toString());
            
            DiaValor dia = new DiaValor(diaObj,valorObj);
            
            faturamentoObj.add(dia);
        }
        
        for(DiaValor dia : faturamentoObj){
            if(dia.getValor() > 0){
                if(valorMenor == 0){
                    valorMenor = dia.getValor();
                    diaMenor = dia.getDia();
                }else if (valorMenor > dia.getValor()){
                    valorMenor = dia.getValor();
                    diaMenor = dia.getDia();
                }
                if(valorMenor == 0){
                    valorMaior = dia.getValor();
                    diaMaior = dia.getDia();
                }else if (valorMaior < dia.getValor()){
                    valorMaior = dia.getValor();
                    diaMaior = dia.getDia();
                }
                contador++;
            }
            somaTotal+= dia.getValor();
        }
        
        float mediaRendimento = (somaTotal/contador);
        
        for(DiaValor dia : faturamentoObj){
            if(dia.getValor() > mediaRendimento){
                contadorAcimaMedia++;
            }
        }

        System.out.println("O dia: "+diaMenor+" teve o menor faturamento, de: R$"+valorMenor);
        System.out.println("O dia: "+diaMaior+" teve o maior faturamento, de: R$"+valorMaior);
        System.out.println("O Faturamento Total foi de: R$" + somaTotal);
        System.out.println("Media de faturamento diario: R$" + (somaTotal/contador));
        System.out.println(contadorAcimaMedia+" Dias tiveram um rendimento Acima da Media");
    }
}