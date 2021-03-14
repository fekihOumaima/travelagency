package com.ditraacademy.travelagency.core.destination;


import com.ditraacademy.travelagency.core.voyage.Voyage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@Entity

@Getter
@Setter
@NoArgsConstructor
@Where(clause= "deleted = false")
@SQLDelete(sql ="update destination set deleted = true where id = ?")

public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String description;
    private boolean deleted =false;
    @JsonIgnore
    @OneToMany(mappedBy = "destination",cascade = {CascadeType.REMOVE})
    private List<Voyage> voyages;

}
