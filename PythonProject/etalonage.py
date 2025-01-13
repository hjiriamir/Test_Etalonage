import re


def somme_valeurs_etalonnage_fichier(nom_fichier):
    somme_totale = 0

    try:
        # Ouvrir le fichier en mode lecture
        with open(nom_fichier, 'r', encoding='utf-8') as fichier:
            for ligne in fichier:
                # Supprimer les espaces ou retours à la ligne
                ligne = ligne.replace("\n", "").replace("\r", "").strip()

                # Trouver tous les chiffres dans la ligne
                chiffres = re.findall(r'\d', ligne)

                if len(chiffres) >= 1:
                    # Si seulement un chiffre est trouvé, il est à la fois premier et dernier
                    premier = chiffres[0]
                    dernier = chiffres[-1] if len(chiffres) > 1 else premier  # Si un seul chiffre, il est utilisé deux fois

                    # Former le nombre et l'ajouter à la somme
                    valeur = int(premier + dernier)
                    somme_totale += valeur

    except FileNotFoundError:
        print(f"Erreur : Le fichier '{nom_fichier}' est introuvable.")
    except Exception as e:
        print(f"Erreur : {e}")

    return somme_totale


if __name__ == "__main__":
    # Nom du fichier à analyser (doit être dans le même dossier que le script)
    nom_fichier = "document.txt"

    # Calculer et afficher la somme totale
    somme = somme_valeurs_etalonnage_fichier(nom_fichier)
    print(f"Somme totale des valeurs d'étalonnage : {somme}")
