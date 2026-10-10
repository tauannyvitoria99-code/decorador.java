public abstract class TextoDecorador implements Texto {
    protected Texto texto;

    public TextoDecorador(Texto texto) {
        this.texto = texto;
    }

    @Override
    public String render() {
        return texto.render();
    }
}
