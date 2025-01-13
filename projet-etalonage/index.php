<?php

function sommeValeursEtalonnageFichier($nomFichier) {
    $sommeTotale = 0;

    try {
        // Ouvrir le fichier en mode lecture
        $fichier = fopen($nomFichier, 'r');
        if (!$fichier) {
            throw new Exception("Erreur : Le fichier '$nomFichier' est introuvable.");
        }

        // Parcourir chaque ligne du fichier
        while (($ligne = fgets($fichier)) !== false) {
            // Supprimer les espaces ou retours à la ligne
            $ligne = str_replace(["\n", "\r"], "", trim($ligne));

            // Trouver tous les chiffres dans la ligne
            preg_match_all('/\d/', $ligne, $chiffres);

            if (count($chiffres[0]) >= 1) {
                // Si seulement un chiffre est trouvé, il est à la fois premier et dernier
                $premier = $chiffres[0][0];
                $dernier = (count($chiffres[0]) > 1) ? $chiffres[0][count($chiffres[0]) - 1] : $premier;

                // Former le nombre et l'ajouter à la somme
                $valeur = (int)($premier . $dernier);
                $sommeTotale += $valeur;
            }
        }

        fclose($fichier); // Fermer le fichier après lecture
    } catch (Exception $e) {
        echo $e->getMessage();
    }

    return $sommeTotale;
}

// Nom du fichier à analyser (doit être dans le même dossier que le script)
$nomFichier = 'document.txt';

// Calculer et afficher la somme totale
$somme = sommeValeursEtalonnageFichier($nomFichier);
echo "Somme totale des valeurs d'étalonnage : $somme\n";

?>
