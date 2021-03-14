package com.ditraacademy.travelagency.core.Chambre.Chambre;

import com.ditraacademy.travelagency.core.Chambre.CategorieChambre.CategorieChambre;
import com.ditraacademy.travelagency.core.Chambre.TypeChambre.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChambreRepository extends JpaRepository<Chambre,Integer> {

    Optional<Chambre> findByCategorieAndType(CategorieChambre categorieChambre, TypeChambre typeChambre);
}
