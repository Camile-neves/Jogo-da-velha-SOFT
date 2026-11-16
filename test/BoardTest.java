import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    private void play(Board board, int player, int line, int column) {
        board.setPosition(new int[]{line, column}, player);
    }

    /*
     *   X | O | X
     *   X | O | O
     *   O | X | .
     * Falta uma casa, mas ninguem mais consegue vencer.
     */
    @Test
    public void deveDetectarEmpateAntesDoTabuleiroCheio() {
        Board board = new Board();
        play(board, 1, 0, 0); // X
        play(board, 2, 0, 1); // O
        play(board, 1, 0, 2); // X
        play(board, 2, 1, 1); // O
        play(board, 1, 1, 0); // X
        play(board, 2, 1, 2); // O
        play(board, 1, 2, 1); // X
        play(board, 2, 2, 0); // O

        assertFalse("O tabuleiro ainda nao esta cheio", board.fullBoard());
        assertEquals(0, board.checkLines());
        assertEquals(0, board.checkColumns());
        assertEquals(0, board.checkDiagonals());
        assertTrue("Deveria ser empate (Velha)", board.isDraw());
    }

    @Test
    public void tabuleiroVazioNaoEEmpate() {
        assertFalse(new Board().isDraw());
    }

    @Test
    public void tabuleiroComLinhaAindaVencivelNaoEEmpate() {
        Board board = new Board();
        play(board, 1, 0, 0); // X
        play(board, 2, 1, 1); // O
        play(board, 1, 0, 1); // X
        assertFalse(board.isDraw());
    }

    @Test
    public void vitoriaNaUltimaJogadaNaoEEmpate() {
        Board board = new Board();
        play(board, 1, 0, 0);
        play(board, 2, 0, 1);
        play(board, 1, 0, 2);
        play(board, 2, 1, 0);
        play(board, 1, 1, 1);
        play(board, 2, 1, 2);
        play(board, 2, 2, 0);
        play(board, 1, 2, 1);
        play(board, 1, 2, 2);

        assertTrue(board.fullBoard());
        assertEquals(-1, board.checkDiagonals());
        assertFalse(board.isDraw());
    }

    @Test
    public void tabuleiroCheioSemVencedorEEmpate() {
        Board board = new Board();
        play(board, 1, 0, 0);
        play(board, 2, 0, 1);
        play(board, 1, 0, 2);
        play(board, 1, 1, 0);
        play(board, 2, 1, 1);
        play(board, 2, 1, 2);
        play(board, 2, 2, 0);
        play(board, 1, 2, 1);
        play(board, 1, 2, 2);

        assertTrue(board.fullBoard());
        assertTrue(board.isDraw());
    }
}