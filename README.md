# Atividade – Padrão Decorator (Editor de Texto)

## Como executar
```
cd src
javac *.java
java Main
```

## Perguntas para discussão

### 1. Quantas classes com herança? E com Decorator?
**Só herança:** cada combinação vira uma classe.
- Sem considerar ordem nem repetição: 2³ = 8 combinações (TextoSimples, N, I, S, NI, NS, IS, NIS) → **8 classes**.
- Considerando a ordem (sem repetir): 1 + 3 + 6 + 6 = 16 classes.
- Permitindo repetição ("qualquer quantidade"): **infinitas** – inviável.
- Cada novo estilo dobra o número de classes (2ⁿ): explosão combinatória.

**Com Decorator:** **6 tipos**, independentemente das combinações:
interface `Texto`, `TextoSimples`, `TextoDecorador` (abstrata) e 3 decoradores.
Um novo estilo adiciona apenas **1 classe** (crescimento linear, n).

### 2. Itálico antes ou depois do negrito?
- `new ItalicoDecorador(new NegritoDecorador(t))` → `<i><b>texto</b></i>`
- `new NegritoDecorador(new ItalicoDecorador(t))` → `<b><i>texto</i></b>`

A ordem muda a **estrutura** da string (qual tag fica por fora), mas
**as duas saídas são HTML válido**, pois as tags estão corretamente aninhadas,
e o navegador renderiza ambas do mesmo jeito (negrito + itálico).
Isso acontece porque cada decorador envolve o resultado completo do anterior,
então a tag aberta por último é sempre fechada primeiro.
(Seria inválido algo como `<b><i>texto</b></i>`, o que o Decorator não gera.)

### 3. Como evitar aplicar o mesmo decorador duas vezes?
Algumas opções:
1. **Verificar a cadeia ao decorar:** adicionar um método que percorre os
   decoradores já aplicados e, se o tipo já existir, devolve o próprio objeto.
   ```java
   // em TextoDecorador
   public boolean possui(Class<? extends TextoDecorador> tipo) {
       if (tipo.isInstance(this)) return true;
       return texto instanceof TextoDecorador d && d.possui(tipo);
   }
   // uso
   Texto t = base;
   if (!(t instanceof TextoDecorador d && d.possui(NegritoDecorador.class)))
       t = new NegritoDecorador(t);
   ```
2. **Fábrica/Builder** que mantém um `Set` dos estilos já aplicados e só
   cria o decorador se ele ainda não estiver no conjunto.
3. **Decorador idempotente:** no `render()`, se o texto interno já começa com
   `<b>` e termina com `</b>`, não envolve de novo (solução simples, porém frágil).

A opção 2 costuma ser a mais limpa, pois não "suja" as classes do Decorator.

### 4. Relação com o princípio Aberto/Fechado (OCP)
O sistema está **aberto para extensão** e **fechado para modificação**.
Para adicionar `TachadoDecorador` basta **criar uma nova classe**:
```java
public class TachadoDecorador extends TextoDecorador {
    public TachadoDecorador(Texto texto) { super(texto); }
    @Override
    public String render() { return "<s>" + super.render() + "</s>"; }
}
```
**Nada precisa mudar** em `Texto`, `TextoSimples`, `TextoDecorador`,
nos outros decoradores nem no código cliente existente.
