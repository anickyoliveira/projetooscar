

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Aplicacao {

        public static void main(String args[])
        {
                ArquivoUtil au = new ArquivoUtil();

                // Transfere o conteudo dos arquivos para a memoria em Stream<String>
                Stream<String> atrizesStream = au.obterLinhas("C:\\Users\\user\\projetooscar\\src\\Atrizes.csv");
                Stream<String> atoresStream = au.obterLinhas("C:\\Users\\user\\projetooscar\\src\\Atores.csv");

                // Estrutura dados dos arquivos (caracteres em classes manipulaveis)
                List<Pessoa> atrizes = au.converterLinhasEmListaDePessoas(atrizesStream);
                List<Pessoa> atores = au.converterLinhasEmListaDePessoas(atoresStream);

                // Quem foi o ator mais jovem a ganhar um Oscar?
                System.out.println(
                        "Ator mais jovem: " +
                        atores.stream().min(
                                Comparator.comparing(
                                        Pessoa::getIdade
                                )
                        ).get().getNome()
                );

                // Quem foi a atriz que mais vezes foi premiada?
                Map<String, Long> atrizesNroPremios =
                        atrizes.stream().collect(
                                Collectors.groupingBy(
                                        p -> p.getNome(),
                                        Collectors.counting()
                                )
                        );

                long maxPremiosAtrizes = atrizesNroPremios.values().stream().max(Comparator.naturalOrder()).get();

                List<String> atrizesMaisPremiadas =  atrizesNroPremios.entrySet().stream()
                        .filter(e -> e.getValue() == maxPremiosAtrizes)
                        .map(Map.Entry::getKey)
                        .collect(
                                Collectors.toList()
                        );

                System.out.println(
                        "Atrizs mais vezes premiada: " +
                        Arrays.toString(atrizesMaisPremiadas.toArray())
                );

                // Qual atriz entre 20 e 30 anos que mais vezes foi vencedora?
                Map<String, Long> atrizesEntre20e30NroPremios = atrizes.stream()
                        .filter(c -> c.getIdade() > 20 && c.getIdade() < 30)
                        .collect(
                                Collectors.groupingBy(
                                        p -> p.getNome(),
                                        Collectors.counting()
                                )
                        );

                long maxPremioAtraizesEntre20e30 = atrizesEntre20e30NroPremios.values().stream().max(Comparator.naturalOrder()).get();

                List<String> atrizesMaisPremiadasEntre20e30 =  atrizesEntre20e30NroPremios.entrySet().stream()
                        .filter(e -> e.getValue() == maxPremioAtraizesEntre20e30)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

                System.out.println(
                        "Atrizs mais vezes premiada entre 20 e 30: " +
                                Arrays.toString(atrizesMaisPremiadasEntre20e30.toArray())
                );

                // Quais atores ou atrizes receberam mais de um Oscar? Elabore uma única estrutura contendo atores e atrizes.
                List<Pessoa> todoMundo = new ArrayList<Pessoa>();
                todoMundo.addAll(atrizes);
                todoMundo.addAll(atores);

                Map<String, Long> todoMundoOscars = todoMundo.stream()
                        .collect(
                                Collectors.groupingBy(
                                        p -> p.getNome(),
                                        Collectors.counting()
                                )
                        );

                List<String> todoMundoMaisPremiado =  todoMundoOscars.entrySet().stream()
                        .filter(e -> e.getValue() > 1)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

                System.out.println(
                        "Todo mundo mais premiado: " +
                                Arrays.toString(todoMundoMaisPremiado.toArray())
                );

                // Quando informado o nome de um ator ou atriz,
                //  dê um resumo de quantos prêmios ele/ela recebeu e
                //  liste ano, idade e nome de cada filme pelo qual foi premiado(a).
                String givenName = "Gary Cooper";

                Long nameNroPremios = todoMundoOscars.get(givenName);

                List<Pessoa> premiosPessoa = todoMundo.stream().filter(
                                p -> givenName.equals(p.getNome())
                        ).collect(
                                Collectors.toList()
                        );

                System.out.println(
                        "Pessoa buscada: " + givenName + " \n" +
                        "Nro Premios: " + nameNroPremios + " \n" +
                        "Encontradas: "  + Arrays.toString(premiosPessoa.toArray())
                );
        }
}
