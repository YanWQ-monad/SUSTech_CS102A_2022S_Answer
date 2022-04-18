import java.util.Objects;
import java.util.Random;

// Usage: put your code in Battle2.java and rename your class's name to Battle2

public class BattleAutoTester {
    private static final int N = 2;
    private static final int PLAYER_POKEMON_COUNT = 2;
    private static final int HP_RANGE = 4;
    private static final int ATK_RANGE = 5;
    private static final int SPEED_RANGE = 1;
    private static final int SKILL_CD_RANGE = 3;
    private static final int SKILL_ATK_RANGE = 10;

    public static Player[] generate(long seed, int n) {
        Random rng = new Random(seed);

        Player[] players = new Player[n];

        for (int i = 0; i < n; i++) {
            Player player = new Player(new Mail("player_" + (i + 1) + "@pokemon.me"), "1");

            for (int j = 0; j < PLAYER_POKEMON_COUNT; j++) {
                int skill_cd = rng.nextInt(SKILL_CD_RANGE);
                int skill_atk = rng.nextInt(SKILL_ATK_RANGE) + 1;
                int pokemon_hp = rng.nextInt(HP_RANGE) + 1;
                int pokemon_atk = rng.nextInt(ATK_RANGE) + 1;
                int pokemon_speed = rng.nextInt(SPEED_RANGE) + 1;

                String id = "_p" + (i + 1) + "_" + (j + 1);
                Skill skill = new Skill("skill" + id, skill_cd, skill_atk);
                Pokemon pokemon = new Pokemon("pokemon" + id, pokemon_hp, pokemon_atk, skill, 1, pokemon_speed, 3, 3);
                player.addPokemon(pokemon);
            }

            players[i] = player;
        }

        return players;
    }

    public static Player tatakaiWith(Player[] players, TatakaiFunction func) {
        Player winner = players[0];
        for (int i = 1; i < players.length; i++) {
            winner = func.apply(winner, players[i]);
            if (winner == null)
                return null;
        }
        return winner;
    }

    public static boolean work(long seed) {
        Player[] playersLhs = generate(seed, N);
        Player[] playersRhs = generate(seed, N);

//        System.out.println("======== NEW TESTCASE ========");
//        System.out.println("### My:");
        Player winnerLhs = tatakaiWith(playersLhs, Battle::tatakai);
//        System.out.println("### Std:");
        Player winnerRhs = tatakaiWith(playersRhs, Battle2::tatakai);

        if (!Objects.equals(winnerLhs, winnerRhs)) {
            System.out.printf("Seed: %d\n", seed);

            Player[] players = generate(seed, N);
            for (int i = 0; i < players.length; i++) {
                System.out.printf("Player %d:\n", i + 1);
                for (Pokemon pokemon : players[i].getPokemons()) {
                    System.out.print("  ");
                    System.out.println(pokemon);
                }
            }

//            System.out.println("Battled Pokemon in Std:");
//            for (int i = 0; i < playersRhs.length; i++) {
//                System.out.printf("Player %d:\n", i + 1);
//                for (Pokemon pokemon : playersRhs[i].getPokemons()) {
//                    System.out.print("  ");
//                    System.out.println(pokemon);
//                }
//            }

            System.out.printf("Correct: %s\n", winnerLhs == null ? "null" : winnerLhs.getMail().mail);
            System.out.printf("   Test: %s\n", winnerRhs == null ? "null" : winnerRhs.getMail().mail);

            return false;
        }
        return true;
    }

    @FunctionalInterface
    public interface TatakaiFunction {
        Player apply(Player p1, Player p2);
    }

    public static void main(String[] args) {
        Random rng = new Random();
        for (int i = 0; work(rng.nextLong()); i++) {
            if (i % 1000000 == 0)
                System.out.println(i);
        }
    }
}
