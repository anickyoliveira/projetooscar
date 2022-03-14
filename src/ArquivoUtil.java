import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArquivoUtil
{
    public Stream<String> obterLinhas(String filePath) {

        try {
            return Files.lines(Paths.get(filePath));
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Pessoa> converterLinhasEmListaDePessoas(Stream<String> linhas) {
            return linhas
                    .skip(1)
                    .map(Pessoa::fromline)
                    .collect(Collectors.toList());
    }
}