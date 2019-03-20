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

    @OneToMany
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
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        builder.append("r=").append(colour.getRed()).append(", ");
        builder.append("g=").append(colour.getGreen()).append(", ");
        builder.append("b=").append(colour.getBlue());
        builder.append(" ]");
        return builder.toString();
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

    public List<Composition> getComposition() {
        return composition;
    }

    public String getStrComposition() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        for (Composition c : composition) {
            builder.append(c.getPercentage()).append(" ").append(c.getMaterial());
            if(composition.iterator().hasNext()){
                builder.append(", ");
            }
        }
        builder.append(" }");
        return builder.toString();
    }

    public void setComposition(List<Composition> composition) {
        this.composition = composition;
    }

    @Override
    public String toString() {
        return String.format("Socks { id: %d, colour: %s, size: %.1f, type: %s, manufacture: %s }",
                this.getId(),
                this.getStrColour(),
                this.getSize(),
                this.getType().getName(),
                this.getManufacture().getName());
    }
}
