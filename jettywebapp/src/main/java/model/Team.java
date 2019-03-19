package model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Team {

    private String department;
    private int floor;
    private String name;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team " + name);
        sb.append(" is located on the " + floor);
        sb.append(" and is part of the " + department + " department.");
        return sb.toString();
    }
}
