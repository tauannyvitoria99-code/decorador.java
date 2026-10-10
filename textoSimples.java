public class TextoSimples implements Texto {
    private final String conteudo;

    public TextoSimples(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String render() {
        return conteudo;
    }
}
