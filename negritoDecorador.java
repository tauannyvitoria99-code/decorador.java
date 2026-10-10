public class NegritoDecorador extends TextoDecorador {

    public NegritoDecorador(Texto texto) {
        super(texto);
    }

    @Override
    public String render() {
        return "<b>" + super.render() + "</b>";
    }
}
