public class Pokemon {
    private String name;
    private int hp;
    private int atk;
    Skill skill;
    private int level;
    private int speed;
    private int rateAtk;
    private int rateHp;

    public Pokemon(String name, int hp, int atk, Skill skill, int level, int speed, int rateAtk, int rateHp) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.skill = skill;
        this.level = level;
        this.speed = speed;
        this.rateAtk = rateAtk;
        this.rateHp = rateHp;
    }

    public void levelUP(int up) {
        this.setHp(this.hp + up * this.rateHp);
        this.setAtk(this.atk + up * this.rateAtk);
    }

    public void learnSkill(Skill skill) {
        this.skill = skill;
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return this.atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Skill getSkill() {
        return this.skill;
    }

    public int getSkillCd() {
        return this.skill.getSkillCd();
    }

    public int getSkillAtk() {
        return this.skill.getSkillAtk();
    }

    public Pokemon copy() {
        return new Pokemon(name, hp, atk,new Skill(skill.getSkillName(),skill.getSkillCd(),skill.getSkillAtk()), level, speed, rateAtk, rateHp);
    }

    @Override  // TODO: Remove debug `toString`
    public String toString() {
        return "Pokemon{" +
                "name='" + this.name + '\'' +
                ", hp=" + this.hp +
                ", atk=" + this.atk +
                ", skill=" + this.skill +
                ", speed=" + this.speed +
                '}';
    }
}
