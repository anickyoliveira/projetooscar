public class Pessoa {
    private int idade;
    private String ano;
    private String nome;
    private String filme;
    private int nroFilmes;

    public static Pessoa fromline(String line) {
        String[] values = line.split(";");
        Pessoa p = new Pessoa();
        p.ano = values[1];
        p.idade = Integer.valueOf(values[2].trim());
        p.nome = values[3].trim();
        p.filme = values[4];
        p.nroFilmes = values[4].split(",").length;
        return p;
    }

    public int getIdade() {
        return this.idade;
    }

    public String getNome() {
        return this.nome;
    }


}

