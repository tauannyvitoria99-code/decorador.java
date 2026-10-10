public class ItalicoDecorador extends TextoDecorador {

    public ItalicoDecorador(Texto texto) {
        super(texto);
    }

    @Override
    public String render() {
        return "<i>" + super.render() + "</i>";
    }
}
