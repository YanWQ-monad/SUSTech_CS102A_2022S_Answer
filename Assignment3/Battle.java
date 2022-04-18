import java.util.Iterator;
import java.util.NoSuchElementException;

public class Battle {
    public static final int ROUND_LIMIT = 50;

    public static Player tatakai(Player p1, Player p2) {
        Iterator<Pokemon> iter1 = p1.getPokemons().iterator();
        Iterator<Pokemon> iter2 = p2.getPokemons().iterator();
        FightingPokemon pokemon1 = new FightingPokemon(iter1.next());
        FightingPokemon pokemon2 = new FightingPokemon(iter2.next());

        try {
            for (int i = 1; i <= ROUND_LIMIT; i++) {
                pokemon1.tick();
                pokemon2.tick();

                FightingPokemon first = pokemon1.getSpeed() >= pokemon2.getSpeed() ? pokemon1 : pokemon2;
                FightingPokemon last = pokemon1.getSpeed() >= pokemon2.getSpeed() ? pokemon2 : pokemon1;

//                System.out.printf("%d: %s attack %s (HP %d - %d => %d)%s\n", i, first.name, last.name, last.getHp(), first.getAtk(), last.getHp() - first.getAtk(), first.isSkill() ? ", with skill" : "");
                last.hurt(first.getAtk());
                if (last.isAlive()) {
//                    System.out.printf("%d: %s attack %s (HP %d - %d => %d)%s\n", i, last.name, first.name, first.getHp(), last.getAtk(), first.getHp() - last.getAtk(), last.isSkill() ? ", with skill" : "");
                    first.hurt(last.getAtk());
                }

                if (!pokemon1.isAlive()) {
//                    System.out.printf("%d: %s dead\n", i, pokemon1.name);
                    pokemon1 = new FightingPokemon(iter1.next());
                    pokemon2.resetCd();
                }
                if (!pokemon2.isAlive()) {
//                    System.out.printf("%d: %s dead\n", i, pokemon2.name);
                    pokemon2 = new FightingPokemon(iter2.next());
                    pokemon1.resetCd();
                }
            }
        }
        catch (NoSuchElementException ignored) {}

        if (!pokemon1.isAlive())
            return p2;
        if (!pokemon2.isAlive())
            return p1;
        return null;
    }

    public static void main(String[] args) {
        Player player1 = new Player(new Mail("1@mail.sustech.edu.cn"), new PhoneNumber("1"), "1");
        Player player2 = new Player(new Mail("2@mail.sustech.edu.cn"), new PhoneNumber("2"), "2");
        Skill skill1 = new Skill("skill1", 2, 3);
        Skill skill2 = new Skill("skill2", 3, 2);
        Pokemon pokemon1 = new Pokemon("pokemon1", 10, 1, skill1, 1, 1, 3, 3);
        Pokemon pokemon2 = new Pokemon("pokemon2", 10, 1, skill2, 1, 2, 3, 3);
        player1.addPokemon(pokemon1);
        player2.addPokemon(pokemon2);

        Player winner = Battle.tatakai(player1, player2);
        System.out.println(winner);
        System.out.println(winner.getPhoneNumber().phoneNumber);
    }
}

class FightingPokemon {
    final String name;  // TODO: Remove debug property
    private final int atk;
    private final int speed;
    private final Skill skill;
    private int hp;
    private int cd;

    FightingPokemon(Pokemon pokemon) {
        this.name = pokemon.getName();
        this.atk = pokemon.getAtk();
        this.speed = pokemon.getSpeed();
        this.skill = pokemon.getSkill();
        this.hp = pokemon.getHp();
        resetCd();
    }

    public void resetCd() {
        this.cd = this.skill == null ? Battle.ROUND_LIMIT + 1 : this.skill.getSkillCd();
    }

    public void tick() {
        if (this.cd == 0)
            this.cd = this.skill.getSkillCd();
        this.cd--;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void hurt(int damage) {
        this.hp -= damage;
    }

    public int getAtk() {
        return this.cd == 0 ? this.skill.getSkillAtk() : this.atk;
    }

    public int getSpeed() {
        return this.speed;
    }

    // TODO: Remove debug method
    public boolean isSkill() {
        return this.cd == 0;
    }

    // TODO: Remove debug method
    public int getHp() {
        return hp;
    }
}
