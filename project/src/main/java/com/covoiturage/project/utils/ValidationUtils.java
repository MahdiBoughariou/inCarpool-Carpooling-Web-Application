package com.covoiturage.project.utils;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    /*------------Tous les fonctions------------*/

    /*Fonction qui vérifie le format de l'email*/
    public static boolean verifEmail(String email) {
        // Expression régulière pour valider le format de l'adresse e-mail
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Création d'un objet Pattern avec l'expression régulière
        Pattern pattern = Pattern.compile(regex);

        // Création d'un objet Matcher pour faire correspondre le motif avec l'adresse e-mail
        Matcher matcher = pattern.matcher(email);

        // Vérification si l'adresse e-mail correspond au format spécifié
        return matcher.matches();
    }

    /*Fonction qui vérifie la longeur du cin*/
    public static boolean verifCin(String cin) {
        // Vérifie si la longueur du CIN est égale à 8
        return cin.length() == 8;
    }

    /*Fonction qui vérifie le format du téléphone*/
    public static boolean verifPhoneNumber(String phoneNumber) {
        // Expression régulière pour valider le format du numéro de téléphone
        String regex = "^\\+\\d{11}$"; // Le numéro doit commencer par '+' et comporter exactement 12 chiffres

        // Création d'un objet Pattern avec l'expression régulière
        Pattern pattern = Pattern.compile(regex);

        // Création d'un objet Matcher pour faire correspondre le motif avec le numéro de téléphone
        Matcher matcher = pattern.matcher(phoneNumber);

        // Vérification si le numéro de téléphone correspond au format spécifié
        return matcher.matches();
    }

    /*Fonction qui vérifie si tous les champs sont remplis formulaire 1*/
    public static boolean checkNullValuesR1(String firstName, String lastName, String cin, String email, LocalDate birthDate, String phone) {
        // Vérifie si l'une des valeurs est nulle
        if (firstName == "" || lastName == "" || cin == "" || email == "" || birthDate == null || phone == "") {
            return false;
        }
        return true;
    }

    /*Fonction qui vérifie si les deux mots de passes entrés sont les même ou pas*/
    public static boolean verifSamePassword(String password, String password2) {
        return password.equals(password2);
    }

    /*Fonction qui vérifie si tous les champs sont remplis formulaire 2*/
    public static boolean checkNullValuesR2(String username, String password, String password2) {
        // Vérifie si l'une des valeurs est nulle
        if (username == "" || password == "" || password2 == "") {
            return false;
        }
        return true;
    }
    /*Fonction qui vérifie si tous les champs sont remplis formulaire 3 edit profile*/
    public static boolean checkNullValuesR3(String firstName, String lastName, String cin, String email, LocalDate birthDate, String phone, String username){
        if (firstName == "" || lastName == "" || cin == "" || email == "" || birthDate == null || phone == "" || username == "") {
            return false;
        }
        return true;
    }
    /*------------------------------------------------------------------------------------------------------------------*/

}
