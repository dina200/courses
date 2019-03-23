package org.courses.domain.hbm;

import org.courses.domain.hbm.sqliteconvertion.ColorConverter;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//log4J or slf4j use 'info'

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
    private List<Composition> composition = new ArrayList<>();

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

    public String getStrColour() {
        String builder = "[ " +
                "r=" + colour.getRed() + " " +
                "g=" + colour.getGreen() + " " +
                "b=" + colour.getBlue() +
                " ]";
        return builder;
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
        return composition;
    }

    public String getStrCompositions() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        for (Composition c : composition) {
            builder
                    .append(c)
                    .append(" ");
        }
        builder.append("}");
        return builder.toString();
    }

    public void setCompositions(List<Composition> composition) {
        this.composition = composition;
    }

    public void add(Composition c) {
        if(composition == null){
            composition = new ArrayList<>();
        }
        composition.add(c);
        c.setSocks(this);
    }

    @Override
    public String toString() {
        return String.format("Socks { id: %d, colour: %s, size: %.1f, type: %s, manufacture: %s, composition: %s }",
                this.getId(),
                this.getStrColour(),
                this.getSize(),
                this.getType().getName(),
                this.getManufacture().getName(),
                this.getStrCompositions());
    }
}
