package com.ditraacademy.travelagency.core.Chambre.Chambre;

import com.ditraacademy.travelagency.core.Chambre.CategorieChambre.CategorieChambre;
import com.ditraacademy.travelagency.core.Chambre.TypeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.Hotel.Hotel;
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
@SQLDelete(sql ="update chambre set deleted = true where id = ?")
@Table(uniqueConstraints =  {
        @UniqueConstraint(columnNames = {"categorie_id", "type_id"})
})

public class Chambre extends Audible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private CategorieChambre categorie;
    @ManyToOne
    private TypeChambre type ;
    @JsonIgnore
    private Boolean deleted = false;

    @JsonIgnore
    @ManyToMany(mappedBy = "chambres")
    private List<Hotel> hotels;
}
