package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class SimpleSpringProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String nomFichier = "document.txt";
		int sommeTotale = sommeValeursEtalonnageFichier(nomFichier);
		System.out.println("Somme totale des valeurs d'étalonnage : " + sommeTotale);

	}

	private int sommeValeursEtalonnageFichier(String nomFichier) {
		int sommeTotale = 0;

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream(nomFichier), "UTF-8"))) {


			String ligne;
			while ((ligne = br.readLine()) != null) {
				ligne = ligne.trim(); // Supprimer les espaces
				ligne = ligne.replaceAll("[\\s\\n\\r\\t]+", "");
				Pattern pattern = Pattern.compile("\\d");
				Matcher matcher = pattern.matcher(ligne);

				String premier = null;
				String dernier = null;
				while (matcher.find()) {
					if (premier == null) {
						premier = matcher.group();  // Premier chiffre
					}
					dernier = matcher.group();  // Dernier chiffre
				}


				if (premier != null && dernier != null) {
					int valeur = Integer.parseInt(premier + dernier);
					sommeTotale += valeur;
					System.out.println("Premiers chiffres extraits : " + premier + ", " + dernier);

				}
			}
		} catch (Exception e) {
			System.err.println("Erreur : " + e.getMessage());
		}

		return sommeTotale;

	}
}
