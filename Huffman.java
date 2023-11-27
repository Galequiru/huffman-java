import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman 
{
    private No raiz;
    private String texto;
    private Map<Character, Integer> frequencias;
    private Map<Character, String> codigos;
    
    public Huffman(String texto)
    {
        this.texto = texto;
        identificarFrequencias();
        this.codigos = new HashMap<>();
    }

    public String codificar() 
    {
        gerarArvoreHuffman();
        gerarCodigoHuffman(raiz, "");
        return codificarTexto();
    }

    public String decodificar(String textoCodificado)
    {
        String ret = "";
        No atual = raiz;
        for (char caracter : textoCodificado.toCharArray())
        {
            atual = caracter=='0'? atual.esq : atual.dir;
            if (atual instanceof Folha)
            {
                ret += ((Folha)atual).caracter;
                atual = raiz;
            }
        }
        return ret;
    }

    public void exibirFrequencias() 
    {
        System.out.println("-----Frequencias-----");
        frequencias.forEach((caracter, frequencia)->{
            System.out.println(caracter + ": " + frequencia);
        });
    }

    public void exibirCodigos() 
    {
        System.out.println("-----Codigos-----");
        codigos.forEach((caracter, codigo)->{
            System.out.println(caracter + ": " + codigo);
        });
    }

    private void gerarArvoreHuffman() 
    {
        Queue<No> fila = new PriorityQueue<>(); //fila ordenada
        frequencias.forEach((caracter, frequencia) -> {
            fila.add(new Folha(caracter, frequencia));
        });
        while(fila.size() > 1) //vao sendo formados Nos com os Nos na lista ate que so sobre um (a raiz)
        {
            fila.add(new No(fila.poll(), fila.poll()));
        }
        raiz = fila.poll();
    }

    private String codificarTexto() 
    {
        String ret = "";
        for (char caracter : texto.toCharArray())
        {
            ret += codigos.get(caracter);
        }
        return ret;
    }

    private void identificarFrequencias()
    {
        this.frequencias = new HashMap<>();
        for(char caracter : texto.toCharArray())
        {
            Integer frequencia = frequencias.get(caracter);
            frequencias.put(caracter, frequencia == null? 1: frequencia+1);
        }
    }

    private void gerarCodigoHuffman(No no, String codigo) 
    {
        if (no instanceof Folha)
        {
            codigos.put(((Folha)no).caracter, codigo);
            return;
        }
        gerarCodigoHuffman(no.esq, codigo.concat("0"));
        gerarCodigoHuffman(no.dir, codigo.concat("1"));
    }
}
