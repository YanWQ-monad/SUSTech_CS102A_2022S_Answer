public class Skill {
    private String name;
    private int cd;
    private int atk;

    public Skill(String name, int cd, int atk) {
        if (cd <= 0 || atk <= 0) {
            this.name = "error";
            this.cd = 51;
            this.atk = 0;
        }
        else {
            this.name = name;
            this.cd = cd;
            this.atk = atk;
        }
    }

    public String getSkillName() {
        return this.name;
    }

    public int getCd() {

        return this.cd;
    }

    public int getSkillCd() {
        return this.cd;
    }

    public int getSkillAtk() {
        return this.atk;
    }

    @Override  // TODO: Remove debug `toString`
    public String toString() {
        return "Skill{" +
                "cd=" + this.cd +
                ", atk=" + this.atk +
                '}';
    }
}
