package com.ditraacademy.travelagency.core.Hotel;

import com.ditraacademy.travelagency.core.Chambre.Chambre.Chambre;
import com.ditraacademy.travelagency.utils.Audible;
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
@SQLDelete(sql ="update voyage set deleted = true where id = ?")
public class Hotel extends Audible {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private Integer etoile;
    private String description;
    private String adress ;
    private Integer telephone;
    @JsonIgnore
    private Boolean deleted = false;

    @ManyToMany
    @JoinTable(name = "hotel_chambre",
                joinColumns = {@JoinColumn (name= "hotel_id")},
                inverseJoinColumns = {@JoinColumn( name = "chambre_id")})
    private List<Chambre> chambres;


}
