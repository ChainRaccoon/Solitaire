import DeckOfCards.CartaInglesa;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Modela un monículo donde se ponen las cartas
 * de por valor, alternando el color.
 *
 * @author Cecilia M. Curlango
 * @version 2025
 */
public class TableauDeck {
    ArrayList<CartaInglesa> cartas = new ArrayList<>();

    /**
     * Carga las cartas iniciales y voltea la última.
     * @param cartas iniciales
     */
    public void inicializar(ArrayList<CartaInglesa> cartas) {
        this.cartas = cartas;
        // voltear la última carta recibida
        CartaInglesa ultima = cartas.getLast();
        ultima.makeFaceUp();
    }

    /**
     * Remove cards starting from the one with a specified value.
     * @param value of starting card to remove
     * @return removed cards or empty ArrayList if it is not possible to remove.
     */
    public ArrayList<CartaInglesa> removeStartingAt(int value) {
        ArrayList<CartaInglesa> removed = new ArrayList<>();
        Iterator<CartaInglesa> iterator = cartas.iterator();
        while (iterator.hasNext()) {
            CartaInglesa next = iterator.next();
            if (next.isFaceup()) {
                if (next.getValor() <= value) {
                    removed.add(next);
                    iterator.remove();
                }
            }
        }
        return removed;
    }
    /**
     * Agrega una carta volteada al montículo. Sólo la agrega si:
     *  A) es la siguiente carta en la secuencia
     *  B) está vacio y la carta es un Rey
     *
     * @param carta que se intenta almancenar
     * @return true si se pudo guardar la carta, false si no
     */
    public boolean agregarCarta(CartaInglesa carta) {
        boolean agregado = false;
        if (cartas.isEmpty()) {
            // la agrega sólo si es un rey
            if (carta.getValor()==13) {
                carta.makeFaceUp();
                cartas.add(carta);
                agregado = true;
            }
        } else {
            CartaInglesa ultima = cartas.getLast();
            // se agrega si es del color alterno a la última
            if (!ultima.getColor().equals(carta.getColor())) {
                // y es la siguiente en la secuencia
                if (ultima.getValor()==carta.getValor()+1) {
                    carta.makeFaceUp();
                    cartas.add(carta);
                    agregado = true;
                }
            }
        }
        return agregado;
    }

    /**
     * Obtener la última carta del montículo sin removerla
     *
     * @return la carta que está al final, null si estaba vacio
     */
    CartaInglesa verUltimaCarta() {
        CartaInglesa ultimaCarta = null;
        if (!cartas.isEmpty()) {
            ultimaCarta = cartas.getLast();
        }
        return ultimaCarta;
    }
    /**
     * Remover la última carta del montículo.
     *
     * @return la carta que removió, null si estaba vacio
     */
    CartaInglesa removerUltimaCarta() {
        CartaInglesa ultimaCarta = null;
        if (!cartas.isEmpty()) {
            ultimaCarta = cartas.getLast();
            cartas.remove(ultimaCarta);
            if (!cartas.isEmpty()) {
                // voltea la siguiente carta del tableau
                cartas.getLast().makeFaceUp();
            }
        }
        return ultimaCarta;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (cartas.isEmpty()) {
            builder.append("---");
        } else {
            for (CartaInglesa carta : cartas) {
                builder.append(carta.toString());
            }
        }
        return builder.toString();
    }

    /**
     * Agrega un bloque de cartas al Tableau si la primera carta de las cartas recibidas
     * es de color alterno a la última carta del tableau y también es la siguiente.
     * @param cartasRecibidas
     * @return true si se pudo agregar el bloque, false si no
     */
    public boolean agregarBloqueDeCartas(ArrayList<CartaInglesa> cartasRecibidas) {
        boolean resultado = false;

        if (!cartasRecibidas.isEmpty()) {
            CartaInglesa primera = cartasRecibidas.getFirst();
            if (isEmpty()) {
                // si no hay cartas en el tableau, la carta que se trata de acomodar
                // debe ser Rey
                if (primera.getValor()==13) {
                    cartas.addAll(cartasRecibidas);
                    resultado = true;
                }
            } else {
                CartaInglesa ultima = cartas.getLast();
                // se agrega si es del color alterno a la última
                if (!ultima.getColor().equals(primera.getColor())) {
                    // y es la siguiente en la secuencia
                    if (ultima.getValor()==primera.getValor()+1) {
                        cartas.addAll(cartasRecibidas);
                        resultado = true;
                    }
                }
            }

        }

        return resultado;
    }

    /**
     * Indica si está vacío  el Tableau
     * @return true si no tiene cartas restantes, false si tiene cartas.
     */
    public boolean isEmpty() {
        return cartas.isEmpty();
    }
}