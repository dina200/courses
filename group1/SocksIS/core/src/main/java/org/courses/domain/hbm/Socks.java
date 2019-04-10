package org.courses.domain.hbm;

import org.courses.domain.hbm.sqliteconvertion.ColorConverter;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Socks")
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "size")
    private double size;

    @Column(name = "colour")
    @Convert(converter = ColorConverter.class)
    private Color colour;

    @ManyToOne
    @JoinColumn(name = "manufacture")
    private Manufacture manufacture;

    @ManyToOne
    @JoinColumn(name = "type")
    private Type type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "socks")
    private List<Composition> compositions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Color getColour() {
        return colour;
    }

    private String getStrColour() {
        return "[ " +
                "r=" + colour.getRed() + " " +
                "g=" + colour.getGreen() + " " +
                "b=" + colour.getBlue() +
                " ]";
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    private String getStrCompositions() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        for (Composition c : compositions) {
            builder
                    .append(c)
                    .append(" ");
        }
        builder.append("}");
        return builder.toString();
    }

    public void setCompositions(List<Composition> composition) {
        this.compositions = composition;
    }

    public void add(Composition c) {
        if (compositions == null) {
            compositions = new ArrayList<>();
        }
        compositions.add(c);
        c.setSocks(this);
    }

    @Override
    public String toString() {
        return String.format("Socks { id: %d, colour: %s, size: %.1f, type: %s, manufacture: %s, compositions: %s }",
                this.id,
                this.getStrColour(),
                this.size,
                this.type.getName(),
                this.manufacture.getName(),
                this.getStrCompositions());
    }
}
