package com.ditraacademy.travelagency.core.Chambre.TypeChambre;

import com.ditraacademy.travelagency.core.Chambre.Chambre.Chambre;
import com.ditraacademy.travelagency.utils.Audible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TypeChambre extends Audible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categorie;
    private String description ;

}
