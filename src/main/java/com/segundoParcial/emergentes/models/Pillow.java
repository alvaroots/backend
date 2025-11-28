package com.segundoParcial.emergentes.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="pillow")
public class Pillow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pillow_code")
    private Long pillowCode;

    private String image;
    private String color;
    private double price;
    private double length;
    private double width;

    @ManyToOne
    @JoinColumn(name = "bed_code")
    @JsonBackReference
    private Bed bed;

    public Pillow() {
    }

    public Pillow(Long pillowCode, String image, String color, double price, double length, double width, Bed bed) {
        this.pillowCode = pillowCode;
        this.image = image;
        this.color = color;
        this.price = price;
        this.length = length;
        this.width = width;
        this.bed = bed;
    }

    public Long getPillowCode() {
        return pillowCode;
    }

    public void setPillowCode(Long pillowCode) {
        this.pillowCode = pillowCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }
}
