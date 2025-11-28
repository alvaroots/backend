package com.segundoParcial.emergentes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="bed")
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Long code;
    private String image;
    private double length;
    private double width;
    private String material;

    @OneToMany(mappedBy = "bed", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pillow> pillows = new ArrayList<>();

    public Bed() {
    }

    public Bed(Long code, String image, double length, double width, String material, List<Pillow> pillows) {
        this.code = code;
        this.image = image;
        this.length = length;
        this.width = width;
        this.material = material;
        this.pillows = pillows;
    }
    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image=image;
    }
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public List<Pillow> getPillows() {
        return pillows;
    }

    public void setPillows(List<Pillow> pillows) {
        this.pillows = pillows;
    }
}