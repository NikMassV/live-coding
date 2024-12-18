package neetcode.backtracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordSearch {

    @Test
    public void test() {
        char[][] board = {
                {'A', 'B', 'C', 'D'},
                {'S', 'A', 'A', 'T'},
                {'A', 'C', 'A', 'E'}
        };
        Solution solution = new Solution();
        assertTrue(solution.exist(board, "CAT"));
        assertFalse(solution.exist(board, "BAT"));
    }

    private class Solution {

        private int ROWS, COLS;

        public boolean exist(char[][] board, String word) {
            ROWS = board.length;
            COLS = board[0].length;

            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, String word, int r, int c, int i) {
            if (i == word.length()) {
                return true;
            }
            if (r < 0 || c < 0 || r >= ROWS || c >= COLS ||
                    board[r][c] != word.charAt(i) || board[r][c] == '#') {
                return false;
            }
            board[r][c] = '#';
            boolean res = dfs(board, word, r + 1, c, i + 1) ||
                    dfs(board, word, r - 1, c, i + 1) ||
                    dfs(board, word, r, c + 1, i + 1) ||
                    dfs(board, word, r, c - 1, i + 1);
            board[r][c] = word.charAt(i);
            return res;
        }
    }
}
