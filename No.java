public class No implements Comparable<No>
{
    No esq, dir;
    int frequencia;

    public No(No esq, No dir)
    {
        this.frequencia = esq.frequencia + dir.frequencia;
        this.esq = esq;
        this.dir = dir;
    }

    public No(int frequencia)
    {
        this.frequencia = frequencia;
        this.esq = this.dir = null;
    }

    public int compareTo(No other)
    {
        return this.frequencia - other.frequencia;
    }
}