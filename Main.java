public class Main {
    public static void main(String[] args) {
        Huffman codificador = new Huffman("01");
        String codigo = codificador.codificar();
        codificador.exibirFrequencias();
        codificador.exibirCodigos();
        System.out.println("Codificado: " + codigo);
        System.out.println("Decodificado: " + codificador.decodificar(codigo));
    }
}