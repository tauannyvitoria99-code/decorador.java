public class SublinhadoDecorador extends TextoDecorador {

    public SublinhadoDecorador(Texto texto) {
        super(texto);
    }

    @Override
    public String render() {
        return "<u>" + super.render() + "</u>";
    }
}
