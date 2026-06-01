package model;

public class Skill {
    private int id;
    private String skillName;

    public Skill(int id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }
}
