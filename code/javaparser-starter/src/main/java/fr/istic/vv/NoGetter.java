package fr.istic.vv;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorWithDefaults;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


//import java.util.ArrayList;
import java.util.HashMap;

//faire une liste avec tous les attributs, transformer le nom des méthodes (genre getMarque -> marque) et comparer avec les attributs pour savoir si il a un get
//faire une hashmap avec clé = nom de l'attribut et contenu = chemain+nomClasse

public class NoGetter extends VoidVisitorWithDefaults<Void> {
    private HashMap<String, String> map;
    private String chemin;

    public NoGetter() {
        this.map = new HashMap<String, String>();
    }

    public HashMap<String, String> getMap (){
        return this.map;
    }

    @Override
    public void visit(CompilationUnit unit, Void arg) {
        for (TypeDeclaration<?> type : unit.getTypes()) {
            type.accept(this, null);
        }
    }

    public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {

        System.out.println(declaration.getFullyQualifiedName().orElse("[Anonymous]"));
        chemin = declaration.getFullyQualifiedName().orElse("[Anonymous]");
        for (FieldDeclaration field : declaration.getFields()) {
            field.accept(this, arg);
        }

        for (MethodDeclaration method : declaration.getMethods()) {
            method.accept(this, arg);
        }
        // Printing nested types in the top level
        for (BodyDeclaration<?> member : declaration.getMembers()) {
            if (member instanceof TypeDeclaration)
                member.accept(this, arg);
        }
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(FieldDeclaration declaration, Void arg) {
        String ligne = declaration.toString();
        String[] mots = ligne.split(" ");
        String dernierMot = mots[mots.length - 1];
        dernierMot = dernierMot.substring(0, dernierMot.length()-1);
        System.out.println(dernierMot);
        map.put(dernierMot, chemin);
    }

    @Override
    public void visit(MethodDeclaration declaration, Void arg) {
        if (!declaration.isPublic())
            return;
        
        String ligne = declaration.getDeclarationAsString(false, false);
        String[] mots = ligne.split(" ");
        String dernierMot = mots[mots.length - 1];
        dernierMot = dernierMot.substring(0, dernierMot.length()-1);
        dernierMot = dernierMot.substring(0, dernierMot.length()-1);
        if (dernierMot.startsWith("get")) {
            String result = dernierMot.substring(3);
            result = result.substring(0, 1).toLowerCase() + result.substring(1);
            System.out.println(result);
            if (map.containsKey(result)) {
                map.remove(result);
                System.out.println("Clé '" + result + "' supprimée");
            } else {
                System.out.println("Clé '" + result + "' non trouvée");
            }
        }
        System.out.println("oui");
        for (String key : map.keySet()) {
            System.out.println("Clé : " + key + " -> Valeur : " + map.get(key));
        }
    }

    public void ecrireHashMapDansCsv(HashMap<String, String> map, String cheminFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (String cle : map.keySet()) {
                String ligne = cle + "," + map.get(cle);
                writer.write(ligne);
                writer.newLine();
            }
            System.out.println("C'est bon");
        } catch (IOException e) {
            System.out.println("Erreur" + e.getMessage());
        }
    }
}
