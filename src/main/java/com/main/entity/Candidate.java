package com.main.entity;

import java.util.Base64;

import jakarta.persistence.*;


@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String party;
    private String description;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] logo;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    private int voteCount; // New field to track the votes

    // Getters and setters for all fields, including the new voteCount field
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getPhotoBase64() {
        return Base64.getEncoder().encodeToString(photo);
    }

    public String getLogoBase64() {
        return Base64.getEncoder().encodeToString(logo);
    }
}
