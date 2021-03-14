package com.ditraacademy.travelagency.core.Chambre.CategorieChambre;

import com.ditraacademy.travelagency.core.Chambre.Chambre.Chambre;
import com.ditraacademy.travelagency.core.voyage.Voyage;
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
public class CategorieChambre extends Audible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String description ;

}
