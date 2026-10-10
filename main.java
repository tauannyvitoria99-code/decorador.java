public class Main {
    public static void main(String[] args) {
        Texto base = new TextoSimples("Olá, Decorator!");

        System.out.println("Simples:              " + base.render());

        System.out.println("Negrito:              "
                + new NegritoDecorador(base).render());

        System.out.println("Itálico:              "
                + new ItalicoDecorador(base).render());

        System.out.println("Sublinhado:           "
                + new SublinhadoDecorador(base).render());

        // Mesma combinação, ordens diferentes
        Texto negritoItalico = new ItalicoDecorador(new NegritoDecorador(base));
        Texto italicoNegrito = new NegritoDecorador(new ItalicoDecorador(base));
        System.out.println("Negrito -> Itálico:   " + negritoItalico.render());
        System.out.println("Itálico -> Negrito:   " + italicoNegrito.render());

        // Os três juntos
        Texto todos = new SublinhadoDecorador(
                new ItalicoDecorador(
                        new NegritoDecorador(base)));
        System.out.println("Os três:              " + todos.render());

        // Repetição (o enunciado permite "qualquer quantidade")
        Texto duplo = new NegritoDecorador(new NegritoDecorador(base));
        System.out.println("Negrito duplo:        " + duplo.render());
    }
}
